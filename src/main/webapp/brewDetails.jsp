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

<div class="brew-container">
    <h1>${brew.brewName}</h1>
    <p class="field-name" id="style-name">${brew.style.name}</p>
    <p class="notes" id="brew-description">
        ${brew.description}
    </p>
    <div class="ingredient-container">
        <h2>Fermentables</h2>
        <table>
            <tr><th>Name</th><th>Amount</th><th>Units</th></tr>
            <c:forEach var="brewFermentable" items="${brew.brewFermentables}">
                <tr><td>${brewFermentable.fermentable.name}</td><td>${brewFermentable.amount}</td><td>${brewFermentable.unitOfMeasure}</td></tr>
            </c:forEach>
        </table>
    </div>
    <div class="ingredient-container">
        <h2>Hops</h2>
        <table>
            <tr><th>Name</th><th>Amount</th><th>Time</th><th>Method</th></tr>
            <c:forEach var="brewHop" items="${brew.brewHops}" >
                <tr><td>${brewHop.hop.name}</td><td>${brewHop.amount} ${brewHop.amountUnitOfMeasure}</td><td>${brewHop.timeInBrew} ${brewHop.timeUnitOfMeasure}</td><td>${brewHop.method}</td></tr>
            </c:forEach>
        </table>
    </div>
    <div class="ingredient-container">
        <h2>Other Ingredients</h2>
        <table>
            <tr><th>Name</th><th>Amount</th><th>Time</th><th>Method</th></tr>
            <c:forEach var="brewMisc" items="${brew.brewMiscSet}" >
                <tr><td>${brewMisc.misc.name}</td><td>${brewMisc.amount} ${brewMisc.amountUnitOfMeasure}</td><td>${brewMisc.timeInBrew} ${brewMisc.timeUnitOfMeasure}</td><td>${brewMisc.additionParameter}</td></tr>
            </c:forEach>
        </table>
    </div>
    <h2>Yeast</h2>
    <p class="field-name" id="yeast-name">
        ${brew.yeast.name}
    </p>
    <p class="notes" id="pitch-notes">
        ${brew.pitchNotes}
    </p>
    <h2>Water Notes</h2>
    <p class="notes" id="water-notes">
        ${brew.waterNotes}
    </p>
</div>

</body>
<c:import url="./templates/footer.jsp"/>
