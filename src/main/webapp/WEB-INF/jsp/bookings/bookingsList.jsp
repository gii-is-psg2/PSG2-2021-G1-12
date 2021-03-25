<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bookings">
    <h2>Bookings</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Pet</th>
            <th style="width: 150px;">Owner</th>
            <th style="width: 200px;">Start Date</th>
            <th style="width: 120px">Finish Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <td>
                    <c:out value="${booking.pet.name}"/>
                </td>
                <td>
                    <spring:url value="/owners/{ownerId}" var="ownerUrl">
                        <spring:param name="ownerId" value="${booking.pet.owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${booking.pet.owner.firstName} ${booking.pet.owner.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${booking.startDate}"/>
                </td>
                <td>
                    <c:out value="${booking.finishDate}"/>
                </td>
                <td>
                    <spring:url value="/owners/{ownerId}/bookings/{bookingId}/delete" var="deleteUrl">
                        <spring:param name="ownerId" value="${booking.pet.owner.id}"/>
                        <spring:param name="bookingId" value="${booking.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(deleteUrl)}"><c:out value="Delete Booking"/></a>
                </td>  
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
