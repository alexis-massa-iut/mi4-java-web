package fr.massaa.noteabsences.data;


public class TestAbsences {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //
        GestionFactory.open();

        // Initialisation
        // Creation des groupes
        Groupe MIAM = GroupDAO.create("MIAM");
        Groupe SIMO = GroupDAO.create("SIMO");
        Groupe MESSI = GroupDAO.create("MESSI");

        // Initialisation
        // Creation des étudiants
        Student FBM = StudentDAO.create("Francis", "Brunet-Manquat", MIAM, 20);
        int idFBM = FBM.getId();
        StudentDAO.create("Philippe", "Martin", MIAM, 19);
        StudentDAO.create("Mario", "Cortes-Cornax", MIAM, 12);
        StudentDAO.create("Françoise", "Coat", SIMO, 12);
        StudentDAO.create("Laurent", "Bonnaud", MESSI, 12);
        StudentDAO.create("Sébastien", "Bourdon", MESSI, 12);
        Student MG = StudentDAO.create("Mathieu", "Gatumel", SIMO, 12);

        // Retrouver un Student par son id
        Student student_TMP = StudentDAO.retrieveById(idFBM);

        // Modification : ajout d'absences
        student_TMP.setNbAbsences(6);
        StudentDAO.update(student_TMP);
        // ou
        StudentDAO.addAbsences(idFBM, 4);

        // Suppression d'un étudiant
        StudentDAO.remove(MG);
        //StudentDAO.remove(MG.getId());

        // Liste des groupes
        System.out.println("Liste des groupes :");
        for (Groupe groupe : GroupDAO.getAll()) {
            System.out.println(groupe.getId() + " : " + groupe.getNom() + " (" + groupe.getStudents().size() + " étudiant(s))");
        }

        // Liste des étudiants
        System.out.println("\nListe des étudiants :");
        for (Student etu : StudentDAO.getAll()) {
            System.out.println(etu.getId() + " : " + etu.getName() + " " + etu.getLastname() + " " + etu.getGroup().getNom() + " " + etu.getNbAbsences());
        }


        // Liste des étudiants par groupe
        for (Groupe groupe : GroupDAO.getAll()) {

            System.out.println("\nListe des étudiants " + groupe.getNom() + " :");
            for (Student etu : groupe.getStudents()) {
                System.out.println(etu.getId() + " : " + etu.getName() + " " + etu.getLastname() + " " + etu.getGroup().getNom() + " " + etu.getNbAbsences());
            }
        }

        // Liste des étudiants ayant des absences
        System.out.println("\nListe des étudiants ayant des absences :");
        for (Student etu : StudentDAO.getAllByAbsences()) {
            System.out.println(etu.getId() + " : " + etu.getName() + " " + etu.getLastname() + " " + etu.getGroup().getNom() + " " + etu.getNbAbsences());
        }

        // Remove un groupe avec les étudiants associés
        GroupDAO.remove(SIMO);

        // Remove entities
        StudentDAO.removeAll();
        GroupDAO.removeAll();

        //
        GestionFactory.close();
    }

}
