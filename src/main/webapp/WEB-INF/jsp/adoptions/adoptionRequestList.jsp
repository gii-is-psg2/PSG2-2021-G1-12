<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptionsRequest">
	<h2>Peticiones de adopción</h2>

	<table id="adoptionsRequestTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Nombre de la mascota</th>
				<th style="width: 100px;">Especie</th>
				<th style="width: 100px;">Dirección</th>
				<th style="width: 100px;">Ciudad</th>
				<th style="width: 100px">Decripción</th>
				<th style="width: 100px">Aceptar</th>
				<th style="width: 100px">Denegar</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${solicitudes}" var="solicitudes">
				<tr>
					<td><c:out value="${solicitudes.adoption.pet.name}" /></td>
					<td><c:out value="${solicitudes.adoption.pet.type}" /></td>
					<td><c:out value="${solicitudes.adoption.owner.address}" /></td>
					<td><c:out value="${solicitudes.adoption.owner.city}" /></td>
					<td><c:out value="${solicitudes.description}" /></td>
					<td><spring:url value="/adoptions/{adoptionId}/{adoptionRequestId}/aceptar" var="adoption">
							<spring:param name="adoptionId" value="${solicitudes.adoption.id}" />
							<spring:param name="adoptionRequestId" value="${solicitudes.id}" />
						</spring:url> <a href="${fn:escapeXml(adoption)}" class="btn btn-default">Aceptar</a>
					</td>
					<td><spring:url value="/adoptions/{adoptionId}/{adoptionRequestId}/denegar" var="adoption">
							<spring:param name="adoptionId" value="${solicitudes.adoption.id}" />
							<spring:param name="adoptionRequestId" value="${solicitudes.id}" />
						</spring:url> <a href="${fn:escapeXml(adoption)}" class="btn btn-default">Denegar</a>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>