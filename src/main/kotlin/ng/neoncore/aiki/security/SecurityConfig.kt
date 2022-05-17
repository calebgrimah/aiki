package ng.neoncore.aiki.security

import ng.neoncore.aiki.service.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsServiceImpl: UserDetailsServiceImpl

    @Autowired
    lateinit var exceptionHandler : AuthEntryPoint

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(BCryptPasswordEncoder())
    }

    @Autowired
    lateinit var authenticationFilter: AuthenticationFilter

    @Bean
    fun getAuthenticationManager(): AuthenticationManager {
        return authenticationManager()
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(
                    HttpMethod.POST, "/login", "/signup"
                ).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(exceptionHandler).and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}