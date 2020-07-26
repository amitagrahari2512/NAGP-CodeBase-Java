package com.nagp.devops.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nagp.devops.service.DevopsService;

@SpringBootTest
@AutoConfigureMockMvc
public class DevopsControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DevopsService devopsService;
	
	@Test
	@DisplayName("Devops Controller get devops version method positive test case.")
	public void getDevopsVersion_PositiveTest() throws Exception {
		Mockito.doReturn("1").when(devopsService).getDevopsVersion();
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/devops/version").contentType(MediaType.TEXT_PLAIN).content("1"))
		.andExpect(status().isOk());
	}
}
