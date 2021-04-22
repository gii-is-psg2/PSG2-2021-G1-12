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

 				<spring:bind path="budgetTarget">
                       <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
                    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
                    <div class="${cssGroup}">
                        <label class="col-sm-2 control-label">Cantidad a recaudar</label>

                        <div class="col-sm-10">
                            <input type="number" name="budgetTarget" value="${cause.budget}" step="0.01">
                               <c:if test="${valid}">
                                   <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                            </c:if>
                            <c:if test="${status.error}">
                                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                                <span class="help-inline">${status.errorMessage}</span>
                            </c:if>
                        </div>
                    </div>
                   </spring:bind>	



                <petclinic:inputField label="Organizacion" name="organisation"/>
           
          
           
           
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Confirmar</button>
                </div>
            </div>
            
            
            
        </form:form>
        
</petclinic:layout>
