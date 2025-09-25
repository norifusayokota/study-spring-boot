package jp.co.study.sample.motocatalog.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.study.sample.motocatalog.model.Brand;


@Mapper
public interface BrandMapper {

    /**
     * ブランド情報を全件検索する
     * 
     * @return ブランド情報リスト
     */
    public List<Brand> selectAll();
}
