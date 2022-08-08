package com.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogo.request.UsuarioRequest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class UsuarioControllerIT {

	@Container
	private static final com.jogo.config.PostgresContainer POSTGRES_CONTAINER = com.jogo.config.PostgresContainer.getInstance();

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper mapper;
	
	 @Test	 
	 void criaUsuarioComSucessoTest() throws Exception {	
		 var usuario = UsuarioRequest.builder().usuario("ana").senha("ana").build();
		
		 MockHttpServletRequestBuilder result = post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                   .content(mapper.writeValueAsString(usuario));
		 
		 mockMvc.perform(result).andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(jsonPath("usuario").value("ana"));		
	 }
}
