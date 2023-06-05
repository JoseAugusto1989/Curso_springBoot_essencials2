package com.devdojo.guto.repositories;

import com.devdojo.guto.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}