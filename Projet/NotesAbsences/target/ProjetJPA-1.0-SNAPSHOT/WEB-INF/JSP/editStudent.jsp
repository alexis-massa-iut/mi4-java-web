<%@ page import="fr.massaa.noteabsences.data.GestionFactory" %>
<%@ page import="fr.massaa.noteabsences.data.GroupDAO" %>
<%@ page import="fr.massaa.noteabsences.data.Groupe" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<jsp:useBean id="student" class="fr.massaa.noteabsences.data.Student" scope="request"/>

<html>
<body>
<%-- Formulaire --%>
<form method="post" action="<%= application.getContextPath()%>/do/modifstudent">
    <input type="hidden" name="id" value="<%=student.getId()%>">

    <div class="card">
        <div class="card-header">Fiche individuelle <small>(student.jsp)</small></div>
        <div class="card-body">

            <h3>Fiche de <%=student.getName()%> <%=student.getLastname()%>
            </h3>

            <table class="table table-bordered">
                <thead></thead>
                <tbody>
                <tr>
                    <td><label for="name-input">Prénom</label></td>
                    <td>
                        <input id="name-input" type="text" name="nameInput" value="<%=student.getName()%>">
                    </td>
                </tr>
                <tr>
                    <td><label for="lastname-input">Nom</label></td>
                    <td>
                        <input id="lastname-input" type="text" name="lastnameInput" value="<%=student.getLastname()%>">
                    </td>
                </tr>
                <tr>
                    <td><label for="group-select">Groupe</label></td>
                    <td>
                        <select name="groupInput" id="group-select">
                            <%
                                for (Groupe g : GroupDAO.getAll()) {
                            %>
                            <option value="<%=g.getId()%>"
                                    <%if (Objects.equals(g.getId(), student.getGroup().getId())){%>selected<%}%> ><%=g.getNom()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="mean-input">Moyenne Générale</label></td>
                    <td>
                        <input id="mean-input" type="number" pattern="[0-9]+([,\.][0-9]+)?" name="meanInput"
                               value="<%=student.getMean()%>" step="0.1" min="0" max="20" title="Veillez saisir un nombre décimal">
                    </td>
                </tr>
                <tr>
                    <td><label for="absences-input">Absences</label></td>
                    <td>
                        <input id="absences-input" type="number" name="absencesInput"
                               value="<%=student.getNbAbsences()%>">
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>
    <input type="submit" value="Modifier"/>
</form>

</body>
</html>
