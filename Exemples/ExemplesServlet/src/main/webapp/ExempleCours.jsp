<%--
  Created by IntelliJ IDEA.
  User: fbm
  Date: 30/11/2020
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>

<%!
    // Déclaration d'une variable
    private int maVariable;

    // Déclaration d'une méthode
    private int somme(int a, int b) {
        return a + b;
    }
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exemples simples de cours</title>
</head>
<body>

<h1>Exemples simples de cours</h1>

<%-- Exemple d'expression --%>
<p>Nous sommes le : <%= new java.util.Date() %>
</p>

<%-- Exemple de scriplet --%>
<%
    int resultat = 0;
    for (int i = 1; i < 15; i++) {
        resultat = somme(resultat, i);
    }
%>

<%-- Exemple de scriplet avec un entier --%>
<div>Le résultat est de <%= resultat %>
</div>

</body>
</html>
