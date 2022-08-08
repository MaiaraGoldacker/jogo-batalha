package com.jogo.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jogo.response.SearchOmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.OmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.SearchOmdbApiFull;

public class MockSearchOmdbApi {

	public static SearchOmdbApiFull buildSearchOmdbApiFull() {
		return SearchOmdbApiFull.builder()
				.imdbID("")
				.title("filme 1")
				.imdbRating(BigDecimal.ZERO)
				.imdbVotes("123").build();				
	}
	
	public static SearchOmdbApiSimple buildSearchOmdbApiSimple() {
		
		List<OmdbApiSimple> lista = new ArrayList<>();
		lista.add(OmdbApiSimple.builder().imdbID("111").Title("111").build());
		lista.add(OmdbApiSimple.builder().imdbID("222").Title("222").build());
		lista.add(OmdbApiSimple.builder().imdbID("333").Title("333").build());
		lista.add(OmdbApiSimple.builder().imdbID("444").Title("444").build());
		lista.add(OmdbApiSimple.builder().imdbID("555").Title("555").build());
		lista.add(OmdbApiSimple.builder().imdbID("666").Title("666").build());
		return SearchOmdbApiSimple.builder().Search(lista).build();
	}
}
