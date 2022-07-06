package com.docker.DockerDemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docker.DockerDemo.dao.LanguageDao;
import com.docker.DockerDemo.entities.Language;

@Service
public class LanguageServiceImpl implements LanguageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);

	@Autowired
	LanguageDao languageDao;

	@Override
	public List<Language> getAllLanguages() {
		LOGGER.info("Find All Languages Service Called !!");
		return languageDao.getAllLanguages();
	}

}
