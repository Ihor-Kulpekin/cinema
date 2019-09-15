package com.webencyclop.demo.controller.implementation;

import java.util.ArrayList;
import java.util.List;

import com.webencyclop.demo.controller.implementation.forUser.IndexController;
import com.webencyclop.demo.model.forAdmin.Movie;
import com.webencyclop.demo.service.interfaces.forAdmin.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexControllerTest {

    @InjectMocks
    private IndexController indexController;

    @Mock
    private View view;

    @Mock
    private MovieService movieService;

    private MockMvc mockMvc;

    private Movie expectedMovie;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(indexController)
                .setSingleView(view)
                .build();
    }

    @Test
    public void showIndexPageTest() throws Exception {
        String url = "/";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(expectedMovie);
        when(movieService.listMovies()).thenReturn(movieList);
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("movie",movieList.get(0)))
                .andExpect(view().name("index"))
                .andReturn();
        int resultStatusCode = mvcResult.getResponse().getStatus();
        int expectedStatusCode = 200;
        assertEquals(expectedStatusCode,resultStatusCode);
    }
}