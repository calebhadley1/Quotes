package com.project.quotes.security;

import com.project.quotes.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public WebSecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userService = userService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and().csrf().disable();
//                .authorizeRequests()
//                .antMatchers("/api/register/**", "/login*",
//                    "/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin().loginPage("/index.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html",true)
//                .failureUrl("/index.html?error=true");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        super.configure(auth);
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userService);
//        return provider;
//    }


}
