package com.docker.DockerDemo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://postgres.cibubh0xivie.us-east-1.rds.amazonaws.com:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}
}