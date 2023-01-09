package lab.lab1.dto;

import lab.lab1.entity.Country;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutCountryRequest {

    private String capitalCity;

    private int perCapitaGDP;

    public static BiFunction<Country, PutCountryRequest, Country> dtoToEntityUpdater() {
        return (country, request) -> {
            country.setCapitalCity(request.getCapitalCity());
            country.setPerCapitaGDP(request.getPerCapitaGDP());
            return country;
        };
    }
}
