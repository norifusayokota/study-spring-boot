package jp.co.study.sample.motocatalog.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.study.sample.motocatalog.mappers.UserMapper;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userMapper.selectByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }

        return user;
    }
}
