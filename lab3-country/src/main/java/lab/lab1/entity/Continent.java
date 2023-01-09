package lab.lab1.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "continents")
public class Continent implements Serializable {

    /**
     * Name of continent
     */
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "continent")
    @ToString.Exclude
    private List<Country> countries;
}
