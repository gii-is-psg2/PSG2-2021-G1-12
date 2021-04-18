<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causa</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Descripcion</th>
            <th style="width: 200px;">Objetivo</th>
            <th style="width: 200px;">Organizacion</th>
           
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.description}"/>
                </td>
                <td>
                    <c:out value="${cause.budgetTarget}"/>
                </td>
                <td>
                    <c:out value="${cause.organisation}"/>
                </td>   
            </tr>
        </tbody>
    </table>
    
    
    <h2>Donaciones</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Fecha</th>
            <th style="width: 150px;">Cantidad</th>
            <th style="width: 200px;">Cliente</th>
           
        </tr>
        </thead>
<!--         <tbody> -->
<!--             <tr> -->
<!--                 <td> -->
<%--                     <c:out value="${cause.name}"/> --%>
<!--                 </td> -->
<!--                 <td> -->
<%--                     <c:out value="${cause.description}"/> --%>
<!--                 </td> -->
<!--                 <td> -->
<%--                     <c:out value="${cause.budgetTarget}"/> --%>
<!--                 </td> -->
<!--                 <td> -->
<%--                     <c:out value="${cause.organisation}"/> --%>
<!--                 </td>    -->
<!--             </tr> -->
<!--         </tbody> -->
    </table>
</petclinic:layout>
