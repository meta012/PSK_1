package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Horse;
import lt.vu.entities.Stable;

import javax.persistence.criteria.CriteriaBuilder;

@Getter @Setter
public class HorseDto {
    private String name;
    private Integer identityNo;
    private Long stableId;

    public static HorseDto convertToHorseDto(Horse horse) {
        if(horse != null) {
            HorseDto horseDto = new HorseDto();
            horseDto.setName(horse.getName());
            horseDto.setIdentityNo(horse.getIdentityNo());
            horseDto.setStableId(horse.getStable().getId());
            return horseDto;
        }
        return null;
    }
}
