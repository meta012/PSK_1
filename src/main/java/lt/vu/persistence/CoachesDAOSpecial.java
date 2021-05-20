package lt.vu.persistence;

import lt.vu.entities.Coach;
import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@LoggedInvocation
@Specializes
public class CoachesDAOSpecial extends CoachesDAO{
    @Inject
    private EntityManager em;
    @Override
    public void persist (Coach coach) {
        coach.setName(coach.getName()+" - special");
        System.out.println("specializes");
        this.em.persist(coach);
    }
}
