package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.StableMapper;
import lt.vu.mybatis.model.Stable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class StablesMyBatis {

    @Inject
    private StableMapper stableMapper;

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
        stableMapper.insert(stableToCreate);
        return "/myBatis/stables?faces-redirect=true";
    }

    private void loadAllStables(){
        this.allStables = stableMapper.selectAll();
    }
}

