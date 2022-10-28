package edu.lucas.DDSpringBootEssentials.requests;

import lombok.Data;

@Data
public class MoviePutRequest {
    private long id;
    private String name;
}
