<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 5/2/20
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>
<h1 id="brew-details-header">Brew Details</h1>
<div class="brew-container">
    <h1>${brew.brewName}</h1>
    <p class="field-name" id="style-name">${brew.style.name}</p>
    <c:if test="${brew.description}">
        <p class="notes" id="brew-description">
                ${brew.description}
        </p>
    </c:if>
    <c:if test="${brew.yeast}">
        <div class="ingredient-container">
            <h2>Yeast</h2>
            <p class="field-name" id="yeast-name">
                ${brew.yeast.name}
            </p>
            <c:if test="${brew.pitchNotes}">
                <p class="notes" id="pitch-notes">
                    <span class="notes-title">Pitch Notes:</span> ${brew.pitchNotes}
                </p>
            </c:if>
        </div>
    </c:if>
    <div class="ingredient-container">
        <h2>Fermentables</h2>
        <c:choose>
            <c:when test="${brew.brewFermentables.size() > 0}">
                <table class="table">
                    <tr><th>Name</th><th>Amount</th><th>Units</th></tr>
                    <c:forEach var="brewFermentable" items="${brew.brewFermentables}">
                        <tr><td>${brewFermentable.fermentable.name}</td><td>${brewFermentable.amount}</td><td>${brewFermentable.unitOfMeasure}</td></tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p class="ingredient-nothing-here">
                    This brew has no fermentables.
                </p>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="ingredient-container">
        <h2>Hops</h2>
        <c:choose>
            <c:when test="${brew.brewHops.size() > 0}">
                <table class="table">
                    <tr><th>Name</th><th>Amount</th><th>Time</th><th>Method</th></tr>
                    <c:forEach var="brewHop" items="${brew.brewHops}" >
                        <tr><td>${brewHop.hop.name}</td><td>${brewHop.amount} ${brewHop.amountUnitOfMeasure}</td><td>${brewHop.timeInBrew} ${brewHop.timeUnitOfMeasure}</td><td>${brewHop.method}</td></tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p class="ingredient-nothing-here">
                    This brew has no hops.
                </p>
            </c:otherwise>
        </c:choose>
    </div>
    <c:if test="${brew.brewMiscSet.size() > 0}">
        <div class="ingredient-container">
            <h2>Other Ingredients</h2>
            <table class="table">
                <tr><th>Name</th><th>Amount</th><th>Time</th><th>Method</th></tr>
                <c:forEach var="brewMisc" items="${brew.brewMiscSet}" >
                    <tr><td>${brewMisc.misc.name}</td><td>${brewMisc.amount} ${brewMisc.amountUnitOfMeasure}</td><td>${brewMisc.timeInBrew} ${brewMisc.timeUnitOfMeasure}</td><td>${brewMisc.additionParameter}</td></tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${brew.waterNotes.length() > 0}">
        <div class="ingredient-container">
            <h2>Water Notes</h2>
            <p class="notes" id="water-notes">
                ${brew.waterNotes}
            </p>
        </div>
    </c:if>
</div>

</body>
<c:import url="./templates/footer.jsp"/>
