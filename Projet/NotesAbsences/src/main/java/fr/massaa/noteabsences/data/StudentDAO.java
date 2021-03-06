package fr.massaa.noteabsences.data;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class StudentDAO {

    private static int NB_ABSENCES_MAX = 8;

    public static Student retrieveById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Student etu = em.find(Student.class, id);
        // etu est maintenant un objet de la classe Student
        // ou NULL si l'étudiant n'existe pas

        // Close the entity manager
        em.close();

        return etu;
    }


    public static Student create(String name, String lastname, Groupe group, float mean) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new Student
        Student student = new Student();
        student.setName(name);
        student.setLastname(lastname);
        student.setGroup(group);
        student.setMean(mean);
        em.persist(student);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return student;
    }

    public static Student update(Student student) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (Student) à l’EntityManager courant  pour réaliser la modification
        em.merge(student);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return student;
    }

    public static Student addAbsences(int id, int absencesToAdd) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Find
        Student student = em.find(Student.class, id);

        //
        em.getTransaction().begin();

        //
        int initialAbsences = student.getNbAbsences();
        student.setNbAbsences(initialAbsences + absencesToAdd);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return student;
    }

    public static void addAbsences(Student student, int absences) {

        // Ajouter ou enlever une absence à l'étudiant
        int nbAbsences = student.getNbAbsences();
        if ((nbAbsences + absences) >= 0) {
            nbAbsences = nbAbsences + absences;
        }
        student.setNbAbsences(nbAbsences);

        // Mettre l'étudiant à jour
        StudentDAO.update(student);

    }


    public static void remove(Student student) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // L'étudiant passé en paramètre doit être associé à l'EM
        if (!em.contains(student)) {
            student = em.merge(student);
        }

        // Remove
        em.remove(student);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }

    public static void remove(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        //
        em.createQuery("DELETE FROM Student AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }

    public static int removeAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // RemoveAll
        int deletedCount = em.createQuery("DELETE FROM Student").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }

    // Retourne l'ensemble des Students
    public static List<Student> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche 
        Query q = em.createQuery("SELECT e FROM Student e");

        @SuppressWarnings("unchecked")
        List<Student> listStudent = q.getResultList();

        return listStudent;
    }

    // Retourne l'ensemble des Students d'un groupe donné
    public static List<Student> getAllByAbsences() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche 
        Query q = em.createQuery("SELECT e FROM Student e WHERE e.nbAbsences > :nbAbsenceMAx")
                .setParameter("nbAbsenceMAx", NB_ABSENCES_MAX);

        @SuppressWarnings("unchecked")
        List<Student> listStudent = q.getResultList();

        return listStudent;
    }

}
