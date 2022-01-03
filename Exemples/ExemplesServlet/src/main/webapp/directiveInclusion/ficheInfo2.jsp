<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h3>Informations sur la personne</h3>

<%
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
%>

<p>Nom : <%= nom %>
</p>
<p>Prénom : <%= prenom %>
</p>
