<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="bookings">
<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#finishDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            New Booking
        </h2>
        <form:form modelAttribute="booking" class="form-horizontal" action="/owners/${ownerId}/bookings/${pet.id}/new">
            <input type="hidden" name="id" value="${booking.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Pet</label>
                    <div class="col-sm-10">
                        <c:out value="${pet.name}"/>
                    </div>
                </div>
                <input type="hidden" name="pet" value="${pet.id}"/>
                <petclinic:inputField label="Start Date" name="startDate"/>
                <petclinic:inputField label="Finish Date" name="finishDate"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<button class="btn btn-default" type="submit">Confirm Booking</button>
                </div>
            </div>
        </form:form>
    </jsp:body>
</petclinic:layout>
