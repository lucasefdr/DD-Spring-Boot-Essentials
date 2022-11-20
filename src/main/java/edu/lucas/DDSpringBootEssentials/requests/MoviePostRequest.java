package edu.lucas.DDSpringBootEssentials.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoviePostRequest {
    @NotEmpty(message = "The movie name cannot be empty")
    private String name;
}
