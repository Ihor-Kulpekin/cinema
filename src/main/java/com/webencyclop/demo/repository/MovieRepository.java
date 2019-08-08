package com.webencyclop.demo.repository;


import com.webencyclop.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
