package ro.kronsoft.training.config.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
@Configuration
public class CustomCorsConfigurer implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		System.out.println("work!!!!!!!!!!!!!!!!");
		registry.addMapping("/**")
//		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//		.allowedOrigins("http://localhost:4200")
//		.exposedHeaders("Authorization")
		.allowedMethods("*")
		.allowedOrigins("*");
	}
}
