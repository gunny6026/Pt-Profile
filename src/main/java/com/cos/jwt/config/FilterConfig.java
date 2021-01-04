package com.cos.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.jwt.config.filter.CorsFilter;
import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.jwt.config.jwt.JwtAuthorizationFilter;
import com.cos.jwt.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class FilterConfig {
	
	private final UserRepository userRepository;
	
	
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		System.out.println("CORS 필터 등록");
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter());
		bean.addUrlPatterns("/*");
		bean.setOrder(0);// 낮은 번호가 필터중에서 가장 먼저 실행됨.
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilter(){
		System.out.println("JwtAuthenticationFilter 필터등록");
		FilterRegistrationBean<JwtAuthenticationFilter> bean 
		= new FilterRegistrationBean<>(new JwtAuthenticationFilter(userRepository));
		bean.addUrlPatterns("/user/login");
		bean.setOrder(1);// 낮은 번호가 필터중에서 가장 먼저 실행됨.
		return bean;
	}
	
	
	@Bean
	public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthoriztionFilter(){
		System.out.println("JwtAuthorizationFilter 필터 등록");
		FilterRegistrationBean<JwtAuthorizationFilter> bean = 
				new FilterRegistrationBean<>(new JwtAuthorizationFilter(userRepository));
		bean.addUrlPatterns("/board/*");
		bean.addUrlPatterns("/pt/*");
		bean.addUrlPatterns("/st/*");
		bean.addUrlPatterns("/user/remove");
		bean.addUrlPatterns("/user/modify");
		bean.addUrlPatterns("/order");
		bean.addUrlPatterns("/wish/*");
		bean.addUrlPatterns("/review/*");
		bean.addUrlPatterns("/admin/userList");
		bean.addUrlPatterns("/admin/userAuth/*");
		bean.setOrder(2);// 낮은 번호가 필터중에서 가장 먼저 실행됨.
		return bean;
	}

}
