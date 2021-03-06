package lt.vu.persistence;

import lt.vu.entities.Horse;
import lt.vu.entities.Stable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class HorsesDAO {
    @Inject
    private EntityManager em;

    public List<Horse> loadAll() {

        return em.createNamedQuery("Horse.findAll", Horse.class).getResultList();
    }

    public void persist(Horse horse){

        this.em.persist(horse);
    }

    public Horse findOne(Long id){

        return em.find(Horse.class, id);
    }

    public Horse update(Horse horse){
        return em.merge(horse);
    }
}
