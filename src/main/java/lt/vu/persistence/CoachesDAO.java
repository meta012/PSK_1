package lt.vu.persistence;

import lt.vu.entities.Coach;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CoachesDAO {
    @Inject
    private EntityManager em;

    public void persist(Coach coach){ this.em.persist(coach);}

    public Coach findOne(Long id){ return em.find(Coach.class, id);}

    public Coach update(Coach coach){ return em.merge(coach);}
}
