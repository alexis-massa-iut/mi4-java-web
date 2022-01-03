<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Exemple d'inclusion de fichiers</h1>

<%
    String nom = "Blanchon";
    String prenom = "Hervé";

%>

<!-- directive d'inclusion -->
<%@ include file="FicheInfo.jsp" %>

<!-- Element d'action : inclusion dynamique-->
<jsp:include page="ficheInfo2.jsp">
    <jsp:param name="nom" value="Brunet-Manquat"/>
    <jsp:param name="prenom" value="Francis"/>
</jsp:include>

</body>
</html>
