package jp.co.study.sample.motocatalog.mappers;

import org.apache.ibatis.annotations.Mapper;

import jp.co.study.sample.motocatalog.model.User;

@Mapper
public interface UserMapper {
    /**
     * ユーザー名に従ったユーザー情報の取得
     * 
     * @param username ユーザー名
     * @return ユーザー情報
     */
    public User selectByUsername(String username);
}
