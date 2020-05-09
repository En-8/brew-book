// Initialize counts for variable-count fields (e.g. fermentables, hops)
// We start with 1 because the form starts with 1 available field
let fermentableCount = 1;
let hopCount = 1;
let miscCount = 1;

// Option values for dropdowns
let fermentableOptions;
let hopOptions;
let miscOptions;

const init = () => {
    initializeFormValidation();

    // Add event listeners to "Add *" and "Delete" buttons
    document.querySelector("#add-fermentable").addEventListener("click", addFermentable);
    document.querySelector("#add-hop").addEventListener("click", addHop);
    document.querySelector("#add-misc").addEventListener("click", addMisc);

    // Add event listeners to "Remove *" buttons
    document.querySelector(".remove-fermentable").addEventListener('click', removeFermentable);
    document.querySelector(".remove-hop").addEventListener('click', removeHop);
    document.querySelector('.remove-misc').addEventListener('click', removeMisc);

    // Grab the option values from the dropdowns, so JS has access to them
    // even if that first value is removed prior to adding another value.
    fermentableOptions = [...document.querySelector("#fermentable-select-0").children];
    hopOptions = [...document.querySelector("#hop-select-0").children];
    miscOptions = [...document.querySelector("#misc-select-0").children];
}

const initializeFormValidation = () => {
    let form = document.querySelector(".needs-validation");
    form.addEventListener('submit', event => {
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated');
        form.insertBefore(constructValidationMessageElement(), form.children[form.children.length - 1])
    }, false);
}

const constructValidationMessageElement = () => {
    let msg = document.createElement('p');
    msg.classList.add("error-msg");
    msg.innerText = "Oops! Some things need fixing before we can create this brew.";
    return msg;
}

const addFermentable = () => {
    // get the fermentables group of form groups
    let formGroup = document.querySelector("#form-fermentables");

    // Create the form pieces
    let rowDiv = document.createElement("div");
    rowDiv.className = "form-row";
    rowDiv.id = `fermentable-row-${fermentableCount}`;

    let selectGroup = document.createElement("div");
    selectGroup.className = "form-group col-6"

    let select = document.createElement("select");
    select.required = true;
    select.className = "form-control";
    select.name = 'fermentable';
    select.id = `fermentable-select-${fermentableCount}`;

    let amountGroup = document.createElement("div");
    amountGroup.className = "form-group col-2";

    let amountInput = document.createElement("input");
    amountInput.type = "text";
    amountInput.className = "form-control";
    amountInput.name = 'fermentable-amount';
    amountInput.id = `fermentable-amount-${fermentableCount}`;
    amountInput.required = true;

    let unitsGroup = document.createElement("div");
    unitsGroup.className = "form-group col-2";

    let unitsSelect = document.createElement("select");
    unitsSelect.className = "form-control"
    unitsSelect.name = 'fermentable-amount-units';
    unitsSelect.id = `fermentable-amount-units-${fermentableCount}`;

    let unitOptionOz = document.createElement("option");
    unitOptionOz.value = "oz";
    unitOptionOz.innerText = "oz";

    let unitOptionLbs = document.createElement("option");
    unitOptionLbs.value = "lbs";
    unitOptionLbs.innerText = "lbs";

    let removeButtonGroup = document.createElement("div");
    removeButtonGroup.className = "form-group col-2";
    let removeButton = document.createElement("button");
    removeButton.addEventListener('click', removeFermentable);
    removeButton.className = "form-control";
    removeButton.id = `remove-fermentable-${fermentableCount}`;
    removeButton.value = `${fermentableCount}`;
    removeButton.type = 'button';
    removeButton.innerText = "Remove";

    // Add pieces to their groups
    fermentableOptions.forEach(fermentable => {
        let option = document.createElement("option");
        option.value = fermentable.value;
        option.innerText = fermentable.innerText;
        select.appendChild(option);
    });
    selectGroup.appendChild(select);

    amountGroup.appendChild(amountInput);

    unitsSelect.appendChild(unitOptionOz);
    unitsSelect.appendChild(unitOptionLbs);
    unitsGroup.appendChild(unitsSelect);

    removeButtonGroup.appendChild(removeButton);

    // Add groups to the row group
    rowDiv.appendChild(selectGroup);
    rowDiv.appendChild(amountGroup);
    rowDiv.appendChild(unitsGroup);
    rowDiv.appendChild(removeButtonGroup);

    // Add the row group to the meta form group
    formGroup.insertBefore(rowDiv, formGroup.children[formGroup.children.length - 1]);

    // Increment the counter
    ++fermentableCount;
}

