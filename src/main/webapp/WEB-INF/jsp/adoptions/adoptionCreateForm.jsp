<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
	<h2>Adopciones</h2>

	<table id="adoptionsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Nombre de la mascota</th>
				<th style="width: 100px;">Especie</th>
				<th style="width: 100px;">Dirección</th>
				<th style="width: 100px;">Ciudad</th>
				<th style="width: 100px">Teléfono</th>
				<th style="width: 100px">¿Desea adoptarla?</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adoption}" var="adoption">
				<tr>
					<td><c:out value="${adoption.pet.name}" /></td>
					<td><c:out value="${adoption.pet.type}" /></td>
					<td><c:out value="${adoption.owner.address}" /></td>
					<td><c:out value="${adoption.owner.city}" /></td>
					<td><c:out value="${adoption.owner.telephone}" /></td>
					<td><spring:url value="/adoptions/{adoptionId}/adoption" var="adopcion">
							<spring:param name="adoptionId" value="${adoption.id}" />
						</spring:url> <a href="${fn:escapeXml(adopcion)}" class="btn btn-default">Adoptar</a>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>