package lab.lab1.dto;

import lab.lab1.entity.Continent;
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

    @Singular
    private List<String> continents;


    public static Function<Collection<Continent>, GetContinentsResponse> entityToDtoMapper() {
        return continents -> {
            GetContinentsResponseBuilder response = GetContinentsResponse.builder();
            continents.stream()
                    .map(Continent::getName)
                    .forEach(response::continent);
            return response.build();
        };
    }
}
