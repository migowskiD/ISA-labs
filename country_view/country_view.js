import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayCountry();
});


function fetchAndDisplayCountry() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCountry(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/continents/' + getParameterByName('continent') + '/countries/' + getParameterByName('country'), true);
    xhttp.send();
}

function displayCountry(country) {
    setTextNode('country-name', country.name);
    setTextNode('capitalCity', country.capitalCity);
    setTextNode('perCapitaGDP', country.perCapitaGDP);
    setTextNode('continent', country.continent);
}