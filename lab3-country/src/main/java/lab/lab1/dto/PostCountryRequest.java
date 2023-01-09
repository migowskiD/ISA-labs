package lab.lab1.dto;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostCountryRequest {

    private String name;

    private String capitalCity;

    private int perCapitaGDP;

    private String continent;

    public static Function<PostCountryRequest, Country> dtoToEntityMapper(
            Function<String, Continent> continentFunction) {
        return request -> Country.builder()
                .name(request.getName())
                .capitalCity(request.getCapitalCity())
                .perCapitaGDP(request.getPerCapitaGDP())
                .continent(continentFunction.apply(request.getContinent()))
                .build();
    }

    public static Function<PostCountryRequest, Country> dtoToEntityMapper(
            Supplier<Continent> continentSupplier) {
        return request -> Country.builder()
                .name(request.getName())
                .capitalCity(request.getCapitalCity())
                .perCapitaGDP(request.getPerCapitaGDP())
                .continent(continentSupplier.get())
                .build();
    }
}
