package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;
import lt.vu.entities.Stable;
import lt.vu.persistence.CoachesDAO;
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
public class Coaches implements Serializable {
    @Inject
    private StablesDAO stablesDAO;
    @Inject
    private CoachesDAO coachesDAO;
    @Getter
    @Setter
    private Coach coachToCreate = new Coach();
    @Getter @Setter
    private Stable stable;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long stableId = Long.parseLong(requestParameters.get("stableId"));
        this.stable = stablesDAO.findOne(stableId);
    }
    @Transactional
    public String createCoach(){
        List<Stable> stables = new ArrayList<>();
        stables.add(this.stable);
        coachToCreate.setStables(stables);
        coachesDAO.persist(this.coachToCreate);
        return "stableDetails?faces-redirect=true&stableId="+this.stable.getId();
    }
}
