package com.muhardin.endy.belajar.spring.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SpringSecurityConfig {

    private static final String SQL_LOGIN = "select username, " +
            "hashed_password as password, true as enabled " +
            "from s_user where username = ?";
    private static final String SQL_PERMISSION = "select u.username, r.nama as authority " +
            "from s_user u inner join s_role r on u.id_role = r.id " +
            "where u.username = ?";

    @Bean
    public UserDetailsService userDetailsService(@Autowired DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setUsersByUsernameQuery(SQL_LOGIN);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SQL_PERMISSION);
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests().antMatchers("/", "/public/**").permitAll()
                    .and().authorizeRequests(authorize -> authorize
                            .anyRequest().authenticated()
                    ).formLogin(login -> login
                        .permitAll()
                        .defaultSuccessUrl("/sessiondata/list", true));
        }
    }
}
