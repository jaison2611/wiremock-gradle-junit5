package com.learn.wiremock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Movie {

   public Integer movie_id;
   public String name;
   public String cast;
   public Integer year;
   public LocalDate release_date;



}
