package com.devdojo.guto.controllers;

import com.devdojo.guto.dtos.DirectorDto;
import com.devdojo.guto.dtos.MovieDto;
import com.devdojo.guto.entities.Director;
import com.devdojo.guto.entities.Movie;
import com.devdojo.guto.services.MovieService;
import com.devdojo.guto.utils.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @Mock
    private DateUtil dateUtil;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveMovie_shouldReturnSavedMovie() {
        DirectorDto directorDto = new DirectorDto("Director name test", "Company test");
        MovieDto movieDto = new MovieDto("Title movie", "Movie description", new Date(), directorDto);

        Movie savedMovie = new Movie();
        Director director = new Director();
        savedMovie.setId(1L);
        savedMovie.setMovieName("Title movie");
        savedMovie.setDescription("Movie description");

        savedMovie.setDate(new Date());
        director.setDirectorName("Director name test");
        director.setCompany("Company test");

        savedMovie.setDirector(director);

        when(dateUtil.formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class)))
                .thenReturn("2021-01-01T10:00:00");
        when(movieService.save(movieDto)).thenReturn(savedMovie);

        Movie result = movieController.saveMovie(movieDto);

        verify(dateUtil, times(1)).formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class));
        verify(movieService, times(1)).save(movieDto);
        assertEquals(savedMovie.getId(), result.getId());
        assertEquals(savedMovie.getMovieName(), result.getMovieName());
        assertEquals(savedMovie.getDescription(), result.getDescription());
        assertEquals(savedMovie.getDate(), result.getDate());
        assertEquals(savedMovie.getDirector().getDirectorName(), result.getDirector().getDirectorName());
        assertEquals(savedMovie.getDirector().getCompany(), result.getDirector().getCompany());
        assertEquals(savedMovie.getDirector().getId(), result.getDirector().getId());
    }

    @Test
    public void findById_shouldReturnMovieById() {
        Long id = 1L;

        Movie movie = new Movie();
        movie.setId(id);
        movie.setMovieName("Movie title");
        movie.setDescription("Movie description");

        Director director = new Director();
        director.setId(id);
        director.setCompany("Company name");
        director.setDirectorName("Director name");
        movie.setDirector(director);

        when(dateUtil.formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class)))
                .thenReturn("2022-02-02T10:00:00");
        when(movieService.findById(id)).thenReturn(movie);

        Movie result = movieController.findById(id);

        verify(dateUtil, times(1)).formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class));
        verify(movieService, times(1)).findById(id);
        assertEquals(movie.getId(), result.getId());
        assertEquals(movie.getMovieName(), result.getMovieName());
        assertEquals(movie.getDescription(), result.getDescription());
        assertEquals(movie.getDate(), result.getDate());
        assertEquals(movie.getDirector().getId(), result.getDirector().getId());
        assertEquals(movie.getDirector().getDirectorName(), result.getDirector().getDirectorName());
        assertEquals(movie.getDirector().getCompany(), result.getDirector().getCompany());
    }

    @Test
    public void deleteById_shouldDeleteMovieById() {
        Long id = 1L;

        when(dateUtil.formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class)))
                .thenReturn("2021-01-01T10:00:00");
        movieController.deleteById(id);

        verify(dateUtil, times(1)).formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class));
        verify(movieService, times(1)).delete(id);
    }

    @Test
    public void listMovies_shouldReturnAllMovies() {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setDate(new Date());
        movie1.setDescription("Description movie");
        movie1.setMovieName("Title movie");
        movie1.setDirector(new Director(1L, "Director name", "Company name"));

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setDate(new Date());
        movie2.setDescription("Description movie");
        movie2.setMovieName("Title movie");
        movie2.setDirector(new Director(2L, "Director name", "Company name"));

        List<Movie> moviesList = Arrays.asList(movie1, movie2);

        when(dateUtil.formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class))).thenReturn("2023-06-05");
        when(movieService.listAll()).thenReturn(moviesList);

        List<Movie> result = movieController.listMovies();

        verify(dateUtil, times(1)).formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class));
        verify(movieService, times(1)).listAll();

        assertEquals(moviesList.size(), result.size());
        assertEquals(moviesList.get(0).getId(), result.get(0).getId());
        assertEquals(moviesList.get(0).getMovieName(), result.get(0).getMovieName());
        assertEquals(moviesList.get(0).getDate(), result.get(0).getDate());
        assertEquals(moviesList.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(moviesList.get(0).getDirector().getId(), result.get(0).getDirector().getId());
        assertEquals(moviesList.get(0).getDirector().getDirectorName(), result.get(0).getDirector().getDirectorName());
        assertEquals(moviesList.get(0).getDirector().getCompany(), result.get(0).getDirector().getCompany());

        assertEquals(moviesList.get(1).getId(), result.get(1).getId());
        assertEquals(moviesList.get(1).getMovieName(), result.get(1).getMovieName());
        assertEquals(moviesList.get(1).getDate(), result.get(1).getDate());
        assertEquals(moviesList.get(1).getDescription(), result.get(1).getDescription());
        assertEquals(moviesList.get(1).getDirector().getId(), result.get(1).getDirector().getId());
        assertEquals(moviesList.get(1).getDirector().getDirectorName(), result.get(1).getDirector().getDirectorName());
        assertEquals(moviesList.get(1).getDirector().getCompany(), result.get(1).getDirector().getCompany());
    }

    @Test
    public void updateMovie_shouldReturnUpdatedMovie() {
        Long id = 1L;
        MovieDto movieDto = new MovieDto("Updated Movie Title", "Updated Movie Description", new Date(),
                new DirectorDto("Company name", "Director name"));

        Movie updatedMovie = new Movie();
        updatedMovie.setId(id);
        updatedMovie.setMovieName("Updated Movie Title");
        updatedMovie.setDescription("Updated Movie Description");
        updatedMovie.setDirector(new Director(id, "New company name", "New director name"));

        when(dateUtil.formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class)))
                .thenReturn("2021-01-01T10:00:00");

        when(movieService.update(eq(id), any(MovieDto.class))).thenReturn(updatedMovie);

        Movie result = movieController.updateMovie(id, movieDto);

        verify(dateUtil, times(1)).formatLocalDateTimeToDatabaseStyle(any(LocalDateTime.class));
        verify(movieService, times(1)).update(eq(id), any(MovieDto.class));

        assertEquals(updatedMovie.getId(), result.getId());
        assertEquals(updatedMovie.getMovieName(), result.getMovieName());
        assertEquals(updatedMovie.getDescription(), result.getDescription());
        assertEquals(updatedMovie.getDate(), result.getDate());
        assertEquals(updatedMovie.getDirector().getId(), result.getDirector().getId());
        assertEquals(updatedMovie.getDirector().getDirectorName(), result.getDirector().getDirectorName());
        assertEquals(updatedMovie.getDirector().getCompany(), result.getDirector().getCompany());
    }

}
