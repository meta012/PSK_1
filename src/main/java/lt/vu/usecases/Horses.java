package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Horse;
import lt.vu.entities.Stable;
import lt.vu.persistence.HorsesDAO;
import lt.vu.persistence.StablesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class Horses implements Serializable {
    @Inject
    private HorsesDAO horsesDAO;

    @Inject
    private StablesDAO stablesDAO;

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
        this.stable = stablesDAO.findOne(stableId);
    }

    @Transactional
    public String createHorse(){
        horseToCreate.setStable(this.stable);
        horsesDAO.persist(horseToCreate);
        System.out.println("stableDetails?faces-redirect=true&stableId="+this.stable.getId());
        return "stableDetails?faces-redirect=true&stableId="+this.stable.getId();
    }

    @Getter
    private List<Horse> allHorses;

    private void loadAllHorses(){
        this.allHorses = horsesDAO.loadAll();
    }

    public Integer getHorseCount(){
        loadAllHorses();
        return this.allHorses.size();
    }
}
