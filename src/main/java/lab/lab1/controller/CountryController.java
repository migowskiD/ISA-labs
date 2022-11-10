package lab.lab1.controller;

import lab.lab1.dto.GetCountriesResponse;
import lab.lab1.dto.GetCountryResponse;
import lab.lab1.dto.PostCountryRequest;
import lab.lab1.dto.PutCountryRequest;
import lab.lab1.entity.Country;
import lab.lab1.service.ContinentService;
import lab.lab1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private CountryService countryService;

    private ContinentService continentService;

    @Autowired
    public CountryController(CountryService countryService, ContinentService continentService) {
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetCountriesResponse> getCountries() {
        List<Country> all = countryService.findAll();
        Function<Collection<Country>, GetCountriesResponse> mapper = GetCountriesResponse.entityToDtoMapper();
        GetCountriesResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCountryResponse> getCountry(@PathVariable("id") String id) {
        return  countryService.find(id)
                .map(value -> ResponseEntity.ok(GetCountryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postCountry(@RequestBody PostCountryRequest request) {
        countryService.find(request.getName()).ifPresent(s -> {
            throw new RuntimeException("Country with this name already exists!");
        });
        Country country = PostCountryRequest
                .dtoToEntityMapper(name -> continentService.find(name).orElseThrow())
                .apply(request);
        country = countryService.create(country);
        return ResponseEntity.created(URI.create(String.format("countries/%s",country.getName()))).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putCountry(@RequestBody PutCountryRequest request, @PathVariable("id") String id) {
        Optional<Country> country = countryService.find(id);
        if (country.isPresent()) {
            PutCountryRequest.dtoToEntityUpdater().apply(country.get(), request);
            countryService.update(country.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id){
        Optional<Country> country = countryService.find(id);
        if(country.isPresent()){
            countryService.delete(country.get().getName());
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
