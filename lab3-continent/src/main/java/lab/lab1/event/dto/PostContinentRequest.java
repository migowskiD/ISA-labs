package lab.lab1.event.dto;

import lab.lab1.entity.Continent;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostContinentRequest {

    private String name;

    public static Function<Continent, PostContinentRequest> entityToDtoMapper() {
        return entity -> PostContinentRequest.builder()
                .name(entity.getName())
                .build();
    }
}
