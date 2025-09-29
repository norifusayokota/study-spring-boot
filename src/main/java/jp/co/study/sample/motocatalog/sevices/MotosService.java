package jp.co.study.sample.motocatalog.sevices;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.study.sample.motocatalog.mappers.BrandMapper;
import jp.co.study.sample.motocatalog.mappers.MotorcycleMapper;
import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchForm;

@Service
public class MotosService {

    /*
     * Spring Framework における 国際化（i18n）やメッセージ管理のためのインターフェース
     * →つまり、多言語対応のメッセージを管理・取得する仕組み
     * →プレースホルダー（{0}, {1}）を使った動的メッセージの生成ができる
     * 
     * getMessageメソッドに関して
     * String getMessage(String code, Object[] args, Locale locale)
     * →args: プレースホルダーに埋め込む値
     *        →プレースホルダーがない場合: nullを使うのがベスト
     */
    @Autowired
    MessageSource messageSource;

    /*
     * SpringのDIコンテナ（アプリ起動時にSpringフレームワークが特定のオブジェクトをインスタンス化しており、そのインスタンスを保管してある場所）から
     * 、インスタンスを注入するための指定
     * 
     * @Component、@Controller、@Serviceなどのアノテーションがついたクラスはコードのなかでnew()でインスタンス化することはなく、
     * DIの仕組みによって呼び出し元クラスへ注入される
     */
    @Autowired
    MotorcycleMapper motorcycleMapper;

    /*
     * @Autowiredのアノテーションはまとめてつけることはできないので注意
     * 下記のようにそれぞれにつける
     */
    @Autowired
    BrandMapper brandMapper;

    /**
     * 検索条件に従ったバイク情報の一覧を取得する
     * 
     * @param condition 検索条件
     * @return バイクの情報の一覧
     */
    public List<Motorcycle> getMotorcycleList(SearchForm condition) {
        return motorcycleMapper.selectByCondition(condition);
    }

    /**
     * バイクの番号（プライマリキー）に従ったバイク情報を取得する
     * 
     * @param motorcycleNo バイクの番号（プライマリキー）
     * @return バイクの情報
     */
    public Motorcycle getMotorcycle(int motorcycleNo) {
        return motorcycleMapper.selectByMotorcycleNo(motorcycleNo);
    }

    /**
     * バイクのブランドの一覧を取得する
     * 
     * @return バイクのブランドの一覧
     */
    public List<Brand> getBrand() {
        return brandMapper.selectAll();
    }

    /**
     * バイク情報を更新する
     * 
     * @param motorcycle バイク情報
     * @return 更新件数
     */
    @Transactional
    public int save(Motorcycle motorcycle) {
        int updateCount = motorcycleMapper.update(motorcycle);

        // 更新できなかった場合: 別の画面で更新がされたか削除されたデータのため、楽観的排他エラーとする
        if (updateCount == 0) {
            /*
             * OptimisticLockingFailureException
             * →Spring Frameworkで定義されている例外クラス
             * →「競合が起きた」と判断して例外を投げる場合に使用する
             */
            throw new OptimisticLockingFailureException(
                    messageSource.getMessage("error.optimisticLockingFailure", null, Locale.JAPANESE));
        }

        // 2件以上の更新の場合: 想定外の挙動のため、SQL の不備の可能性が高い
        if (updateCount > 1) {
            throw new RuntimeException(
                    messageSource.getMessage("error.runtime", new String[] { "2件以上更新されました" }, Locale.JAPANESE));
        }

        return updateCount;
    }
}
