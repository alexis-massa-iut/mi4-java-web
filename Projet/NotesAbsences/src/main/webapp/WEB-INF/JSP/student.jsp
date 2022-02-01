<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<jsp:useBean id="student" class="fr.massaa.noteabsences.data.Student" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title><%=application.getInitParameter("title")%>
    </title>
</head>
<%@ include file="common/header.jsp" %>
<body>
<button type="button" class="btn btn-primary"
        onclick="window.location.replace('<%= application.getContextPath()%>/do/students')"><i
        class="bi bi-box-arrow-left"></i> Retour à la liste
</button>
<h2>
    Fiche individuelle de: <%=student.getName()%> <%=student.getLastname()%>
    <button type="button" class="btn btn-primary"
            onclick="window.location.replace('<%= application.getContextPath()%>/do/editstudent?id=<%=student.getId()%>')">
        Modifier <i class="bi bi-pencil-square"></i></button>
</h2>

<div class="card">
    <div class="card-body">
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th scope="row">Nom</th>
                <td><%=student.getLastname()%>
            </tr>
            <tr>
                <th scope="row">Prénom</th>
                <td><%=student.getName()%>
            </tr>
            <tr>
                <th scope="row">Groupe</th>
                <td><%=student.getGroup().getNom()%>
            </tr>
            <tr>
                <th scope="row">Moyenne Générale</th>
                <td><%=student.getMean()%>
            </tr>
            <tr>
                <th scope="row">Absences</th>
                <td><%=student.getNbAbsences()%>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<%@ include file="common/footer.jsp" %>
</html>

