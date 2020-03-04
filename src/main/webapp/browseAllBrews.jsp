<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp" />
<c:import url="./templates/navbar.jsp" />

<h1>Browse all brews...</h1>
<table>
    <tr><th>Brew Name</th><th>Description</th><th>Brewer (User)</th></tr>
    <c:forEach items="${brews}" var="brew">
        <tr><td>${brew.brewName}</td><td>${brew.description}</td><td>${brew.user.username}</td></tr>
    </c:forEach>
</table>

<style>
    table {
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid black;
    }
</style>

<c:import url="./templates/footer.jsp" />