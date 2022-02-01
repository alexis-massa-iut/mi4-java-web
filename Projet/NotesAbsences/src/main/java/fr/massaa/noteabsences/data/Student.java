package fr.massaa.noteabsences.data;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity implementation class for Entity: Group
 */
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    private int nbAbsences;

    private float mean;

    @ManyToOne
    private Groupe group;

    private static final long serialVersionUID = 1L;

    public Student() {
        super();
        nbAbsences = 0;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Groupe getGroup() {
        return this.group;
    }

    public void setGroup(Groupe groupe) {
        this.group = groupe;
        if (!groupe.getStudents().contains(this)) {
            groupe.getStudents().add(this);
        }
    }

    public int getNbAbsences() {
        return nbAbsences;
    }

    public void setNbAbsences(int nbAbsences) {
        this.nbAbsences = nbAbsences;
    }

    public float getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }

    @Override
    public String toString() {
        return "[" + this.getId() + "] " + this.getName() + " " + this.getLastname();
    }
}
