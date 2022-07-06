package com.docker.DockerDemo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.docker.DockerDemo.entities.Language;

@Repository
public class LanguageDaoImpl implements LanguageDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageDaoImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Language> getAllLanguages() {
		
		LOGGER.info("Find All Languages Called !!");

		String sql = "select * from language";
		
		List<Language> languages = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Language>(Language.class));
		System.out.println(languages);
		return languages;
	}

}
