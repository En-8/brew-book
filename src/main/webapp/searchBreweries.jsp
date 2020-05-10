<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 5/10/20
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>

<div id="search-container">
    <h1>Search for breweries near you...</h1>
    <form action="${pageContext.request.contextPath}/thirsty" method="post" id="search-form" class="needs-validation" novalidate>
        <div class="form-group">
            <label for="city">City</label>
            <input type="text" class="form-control" id="city" name="city" required>
        </div>
        <div class="form-group">
            <label for="state">State</label>
            <input type="text" class="form-control" id="state" name="state" required>
        </div>
        <button class="btn btn-primary" type="submit">Search</button>
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
