<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="bookings">

        <h2>
            Nueva Causa
        </h2>
        
        <form:form modelAttribute="causes" class="form-horizontal" action="/causes/create">

                 <input type="hidden" name="active" value="true"/>
            
                <petclinic:inputField label="Nombre" name="name"/>
                <petclinic:inputField label="Descripcion" name="description"/>
                <petclinic:inputField label="Objetivo" name="budgetTarget"/>
                <petclinic:inputField label="Organizacion" name="organisation"/>
           
           
           
           
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Confirmar</button>
                </div>
            </div>
            
            
            
        </form:form>
        
</petclinic:layout>
