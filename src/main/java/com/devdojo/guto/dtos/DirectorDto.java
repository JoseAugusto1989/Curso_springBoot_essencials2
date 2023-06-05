package com.devdojo.guto.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    @NotBlank(message = "The 'name' field is required.")
    private String directorName;

    @NotBlank(message = "The 'company' field is required.")
    private String company;

}
