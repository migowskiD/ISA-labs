package lab.lab1.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Country implements Serializable {

    /**
     * Name of country
     */
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
    private Continent continent;

}
