package lt.vu.persistence.interfaces;

import lt.vu.entities.Coach;

import java.util.List;

public interface ICoachesDAO {
    List<Coach> loadAll();
    void persist(Coach coach);
    Coach findOne(Long id);
    Coach update(Coach coach);
}
