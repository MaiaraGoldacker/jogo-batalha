package com.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogo.request.UsuarioRequest;
import com.jogo.service.PartidaService;


@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class PartidaControllerIT {
	
	@Container
	private static final com.jogo.config.PostgresContainer POSTGRES_CONTAINER = com.jogo.config.PostgresContainer.getInstance();

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private PartidaService partidaService;
	
	
    @Test	 
    void iniciaPartidaComSucessoTest() throws Exception {
	   
    	var usuario = UsuarioRequest.builder().usuario("admin").senha("admin").build();
		 
    	MvcResult result = mockMvc.perform(post("/login")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(mapper.writeValueAsString(usuario))).andReturn();

    	var token = result.getResponse().getContentAsString();
	
    	mockMvc.perform(post("/partidas/iniciar").header("Authorization", "Bearer ".concat(token)).header("usuario", "admin"))
    	.andExpect(status().isCreated());
    	
    	var partidaAtual = partidaService.pegaPartidaAtual("admin");
    
    	Assertions.assertEquals(partidaAtual.getDataInicioPartida().getMonth(), LocalDateTime.now().getMonth());
    	Assertions.assertEquals(partidaAtual.getDataInicioPartida().getYear(), LocalDateTime.now().getYear());
    	Assertions.assertEquals(partidaAtual.getDataInicioPartida().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
    }

}
