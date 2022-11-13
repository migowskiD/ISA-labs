package lab.lab1.dto;

import lab.lab1.entity.Continent;
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

    public static Function<PostContinentRequest, Continent> dtoToEntityMapper() {
        return request -> Continent.builder()
                .name(request.getName())
                .build();
    }
}
