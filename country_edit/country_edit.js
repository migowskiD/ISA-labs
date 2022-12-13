import {getParameterByName, setTextNode} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayCountry();
});

function fetchAndDisplayCountry() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if(key === "name"){
                    setTextNode(key,value)
                }
                else if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/continents/' + getParameterByName('continent') + '/countries/' + getParameterByName('country'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCountry();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/continents/' + getParameterByName('continent') + '/countries/' + getParameterByName('country'), true);

    const request = {
        'capitalCity': document.getElementById('capitalCity').value,
        'perCapitaGDP': parseInt(document.getElementById('perCapitaGDP').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}