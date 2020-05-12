<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 3/8/20
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>
<div class="multi-brew-container" id="your-brews-container">
    <h1>Your Brews</h1>
    <c:if test="${brews.size() == 0}" >
        <p class="nothing-here">
            There's nothing here! <a href="${pageContext.request.contextPath}/createBrew">Create a new brew to get started.</a>
        </p>
    </c:if>
    <c:forEach items="${brews}" var="brew">
        <div class="brew-card">
            <h2 class="brew-card-name">${brew.brewName}</h2>
            <h3 class="brew-card-style">${brew.style.name}</h3>
            <p class="brew-card-description">
                ${brew.description}
            </p>
            <div class="brew-card-button-container">
                <a href="brewDetails?id=${brew.id}"><button class="brew-card-button btn btn-secondary brew-details-button">View Brew</button></a>
                <a href="${pageContext.request.contextPath}/deleteBrew?id=${brew.id}"><button class="brew-card-button btn btn-danger delete-brew-button">Delete Brew</button></a>
            </div>
        </div>
    </c:forEach>
</div>

<c:import url="./templates/footer.jsp"/>
