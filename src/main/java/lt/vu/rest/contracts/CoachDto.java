package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CoachDto {
    private String name;
    private Integer personalIdNo;
    private Integer version;
    private List<StableDto> stables;

    public static CoachDto convertToCoachDto(Coach coach) {
        if(coach != null) {
            CoachDto coachDto = new CoachDto();
            coachDto.setName(coach.getName());
            coachDto.setVersion(coach.getVersion());
            coachDto.setPersonalIdNo(coach.getPersonalIdNo());
            coachDto.setStables(
                    coach.getStables().stream()
                    .map(StableDto::convertToStableDto)
                    .collect(Collectors.toList())
            );
            return coachDto;
        }
        return null;
    }
}
