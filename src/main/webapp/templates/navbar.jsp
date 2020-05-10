<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="index">BrewBook</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="index">Home</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Brews
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="yourBrews">View your brews</a>
                    <a class="dropdown-item" href="createBrew">Create a new brew</a>
                </div>
            </li>
            <li class="nav-item">
                <a href="thirsty" class="nav-link">I'm thirsty</a>
            </li>
            <c:choose>
                <c:when test="${userId != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="manageAccount">My Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Log Out</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="login">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="createUser">Sign up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>