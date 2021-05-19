package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;

@Getter @Setter
public class CoachDto {
    private String name;
    private Integer personalIdNo;

    public static CoachDto fromEntity(Coach coach) {
        if(coach != null) {
            CoachDto coachDto = new CoachDto();
            coachDto.setName(coach.getName());
            coachDto.setPersonalIdNo(coach.getPersonalIdNo());
            return coachDto;
        }
        return null;
    }
}
