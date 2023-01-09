package lab.lab1.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    /**
     * Name of country
     */
    @Id
    private String name;

    /**
     * Name of capital city
     */
    private String capitalCity;

    /**
     * Gross domestic product per capita in US dollar
     */
    private int perCapitaGDP;

    /**
     * Country's continent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent")
    private Continent continent;

}
