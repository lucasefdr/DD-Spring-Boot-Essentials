package edu.lucas.DDSpringBootEssentials.requests;

import lombok.Builder;
import lombok.Data;

/**
 * Classe de <strong>transferência de objeto</strong>: usada para fazer requisições do método <strong>PUT</strong>
 */
@Data
@Builder
public class MoviePutRequest {
    private long id;
    private String name;
}
