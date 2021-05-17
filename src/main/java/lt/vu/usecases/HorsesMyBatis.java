package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.HorseMapper;
import lt.vu.mybatis.dao.StableMapper;
import lt.vu.mybatis.model.Horse;
import lt.vu.mybatis.model.Stable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class HorsesMyBatis {
    @Inject
    private HorseMapper horseMapper;

    @Inject
    private StableMapper stableMapper;

    @Getter @Setter
    private Stable stable;

    @Getter @Setter
    private Horse horseToCreate = new Horse();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long stableId = Long.parseLong(requestParameters.get("stableId"));
        System.out.println("STABLE ID: " + stableId);
        this.stable = stableMapper.selectByPrimaryKey(stableId);

        loadAllHorses();
    }

    @Transactional
    public String createHorse(){
        horseToCreate.setStableId(this.stable.getId());
        horseMapper.insert(horseToCreate);
        System.out.println("stableDetails?faces-redirect=true&stableId="+this.stable.getId());
        return "stableDetails?faces-redirect=true&stableId="+this.stable.getId();
    }

    @Getter
    private List<Horse> allHorses;

    private void loadAllHorses(){
        this.allHorses = horseMapper.selectAll();

        if (allHorses.size() != 0) {
            for (Horse horse : allHorses) {
                if (horse.getStableId() != stable.getId()) {
                    this.allHorses.remove(horse);
                }
            }
        }
    }
}
