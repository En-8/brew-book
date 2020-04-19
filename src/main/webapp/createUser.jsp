<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 4/19/20
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>

</head>
<body>
<c:import url="./templates/navbar.jsp"/>
<h1>Sign up to get started!</h1>
<form action="createUser" method="post">
    <div class="form-group">
        <label for="username">Desired Username: </label>
        <input type="text" name="username" id="username" class="form-control">
    </div>
    <div class="form-group">
        <label for="email">Email: </label>
        <input type="email" name="email" id="email" class="form-control">
    </div>
    <div class="form-group">
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" class="form-control">
    </div>

    <input type="submit" name="submit" value="Sign me up!" class="btn btn-primary">
</form>

<c:import url="./templates/footer.jsp"/>
