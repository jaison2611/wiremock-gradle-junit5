package com.learn.wiremock.test;

import com.learn.wiremock.dto.Movie;
import com.learn.wiremock.service.MoviesRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoviesRestClientTest {

    MoviesRestClient moviesRestClient;
    WebClient webClient;

    @BeforeEach
    void Setup(){
        String baseUrl = "http://localhost:8081";
        System.out.println(baseUrl);

        webClient = WebClient.create(baseUrl);
        moviesRestClient  = new MoviesRestClient(webClient);
    }
    @Test
    void getAllMovies() {
        //when
        List<Movie> movieList = moviesRestClient.getAllMovies();

        //then
        System.out.println(movieList);
        assertTrue(movieList.size() > 0);
    }

    @Test
    void getMovieById(){

        //given
        int movieId = 1;

        //when
        Movie movie = moviesRestClient.getMovieById(movieId);

        //then
        System.out.println(movie);
        Assertions.assertEquals(movieId,
                movie);

    }
}
