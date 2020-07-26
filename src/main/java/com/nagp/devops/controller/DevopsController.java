package com.nagp.devops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.devops.service.DevopsService;

@RestController
@RequestMapping(value = "/devops/v1")
public class DevopsController {
	
	@Autowired
	DevopsService devopsService;
	
	@GetMapping(value = "/version")
	public ResponseEntity<String> getDevopsVersion() {
		devopsService.getDevopsVersion();
		return new ResponseEntity<String>(devopsService.getDevopsVersion(), HttpStatus.OK);
	}
	
}
