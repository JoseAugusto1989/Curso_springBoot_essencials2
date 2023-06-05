package com.devdojo.guto.repositories;

import com.devdojo.guto.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