const addHop = () => {
    let formGroup = document.querySelector("#form-hops");

    // Create the form pieces
    let rowDiv = document.createElement("div");
    rowDiv.className = "form-row";
    rowDiv.id = `hop-row-${hopCount}`;

    let selectGroup = document.createElement("div");
    selectGroup.className = "form-group col-5"

    let select = document.createElement("select");
    select.className = "form-control";
    select.name = 'hop';
    select.id = `hop-select-${hopCount}`;
    select.required = true;

    let amountGroup = document.createElement("div");
    amountGroup.className = "form-group col-1";

    let amountInput = document.createElement("input");
    amountInput.type = "text";
    amountInput.className = "form-control";
    amountInput.name = 'hop-amount';
    amountInput.id = `hop-amount-${hopCount}`;
    amountInput.required = true;

    let unitsGroup = document.createElement("div");
    unitsGroup.className = "form-group col-1";

    let unitsSelect = document.createElement("select");
    unitsSelect.className = "form-control"
    unitsSelect.name = 'hop-amount-units';
    unitsSelect.id = `hop-amount-units-${hopCount}`;

    let unitOptionOz = document.createElement("option");
    unitOptionOz.value = "oz";
    unitOptionOz.innerText = "oz";

    let unitOptionLbs = document.createElement("option");
    unitOptionLbs.value = "lbs";
    unitOptionLbs.innerText = "lbs";

    let timeGroup = document.createElement("div");
    timeGroup.className = "form-group col-1"

    let timeInput = document.createElement("input");
    timeInput.type = "text";
    timeInput.className = "form-control";
    timeInput.name = 'hop-time';
    timeInput.id = `hop-time-${hopCount}`;
    timeInput.required = true;

    let timeUnitsGroup = document.createElement("div");
    timeUnitsGroup.className = "form-group col-1";

    let timeUnitsSelect = document.createElement("select");
    timeUnitsSelect.className = "form-control"
    timeUnitsSelect.name = 'hop-time-units';
    timeUnitsSelect.id = `hop-time-units-${hopCount}`;

    let timeUnitOptionSeconds = document.createElement("option");
    timeUnitOptionSeconds.value = "sec";
    timeUnitOptionSeconds.innerText = "sec";

    let timeUnitOptionMinutes = document.createElement("option");
    timeUnitOptionMinutes.value = "min";
    timeUnitOptionMinutes.innerText = "min";

    let timeUnitOptionHours = document.createElement("option");
    timeUnitOptionHours.value = "hr";
    timeUnitOptionHours.innerText = "hr";

    let methodGroup = document.createElement("div");
    methodGroup.className = "form-group col-1"

    let methodSelect = document.createElement("select");
    methodSelect.className = "form-control"
    methodSelect.name = 'hop-method';
    methodSelect.id = `hop-method-${hopCount}`;

    let methodOptionBoil = document.createElement("option");
    methodOptionBoil.value = "Boil";
    methodOptionBoil.innerText = "Boil";

    let methodOptionDryHop = document.createElement("option");
    methodOptionDryHop.value = "Dry Hop";
    methodOptionDryHop.innerText = "Dry Hop";

    let removeButtonGroup = document.createElement("div");
    removeButtonGroup.className = "form-group col-2";
    let removeButton = document.createElement("button");
    removeButton.addEventListener('click', removeHop);
    removeButton.className = "form-control";
    removeButton.id = `remove-hop-${hopCount}`;
    removeButton.value = `${hopCount}`;
    removeButton.type = 'button';
    removeButton.innerText = "Remove";

    // Add pieces to their groups
    hopOptions.forEach(hop => {
        let option = document.createElement("option");
        option.value = hop.value;
        option.innerText = hop.innerText;
        select.appendChild(option);
    });
    selectGroup.appendChild(select);

    amountGroup.appendChild(amountInput);

    unitsSelect.appendChild(unitOptionOz);
    unitsSelect.appendChild(unitOptionLbs);
    unitsGroup.appendChild(unitsSelect);

    timeGroup.appendChild(timeInput);
    timeUnitsSelect.appendChild(timeUnitOptionSeconds);
    timeUnitsSelect.appendChild(timeUnitOptionMinutes);
    timeUnitsSelect.appendChild(timeUnitOptionHours);
    timeUnitsGroup.appendChild(timeUnitsSelect)

    methodSelect.appendChild(methodOptionBoil);
    methodSelect.appendChild(methodOptionDryHop);
    methodGroup.appendChild(methodSelect);

    removeButtonGroup.appendChild(removeButton);

    // Add groups to the row group
    rowDiv.appendChild(selectGroup);
    rowDiv.appendChild(amountGroup);
    rowDiv.appendChild(unitsGroup);
    rowDiv.appendChild(timeGroup);
    rowDiv.appendChild(timeUnitsGroup);
    rowDiv.appendChild(methodGroup);
    rowDiv.appendChild(removeButtonGroup);

    // Add the row group to the meta form group
    formGroup.insertBefore(rowDiv, formGroup.children[formGroup.children.length - 1]);

    // Increment the counter
    ++hopCount;
}

