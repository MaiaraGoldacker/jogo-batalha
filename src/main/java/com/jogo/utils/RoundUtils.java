package com.jogo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jogo.client.OmdbApi;
import com.jogo.response.SearchOmdbApiSimple;

@Component
public class RoundUtils {
	
	@Autowired
	private OmdbApi omdbApi;
	
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
	
	public SearchOmdbApiSimple listaGenericaDeFilmes() {
		Random random = new Random();
		int numero = random.nextInt(lista.size());
		var indice = lista.get(numero);

		return omdbApi.getListaFilmes(indice, "8fa53dcb", "movie");
	}

}
