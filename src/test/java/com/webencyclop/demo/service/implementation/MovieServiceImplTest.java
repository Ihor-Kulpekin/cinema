package com.webencyclop.demo.service.implementation;

import java.util.List;
import com.webencyclop.demo.model.Movie;
import com.webencyclop.demo.service.interfaces.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;




@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    private Movie expectedMovie;

    @Before
    public void setUp() throws Exception {
        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("The King");
        expectedMovie.setUrlTrailer("dasdas");
        expectedMovie.setUrlImage("sdasdad");
        expectedMovie.setMainRoles("dasdas");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setDuration("124");
        expectedMovie.setGenre("horror");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setDiscription("Cool cool");
    }

    @Test
    public void addMovie() {
        movieService.addMovie(expectedMovie);
        Movie resultMovie = movieService.getMovieById(1);
        assertNotNull(resultMovie);
    }


    // Need to fix errors
    @Test
    public void updateMovie() {
        Movie resultMovie = new Movie();
        resultMovie.setId(expectedMovie.getId());
        resultMovie.setNameMovie(expectedMovie.getNameMovie());
        resultMovie.setUrlTrailer(expectedMovie.getUrlTrailer());
        resultMovie.setUrlImage(expectedMovie.getUrlImage());
        resultMovie.setMainRoles(expectedMovie.getMainRoles());
        resultMovie.setGraduationYear(expectedMovie.getGraduationYear());
        resultMovie.setDuration(expectedMovie.getDuration());
        resultMovie.setGenre(expectedMovie.getGenre());
        resultMovie.setDirector(expectedMovie.getDirector());
        resultMovie.setDiscription(expectedMovie.getDiscription());
        movieService.addMovie(resultMovie);
        resultMovie.setNameMovie("bla bla");
        movieService.updateMovie(resultMovie);
        assertNotEquals(expectedMovie.getNameMovie(),resultMovie.getNameMovie());
    }

    @Test
    public void getMovieById() {
        movieService.addMovie(expectedMovie);
        List<Movie> movieList = movieService.listMovies();
        Movie movieFromList = movieList.get(0);
        Movie movieGotById = movieService.getMovieById(movieFromList.getId());
        assertNotNull(movieGotById);
    }

    @Test
    public void removeMovie() {
        assertEquals(1L,movieService.listMovies().size());
        movieService.removeMovie(8);
        assertEquals(0L,movieService.listMovies().size());
    }

    @Test
    public void listMovies() {
        assertEquals(1L,movieService.listMovies().size());
    }
}