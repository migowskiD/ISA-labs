import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/continents/', true);

    const request = {
        'name': document.getElementById('name').value,
        'area': parseInt(document.getElementById('area').value),
        'populationDensity': parseFloat(document.getElementById('populationDensity').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}