<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 5/10/20
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>
<div class="multi-brew-container">
    <h1>Breweries in ${city}, ${state}</h1>
    <c:forEach var="brewery" items="${breweries}">
        <div class="brew-card">
            <h2 class="brew-card-name">${brewery.name}</h2>
            <p class="brew-card-style">${brewery.websiteUrl}</p>
            <p class="brew-card-style">${brewery.street}</p>
        </div>
    </c:forEach>
</div>

<c:import url="./templates/footer.jsp"/>
