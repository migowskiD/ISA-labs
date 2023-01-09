import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    createLinkButton
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayContinent();
    fetchAndDisplayCountries();
    addCountryButton();
});

function fetchAndDisplayCountries() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCountries(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/continents/' + getParameterByName('continent') + '/countries', true);
    xhttp.send();
}

function displayCountries(countries) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    countries.countries.forEach(country => {
        tableBody.appendChild(createTableRow(country));
    })
}

function createTableRow(country) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(country.name));
    tr.appendChild(createLinkCell('view', '../country_view/country_view.html?continent=' + getParameterByName('continent') + '&country=' + country.name));
    tr.appendChild(createLinkCell('edit', '../country_edit/country_edit.html?continent=' + getParameterByName('continent') + '&country=' + country.name));
    tr.appendChild(createButtonCell('delete', () => deleteCountry(country.name)));
    return tr;
}

function deleteCountry(country) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCountries();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/continents/' + getParameterByName('continent')
        + '/countries/' + country, true);
    xhttp.send();
}

function fetchAndDisplayContinent() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayContinent(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/continents/' + getParameterByName('continent'), true);
    xhttp.send();
}

function displayContinent(continent) {
    setTextNode('continent-name', continent.name);
    setTextNode('area', continent.area);
    setTextNode('populationDensity', continent.populationDensity);
}

function addCountryButton(){
    let addButtonDiv = document.getElementById('addButtonDiv');
    clearElementChildren(addButtonDiv);
    addButtonDiv.appendChild(createLinkButton('add', '../country_add/country_add.html?continent=' + getParameterByName('continent')));
}