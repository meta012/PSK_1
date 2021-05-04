package lt.vu.persistence;

import lt.vu.entities.Stable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class StablesDAO {
    @Inject
    private EntityManager em;

    public List<Stable> loadAll() {

        return em.createNamedQuery("Stable.findAll", Stable.class).getResultList();
    }

    public void persist(Stable stable){

        this.em.persist(stable);
    }

    public Stable findOne(Integer id){

        return em.find(Stable.class, id);
    }

    public Stable update(Stable stable){
        return em.merge(stable);
    }
}
