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
<form action="createBrew" method="post">
    <div class="multi-select">
        <div class="form-group">
            <label for="name" class="main-label">Name your Brew:</label>
            <input type="text" name="name" id="name" class="form-control">
        </div>
        <div class="form-group">
            <label for="description" class="main-label">Describe your Brew:</label>
            <textarea name="description" id="description" class="form-control" placeholder="Add description of your brew here..."></textarea>
        </div>
        <div class="form-group">
            <label for="style-select" class="main-label">Style</label>
            <select class="form-control" name="style" id="style-select">
                <c:forEach var="style" items="${styles}">
                    <option value="${style.id}">${style.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="multi-select">
        <div class="form-group">
            <label for="yeast-select" class="main-label">Yeast</label>
            <select class="form-control" name="yeast" id="yeast-select">
                <c:forEach var="yeast" items="${yeasts}">
                    <option value="${yeast.id}">${yeast.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="yeast-pitch-notes">Pitch Notes</label>
            <textarea class="form-control" name="yeast-pitch-notes" id="yeast-pitch-notes" placeholder="Add some notes about your yeast pitch conditions"></textarea>
        </div>
    </div>
    <div class="multi-select" id="form-fermentables">
        <h2 class="row main-label">Fermentables</h2>
        <div class="form-row" id="fermentable-row-0">
            <div class="form-group col-6">
                <label for="fermentable-select-0"> Select Fermentable</label>
                <select class="form-control" name="fermentable" id="fermentable-select-0">
                    <c:forEach var="fermentable" items="${fermentables}">
                        <option value="${fermentable.id}">${fermentable.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-2">
                <label for="fermentable-amount-0">Amount</label>
                <input class="form-control" type="text" name="fermentable-amount" id="fermentable-amount-0">
            </div>
            <div class="form-group col-2">
                <label for="fermentable-amount-units-0">Units</label>
                <select class="form-control" name="fermentable-amount-units" id="fermentable-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <label for="remove-fermentable-0">Remove</label>
                <button class="form-control" id="remove-fermentable-0" value="0" type="button">Remove</button>
            </div>
        </div>
        <div class="row button-form-row">
            <div class="col-5">
                <button class="form-control btn btn-secondary" id="add-fermentable" type="button" value="add-fermentable">Add Fermentable</button>
            </div>
        </div>
    </div>
    <div class="multi-select" id="form-hops">
        <h2 class="row main-label">Hops</h2>
        <div class="form-row" id="hop-row-0">
            <div class="form-group col-5">
                <label for="hop-select-0">Select Hop</label>
                <select class="form-control" name="hop" id="hop-select-0">
                    <c:forEach var="hop" items="${hops}">
                        <option value="${hop.id}">${hop.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-0">Amount</label>
                <input class="form-control" type="text" name="hop-amount" id="hop-amount-0">
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-units-0">Units</label>
                <select class="form-control" name="hop-amount-units" id="hop-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-time-0">Time</label>
                <input class="form-control" type="text" name="hop-time" id="hop-time-0">
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-units-0">Time Units</label>
                <select class="form-control" name="hop-time-units" id="hop-time-units-0">
                    <option value="sec">sec</option>
                    <option value="min">min</option>
                    <option value="hr">hr</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-method-0">Added in</label>
                <select class="form-control" name="hop-method" id="hop-method-0">
                    <option value="Boil">Boil</option>
                    <option value="Dry Hop">Dry Hop</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <label for="remove-hop-0">Remove</label>
                <button class="form-control" id="remove-hop-0" value="remove-hop">Remove</button>
            </div>
        </div>
        <div class="row button-form-row">
            <div class="col-5">
                <button class="form-control btn btn-secondary" id="add-hop" type="button" value="add-hop">Add Hop</button>
            </div>
        </div>
    </div>
    <div class="multi-select" id="form-misc">
        <h2 class="row main-label">Other Ingredients</h2>
        <div class="form-row" id="misc-row-0">
            <div class="form-group col-5">
                <label for="misc-select-0">Select Ingredient</label>
                <select class="form-control" name="misc" id="misc-select-0">
                    <c:forEach var="misc" items="${otherIngredients}">
                        <option value="${misc.id}">${misc.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-0">Amount</label>
                <input class="form-control" type="text" name="misc-amount" id="misc-amount-0">
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-units-0">Units</label>
                <select class="form-control" name="misc-amount-units" id="misc-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-time-0">Time</label>
                <input class="form-control" type="text" name="misc-time" id="misc-time-0">
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-units-0">Time Units</label>
                <select class="form-control" name="misc-time-units" id="misc-time-units-0">
                    <option value="sec">sec</option>
                    <option value="min">min</option>
                    <option value="hr">hr</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-addition-0">Added in</label>
                <select class="form-control" name="misc-addition" id="misc-addition-0">
                    <option value="Boil">Boil</option>
                    <option value="Fermentation">Fermentation</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <label for="remove-misc-0">Remove</label>
                <button class="form-control" id="remove-misc-0" value="remove-misc-0">Remove</button>
            </div>
        </div>
        <div class="row button-form-row">
            <div class="col-5">
                <button class="form-control btn btn-secondary" id="add-misc" type="button" value="add-misc">Add Other Ingredient</button>
            </div>
        </div>
    </div>
    <div class="multi-select">
        <div class="form-group">
            <label for="water-notes" class="main-label">Water Notes</label>
            <textarea class="form-control" name="water-notes" id="water-notes" placeholder="Add notes about your water..."></textarea>
        </div>
    </div>
    <div class="form-group">
        <input class="form-control btn btn-primary" type="submit" value="Save Brew">
    </div>
</form>

<c:import url="./templates/footer.jsp"/>
