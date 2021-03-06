package fr.iut2.JeuMVC.controleur;

import fr.iut2.JeuMVC.data.Partie;
import fr.iut2.JeuMVC.data.Resultat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    // URL
    private String urlJeu;
    private String urlResultat;

    // CONSTANTE
    private static final String RESULTAT_KEY = "resultat";
    private static final String PARTIE_KEY = "partie";


    // INIT
    public void init() throws ServletException {
        urlJeu = getInitParameter("urlJeu");
        urlResultat = getInitParameter("urlResultat");
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        // On récupère la méthode d'envoi de la requête
        String methode = request.getMethod().toLowerCase();

        // On récupère l'action à exécuter
        String action = request.getPathInfo();
        if (action == null) {
            action = "/jeu";
            System.out.println("action == null");
        }
        System.out.println("JeuMVC : action = " + action);

        // Exécution action
        if (methode.equals("get") && action.equals("/jeu")) {
            doJeu(request, response);

        } else if (methode.equals("post") && action.equals("/resultat")) {
            doResultat(request, response);

        } else {
            // Autres cas
            doJeu(request, response);
        }
    }

    //
    private void doJeu(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {


        loadJSP(urlJeu, request, response);
    }

    //
    private void doResultat(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {

        // Récupérer l'attribut resultat de la session
        Resultat resultat = (Resultat) request.getSession().getAttribute(RESULTAT_KEY);
        if (resultat == null) {
            resultat = new Resultat();
            request.getSession().setAttribute(RESULTAT_KEY, resultat);
        }

        // Créer une objet de type jeu
        Partie partie = new Partie();
        partie.setMainJoueur(request.getParameter("mainJoueur"));

        // Traitement du résultat
        if (partie.egalite()) {
            resultat.addEgalite();
        } else if (partie.joueurGagne()) {
            resultat.addGagne();
        } else {
            resultat.addPerdu();
        }

        // Mettre l'objet jeu en attribut de requête
        request.setAttribute(PARTIE_KEY, partie);

        loadJSP(urlResultat, request, response);
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
    public void loadJSP(String url, HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction). 
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }


}
