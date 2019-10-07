package com.webencyclop.demo.controller.implementation.admin;

import com.webencyclop.demo.controller.implementation.admin.movie.MovieController;
import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Mock
    private View view;

    private Movie expectedMovie;


    private MockMvc mockMvc;


    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showListMoviesTest() throws Exception {
        String url = "/admin/listMovies";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(expectedMovie);
        when(movieService.listMovies()).thenReturn(movieList);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("movie",movieList.get(0)))
                .andExpect(view().name("listMovies"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void showPageAddMovieTest() throws Exception {
        String url = "/admin/new";
        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("dsda");
        expectedMovie.setDiscription("sdasd");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setGenre("dsadas");
        expectedMovie.setDuration("Dsda");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setMainRoles("dasasd");
        expectedMovie.setUrlImage("dsadas");
        expectedMovie.setUrlTrailer("dasdasd");
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("pageAddMovie"))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void saveMovieTest() throws Exception {
        String url = "/admin/new";
        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("dsda");
        expectedMovie.setDiscription("sdasd");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setGenre("dsadas");
        expectedMovie.setDuration("Dsda");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setMainRoles("dasasd");
        expectedMovie.setUrlImage("dsadas");
        expectedMovie.setUrlTrailer("dasdasd");

        doNothing().when(movieService).addMovie(expectedMovie);
        mockMvc.perform(post(url)
        .param("id", String.valueOf(expectedMovie.getId()))
        .param("nameMovie",expectedMovie.getNameMovie())
        .param("discription",expectedMovie.getDiscription())
        .param("director",expectedMovie.getDirector())
        .param("genre",expectedMovie.getGenre())
        .param("duration",expectedMovie.getDuration())
        .param("graduationYear", String.valueOf(expectedMovie.getGraduationYear()))
        .param("mainRoles",expectedMovie.getMainRoles())
        .param("urlImage",expectedMovie.getUrlImage())
        .param("urlTrailer",expectedMovie.getUrlTrailer()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/listMovies"))
                .andExpect(model().attribute("movie",instanceOf(Movie.class)))
                .andExpect(model().attribute("movie",hasProperty("id")))
                .andExpect(model().attribute("movie",hasProperty("nameMovie")))
                .andExpect(model().attribute("movie",hasProperty("discription")))
                .andExpect(model().attribute("movie",hasProperty("director")))
                .andExpect(model().attribute("movie",hasProperty("genre")))
                .andExpect(model().attribute("movie",hasProperty("duration")))
                .andExpect(model().attribute("movie",hasProperty("graduationYear")))
                .andExpect(model().attribute("movie",hasProperty("mainRoles")))
                .andExpect(model().attribute("movie",hasProperty("urlImage")))
                .andExpect(model().attribute("movie",hasProperty("urlTrailer")));

        ArgumentCaptor<Movie> boundMovie = ArgumentCaptor.forClass(Movie.class);
        verify(movieService).addMovie(boundMovie.capture());
        assertEquals(expectedMovie.getId(),boundMovie.getValue().getId());
    }

    @Test
    public void showPageEditMovieTest() throws Exception {
        String url = "/admin/edit/1";

        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("dsda");
        expectedMovie.setDiscription("sdasd");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setGenre("dsadas");
        expectedMovie.setDuration("Dsda");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setMainRoles("dasasd");
        expectedMovie.setUrlImage("dsadas");
        expectedMovie.setUrlTrailer("dasdasd");

        when(movieService.getMovieById(expectedMovie.getId())).thenReturn(expectedMovie);

        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("editMovie"))
                .andExpect(model().attribute("movie",expectedMovie))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void editMovieTest() throws Exception {
        String url = "/admin/edit";
        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("dsda");
        expectedMovie.setDiscription("sdasd");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setGenre("dsadas");
        expectedMovie.setDuration("Dsda");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setMainRoles("dasasd");
        expectedMovie.setUrlImage("dsadas");
        expectedMovie.setUrlTrailer("dasdasd");

        MvcResult mvcResult = mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(expectedMovie.toString()))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        int expectedStatus = 200;
        assertEquals(expectedStatus,status);
    }

    @Test
    public void deleteMovieTest() throws Exception {
        String url = "/admin/delete/1";
        expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameMovie("dsda");
        expectedMovie.setDiscription("sdasd");
        expectedMovie.setDirector("dasdas");
        expectedMovie.setGenre("dsadas");
        expectedMovie.setDuration("Dsda");
        expectedMovie.setGraduationYear(1234);
        expectedMovie.setMainRoles("dasasd");
        expectedMovie.setUrlImage("dsadas");
        expectedMovie.setUrlTrailer("dasdasd");

        doNothing().when(movieService).removeMovie(expectedMovie.getId());

        mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andExpect(view().name("listMovies"));

        verify(movieService,times(1)).removeMovie(expectedMovie.getId());
    }
}