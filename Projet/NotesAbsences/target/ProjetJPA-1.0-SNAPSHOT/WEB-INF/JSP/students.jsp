<%@page import="fr.massaa.noteabsences.data.Student" %>
<%@page import="java.util.List" %>
<%@ page import="fr.massaa.noteabsences.data.Student" %>
<%@ page import="fr.massaa.noteabsences.data.GestionFactory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="students" type="java.util.List<fr.massaa.noteabsences.data.Student>" scope="request"/>

<html>
<head>
    <title><%=application.getInitParameter("title")%>
    </title>
</head>
<%@ include file="common/header.jsp" %>
<body>
<h3>Add student : </h3>
<form class="form-inline" action="">
    <div class="mx-sm-3 mb-2">
        <input type="text" class="form-control" id="inputName" placeholder="Enter name">
    </div>
    <div class="mx-sm-3 mb-2">
        <input type="text" class="form-control" id="inputLastname" placeholder="Enter lastname">
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
<hr>
<h1>Student list</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
        <th scope="col">Lastname</th>
        <th scope="col">Groupe</th>
        <th scope="col">Moyenne</th>
        <th scope="col">Absences</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Student s : students) {
    %>
    <tr onclick="window.location.replace('<%= application.getContextPath()%>/do/student?id=<%=s.getId()%>')">
        <th scope="row">
            <%=s.getId()%>
        </th>
        <td>
            <%= s.getName() %>
        </td>
        <td>
            <%= s.getLastname() %>
        </td>
        <td>
            <%= s.getGroup().getNom() %>
        </td>
        <td>
            <%= s.getMean() %>
        </td>
        <td>
            <%= s.getNbAbsences() %>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
<%@ include file="common/footer.jsp" %>
</html>
