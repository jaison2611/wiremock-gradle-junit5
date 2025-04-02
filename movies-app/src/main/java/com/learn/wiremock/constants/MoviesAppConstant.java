package com.learn.wiremock.constants;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public class MoviesAppConstant {

    public static final String GET_ALL_MOVIES_V1 = "/movieservice/v1/allMovies";
    public static final String GET_MOVIE_BY_ID = "/movieservice/v1/movie/{id}";
    public static final String GET_MOVIE_WITH_QUERY_PARAM= "/movieservice/v1/movieName";
    public static final String GET_MOVIE_YEAR_WITH_QUERY_PARAM = "/movieservice/v1/movieYear";
    public static final String POST_MOVIE_V1 = "/movieservice/v1/movie";

}
