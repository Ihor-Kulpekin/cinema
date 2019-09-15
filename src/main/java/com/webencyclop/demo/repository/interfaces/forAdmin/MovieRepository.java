package com.webencyclop.demo.repository.interfaces.forAdmin;


import com.webencyclop.demo.model.forAdmin.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
