package com.docker.DockerDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docker.DockerDemo.entities.Greeting;

@RestController
public class HelloController {

	@RequestMapping(path = "/greeting", method = RequestMethod.GET)
	public Greeting sayHello(@RequestParam String name) {
		String message = "Welcome to Amazon Its time we change " + name;
		Greeting dto = new Greeting();
		dto.setMessage(message);
		return dto;
	}
}
