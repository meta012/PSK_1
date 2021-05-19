package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Stable.findAll", query = "select s from Stable as s")
})
@Table(name = "STABLE")
@Getter @Setter
public class Stable {
    public Stable(){ }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "stable")
    private List<Horse> horses = new ArrayList<>();

    @ManyToMany(mappedBy = "stables")
    private List<Coach> coaches = new ArrayList<>();

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Stable stable = (Stable) o;
        return Objects.equals(id, stable.id) &&
                Objects.equals(name, stable.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }
}
