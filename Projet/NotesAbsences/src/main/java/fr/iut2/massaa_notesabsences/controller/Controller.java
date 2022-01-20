package fr.iut2.massaa_notesabsences.controller;

import fr.iut2.massaa_notesabsences.data.Etudiant;
import fr.iut2.massaa_notesabsences.data.GestionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    // URL
    private String urlAccueil;
    private String urlEtudiant;
    private String urlNotes;
    private String urlAbsences;

    // INIT
    @Override
    public void init() throws ServletException {
        urlAccueil = getInitParameter("urlAccueil");
        urlEtudiant = getInitParameter("urlEtudiant");
        urlNotes = getInitParameter("urlNotes");
        urlAbsences = getInitParameter("urlAbsences");
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Méthode utilisée
        String methode = request.getMethod().toLowerCase();

        // Action demandée
        String action = request.getPathInfo();
        System.out.println(methode);
        if (action == null) {
            action = "/accueil";
            System.out.println("action == null");
        }
        if (methode.equals("get")) {
            switch (action) {
                case "/accueil":
                    doAccueil(request, response);
                    break;
                case "/etudiant":
                    doEtudiant(request, response);
                    break;
                case "/notes":
                    doNotes(request, response);
                    break;
                case "/absences":
                    doAbsences(request, response);
                    break;
                default:
                    break;
            }
        } else if (methode.equals("post")) {
            switch (action) {
                default:
                    doAccueil(request, response);
                    break;
            }
        }
    }

    /**
     * Load home page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadJSP(urlAccueil, request, response);
    }

    /**
     * Load liste of students page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'attribut id de la session
        int id = Integer.parseInt(request.getParameter("id"));

        // Creation de l'étudiant
        Etudiant etudiant = GestionFactory.getEtudiantById(id);

        // Mettre l'id en attribut de requête
        request.setAttribute("etudiant", etudiant);

        // Méthode qui transfère le contrôle à une autre servlet
        loadJSP(urlEtudiant, request, response);
    }

    /**
     * Load list of marks page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Méthode qui transfère le contrôle à une autre servlet
        loadJSP(urlNotes, request, response);
    }

    /**
     * Load list of absences
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doAbsences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Méthode qui transfère le contrôle à une autre servlet
        loadJSP(urlAbsences, request, response);
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url      : l'URL de la jsp à charger
     * @param request  : la requête HTTP
     * @param response : la réponse HTTP
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

}