const addMisc = () => {
    let formGroup = document.querySelector("#form-misc");

    // Create the form pieces
    let rowDiv = document.createElement("div");
    rowDiv.className = "form-row";
    rowDiv.id = `misc-row-${miscCount}`;

    let selectGroup = document.createElement("div");
    selectGroup.className = "form-group col-5";

    let select = document.createElement("select");
    select.className = "form-control";
    select.name = 'misc';
    select.id = `misc-select-${miscCount}`;
    selec.required = true;

    let amountGroup = document.createElement("div");
    amountGroup.className = "form-group col-1";

    let amountInput = document.createElement("input");
    amountInput.type = "text";
    amountInput.className = "form-control";
    amountInput.name = 'misc-amount';
    amountInput.id = `misc-amount-${miscCount}`;
    amountInput.required = true;

    let unitsGroup = document.createElement("div");
    unitsGroup.className = "form-group col-1";

    let unitsSelect = document.createElement("select");
    unitsSelect.className = "form-control";
    unitsSelect.name = 'misc-amount-units';
    unitsSelect.id = `misc-amount-units-${miscCount}`;

    let unitOptionOz = document.createElement("option");
    unitOptionOz.value = "oz";
    unitOptionOz.innerText = "oz";

    let unitOptionLbs = document.createElement("option");
    unitOptionLbs.value = "lbs";
    unitOptionLbs.innerText = "lbs";

    let timeGroup = document.createElement("div");
    timeGroup.className = "form-group col-1";

    let timeInput = document.createElement("input");
    timeInput.type = "text";
    timeInput.className = "form-control";
    timeInput.name = 'misc-time';
    timeInput.id = `misc-time-${miscCount}`;
    timeInput.required = true;

    let timeUnitsGroup = document.createElement("div");
    timeUnitsGroup.className = "form-group col-1";

    let timeUnitsSelect = document.createElement("select");
    timeUnitsSelect.className = "form-control"
    timeUnitsSelect.name = 'misc-time-units';
    timeUnitsSelect.id = `misc-time-units-${miscCount}`;

    let timeUnitOptionSeconds = document.createElement("option");
    timeUnitOptionSeconds.value = "sec";
    timeUnitOptionSeconds.innerText = "sec";

    let timeUnitOptionMinutes = document.createElement("option");
    timeUnitOptionMinutes.value = "min";
    timeUnitOptionMinutes.innerText = "min";

    let timeUnitOptionHours = document.createElement("option");
    timeUnitOptionHours.value = "hr";
    timeUnitOptionHours.innerText = "hr";

    let methodGroup = document.createElement("div");
    methodGroup.className = "form-group col-1";

    let methodSelect = document.createElement("select");
    methodSelect.className = "form-control"
    methodSelect.name = 'misc-addition';
    methodSelect.id = `misc-addition-${miscCount}`;

    let methodOptionBoil = document.createElement("option");
    methodOptionBoil.value = "Boil";
    methodOptionBoil.innerText = "Boil";

    let methodOptionFerment = document.createElement("option");
    methodOptionFerment.value = "Fermentation";
    methodOptionFerment.innerText = "Fermentation";

    let methodOptionOther = document.createElement("option");
    methodOptionOther.value = "Other";
    methodOptionOther.innerText = "Other";

    let removeButtonGroup = document.createElement("div");
    removeButtonGroup.className = "form-group col-2";
    let removeButton = document.createElement("button");
    removeButton.addEventListener('click', removeMisc);
    removeButton.className = "form-control";
    removeButton.id = `remove-misc-${miscCount}`;
    removeButton.value = `${miscCount}`;
    removeButton.type = 'button';
    removeButton.innerText = "Remove";

    // Add pieces to their groups
    miscOptions.forEach(misc => {
        let option = document.createElement("option");
        option.value = misc.value;
        option.innerText = misc.innerText;
        select.appendChild(option);
    });
    selectGroup.appendChild(select);

    amountGroup.appendChild(amountInput);

    unitsSelect.appendChild(unitOptionOz);
    unitsSelect.appendChild(unitOptionLbs);
    unitsGroup.appendChild(unitsSelect);

    timeGroup.appendChild(timeInput);
    timeUnitsSelect.appendChild(timeUnitOptionSeconds);
    timeUnitsSelect.appendChild(timeUnitOptionMinutes);
    timeUnitsSelect.appendChild(timeUnitOptionHours);
    timeUnitsGroup.appendChild(timeUnitsSelect)

    methodSelect.appendChild(methodOptionBoil);
    methodSelect.appendChild(methodOptionFerment);
    methodSelect.appendChild(methodOptionOther);
    methodGroup.appendChild(methodSelect);

    removeButtonGroup.appendChild(removeButton);

    // Add groups to the row group
    rowDiv.appendChild(selectGroup);
    rowDiv.appendChild(amountGroup);
    rowDiv.appendChild(unitsGroup);
    rowDiv.appendChild(timeGroup);
    rowDiv.appendChild(timeUnitsGroup);
    rowDiv.appendChild(methodGroup);
    rowDiv.appendChild(removeButtonGroup);

    // Add the row group to the meta form group
    formGroup.insertBefore(rowDiv, formGroup.children[formGroup.children.length - 1]);

    // Increment the counter
    ++miscCount;
}

const removeFermentable = event => {
    event.target.parentElement.parentElement.remove();
    --fermentableCount;
}

const removeHop = event => {
    event.target.parentElement.parentElement.remove();
    --hopCount;
}

const removeMisc = event => {
    event.target.parentElement.parentElement.remove();
    --miscCount;
}

window.onload = init;