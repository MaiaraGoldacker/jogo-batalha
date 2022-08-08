package com.jogo.controller;

import static com.github.jenspiegsa.wiremockextension.ManagedWireMockServer.with;
import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jogo.request.EscolhaRequest;
import com.jogo.request.UsuarioRequest;
import com.jogo.response.SearchOmdbApiSimple.SearchOmdbApiFull;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.jenspiegsa.wiremockextension.Managed;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.jenspiegsa.wiremockextension.Managed;
import static com.github.jenspiegsa.wiremockextension.ManagedWireMockServer.with;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class RoundControllerIT {

	@Container
	private static final com.jogo.config.PostgresContainer POSTGRES_CONTAINER = com.jogo.config.PostgresContainer.getInstance();

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper mapper;
    
    
	@Managed
	WireMockServer wireMockServer = with(wireMockConfig().port(9876));

	@BeforeEach
	void setup() throws JsonProcessingException {		
		wireMockServer.stubFor(WireMock.put("/?apikey=8fa53dcb").willReturn(
				okJson(mapper.writeValueAsString(SearchOmdbApiFull.builder().build()))));
	}	
    
    @Test	 
    void pegaFilmesDoRoundComSucessoTest() throws Exception {
	   
    	var usuario = UsuarioRequest.builder().usuario("john").senha("john").build();
		 
    	MvcResult result = mockMvc.perform(post("/login")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(mapper.writeValueAsString(usuario))).andReturn();

    	var token = result.getResponse().getContentAsString();
	
    	MockHttpServletRequestBuilder getResult = get("/rounds").header("Authorization", "Bearer ".concat(token)).header("usuario", "john");
 
		 mockMvc.perform(getResult).andExpect(MockMvcResultMatchers.status().isOk())
		 .andExpect(jsonPath("$").isNotEmpty());
    }
    
    
    @Test	 
    void validaEscolhaFilmeComSucessoTest() throws Exception {
	   
    	var usuario = UsuarioRequest.builder().usuario("katy").senha("katy").build();
		 
    	MvcResult result = mockMvc.perform(post("/login")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(mapper.writeValueAsString(usuario))).andReturn();

    	var token = result.getResponse().getContentAsString();
	
    	MvcResult postResult = mockMvc.perform(post("/rounds")
    			.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(EscolhaRequest.builder().opcaoId(59L).build()))
    			.header("Authorization", "Bearer ".concat(token)).header("usuario", "katy")).andReturn();
 
    	
    	Assertions.assertEquals(postResult.getResponse().getContentAsString(), "Resposta certa!");
    }
}
