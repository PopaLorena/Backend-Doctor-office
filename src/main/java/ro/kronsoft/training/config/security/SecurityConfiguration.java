package ro.kronsoft.training.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.kronsoft.training.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");

		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/**/v2/api-docs", "/**/swagger-resources/**", "/**/swagger-ui.html", "/**/webjars/**")
//				.permitAll();
//		http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
//		http.authorizeRequests().antMatchers("/user/create").permitAll();
//		http.authorizeRequests().antMatchers("/doctor/create").permitAll();
//		//http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/**/**/delete").hasAuthority("ADMIN");
//		http.authorizeRequests().anyRequest().authenticated();
//		
//	//	http.httpBasic();// activam autentificarea basic
//		http.formLogin();
//		http.logout().deleteCookies("JSESSIONIN").invalidateHttpSession(true);
//		
//		http.csrf().disable(); // obligatoriu
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		    .antMatchers("/**/v2/api-docs",  "/**/swagger-resources/**", "/**/swagger-ui.html", "/**/webjars/**").permitAll()
//    		//.antMatchers(HttpMethod.GET).permitAll()
//    		//.antMatchers("/user/create").permitAll()
//    		.antMatchers("/**").permitAll();
//		http.httpBasic();
//		http.formLogin().disable();
//		http.csrf().disable();
//		http.logout()
//			.deleteCookies("JSESSIONID")
//			.invalidateHttpSession(true);
		
		http
		.httpBasic()
		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.headers()
				.frameOptions()
				.sameOrigin()
		.and()
			.authorizeRequests()
				.antMatchers("/**/v2/api-docs",  "/**/swagger-resources/**", "/**/swagger-ui.html", "/**/webjars/**").permitAll()
				.antMatchers("/**").authenticated()
		.and()
			.formLogin()
				.disable()
			.csrf()
				.disable()
			.cors();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
