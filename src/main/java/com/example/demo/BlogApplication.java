package com.example.demo;

import com.example.demo.security.RateLimitingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<RateLimitingFilter> rateLimiterFilter(){
		FilterRegistrationBean<RateLimitingFilter> registeration = new FilterRegistrationBean<>();
		registeration.setFilter(new RateLimitingFilter());
		registeration.addUrlPatterns("/*");
		return registeration;
	}

}
