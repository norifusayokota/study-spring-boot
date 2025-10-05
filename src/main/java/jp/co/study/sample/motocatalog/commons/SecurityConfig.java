package jp.co.study.sample.motocatalog.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// import lombok.extern.slf4j.Slf4j;

/*
 * @Configuration
 * →Javaクラスを 設定クラス（Configuration Class） として扱うために使用される
 * →Springコンテナに対して Bean定義を提供するクラス であることを示す
 * 
 * @Bean
 * →これを付けたメソッドは、Springコンテナが呼び出して、その戻り値をBeanとして登録する
 * →これにより、そのオブジェクトを Spring に「使う準備ができた部品」として渡しておくことができ、Springがそれを必要なときに自動で作って、必要な場所に渡してくれるようになる
 * 
 * @EnableWebSecurity
 * →Webセキュリティの設定を有効化するために使用する
 */
@Configuration
@EnableWebSecurity
// @Slf4j
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/webjars/**", "/favicon.ico", "/login", "/error").permitAll()
                .anyRequest().authenticated())
                .formLogin(form -> form.defaultSuccessUrl("/motos", true).permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        // log.info("PasswordEncoder: {}", encoder.encode("test"));

        return encoder;
    }
}
