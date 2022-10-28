package edu.lucas.DDSpringBootEssentials.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MoviePostRequest {
    @NotEmpty(message = "The movie name cannot be empty")
    private String name;
}
