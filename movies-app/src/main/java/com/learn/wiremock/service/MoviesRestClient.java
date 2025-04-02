package com.learn.wiremock.service;

import com.learn.wiremock.constants.MoviesAppConstant;
import com.learn.wiremock.dto.Movie;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;

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
    public Movie getMovieById(int movie_id) {
        try {
            return webClient.get().uri(MoviesAppConstant.GET_MOVIE_BY_ID, movie_id)
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
        } catch (WebClientResponseException ex) {
            throw new RuntimeException("Web client Response Exception is " + ex.getResponseBodyAsString() + ex.getStatusCode());
        } catch (Exception e) {
            throw new RuntimeException("Runtime Exception" + e);
        }
    }

        public List<Movie> getMovieByMovieName(String movie_name)
        {
            //http://localhost:8081/movieservice/v1/movieName?movie_name=The%20Departed
            String invokeApiWithQueryParam = UriComponentsBuilder.fromUriString(MoviesAppConstant.GET_MOVIE_WITH_QUERY_PARAM)
                    .queryParam("movie_name", movie_name)
                    .buildAndExpand()
                    .toUriString();
            return webClient.get().uri(invokeApiWithQueryParam)
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .collectList()
                    .block();
        }

        public List<Movie> getMovieByYear(int movie_year)
        {
            //http://localhost:8081/movieservice/v1/movieYear?year=2019
            String invokeAPI = UriComponentsBuilder.fromUriString(MoviesAppConstant.GET_MOVIE_YEAR_WITH_QUERY_PARAM)
                    .queryParam("year", movie_year)
                    .buildAndExpand()
                    .toUriString();
            return webClient.get().uri(invokeAPI)
                    .retrieve()
                    .bodyToFlux(Movie.class)
                    .collectList()
                    .block();
        }

        public Movie postMovie (Movie post_movie)
        {
            //http://localhost:8081/movieservice/v1/movie
            return webClient.post().uri(MoviesAppConstant.POST_MOVIE_V1)
                    .bodyValue(post_movie)
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();



        }
}
