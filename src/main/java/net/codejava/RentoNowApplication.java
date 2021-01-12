package net.codejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*@EnableJpaRepositories(basePackages = {"pl.hypeapp.episodie.dataproviders"})
@EntityScan(basePackages = {"pl.hypeapp.core.entity"})
@ComponentScan(basePackages = "pl.hypeapp.episodie.configuration")
@Configuration
@EnableAutoConfiguration*/
@SpringBootApplication
public class RentoNowApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RentoNowApplication.class, args);
	}

}
