package com.revature.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	@Profile("dev") // this correlates to spring.profiles.active
	@Bean
	public String devDatabaseConnection() {
		System.out.println("DB connection for DEV - H2");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for DEV - H2";
	}
	
	@Profile("test") // this correlates to spring.profiles.active
	@Bean
	public String testDatabaseConnection() {
		System.out.println("DB connection for TEST - low cost RDS instance");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for TEST - low cost RDS instance";
	}
	
	@Profile("prod") // this correlates to spring.profiles.active
	@Bean
	public String prodDatabaseConnection() {
		System.out.println("DB connection for DEV - high performance RDS instance");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for DEV - high performance RDS instance";
	}
	
}
