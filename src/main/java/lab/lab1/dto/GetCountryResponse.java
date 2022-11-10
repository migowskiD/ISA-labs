package lab.lab1.dto;

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
public class GetCountryResponse {
    private String name;
    
    private String capitalCity;
    
    private int perCapitaGDP;
    
    private String continent;

    public static Function<Country, GetCountryResponse> entityToDtoMapper() {
        return country -> GetCountryResponse.builder()
                .name(country.getName())
                .capitalCity(country.getCapitalCity())
                .perCapitaGDP(country.getPerCapitaGDP())
                .continent(country.getContinent().getName())
                .build();
    }
}
