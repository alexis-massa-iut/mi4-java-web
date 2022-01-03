<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id='partie' class="fr.iut2.JeuMVC.data.Partie" scope="request"/>
<jsp:useBean id="resultat" class="fr.iut2.JeuMVC.data.Resultat" scope="session"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%--  on récupère les paramètres d'initialisation de la servlet --%>
    <title><%= application.getInitParameter("title")%>
    </title>
</head>
<body>

<%-- Element d'action : jsp:include --%>
<jsp:include page='<%= application.getInitParameter("entetedepage")%>'/>

<h3>Résultat</h3>

<%-- Element de script : Expression --%>

Joueur <img src="<%= application.getContextPath()%>/ressources/<%=partie.getMainJoueur()%>.jpg"
            alt="<%=partie.getMainJoueur()%>"/>
vs Ordinateur <img src="<%= application.getContextPath()%>/ressources/<%=partie.getMainOrdinateur()%>.jpg"
                   alt="<%=partie.getMainOrdinateur()%>"/>

<h3>
    <% if (partie.egalite()) {
        out.append("Egalité !");
    } else if (partie.joueurGagne()) {
        out.append("Joueur gagne !");
    } else {
        out.append("Ordinateur gagne !");
    }%>
</h3>

<p>nombre de victoires :
    <jsp:getProperty name="resultat" property="nombreVictoire"/>
</p>
<p>nombre de défaites :
    <jsp:getProperty name="resultat" property="nombreDefaite"/>
</p>
<p>nombre d'égalités :
    <jsp:getProperty name="resultat" property="nombreEgalite"/>
</p>

<p><a href="<%= application.getContextPath()%>/do/jeu">rejouer</a></p>

<%-- Element d'action : jsp:include --%>
<jsp:include page='<%= application.getInitParameter("pieddepage")%>'/>

</body>
</html>
