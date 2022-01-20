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
    <title>Gestion des étudiants | Notes</title>
</head>
<jsp:include page='<%=application.getInitParameter("header")%>'/>
<body>
<h1>Notes des étudiants</h1>
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
