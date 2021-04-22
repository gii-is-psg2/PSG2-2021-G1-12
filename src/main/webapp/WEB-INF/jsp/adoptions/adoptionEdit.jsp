<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoption">
  
       
        <h3>Por favor, detállenos por qué desea adoptar a este animal</h3>
        
        <table id="adoptionsTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 100px;">Nombre de la mascota</th>
				<th style="width: 100px;">Especie</th>
				<th style="width: 100px;">Dirección</th>
				<th style="width: 100px;">Ciudad</th>
				<th style="width: 100px">Teléfono</th>
			</tr>
		</thead>
		
		  <tbody>
				<tr>
					<td><c:out value="${adoption.pet.name}" /></td>
					<td><c:out value="${adoption.pet.type}" /></td>
					<td><c:out value="${adoption.owner.address}" /></td>
					<td><c:out value="${adoption.owner.city}" /></td>
					<td><c:out value="${adoption.owner.telephone}" /></td>
				</tr>
		</tbody> 
	</table>
	
	<form:form modelAttribute="description" id="add-adoption-form">
        <div class="form-group"><div>
                <input type='hidden' value='${adoptionRequest.owner.id}' name='newOwner'>
                <input type='hidden' value='${adoptionRequest.adoption.id}' name='adoption'>
                <petclinic:inputField label="Descripción de la adopción" name="description" />
                <button class="btn btn-default" type="submit">Solicitar    adopción</button>
        </div></div>
    </form:form>

  
        
   
       
</petclinic:layout>