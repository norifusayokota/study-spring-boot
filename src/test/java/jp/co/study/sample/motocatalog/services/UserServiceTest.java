package jp.co.study.sample.motocatalog.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.study.sample.motocatalog.sevices.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;

    @DisplayName("ユーザー取得テスト")
    @Test
    void test001() {
        // UserDetails user = service.loadUserByUsername("test");
        UserDetails user = service.loadUserByUsername("testUser");
        // UserDetails user = service.loadUserByUsername("aaa");
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        assertThat(encoder.matches("test", user.getPassword())).isTrue();
    }
}
