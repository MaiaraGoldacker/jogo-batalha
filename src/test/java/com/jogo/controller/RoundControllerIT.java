package com.jogo.controller;

import static com.github.jenspiegsa.wiremockextension.ManagedWireMockServer.with;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import com.github.jenspiegsa.wiremockextension.Managed;
import com.jogo.config.PostgresContainer;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class RoundControllerIT {

	@Container
	private static final PostgresContainer POSTGRES_CONTAINER = PostgresContainer.getInstance();

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper mapper;
    
	@Managed
	WireMockServer wireMockServer = with(wireMockConfig().port(9876));
	
	private static final String  EXTERNAL_ACCESS_KEY_OMDB_API = "8fa53dcb";

	@BeforeEach
	void setup() throws JsonProcessingException {		
		wireMockServer.stubFor(WireMock.get("/?apikey=".concat(EXTERNAL_ACCESS_KEY_OMDB_API)).willReturn(
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
