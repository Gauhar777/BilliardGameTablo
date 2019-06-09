package kz.ivc.games.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/","/**/img","/**/avatar","/Competition/**/showGames","/helloJs","/admission","/games","/main2","/chooseList",
                            "/fonts/**","/javascript/**","/css/**","/js/**", "/img/**", "/upload/**", "/logo-vtornik.png","/images/**","/login","/error","/logout","/listGamer").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/loginpage")
                    .failureForwardUrl("/error")
                    .successForwardUrl("/")
                    .defaultSuccessUrl("/chooseList")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/");


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
            }

}