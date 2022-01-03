<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.iut2.massaa_notesabsences.GestionFactory" %>
<%@ page import="fr.iut2.massaa_notesabsences.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des étudiants</title>
</head>
<body>

<h1>Accueil</h1>
<%

    for (Etudiant e : GestionFactory.getEtudiants()) {
%>
<a href="details.jsp?id=<%=e.getId()%>"><%=e.getNom()%> <%=e.getPrenom()%></a><br>
        <%
    }
%>
</body>
</html>