package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;
import lt.vu.entities.Stable;
import lt.vu.persistence.CoachesDAO;
import lt.vu.persistence.StablesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ViewScoped
@Named
@Getter
@Setter
public class CoachDetails implements Serializable {
    private Coach coach;

    @Inject
    private CoachesDAO coachesDAO;
    @Inject
    private StablesDAO stablesDAO;
    @Getter @Setter
    private Long stableToAssignId = (long)0;

    @PostConstruct
    private void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long coachId = Long.parseLong(requestParameters.get("coachId"));
        this.coach = coachesDAO.findOne(coachId);
    }
    @Transactional
    public String assignToNewStable(){
        List<Stable> stables = coach.getStables();
        Stable stableToAssign = stablesDAO.findOne(stableToAssignId);
        if(!stables.contains(stableToAssign)) {
            stables.add(stableToAssign);
            coach.setStables(stables);
            coachesDAO.update(coach);
        }
        return "coachDetails?faces-redirect=true&coachId="+ coach.getId();
    }

    public List<Stable> getAvailableStables(){
        List<Stable> availableStables = new ArrayList<>();
        List<Stable> allStables = stablesDAO.loadAll();
        List<Stable> stables = coach.getStables();

        return allStables.stream().filter(s -> !stables.contains(s)).collect(Collectors.toList());
    }
}
