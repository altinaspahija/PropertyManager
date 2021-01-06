package net.codejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*@EnableJpaRepositories(basePackages = {"pl.hypeapp.episodie.dataproviders"})
@EntityScan(basePackages = {"pl.hypeapp.core.entity"})
@ComponentScan(basePackages = "pl.hypeapp.episodie.configuration")
@Configuration
@EnableAutoConfiguration*/
@SpringBootApplication
public class ProductManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagerApplication.class, args);
	}

}
