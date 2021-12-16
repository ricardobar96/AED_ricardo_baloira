package es.iespuertodelacruz.ricardo.matriculasREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication

public class MatriculasRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculasRestApplication.class, args);
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		public void configure(WebSecurity webSecurity) throws Exception
		{
			webSecurity
			.ignoring()
			.antMatchers("/**");
			}
		}
	}
