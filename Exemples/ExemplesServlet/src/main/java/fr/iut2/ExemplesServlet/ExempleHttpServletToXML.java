package fr.iut2.ExemplesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class exempleServlet
 */
@WebServlet("/ExempleHttpServletToXML")
public class ExempleHttpServletToXML extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Content type
        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");

        // Content
        PrintWriter out = response.getWriter();
        out.println("<?xml version='1.0' encoding='UTF-8'?>");
        out.println("<troll>");
        out.println("<name>Profytroll</name>");
        out.println("</troll>");
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
