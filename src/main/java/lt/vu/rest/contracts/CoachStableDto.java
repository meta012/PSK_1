package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;
import lt.vu.entities.Stable;

import java.util.List;

@Getter
@Setter
public class CoachStableDto {
    private String name;
    private Integer personalIdNo;
    private Integer version;
    private List<Stable> stables;

    public static CoachStableDto convertToCoachStableDto(Coach coach) {
        if(coach != null) {
            CoachStableDto coachStableDto = new CoachStableDto();
            coachStableDto.setName(coach.getName());
            coachStableDto.setPersonalIdNo(coach.getPersonalIdNo());
            coachStableDto.setStables(coach.getStables());
            return coachStableDto;
        }
        return null;
    }
}
