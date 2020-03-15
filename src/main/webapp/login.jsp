<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp" />
</head>
<body>
<c:import url="./templates/navbar.jsp" />

<h1>Log In</h1>
<form action="j_security_check" method="POST">
    <label for="username">Username: </label>
    <input type="text" name="j_username" id="username">
    <label for="password">Password: </label>
    <input type="password" name="j_password" id="password">
    <input type="submit" value="Log In">
</form>

<c:import url="./templates/footer.jsp" />