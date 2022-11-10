package lab.lab1.dto;

import lab.lab1.entity.Continent;
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
public class GetContinentsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class ContinentEntry {

        private String name;

    }

    @Singular
    private List<ContinentEntry> continents;


    public static Function<Collection<Continent>, GetContinentsResponse> entityToDtoMapper() {
        return continents -> {
            GetContinentsResponseBuilder response = GetContinentsResponse.builder();
            continents.stream()
                    .map(continent -> ContinentEntry.builder()
                            .name(continent.getName())
                            .build())
                    .forEach(response::continent);
            return response.build();
        };
    }
}
