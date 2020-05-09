<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 3/15/20
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>
<div class="large-banner">
    <div class="banner-content" id="landing-banner">
        <img src="${pageContext.request.contextPath}/images/cheers.jpg" alt="Cheers!">
        <div id="landing-banner-content">
           <h1>Cheers!<br>And welcome to BrewBook</h1>
            <p>
                ...A simple way to manage your homebrew beer recipes
            </p>
            <button id="banner-sign-up" class="btn btn-primary">Sign up to start brewing</button>
        </div>
    </div>
</div>
<div class="banner banner-colored">
    <div class="banner-content" id="about-banner">
        <h1>About BrewBook</h1>
        <p>
            BrewBook is a simple, no-frills tool to manage you repertoire of brewing recipes. With no limits on
            the number of brews you can create, you can store your entire collection, no matter how large. No more
            excel spreadsheets, no more recipes strewn across disparate digital tools and physical documents.
        </p>
        <p>And best of all...it's <em>always</em> free! No more pesky subscriptions</p>
    </div>
</div>
<div class="banner">
    <div class="banner-content" id="contact-banner">
        <div id="contact-banner-text">
            <h1>Got feedback?<br>We want to hear it.</h1>
        </div>
        <form action="">
            <div class="form-group">
                <label for="email">Your Email</label>
                <input class="form-control" type="email" id="email">
            </div>
            <div class="form-group">
                <textarea name="feedback" id="feedback" class="form-control" placeholder="Tell us what you think..."></textarea>
            </div>
            <button type="submit" class="form-control btn btn-primary">Submit feedback</button>
        </form>
    </div>
</div>
<c:import url="./templates/footer.jsp"/>
