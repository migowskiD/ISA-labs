import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayContinents();
});

function fetchAndDisplayContinents() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayContinents(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/continents', true);
    xhttp.send();
}

function displayContinents(continents) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    continents.continents.forEach(continent => {
        tableBody.appendChild(createTableRow(continent));
    })
}

function createTableRow(continent) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(continent));
    tr.appendChild(createLinkCell('view', '../continent_view/continent_view.html?continent=' + continent));
    tr.appendChild(createLinkCell('edit', '../continent_edit/continent_edit.html?continent=' + continent));
    tr.appendChild(createButtonCell('delete', () => deleteContinent(continent)));
    return tr;
}

function deleteContinent(continent) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayContinents();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/continents/' + continent, true);
    xhttp.send();
}