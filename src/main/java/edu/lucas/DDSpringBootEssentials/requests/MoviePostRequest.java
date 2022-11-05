package edu.lucas.DDSpringBootEssentials.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class MoviePostRequest {
    @NotEmpty(message = "The movie name cannot be empty")
    private String name;
}
