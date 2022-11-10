package lab.lab1.dto;

import lab.lab1.entity.Country;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCountriesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class CountryEntry {

        private String name;

    }

    @Singular
    private List<CountryEntry> countries;


    public static Function<Collection<Country>, GetCountriesResponse> entityToDtoMapper() {
        return countries -> {
            GetCountriesResponseBuilder response = GetCountriesResponse.builder();
            countries.stream()
                    .map(country -> CountryEntry.builder()
                            .name(country.getName())
                            .build())
                    .forEach(response::country);
            return response.build();
        };
    }
}
