package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Horse.findAll", query = "select h from Horse as h")
})
@Table(name = "HORSE")
@Getter @Setter
public class Horse {
    public Horse(){ }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTITY_NO")
    private Integer identityNo;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Stable stable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Objects.equals(id, horse.id) &&
                Objects.equals(identityNo, horse.identityNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityNo);
    }
}
