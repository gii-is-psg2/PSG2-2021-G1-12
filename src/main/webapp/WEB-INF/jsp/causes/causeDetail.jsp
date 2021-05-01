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
            <th style="width: 200px;">Objetivo (en euros)</th>
            <th style="width: 200px;">Total Recaudado (en euros)</th>
            <th style="width: 200px;">Organizacion</th>
            <th style="width: 100px;">Estado</th>
           
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
                    <c:out value="${cause.budgetAchieved}"/>
                </td>
                <td>
                    <c:out value="${cause.organisation}"/>
                </td>
                <td>
                	<c:choose>
                	<c:when test="${cause.active}">
                		<c:out value="Activa"/>
                	</c:when>
                	<c:otherwise>
                		<c:out value="Cerrada"/>
                	</c:otherwise>
                </c:choose>
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
         <tbody> 
           <c:forEach items="${donations}" var="donation">
             <tr> 
                 <td> 
                     <c:out value="${donation.date}"/> 
                 </td> 
                 <td> 
                     <c:out value="${donation.amount}"/> 
                 </td> 
                 <td> 
                     <c:out value="${donation.user.username}"/> 
                 </td>     
             </tr>
             </c:forEach> 
         </tbody>
    </table>
</petclinic:layout>
