/**
 * @author hb
 * @author hb
 * @author hb
 * @author hb
 * @author hb
 * @author hb
 * @author hb
 */

/**
 * @author hb
 *
 */
package fr.massaa.noteabsences.controller;

import fr.massaa.noteabsences.data.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    private String urlStudents;
    private String urlGroups;
    private String urlStudent;
    private String urlEditStudent;

    // INIT
    @Override
    public void init() throws ServletException {
        // Récupération des URLs en paramètre du web.xml
        urlStudents = getServletConfig().getInitParameter("urlStudents");
        urlGroups = getServletConfig().getInitParameter("urlGroups");
        urlStudent = getServletConfig().getInitParameter("urlStudent");
        urlEditStudent = getServletConfig().getInitParameter("urlEditStudent");

        // Création de la factory permettant la création d'EntityManager
        // (gestion des transactions)
        GestionFactory.open();

        ///// INITIALISATION DE LA BD
        // Normalement l'initialisation se fait directement dans la base de données
        if ((GroupDAO.getAll().size() == 0) && (StudentDAO.getAll().size() == 0)) {

            // Creation des groupes
            Groupe MIAM = GroupDAO.create("MIAM");
            Groupe SIMO = GroupDAO.create("SIMO");
            Groupe MESSI = GroupDAO.create("MESSI");

            // Creation des étudiants
            StudentDAO.create("Francis", "Brunet-Manquat", MIAM, 20);
            StudentDAO.create("Philippe", "Martin", MIAM, 19);
            StudentDAO.create("Mario", "Cortes-Cornax", MIAM, 12);
            StudentDAO.create("Françoise", "Coat", SIMO, 12);
            StudentDAO.create("Laurent", "Bonnaud", MESSI, 12);
            StudentDAO.create("Sébastien", "Bourdon", MESSI, 12);
            StudentDAO.create("Mathieu", "Gatumel", SIMO, 12);
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();
        if (action == null) {
            action = "/students";
        }

        // Log action
        System.out.println("PROJET JPA : action = " + action);

        // Exécution action
        if (action.equals("/students")) {

            doStudents(request, response);

        } else if (action.equals("/groups")) {
            doGroups(request, response);

        } else if (action.equals("/student")) {
            doStudent(request, response);

        } else if (action.equals("/editstudent")) {
            doEditStudent(request, response);

        } else if (action.equals("/modifstudent")) {
            doModifStudent(request, response);

        } else {
            // Autres cas
            doStudents(request, response);
        }
    }

    // ///////////////////////
    //
    private void doStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les étudiants
        List<Student> students = StudentDAO.getAll();
        System.out.println("les étudiants : " + students);
        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("students", students);

        //
        loadJSP(urlStudents, request, response);
    }

    // ///////////////////////
    //
    private void doGroups(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les étudiants
        List<Groupe> groupes = GroupDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("groupes", groupes);

        //
        loadJSP(urlGroups, request, response);
    }

    // ///////////////////////
    //
    private void doStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idStudent = Integer.parseInt(request.getParameter("id"));

        // Récupérer l'étudiant
        Student student = StudentDAO.retrieveById(idStudent);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("student", student);

        //
        loadJSP(urlStudent, request, response);
    }

    // ///////////////////////
    //
    private void doEditStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idStudent = Integer.parseInt(request.getParameter("id"));

        // Récupérer l'étudiant
        Student student = StudentDAO.retrieveById(idStudent);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("student", student);

        //
        loadJSP(urlEditStudent, request, response);
    }


    // ///////////////////////
    //
    private void doModifStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idStudent = Integer.parseInt(request.getParameter("id"));

        // Récupérer l'étudiant
        Student student = StudentDAO.retrieveById(idStudent);

        // Get inputs
        int groupStudent = getValueOrDefault(Integer.parseInt(request.getParameter("groupInput")), student.getGroup().getId());
        String nameStudent = getValueOrDefault(request.getParameter("nameInput"), student.getName());
        String lastnameStudent = getValueOrDefault(request.getParameter("lastnameInput"), student.getLastname());
        float meanStudent = request.getParameter("meanInput").isEmpty() ? Float.parseFloat(request.getParameter("meanInput")) : 0f;
        int absencesStudent = request.getParameter("absencesInput").isEmpty() ? Integer.parseInt(request.getParameter("absencesInput")) : 0;

        // Modifier l'étudiant
        if (groupStudent != 0) student.setGroup(GroupDAO.retrieveById(groupStudent));
        if (nameStudent != null) student.setName(nameStudent);
        if (lastnameStudent != null) student.setLastname(lastnameStudent);
        // if 0 < mean < 20 and mean != current mean
        if (Float.compare(meanStudent, student.getMean()) != 0 && Float.compare(meanStudent, 0f) >= 0 && Float.compare(meanStudent, 20f) <= 0)
            student.setMean(meanStudent);
        if (absencesStudent != 0) student.setNbAbsences(absencesStudent);
        StudentDAO.update(student);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("student", student);

        //
        loadJSP(urlStudent, request, response);
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
     * Returns defaultValue if value is null
     *
     * @param value the value to assign
     * @param defaultValue default value returned if value is empty
     * @return value to assign
     */
    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

}
