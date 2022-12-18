package io.github.lucasefdr.DDSpringBootEssentials.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Classe de <strong>transferência de objeto</strong>: usada para fazer requisições do método <strong>POST</strong>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoviePostRequest {
    @NotEmpty(message = "The movie name cannot be empty")
    @Schema(description = "This is the movie's name", example = "O Senhor dos Anéis")
    private String name;
}
