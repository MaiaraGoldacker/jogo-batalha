package com.jogo.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchOmdbApiSimple {
	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<OmdbApiSimple> Search;
	
	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class OmdbApiSimple {

		@JsonProperty("imdbID")
		private String imdbID;
		
		@JsonProperty("Title")
		private String title;
	}
	
	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SearchOmdbApiFull {

		private String imdbID;

		@JsonProperty("Title")
		private String title;

		private BigDecimal imdbRating;
		
		private String imdbVotes;
	}
	
}



