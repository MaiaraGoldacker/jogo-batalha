package com.jogo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jogo.client.OmdbApi;
import com.jogo.response.SearchOmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.SearchOmdbApiFull;

@Component
public class RoundUtils {
	
	@Autowired
	private OmdbApi omdbApi;
	
	@Value("${jogo.config.external_access_key_omdb_api}")
	private String externalAccessKeyOmdbApi;
	
	public static List<String> lista = new ArrayList<>();

	@EventListener(ContextRefreshedEvent.class)
	public void warmup() {
		
		lista.add("man");
		lista.add("harry");
		lista.add("doctor");
		lista.add("house");
		lista.add("car");
		lista.add("dog");
		lista.add("moon");
		lista.add("take");
		lista.add("10");
		lista.add("she");
		lista.add("he");
		lista.add("her");
		lista.add("shirt");
		lista.add("police");		
	}
	
	public SearchOmdbApiSimple listaGenericaDeFilmes() throws Exception {

		var searchOmdbApiSimple = new SearchOmdbApiSimple();

		do {
			searchOmdbApiSimple = retornaListaGeralDeFilmesServicoExterno();
		} while (Objects.isNull(searchOmdbApiSimple) ||
					Objects.isNull(searchOmdbApiSimple.getSearch()) &&
						searchOmdbApiSimple.getSearch().size() == 0) ;

		return searchOmdbApiSimple;
	}
	
	public static Integer getRandomIndiceParaLista(Integer tamanhoLista) {
		Random random = new Random();
		return random.nextInt(tamanhoLista);
	}
	
	private SearchOmdbApiSimple retornaListaGeralDeFilmesServicoExterno() throws Exception {
		var indice = lista.get(getRandomIndiceParaLista(lista.size()));
		try {
			return omdbApi.getListaFilmes(indice, externalAccessKeyOmdbApi, "movie");
		} catch (Exception e) {
			throw new Exception ("Erro ao tentar conectar em API externa. Tente novamente mais tarde");
		}
	}
	
	public SearchOmdbApiFull retornaFilmeEspecificoServicoExterno(String imdbId) throws Exception {
		try {
			return omdbApi.getEspecificoFilme(imdbId, externalAccessKeyOmdbApi);
		} catch (Exception e) {
			throw new Exception ("Erro ao tentar conectar em API externa. Tente novamente mais tarde");
		}
	}

}
