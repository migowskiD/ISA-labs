package lab.lab1.controller;

import lab.lab1.dto.GetCountriesResponse;
import lab.lab1.dto.GetCountryResponse;
import lab.lab1.dto.PostCountryRequest;
import lab.lab1.dto.PutCountryRequest;
import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lab.lab1.service.ContinentService;
import lab.lab1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/continents/{continent}/countries")
public class ContinentCountryController {

    private CountryService countryService;

    private ContinentService continentService;

    @Autowired
    public ContinentCountryController(CountryService countryService, ContinentService continentService) {
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @GetMapping
    public ResponseEntity<GetCountriesResponse> getCountries(@PathVariable("continent") String continentName) {
        Optional<Continent> continent = continentService.find(continentName);
        return continent.map(value -> ResponseEntity.ok(GetCountriesResponse.entityToDtoMapper().apply(countryService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCountryResponse> getCountry(@PathVariable("continent") String continentName, @PathVariable("id") String id) {
        return  countryService.find(id, continentName)
                .map(value -> ResponseEntity.ok(GetCountryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postCountry(@PathVariable("continent") String continentName, @RequestBody PostCountryRequest request, UriComponentsBuilder builder) {
        Optional<Continent> continent = continentService.find(continentName);
        if (continent.isPresent()) {
            countryService.find(request.getName(), continentName).ifPresent(s -> {
                throw new RuntimeException("Country with this name already exists!");
            });
            Country country = PostCountryRequest
                    .dtoToEntityMapper(continent::get)
                    .apply(request);
            country = countryService.create(country);
            return ResponseEntity.created(builder.pathSegment("api", "continents", "{continent}", "countries", "{id}")
                    .buildAndExpand(continent.get().getName(), country.getName()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//TO DO FROM HERE
    @PutMapping("{id}")
    public ResponseEntity<Void> putCountry(@PathVariable("continent") String continentName, @RequestBody PutCountryRequest request, @PathVariable("id") String id) {
        Optional<Country> country = countryService.find(id, continentName);
        if (country.isPresent()) {
            PutCountryRequest.dtoToEntityUpdater().apply(country.get(), request);
            countryService.update(country.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("continent") String continentName, @PathVariable("id") String id){
        Optional<Country> country = countryService.find(id, continentName);
        if(country.isPresent()){
            countryService.delete(country.get().getName());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
