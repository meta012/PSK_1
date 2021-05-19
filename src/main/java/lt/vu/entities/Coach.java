package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Coach.findAll", query = "select c from Coach as c")
})
@Table(name = "COACH")
@Getter @Setter
public class Coach {
    public Coach(){ }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERSONAL_ID_NO")
    private Integer personalIdNo;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    private List<Stable> stables =  new ArrayList<>();

    private Integer coachWorkNumber;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) &&
                Objects.equals(personalIdNo, coach.personalIdNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalIdNo);
    }
}
