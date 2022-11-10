package lab.lab1.controller;

import lab.lab1.dto.*;
import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lab.lab1.service.ContinentService;
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
@RequestMapping("api/continents")
public class ContinentController {

    private CountryService countryService;

    private ContinentService continentService;

    @Autowired
    public ContinentController(CountryService countryService, ContinentService continentService) {
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetContinentsResponse> getContinents() {
        List<Continent> all = continentService.findAll();
        Function<Collection<Continent>, GetContinentsResponse> mapper = GetContinentsResponse.entityToDtoMapper();
        GetContinentsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetContinentResponse> getContinent(@PathVariable("id") String id) {
        return  continentService.find(id)
                .map(value -> ResponseEntity.ok(GetContinentResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postContinent(@RequestBody PostContinentRequest request) {
        continentService.find(request.getName()).ifPresent(s -> {
            throw new RuntimeException("Continent with this name already exists!");
        });
        Continent continent = PostContinentRequest
                .dtoToEntityMapper()
                .apply(request);
        continent = continentService.create(continent);
        return ResponseEntity.created(URI.create(String.format("continents/%s",continent.getName()))).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putContinent(@RequestBody PutContinentRequest request, @PathVariable("id") String id) {
        Optional<Continent> continent = continentService.find(id);
        if (continent.isPresent()) {
            PutContinentRequest.dtoToEntityUpdater().apply(continent.get(), request);
            continentService.update(continent.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/countries")
    public ResponseEntity<GetCountriesResponse> getContinentsCountries(@PathVariable("id") String id){
        Optional<Continent> continent = continentService.find(id);
        return continent
                .map(value -> ResponseEntity.ok(GetCountriesResponse.entityToDtoMapper().apply(value.getCountries())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable("id") String id){
        Optional<Continent> continent = continentService.find(id);
        if(continent.isPresent()){
            List<Country> countryList = continent.get().getCountries();
            for(Country country : countryList){
                countryService.delete(country.getName());
            }
            continentService.delete(continent.get().getName());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
