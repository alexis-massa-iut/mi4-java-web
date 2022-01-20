<%@ page import="fr.iut2.massaa_notesabsences.data.Etudiant" %>
<%@ page import="fr.iut2.massaa_notesabsences.data.GestionFactory" %><%--
  Created by IntelliJ IDEA.
  User: alexi
  Date: 03/01/2022
  Time: 10:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="etudiant" class="fr.iut2.massaa_notesabsences.data.Etudiant" scope="request"/>
<html>
<head>
    <title>Gestion des étudiants | Etudiant</title>
</head>
<jsp:include page='<%=application.getInitParameter("header")%>'/>
<body>
<h1>Détails de l'étudiant n°: <%=etudiant.getId()%>
</h1>
Nom: <%=etudiant.getNom()%>
<br>
Prénom: <%=etudiant.getPrenom()%>
</body>
<jsp:include page='<%=application.getInitParameter("footer")%>'/>
</html>
