const init = () => {
    document.querySelector(".delete-brew-button").addEventListener('click', deleteBrew);
}

const deleteBrew = event => {
    event.stopPropagation();
    let brewId = {id: event.target.value};
    fetch('http://localhost:8080/brew-book/deleteBrew', {
        method: 'DELETE',
        body: JSON.stringify(brewId),
        headers: {
            'Content-type': 'application/json'
        }
        })
        .then(response => {
            if (response.ok) {
                console.log("Successfully deleted brew");
            } else {
                console.log(response.status)
            }
        })
        .catch(err => console.error(err));

    event.target.parentElement.parentElement.parentElement.remove();
}

window.onload = init;