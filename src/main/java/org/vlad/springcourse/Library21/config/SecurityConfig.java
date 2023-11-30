package org.vlad.springcourse.Library21.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // настраиваем кастомную форму логина
        http.formLogin((formLogin) -> formLogin
                // указываем какие данные нужно использовать для аутентификации
                .usernameParameter("username").passwordParameter("password")
                // указываем какую форму логина нужно использовать
                .loginPage("/auth/login")
                // указываем адрес, на который придут данные c формы
                .loginProcessingUrl("/process_login")
                // указываем куда отправлять пользователя после успешной аутентификации
                .defaultSuccessUrl("/main", true)
                // указываем куда отправлять пользователя после неудачной аутентификации
                .failureUrl("/auth/login?error"));

        // настраиваем правила авторизации: важен их порядок, потому что они применяются сверху-вниз
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                // смотрим откуда пришел запрос и указываем правило доступности (permit all)
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                // на все остальные запросы пускаем только аутентифицированнх юзеров
                .anyRequest().authenticated());

        // отключаем защиту от CSRF (без этого работать не будет, но вообще так делать не надо)
        http.csrf().disable();

        return http.build();
    }

    // Указываем алгоритм шифрования пароля (в данном случае без шифрования вообще, и опять же так делать не надо)
    @Bean
    public PasswordEncoder getPasswordREncode() {
        return NoOpPasswordEncoder.getInstance();
    }
}
