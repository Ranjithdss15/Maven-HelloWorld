package com.sowisetech.simple.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloWorldController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity getEcv() {
		return ResponseEntity.ok().body("Hello World");
	}
}
