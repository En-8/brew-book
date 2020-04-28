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
    <div class="form-group">
        <label for="name">Name your Brew:</label>
        <input type="text" name="name" id="name" class="form-control">
    </div>
    <div class="form-group">
        <label for="description">Describe your Brew:</label>
        <textarea name="description" id="description" class="form-control" placeholder="Add description of your brew here..."></textarea>
    </div>
    <div class="form-group">
        <label for="style-select">Style</label>
        <select class="form-control" name="style" id="style-select">
            <c:forEach var="style" items="${styles}">
                <option value="${style.id}">${style.name}</option>
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
    <div class="multi-select" id="form-fermentables">
        <div class="row">
            <div class="form-group col-5">
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
            <div class="col-3 form-group">
                <button class="form-control" id="remove-fermentable-0" value="remove-fermentable-0">Remove</button>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <button class="form-control" id="add-fermentable" type="button" value="add-fermentable">Add Fermentable</button>
            </div>
        </div>
    </div>
    <div class="multi-select" id="form-hops">
        <div class="row">
            <div class="form-group col-5">
                <label for="fermentable-select-0">Hops</label>
                <select class="form-control" name="hop-0" id="hop-select-0">
                    <c:forEach var="hop" items="${hops}">
                        <option value="${hop.id}">${hop.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-0">Amount</label>
                <input class="form-control" type="text" name="hop-amount-0" id="hop-amount-0">
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-units-0">Units</label>
                <select class="form-control" name="hop-amount-units-0" id="hop-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-0">Time</label>
                <input class="form-control" type="text" name="hop-time-0" id="hop-time-0">
            </div>
            <div class="form-group col-1">
                <label for="hop-amount-units-0">Time Units</label>
                <select class="form-control" name="hop-time-units-0" id="hop-time-units-0">
                    <option value="sec">sec</option>
                    <option value="min">min</option>
                    <option value="hr">hr</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="hop-method-0">Added in</label>
                <select class="form-control" name="hop-method-0" id="hop-method-0">
                    <option value="Boil">Boil</option>
                    <option value="Dry Hop">Dry Hop</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <button class="form-control" id="remove-hop-0" value="remove-hop-0">Remove</button>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <button class="form-control" id="add-hop" type="button" value="add-hop">Add Hop</button>
            </div>
        </div>
    </div>
    <div class="multi-select" id="form-misc">
        <div class="row">
            <div class="form-group col-5">
                <label for="misc-select-0">Hops</label>
                <select class="form-control" name="misc-0" id="misc-select-0">
                    <c:forEach var="misc" items="${otherIngredients}">
                        <option value="${misc.id}">${misc.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-0">Amount</label>
                <input class="form-control" type="text" name="misc-amount-0" id="misc-amount-0">
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-units-0">Units</label>
                <select class="form-control" name="misc-amount-units-0" id="misc-amount-units-0">
                    <option value="oz">oz</option>
                    <option value="lbs">lbs</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-0">Time</label>
                <input class="form-control" type="text" name="misc-time-0" id="misc-time-0">
            </div>
            <div class="form-group col-1">
                <label for="misc-amount-units-0">Time Units</label>
                <select class="form-control" name="misc-time-units-0" id="misc-time-units-0">
                    <option value="sec">sec</option>
                    <option value="min">min</option>
                    <option value="hr">hr</option>
                </select>
            </div>
            <div class="form-group col-1">
                <label for="misc-addition-0">Added in</label>
                <select class="form-control" name="misc-addition-0" id="misc-addition-0">
                    <option value="Boil">Boil</option>
                    <option value="Fermentation">Fermentation</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="col-2 form-group">
                <button class="form-control" id="remove-misc-0" value="remove-misc-0">Remove</button>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <button class="form-control" id="add-misc" type="button" value="add-misc">Add Hop</button>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="water-notes">Water Notes</label>
        <textarea class="form-control" name="water-notes" id="water-notes" placeholder="Add notes about your water..."></textarea>
    </div>
    <input type="submit" value="submit">
</form>

<c:import url="./templates/footer.jsp"/>
