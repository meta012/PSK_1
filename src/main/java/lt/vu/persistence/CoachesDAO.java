package lt.vu.persistence;

import lt.vu.entities.Coach;
import lt.vu.entities.Horse;
import lt.vu.entities.Stable;
import lt.vu.persistence.interfaces.ICoachesDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CoachesDAO implements ICoachesDAO {
    @Inject
    private EntityManager em;
    @Override
    public List<Coach> loadAll() {

        return em.createNamedQuery("Coach.findAll", Coach.class).getResultList();
    }

    @Override
    public void persist(Coach coach){ this.em.persist(coach);}

    @Override
    public Coach findOne(Long id){ return em.find(Coach.class, id);}

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Coach update(Coach coach){
        coach = em.merge(coach);
        em.flush();
        return coach;
    }
}
