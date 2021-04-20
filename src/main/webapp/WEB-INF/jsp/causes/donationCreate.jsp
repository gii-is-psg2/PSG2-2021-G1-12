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
                <petclinic:inputField label="Cantidad a donar" name="amount"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Confirmar Donacion</button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
