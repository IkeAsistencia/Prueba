package com.docker.DockerDemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docker.DockerDemo.entities.Greeting;

@RestController 
public class HelloWorldController {
 
    @GetMapping
    public String saludos(@RequestParam(required = false, defaultValue = "") String nombre) {
        return "".equals(nombre) ? "Hola Mundo!" : "Hola " + nombre;
    }
}