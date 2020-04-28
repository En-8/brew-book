// Initialize counts for variable-count fields (e.g. fermentables, hops)
// We start with 1 because the form starts with 1 available field
let fermentableCount = 1;

const init = () => {
    // Add event listeners to "Add *" and "Delete" buttons
    document.querySelector("#add-fermentable").addEventListener("click", addFermentable);
    // let removeFermentable = document.querySelector("#remove-fermentable").addEventListener("click", removeFermentable);
}

const addFermentable = () => {
    // Get the dropdown options from the first fermentable
    let fermentableOptions = [...document.querySelector("#fermentable-select-0").children]
    let formGroup = document.querySelector("#form-fermentables");

    // Create the form pieces
    let rowDiv = document.createElement("div");
    rowDiv.className = "row";

    let selectGroup = document.createElement("div");
    selectGroup.className = "form-group col-6"

    let select = document.createElement("select");
    select.className = "form-control"
    select.name = `fermentable-${fermentableCount}`
    select.id = `fermentable-select-${fermentableCount}`

    let amountGroup = document.createElement("div");
    amountGroup.className = "form-group col-2"

    let amountInput = document.createElement("input");
    amountInput.type = "text";
    amountInput.className = "form-control";
    amountInput.name = `fermentable-amount-${fermentableCount}`;
    amountInput.id = `fermentable-amount-${fermentableCount}`;

    let unitsGroup = document.createElement("div");
    unitsGroup.className = "form-group col-2";

    let unitsSelect = document.createElement("select");
    unitsSelect.className = "form-control"
    unitsSelect.name = `fermentable-amount-units-${fermentableCount}`;
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
    removeButton.className = "form-control";
    removeButton.id = `remove-fermentable-${fermentableCount}`;
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

window.onload = init;