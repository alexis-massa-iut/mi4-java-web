package fr.iut2.ExemplesServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet implementation class exempleServlet
 */
@WebServlet("/ExempleHttpServletToHTML")
public class ExempleHttpServletToHTML extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DATA
    private Date dateInit;
    private int nombreChargementPage;

    /**
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        dateInit = new Date();
        nombreChargementPage = 0;
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Content type
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Content
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Bonjour tout le monde !</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>ExempleHttpServletToHTML</h2>");
        out.println("<p>Hello world!</p>");

        out.println("<p>Nombre chargement de la page : " + ++nombreChargementPage + "</p>");
        out.println("<p>Date de la visite : " + new Date() + "</p>");
        out.println("<p>Date d'initialisation de la servlet : " + dateInit + "</p>");
        out.println("</body>");
        out.println("</html>");

        //ArrayList<String> test = null;
        //test.clear();
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
