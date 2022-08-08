package com.jogo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jogo.config.PostgresContainer;
import com.jogo.request.UsuarioRequest;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class RankingControllerIT {
	
	@Container
	private static final PostgresContainer POSTGRES_CONTAINER = PostgresContainer.getInstance();

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper mapper;

	@Test	 
	void buscaRankingComSucessoTest() throws Exception {
		   
		var usuario = UsuarioRequest.builder().usuario("admin").senha("admin").build();
			 
		MvcResult result = mockMvc.perform(post("/login")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(mapper.writeValueAsString(usuario))).andReturn();

	    var token = result.getResponse().getContentAsString();
		
	    mockMvc.perform(get("/ranking").header("Authorization", "Bearer ".concat(token)))
	    	.andExpect(status().isOk());
	}
}
