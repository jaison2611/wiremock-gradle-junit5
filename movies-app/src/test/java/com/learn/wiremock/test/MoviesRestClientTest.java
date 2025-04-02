package com.learn.wiremock.test;

import com.learn.wiremock.dto.Movie;
import com.learn.wiremock.service.MoviesRestClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Slf4j
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
        Assertions.assertEquals("Batman Begins",
                movie.getName());

    }

    @Test
    void getMovieByMovieName()
    {
        //given
        String movie_name = "The Departed";

        //when
        List <Movie> movieList = moviesRestClient.getMovieByMovieName(movie_name);

        //then
        assertEquals(movie_name, movieList.get(0).getName());
    }

    @Test
    void getMovieByYear()
    {
        //given
        int year = 2019;

        //when
        List <Movie> movieList = moviesRestClient.getMovieByYear(year);

        //then
        assertEquals(year, movieList.get(0).getYear());
        log.info("Expected Year "+year+" Actual Year "+movieList.get(0).getYear());
        System.out.println("Expected Year "+year+" Actual Year "+movieList.get(0).getYear());
    }

    @Test
    void postMovie()
    {
        //given
        Movie post_movie = new Movie(null, "Batman Test", "Steve Grep",2025, LocalDate.of(2025,11,15));

        //when
        Movie addMovie = moviesRestClient.postMovie(post_movie);

        //then
        System.out.println(addMovie.getMovie_id());
        assertTrue(addMovie.getMovie_id()!= null);
    }
}
