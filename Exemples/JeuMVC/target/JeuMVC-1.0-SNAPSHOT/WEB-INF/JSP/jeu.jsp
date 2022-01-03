<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%--  on récupère les paramètres d'initialisation de la servlet --%>
    <title><%= application.getInitParameter("title")%></title>
</head>
<body>

<%-- Element d'action : jsp:include --%>
<jsp:include page='<%= application.getInitParameter("entetedepage")%>'/>

<%-- Formulaire --%>
<h3>Choisissez une main</h3>
<form method="post" action="<%= application.getContextPath()%>/do/resultat">

    <label for="pierre"><img src="<%= application.getContextPath()%>/ressources/pierre.jpg" alt="pierre"/></label>
    <input id="pierre" type="radio" name="mainJoueur" value="pierre"/>Pierre

    <label for="papier"><img src="<%= application.getContextPath()%>/ressources/papier.jpg" alt="papier"/></label>
    <input id="papier" type="radio" name="mainJoueur" value="papier"/>Papier

    <label for="ciseaux"><img src="<%= application.getContextPath()%>/ressources/ciseaux.jpg" alt="ciseaux"/></label>
    <input id="ciseaux" type="radio" name="mainJoueur" value="ciseaux"/>Ciseaux

    <input type="submit" value="Valider"/>
</form>

<%-- Element d'action : jsp:include --%>
<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
