<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.iut2.massaa_notesabsences.data.GestionFactory" %>
<%@ page import="fr.iut2.massaa_notesabsences.data.Etudiant" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des étudiants | Accueil</title>
</head>
<jsp:include page='<%=application.getInitParameter("header")%>'/>
<body>
<h1>Accueil</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Nom</th>
        <th scope="col">Prénom</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Etudiant e : GestionFactory.getEtudiants()) {
    %>
    <tr onclick="window.location.replace('<%= application.getContextPath()%>/do/etudiant?id=<%=e.getId()%>')">
        <th scope="row"><%=e.getId()%>
        </th>
        <td><%= e.getNom() %>
        </td>
        <td><%= e.getPrenom() %>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
<jsp:include page='<%=application.getInitParameter("footer")%>'/>
</html>