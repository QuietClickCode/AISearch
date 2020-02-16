package com.zjj.aisearch.repository;


import com.zjj.aisearch.pojo.dto.MovieDTO;

public interface IMovieRepository {

    boolean save(MovieDTO movieDTO);

}
