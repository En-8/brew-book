<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp" />
</head>
<body>
<c:import url="./templates/navbar.jsp" />

<div class="login-container">
    <h1>Log In</h1>
    <form id="login-form" action="j_security_check" method="POST" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="username">Username: </label>
            <input class="form-control" type="text" name="j_username" id="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password: </label>
            <input class="form-control" type="password" name="j_password" id="password" required>
        </div>
        <button class="btn btn-primary" type="submit" value="Log In" >Log In</button>
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
<c:import url="./templates/footer.jsp" />