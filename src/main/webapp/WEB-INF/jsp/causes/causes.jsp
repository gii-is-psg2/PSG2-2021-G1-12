<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causas</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Suma</th>
            <th style="width: 200px;">Objetivo</th>
            <th style="width: 200px;">Acciones</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCauses}" var="cause">
            
            <tr>
                <td>
                    <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.budgetAchieved}"/>
                </td>
                <td>
                    <c:out value="${cause.budgetTarget}"/>
                </td>
                <td>
                <c:if test = "${cause.active}">
                    <spring:url value="/causes/{causeId}/donate" var="donationUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(donationUrl)}"><c:out value="Hacer donacion"/></a>
                </c:if>
                
                    <spring:url value="/causes/{causeId}/details" var="detailsUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(detailsUrl)}"><c:out value="Ver detalles"/></a>
                </td>   
            </tr>		
		</c:forEach>
        </tbody>
    </table>
</petclinic:layout>
