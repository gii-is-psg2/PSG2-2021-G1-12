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
					<td><c:out value="${pet.name}" /></td>
					<td><c:out value="${pet.type}" /></td>
					<td><c:out value="${pet.owner.address}" /></td>
					<td><c:out value="${pet.owner.city}" /></td>
					<td><c:out value="${pet.owner.telephone}" /></td>
				</tr>
		</tbody> 
	</table>
	
	<form:form modelAttribute="adoptionRequest" id="add-adoption-form">
        <div class="form-group"><div>
<petclinic:inputField label="Descripción de la adopción" name="description" />
                <button class="btn btn-default" type="submit">Solicitar adopción</button>
        </div></div>
    </form:form>

  
        
   
       
</petclinic:layout>