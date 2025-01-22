package com.example.enterparkticket.apis.enduser.security.config

import com.example.enterparkticket.apis.enduser.security.JwtTokenFilter
import com.example.enterparkticket.apis.enduser.security.handler.OAuth2AuthenticationSuccessHandler
import com.example.enterparkticket.apis.enduser.security.service.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val oAuth2UserService: CustomOAuth2UserService,
    private val jwtTokenFilter: JwtTokenFilter,
    private val successHandler: OAuth2AuthenticationSuccessHandler,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers("/oauth2/**", "/actuator/**").permitAll()
                it.anyRequest().authenticated()
            }
            .oauth2Login { loginCustomizer ->
                loginCustomizer.userInfoEndpoint {
                    it.userService(oAuth2UserService)
                }
                loginCustomizer.successHandler(successHandler)
            }
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}
