package fr.massaa.noteabsences.data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity implementation class for Entity: Groupe
 */
@Entity
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})    // LAZY = fetch when needed, EAGER = fetch immediately
    private List<Student> students;

    public Groupe() {
        super();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public List<Student> getStudents() {
        return this.students;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groupe)) return false;
        return id != null && id.equals(((Groupe) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
