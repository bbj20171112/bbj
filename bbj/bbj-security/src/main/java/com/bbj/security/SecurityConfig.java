package com.bbj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            .antMatchers("/resources/**").permitAll() 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/security/login")
                .permitAll()
                .and()
                .logout()                                    
                    .permitAll();
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("bbj").password("bbj").roles("USER");
        //inMemoryAuthentication 从内存中获取    
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("bbj").password(new BCryptPasswordEncoder().encode("bbj")).roles("USER");  
    }
    
}
