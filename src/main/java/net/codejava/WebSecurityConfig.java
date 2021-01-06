package net.codejava;

import net.codejava.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());

		
		return authProvider;
	}
*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.authenticationProvider(authenticationProvider());
		 */
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())

		.dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users u where u.username=?")
				.authoritiesByUsernameQuery("select\n" +
						"username,\n" +
						"ro.role_description\n" +
						"from roles ro\n" +
						"join users_roles ur on ur.role_id = ro.role_id\n" +
						"join users u on u.user_id = ur.user_id\n" +
						"where u.username=?")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasAnyAuthority("Host","Guest")
			.antMatchers("/new").hasAuthority("Host")
			.antMatchers("/edit/**").hasAuthority("Host")
			.antMatchers("/delete/**").hasAuthority("Host")
			.anyRequest().authenticated()
				.and()
				.formLogin()
				.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403");

/*
			.and()
			.formLogin()
				.permitAll()
				.loginPage("/login")

				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/login_success")
				.failureUrl("/login_error")
				.successForwardUrl("/login_success_handler")
				.failureForwardUrl("/login_failure_handler")


			.and()

			.logout()
				.permitAll()
				.logoutUrl("logout")

				.logoutSuccessUrl("/logout_success")

			.and()


			.exceptionHandling().accessDeniedPage("/403");


*/
	}
}
