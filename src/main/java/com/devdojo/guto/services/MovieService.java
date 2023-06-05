package com.devdojo.guto.services;

import com.devdojo.guto.dtos.DirectorDto;
import com.devdojo.guto.dtos.MovieDto;
import com.devdojo.guto.entities.Director;
import com.devdojo.guto.entities.Movie;
import com.devdojo.guto.exceptions.MovieException;
import com.devdojo.guto.repositories.DirectorRepository;
import com.devdojo.guto.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public Movie save(MovieDto movieDto) {
        try {
            Movie movie = new Movie();
            movie.setDate(new Date());
            movie.setMovieName(movieDto.getMovieName());
            movie.setDescription(movieDto.getDescription());

            DirectorDto directorDto = movieDto.getDirector();
            Director director = new Director();

            director.setDirectorName(directorDto.getDirectorName());
            director.setCompany(directorDto.getCompany());
            director = directorRepository.save(director);
            movie.setDirector(director);

            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new MovieException("Error to save the movie", e);
        }
    }

    public Movie update(Long id, MovieDto movieDto) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isPresent()) {
                Movie existingMovie = optionalMovie.get();
                existingMovie.setMovieName(movieDto.getMovieName());
                existingMovie.setDescription(movieDto.getDescription());
                existingMovie.setDate(movieDto.getDate());

                DirectorDto directorDto = movieDto.getDirector();
                Director director = new Director();
                director.setDirectorName(directorDto.getDirectorName());
                director.setCompany(directorDto.getCompany());

                directorRepository.save(director);
                existingMovie.setDirector(director);

                return movieRepository.save(existingMovie);
            } else {
                throw new MovieException("Movie not found", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MovieException("Error to update movie", e);
        }
    }

    public Movie findById(Long id) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isPresent()) {
                return optionalMovie.get();
            } else {
                throw new MovieException("Movie not found", null);
            }
        } catch (Exception e) {
            throw new MovieException("Error finding movie by ID", e);
        }
    }

    public List<Movie> listAll() {
        try {
            return movieRepository.findAll();
        } catch (Exception e) {
            throw new MovieException("Error to list the movies", e);
        }

    }

    @Transactional
    public void delete(Long id) {
        try {
            Optional<Movie> optionalMovie = movieRepository.findById(id);
            if (optionalMovie.isPresent()) {
                movieRepository.delete(optionalMovie.get());
            } else {
                throw new MovieException("Movie not found", null);
            }
        } catch (Exception e) {
            throw new MovieException("Error to delete movie by ID", e);
        }
    }
}
