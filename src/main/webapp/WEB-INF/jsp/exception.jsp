<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/errorDog.jpg" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Vaya ha ocurrido algún tipo de error</h2>

    <p>${exception.message}</p>

</petclinic:layout>
