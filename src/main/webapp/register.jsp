<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp" />
</head>
<body>
<c:import url="./templates/navbar.jsp" />

<h1>Register</h1>
<h1>Register</h1>
<form action="" method="GET">
    <label for="email">Email: </label>
    <input type="email" name="email" id="email">
    <label for="username">Username: </label>
    <input type="text" name="username" id="username">
    <label for="password">Password: </label>
    <input type="password" name="password" id="password">
    <label for="confirmPassword">Password: </label>
    <input type="password" name="confirmPassword" id="confirmPassword">
    <input type="submit" value="Log In">
</form>

<c:import url="./templates/footer.jsp" />