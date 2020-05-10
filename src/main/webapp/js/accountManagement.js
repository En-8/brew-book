let formOpen = false;

function init() {
    document.querySelector("#editAccountButton").addEventListener("click", toggleEditAccountForm);
}

function toggleEditAccountForm() {
    let formContainer = document.querySelector("#editAccountContainer");
    if (formOpen) {
        let children = [...formContainer.children];
        children.forEach(child => formContainer.removeChild(child));
        formOpen = false;
    } else {
        // Generate and configure form
        let form = document.createElement("form");
        form.action = "manageAccount"
        form.method = "post"

        // Generate username input
        let usernameGroup = document.createElement('div');
        usernameGroup.classList.add('form-group');
        let usernameLabel = document.createElement("label");
        usernameLabel.innerText = "New Username:";
        let usernameInput = document.createElement("input");
        usernameInput.placeholder = "Enter new username"
        usernameInput.type = "text";
        usernameInput.name = "username";
        usernameInput.classList.add("form-control");
        usernameGroup.appendChild(usernameLabel);
        usernameGroup.appendChild(usernameInput);

        // Generate email input
        let emailGroup = document.createElement('div');
        emailGroup.classList.add('form-group');
        let emailLabel = document.createElement("label");
        emailLabel.innerText = "New email:";
        let emailInput = document.createElement("input");
        emailInput.placeholder = "Enter new email";
        emailInput.type = "email";
        emailInput.name = "email";
        emailInput.classList.add("form-control");
        emailGroup.appendChild(emailLabel);
        emailGroup.appendChild(emailInput);

        // Generate form submit button
        let submit = document.createElement("input");
        submit.type = "submit";
        submit.value = "Update account";
        submit.className = "btn btn-primary";

        // Put it all together
        form.appendChild(usernameGroup);
        form.appendChild(emailGroup);
        form.appendChild(submit);
        formContainer.appendChild(form);

        formOpen = true;
    }
}

window.onload = init;