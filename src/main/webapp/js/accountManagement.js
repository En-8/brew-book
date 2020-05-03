let formOpen = false;

function init() {
    document.querySelector("#editAccountButton").addEventListener("click", toggleEditAccountForm);
}

function toggleEditAccountForm() {
    formContainer = document.querySelector("#editAccountContainer");
    if (formOpen) {
        let children = [...formContainer.children];
        children.forEach(child => formContainer.removeChild(child));
        formOpen = false;
    } else {
        // Generate and configure form
        form = document.createElement("form");
        form.action = "manageAccount"
        form.method = "post"

        // Generate username input
        usernameLabel = document.createElement("label");
        usernameLabel.innerText = "New Username:";
        usernameInput = document.createElement("input");
        usernameInput.placeholder = "Enter new username"
        usernameInput.type = "text";
        usernameInput.name = "username"

        // Generate email input
        emailLabel = document.createElement("label");
        emailLabel.innerText = "New email:";
        emailInput = document.createElement("input");
        emailInput.placeholder = "Enter new email";
        emailInput.type = "email";
        emailInput.name = "email"

        // Generate form submit button
        submit = document.createElement("input");
        submit.type = "submit";
        submit.value = "Update account";
        submit.className = "btn btn-primary";

        // Put it all together
        form.appendChild(usernameLabel);
        form.appendChild(usernameInput);
        form.appendChild(emailLabel);
        form.appendChild(emailInput);
        form.appendChild(submit);
        formContainer.appendChild(form);

        formOpen = true;
    }
}

window.onload = init;