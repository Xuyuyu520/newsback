package com.xyc.news.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author xuyuyu
 * @ClassName SecurityConfig
 * @Description TODO
 * 2024/4/7  11:18
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserDetailsService customUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置用户User 根据用户名 找到用户User，比对密码 获取role
		auth.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET).permitAll() // 允许所有GET请求
				.anyRequest().authenticated() // 任何服务 登陆后才可以访问
				// 尚未登陆提示
				.and().formLogin().loginPage("/login_page")
				// security提供的登陆服务
				.loginProcessingUrl("/login") // 参数 /login?username=xx&password=xx
				// 开启跨域 cors()
				.and().cors().configurationSource(corsConfigurationSource())
				.and().csrf().disable()
				// 登录拦截器 成功登录 发放token
				.addFilter(new JwtLoginFilter(authenticationManager()))
				// 其他访问拦截器 验证token是否有效 有效才放行
				.addFilter(new JwtAuthenticationFilter(authenticationManager()));


	}

	// 设置无需权限就可以访问的资源
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/login_page");
		web.ignoring().antMatchers("/upload/**");
		web.ignoring().antMatchers("/register");
		web.ignoring().antMatchers("/index.html");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/js/**");

	}

	// spring security 配置跨域访问资源
	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOriginPattern("*"); // 同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
		corsConfiguration.addAllowedHeader("*");  // header，允许哪些header
		corsConfiguration.addAllowedMethod("*");  // 允许的请求方法，GET，PSOT、GET、PUT等
		corsConfiguration.addExposedHeader("token"); // 拓展header 浏览器放过redponse的token 不然跨域登录收不到token
		corsConfiguration.setAllowCredentials(true); // 允许浏览器携带cookie
		((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**", corsConfiguration); // 配置允许跨域访问的url
		return source;
	}
}
