<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 3/8/20
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
<script src="${pageContext.request.contextPath}/js/accountManagement.js"></script>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>

<h1>Your account</h1>
<p>Username: ${username}</p>
<p>Email: ${email}</p>
<p><a href="#">Reset my password</a></p>
<button type="button" class="btn btn-outline-primary" id="editAccountButton">Edit account info</button>
<div id="editAccountContainer"></div>
<c:import url="./templates/footer.jsp"/>
