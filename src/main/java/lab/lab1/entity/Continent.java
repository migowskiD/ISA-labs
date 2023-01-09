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
public class Continent implements Serializable {

    /**
     * Name of continent
     */
    private String name;

    /**
     * Area of continent in square kilometers
     */
    private int area;

    /**
     * People per square kilometer
     */
    private double populationDensity;
}
