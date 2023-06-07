package com.devdojo.guto.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    @NotBlank(message = "The 'movieName' field is required.")
    private String movieName;

    @NotBlank(message = "The 'description' field is required.")
    private String description;

    @NotNull(message = "The 'date' field is required.")
    private Date date;

    @NotNull(message = "The 'director' field is required.")
    private DirectorDto director;
}
