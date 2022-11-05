package edu.lucas.DDSpringBootEssentials.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoviePutRequest {
    private long id;
    private String name;
}
