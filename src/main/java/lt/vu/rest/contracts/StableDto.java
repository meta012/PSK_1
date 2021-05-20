package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Stable;

@Getter
@Setter
public class StableDto {

    public String name;
    public Long id;

    public static StableDto convertToStableDto(Stable stable) {
        if(stable != null) {
            StableDto stableDto = new StableDto();
            stableDto.setName(stable.getName());
            stableDto.setId(stable.getId());
            return stableDto;
        }
        return null;
    }
}
