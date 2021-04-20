<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoption">
  
    <form:form modelAttribute="adoptionToEdit" class="form-horizontal" id="add-adoption-form" action="/adoption/${adoption.id}/adoption">
        <div class="form-group has-feedback"> 	
        <h3>Por favor, detállenos por qué desea adoptar a este animal</h3>

  
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Enviar petición</button>
            </div>
   
    </form:form>
 
    
</petclinic:layout>