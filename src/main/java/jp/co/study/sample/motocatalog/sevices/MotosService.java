package jp.co.study.sample.motocatalog.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.study.sample.motocatalog.mappers.BrandMapper;
import jp.co.study.sample.motocatalog.mappers.MotorcycleMapper;
import jp.co.study.sample.motocatalog.model.Brand;
import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchForm;

@Service
public class MotosService {

    /*
     * SpringのDIコンテナ（アプリ起動時にSpringフレームワークが特定のオブジェクトをインスタンス化しており、そのインスタンスを保管してある場所）から、インスタンスを注入するための指定
     * @Component、@Controller、@Serviceなどのアノテーションがついたクラスはコードのなかでnew()でインスタンス化することはなく、DIの仕組みによって呼び出し元クラスへ注入される
     */
    @Autowired
    MotorcycleMapper motorcycleMapper;

    /*
     * @Autowiredのアノテーションはまとめてつけることはできないので注意
     * 下記のようにそれぞれにつける
     */
    @Autowired
    BrandMapper brandMapper;

    public List<Motorcycle> getMotorcycleList(SearchForm condition) {
        return motorcycleMapper.selectByCondition(condition);
    }

    public Motorcycle getMotorcycle(int motorcycleNo) {
        return motorcycleMapper.selectByMotorcycleNo(motorcycleNo);
    }

    public List<Brand> getBrand() {
        return brandMapper.selectAll();
    }

    public int save(Motorcycle motorcycle) {
        return motorcycleMapper.update(motorcycle);
    }
}
