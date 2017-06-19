package by.epam.rentalcars.security.config;

import by.epam.rentalcars.security.filter.AuthenticationTokenFilter;
import by.epam.rentalcars.security.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration for Spring Security
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/", "/user/register", "/auth/login").permitAll()
                .antMatchers("/car/cars", "/car/cars/**").permitAll()
                .antMatchers("/car/add", "/car/edit", "/car/delete", "/car/delete/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/order/orders", "/order/orders/**", "/order/history/**", "/order/freedates/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/order/add", "/order/edit").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/order/delete", "/order/delete/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/users").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/users/**", "/user/edit").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/user/delete", "/user/delete/**").hasAuthority("ROLE_ADMIN")
                .and()
                .addFilterBefore(new AuthenticationTokenFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
}
