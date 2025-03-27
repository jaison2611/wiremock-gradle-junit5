package com.learn.wiremock.service;

import com.learn.wiremock.constants.MoviesAppConstant;
import com.learn.wiremock.dto.Movie;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Function;


public class MoviesRestClient {

    private final WebClient webClient;

    public MoviesRestClient(WebClient webClient)
    {
        this.webClient = webClient;
    }

    //http://localhost:8081/movieservice/v1/allMovies
    public List<Movie> getAllMovies()
    {
        return webClient.get().uri(MoviesAppConstant.GET_ALL_MOVIES_V1)
                .retrieve()
                .bodyToFlux(Movie.class)
                .collectList()
                .block();
    }

    //http://localhost:8081/movieservice/v1/movie/1
    public Movie getMovieById(int movie_id)
    {
        return webClient.get().uri(MoviesAppConstant.GET_MOVIE_BY_ID, movie_id)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
}
