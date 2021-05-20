package lt.vu.persistence;

import lt.vu.entities.Coach;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.interfaces.ICoachesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Alternative
public class LowRiskCoachesDAO extends CoachesDAO implements ICoachesDAO {
    @Inject
    EntityManager em;

    @Override
    public List<Coach> loadAll() {
        System.out.println("ALTERNATIVE METHOD");
        return em
                .createQuery("select c from Coach c where c.version < 3", Coach.class)
                .getResultList();
    }
}
