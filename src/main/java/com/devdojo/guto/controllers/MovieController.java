package com.devdojo.guto.controllers;

import com.devdojo.guto.dtos.MovieDto;
import com.devdojo.guto.entities.Movie;
import com.devdojo.guto.services.MovieService;
import com.devdojo.guto.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movie")
@Log4j2
@Api(tags = "Movie", description = "Operações relacionadas a filmes")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final DateUtil dateUtil;

    @PostMapping()
    @ApiOperation("Save the movies")
    public Movie saveMovie(@Valid @RequestBody MovieDto movieDto) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return movieService.save(movieDto);
    }

    @GetMapping("/{id}")
    @ApiOperation("List movie by id")
    public Movie findById(@PathVariable Long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return movieService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete movie by id")
    public void deleteById(@PathVariable Long id) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        movieService.delete(id);
    }

    @GetMapping()
    @ApiOperation("List all the movies")
    public List<Movie> listMovies() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return movieService.listAll();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a movie by ID")
    public Movie updateMovie(@PathVariable Long id, @Validated @RequestBody MovieDto movieDto) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return movieService.update(id, movieDto);
    }
}
