<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="bookings">
<jsp:attribute name="customScript">
    </jsp:attribute>
    <jsp:body>
        <h2>
            Nueva Donacion
        </h2>
        <form:form modelAttribute="donation" class="form-horizontal" action="/causes/${causeId}/donate">
            <input type="hidden" name="id" value="${donation.id}"/>
            
            
            
            <div class="form-group has-feedback">
                <input type="hidden" name="cause" value="${causeId}"/>
                
					<spring:bind path="amount">
                       <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
                    <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
                    <div class="${cssGroup}">
                        <label class="col-sm-2 control-label">Cantidad a donar</label>

                        <div class="col-sm-10">
                            <input type="number" name="amount" value="${donation.amount}" step="0.01">
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
                   
                   </div>
            
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Confirmar Donacion</button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
