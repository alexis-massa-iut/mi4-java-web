<%@ page import="fr.iut2.massaa_notesabsences.Etudiant" %>
<%@ page import="fr.iut2.massaa_notesabsences.GestionFactory" %><%--
  Created by IntelliJ IDEA.
  User: alexi
  Date: 03/01/2022
  Time: 10:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Détails</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    Etudiant e = GestionFactory.getEtudiantById(id);
%>
<h1>Détails de l'étudiant n°: <%=id%></h1>
Nom: <%=e.getNom()%>
<br>
Prénom: <%=e.getPrenom()%>
<br><br><br>
<a href="index.jsp">Retour à l'accueil</a>
</body>
</html>
