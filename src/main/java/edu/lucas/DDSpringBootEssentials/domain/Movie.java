package edu.lucas.DDSpringBootEssentials.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Gera get, set, equals, hashcode, toString
@AllArgsConstructor // Gera constructor com todos os valores
public class Movie {
    private Long id;

    @JsonProperty("name")
    private String name;

    /*public Movie(String name) {
        this.name = name;
    }

    public Movie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
