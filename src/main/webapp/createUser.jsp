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
<div class="login-container">
<h1>Sign up to get started!</h1>
    <c:if test="${error.length() > 0}">
        <p class="error-msg">${error}</p>
    </c:if>
<form action="createUser" method="post" class="login-form needs-validation" novalidate>
    <div class="form-group">
        <label for="username">Desired Username: </label>
        <input type="text" name="username" id="username" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="email">Email: </label>
        <input type="email" name="email" id="email" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="password">Password: </label>
        <input type="password" name="password" id="password" class="form-control" required>
    </div>

    <input type="submit" name="submit" value="Sign me up!" class="btn btn-primary">
</form>
</div>
<script>
    window.onload = () => {
        let form = document.querySelector(".needs-validation")
        form.addEventListener("submit", () => {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false)
    }
</script>
<c:import url="./templates/footer.jsp"/>
