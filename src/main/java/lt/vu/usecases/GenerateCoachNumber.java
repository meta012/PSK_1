package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.CoachNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateCoachNumber implements Serializable {
    @Inject
    CoachNumberGenerator coachNumberGenerator;

    private CompletableFuture<Integer> coachNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewCoachNumber() {
        Map<String,String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        coachNumberGenerationTask = CompletableFuture.supplyAsync(() -> coachNumberGenerator.generateCoachNumber());
        return  "/coachDetails.xhtml?faces-redirect=true&coachId=" + requestParameters.get("coachId");
    }

    public boolean isGenerationRunning() {
        return coachNumberGenerationTask != null && !coachNumberGenerationTask.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (coachNumberGenerationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Coach work number generation status is in progress..";
        }
        return "Suggested coach work number: " + coachNumberGenerationTask.get();
    }
}
