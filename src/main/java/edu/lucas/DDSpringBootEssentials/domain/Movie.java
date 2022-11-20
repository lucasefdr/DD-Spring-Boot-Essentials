package edu.lucas.DDSpringBootEssentials.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * As classes de <b>domínio</b> são <b>entidades</b> do banco de dados. <br>
 * Annotations: <br>
 * <b>@Data</b>: gera getter, setter, construtor final, equals, hashcode e toString <br>
 * <b>@AllArgsConstructor</b>: gera construtor com todos os atributos <br>
 * <b>@NoArgsConstructor</b>: gera construtor vazio <br>
 * <b>@Entity</b>: entidade do banco de dados: necessita de construtor sem argumentos e @Id <br>
 * <b>@Builder</b>: através do builder() é possível construir um objeto <br>
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera valor incremental do ID
    private Long id;

    //@JsonProperty("name")
    @NotEmpty(message = "The movie name cannot be empty") // A propriedade name não pode ser vazio
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
