package lab.lab1.dto;

import lab.lab1.entity.Continent;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutContinentRequest {

    private int area;

    private double populationDensity;

    public static BiFunction<Continent, PutContinentRequest, Continent> dtoToEntityUpdater() {
        return (continent, request) -> {
            continent.setArea(request.getArea());
            continent.setPopulationDensity(request.getPopulationDensity());
            return continent;
        };
    }
}
