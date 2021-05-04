package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.StablesDAO;
import lt.vu.entities.Stable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Stables {

    @Inject
    private StablesDAO stablesDAO;

    @Getter @Setter
    private Stable stableToCreate = new Stable();

    @Getter
    private List<Stable> allStables;

    @PostConstruct
    public void init(){
        loadAllStables();
    }

    @Transactional
    public String createStable(){
        this.stablesDAO.persist(stableToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllStables(){
        this.allStables = stablesDAO.loadAll();
    }
}

