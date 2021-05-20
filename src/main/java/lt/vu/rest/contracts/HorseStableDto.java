package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Horse;
import lt.vu.entities.Stable;

@Getter
@Setter
public class HorseStableDto {

    public String name;
    private Integer identityNo;
    public Stable stable;

    public static HorseStableDto convertToHorseStableDto(Horse horse) {
        if(horse != null) {
            HorseStableDto horseStableDto = new HorseStableDto();
            horseStableDto.setName(horse.getName());
            horseStableDto.setIdentityNo(horse.getIdentityNo());
            horseStableDto.setStable(horse.getStable());
            return horseStableDto;
        }
        return null;
    }
}
