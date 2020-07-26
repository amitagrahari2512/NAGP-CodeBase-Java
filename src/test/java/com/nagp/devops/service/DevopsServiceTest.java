package com.nagp.devops.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DevopsServiceTest {
	
	@Autowired
	DevopsService devopsService;
	
	@Test
	@DisplayName("Devops Service get devops version method positive test case.")
	public void getDevopsVersion_PositiveTest() {
		assertEquals("1", devopsService.getDevopsVersion());
	}
}
