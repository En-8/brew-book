<%--
  Created by IntelliJ IDEA.
  User: nate
  Date: 3/8/20
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./templates/head.jsp"/>
<script src="${pageContext.request.contextPath}/js/createBrew.js"></script>
</head>
<body>
<c:import url="./templates/navbar.jsp"/>

<h1>This is where a user will create a brew</h1>
<form action="">
    <div class="form-group">
        <label for="style-select">Style</label>
        <select class="form-control" name="style" id="style-select">
            <c:forEach var="style" items="${styles}">
                <option value="${style.id}">${style.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="multi-select" id="form-fermentables">
        <div class="row">
            <div class="form-group col-6">
                <label for="fermentable-select-0">Fermentables</label>
                <select class="form-control" name="fermentable-0" id="fermentable-select-0">
                    <c:forEach var="fermentable" items="${fermentables}">
                        <option value="${fermentable.id}">${fermentable.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-2">
                <label for="fermentable-amount-0">Amount</label>
                <input class="form-control" type="text" name="fermentable-amount-0" id="fermentable-amount-0">
            </div>
            <div class="form-group col-2">
                <label for="fermentable-amount-units-0">Units</label>
                <select class="form-control" name="fermentable-amount-units-0" id="fermentable-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <button class="form-control" id="remove-fermentable-0" value="remove-fermentable-0">Remove</button>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <button class="form-control" id="add-fermentable" type="button" value="add-fermentable">Add Fermentable</button>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="hop-select">Hops</label>
        <select class="form-control" name="hop-1" id="hop-select">
            <c:forEach var="hop" items="${hops}">
                <option value="${hop.id}">${hop.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="yeast-select">Yeast</label>
        <select class="form-control" name="yeast" id="yeast-select">
            <c:forEach var="yeast" items="${yeasts}">
                <option value="${yeast.id}">${yeast.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="misc-select">Other Ingredients</label>
        <select class="form-control" name="misc-1" id="misc-select">
            <c:forEach var="ingredient" items="${otherIngredients}">
                <option value="${ingredient.id}">${ingredient.name}</option>
            </c:forEach>
        </select>
    </div>
</form>

<c:import url="./templates/footer.jsp"/>
