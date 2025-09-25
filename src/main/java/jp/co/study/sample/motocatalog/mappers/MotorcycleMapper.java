package jp.co.study.sample.motocatalog.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.study.sample.motocatalog.model.Motorcycle;
import jp.co.study.sample.motocatalog.model.SearchCondition;

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
    public List<Motorcycle> selectByCondition(SearchCondition condition);
}
