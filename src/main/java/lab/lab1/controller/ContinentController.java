package lab.lab1.controller;

import lab.lab1.dto.*;
import lab.lab1.entity.Continent;
import lab.lab1.service.ContinentService;
import lab.lab1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<GetContinentsResponse> getContinents() {
        return ResponseEntity.ok(GetContinentsResponse.entityToDtoMapper().apply(continentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetContinentResponse> getContinent(@PathVariable("id") String id) {
        return  continentService.find(id)
                .map(value -> ResponseEntity.ok(GetContinentResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postContinent(@RequestBody PostContinentRequest request, UriComponentsBuilder builder) {
        continentService.find(request.getName()).ifPresent(s -> {
            throw new RuntimeException("Continent with this name already exists!");
        });
        Continent continent = PostContinentRequest
                .dtoToEntityMapper()
                .apply(request);
        continent = continentService.create(continent);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "continents", "{id}")
                        .buildAndExpand(continent.getName()).toUri())
                .build();
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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable("id") String id){
        Optional<Continent> continent = continentService.find(id);
        if(continent.isPresent()){
            continentService.delete(continent.get().getName());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
