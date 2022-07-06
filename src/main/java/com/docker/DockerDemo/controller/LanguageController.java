package com.docker.DockerDemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.DockerDemo.entities.Language;
import com.docker.DockerDemo.service.LanguageService;

@RestController
public class LanguageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageController.class);

	@Autowired
	LanguageService languageService;

	@RequestMapping(path = "/languages", method = RequestMethod.GET)
	public ResponseEntity<List<Language>> listAllLanguages() {

		LOGGER.info("Languages Controller");

		List<Language> allLanguages = languageService.getAllLanguages();
		LOGGER.info("List Of Languages ::" + allLanguages);

		if (allLanguages.isEmpty()) {
			return new ResponseEntity<List<Language>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Language>>(allLanguages, HttpStatus.OK);

	}
}
