package com.jogo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jogo.response.SearchOmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.SearchOmdbApiFull;


@FeignClient(name = "OmdbApi", url = "http://www.omdbapi.com")
public interface OmdbApi {
	  
	  @RequestMapping(method = RequestMethod.GET, value = "/",
	            consumes = "application/json", produces = "application/json")
	  SearchOmdbApiSimple getListaFilmes(@RequestParam("s") String search,
			  @RequestParam("apikey") String apikey,
			  @RequestParam("type") String type);
	  
	  @RequestMapping(method = RequestMethod.GET, value = "/",
	            consumes = "application/json", produces = "application/json")
	  SearchOmdbApiFull getEspecificoFilme(@RequestParam("i") String imdbID,
			  @RequestParam("apikey") String apikey);
}
