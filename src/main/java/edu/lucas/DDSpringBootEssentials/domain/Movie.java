package edu.lucas.DDSpringBootEssentials.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // Gera get, set, equals, hashcode, toString
@AllArgsConstructor // Gera constructor com todos os valores
@NoArgsConstructor
@Entity // Entidade do banco de dados -> necessita de construtor sem argumentos e @Id
@Builder //
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonProperty("name")
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
