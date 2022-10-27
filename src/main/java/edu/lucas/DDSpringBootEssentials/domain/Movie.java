package edu.lucas.DDSpringBootEssentials.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Gera get, set, equals, hashcode, toString
@AllArgsConstructor // Gera constructor com todos os valores
public class Movie {
    private Long id;
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
