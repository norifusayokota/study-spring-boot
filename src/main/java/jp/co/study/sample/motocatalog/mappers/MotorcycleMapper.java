package jp.co.study.sample.motocatalog.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import jp.co.study.sample.motocatalog.forms.SearchForm;
import jp.co.study.sample.motocatalog.model.Motorcycle;

/* 
MyBatisを用いてDBと接続する
→MyBatisは、Javaアプリケーションでリレーショナルデータベースを操作するための「データマッパーフレームワーク」で、SQL文を外部のXMLファイルやアノテーションに記述することで、Javaオブジェクトとデータベースとのやり取りを簡潔にし、永続化（データの保存）処理を効率化する
→JDBCを直接扱う場合に比べてデータベース操作のコーディング量を大幅に削減できるのが特徴で、カスタムSQLやストアドプロシージャの実行、動的なSQL生成にも柔軟に対応できる
*/
@Mapper
public interface MotorcycleMapper {

    /**
     * バイク情報を検索条件に従って検索する
     * 
     * @param condition 検索条件
     * @return バイク情報リスト
     */
    public List<Motorcycle> selectByCondition(SearchForm condition);

    /**
     * バイク情報をバイク番号（主キー）に従って検索する
     * 
     * @param motorcycleNo バイク番号
     * @return バイク情報
     */
    public Motorcycle selectByMotorcycleNo(int motorcycleNo);

    /**
     * 新しいバイク情報のバイク番号（主キー）を生成する
     * 
     * @return バイク番号
     */
    public Integer selectNewMotorcycleNo();

    /**
     * バイク情報を登録する
     * 
     * @param motorcycle バイク情報
     * @return 更新件数
     */
    public int insert(Motorcycle motorcycle);

    /**
     * バイク情報を更新する
     * 
     * @param motorcycle バイク情報
     * @return 更新件数
     */
    @Update("UPDATE m_motorcycle SET motorcycle_name = #{motorcycleName}, seat_height = #{seatHeight}, cylinders = #{cylinders}, cooling = #{cooling}, price = #{price}, comment = #{comment}, brand_id = #{brand.brandId}, version = #{version} + 1, insert_date = #{insertDate}, update_date = #{updateDate} WHERE motorcycle_no = #{motorcycleNo} AND version = #{version}")
    public int update(Motorcycle motorcycle);

    /**
     * バイク情報を削除する
     * 
     * @param motorcycle バイク情報
     * @return 削除件数
     */
    @Delete("DELETE FROM m_motorcycle WHERE motorcycle_no = #{motorcycleNo} AND version = #{version}")
    public int delete(Motorcycle motorcycle);
}
