//package com.example.labtooljava;
//
//import com.example.labtooljava.Login.LoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
////@ComponentScan(basePackages = { "com.example.labtooljava" })
////@EnableWebSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public ApplicationSecurity applicationSecurity() {
//        return new ApplicationSecurity();
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/users", "/login").permitAll().anyRequest()
//                .authenticated()
//                .anyRequest().hasAuthority("READ_PRIVILEGE")
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/")
//                .defaultSuccessUrl("/")
//                .failureUrl("/")
//                .permitAll()
//                .and()
//                .cors().disable();
//
//
//        //http.cors().disable();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        //or any domain that you want to restrict to
//        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
//        //Add the method support as you like
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Order(SecurityProperties.IGNORED_ORDER)
//    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .anyRequest().authenticated();
//            http
//                    .formLogin()
//                    .loginPage("/")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll().logoutSuccessUrl("/");
//
//
//        }
//    }
//}