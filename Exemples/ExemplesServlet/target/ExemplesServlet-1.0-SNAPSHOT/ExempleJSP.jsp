<%-- Directive de page : permet de définir les attributs de la page --%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<%!
    // DATA
    private Date dateInit;
    private int nombreChargementPage;

    // INITIALISATION
    public void jspInit() {
        dateInit = new Date();
        nombreChargementPage = 0;
    }
%>

<html>
<head>
    <title>Exemples JSP</title>
</head>
<body>

<h1>Exemples JSP</h1>

<%-- Content --%>
<p>Hello world !!!!!</p>

<p>Nombre chargement de la page : <%= ++nombreChargementPage%>
</p>
<p>Date de la visite : <%= new Date()%>
</p>
<p>Date d'initialisation de la servlet : <%= dateInit%>
</p>

</body>
</html>
