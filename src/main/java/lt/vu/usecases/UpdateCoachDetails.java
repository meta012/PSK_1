package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Coach;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CoachesDAO;
import lt.vu.usecases.interfaces.IUpdateCoachDetails;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@LoggedInvocation
@ViewScoped
@Named
@Getter @Setter
public class UpdateCoachDetails implements Serializable, IUpdateCoachDetails {
    private Coach coach;

    @Inject
    private CoachesDAO coachesDAO;

    @PostConstruct
    private void init() {
        System.out.println("Coach details update init");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long coachId = Long.parseLong(requestParameters.get("coachId"));
        this.coach = coachesDAO.findOne(coachId);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @LoggedInvocation
    public String updateCoach() throws InterruptedException{
        try {
            coachesDAO.update(this.coach);
        } catch (OptimisticLockException o) {
            return "/coachDetails.xhtml?faces-redirect=true&coachId=" + this.coach.getId() + "&error=optimistic-lock-exception";
        }
        return "/coachDetails.xhtml?faces-redirect=true&coachId=" + this.coach.getId();
    }
}
