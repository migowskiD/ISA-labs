package lab.lab1.dto;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetContinentResponse {

    private String name;

    private int area;

    private double populationDensity;

    public static Function<Continent, GetContinentResponse> entityToDtoMapper() {
        return continent -> GetContinentResponse.builder()
                .name(continent.getName())
                .area(continent.getArea())
                .populationDensity(continent.getPopulationDensity())
                .build();
    }
}
