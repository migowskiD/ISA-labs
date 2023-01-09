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

    private ContinentService continentService;

    @Autowired
    public ContinentController(CountryService countryService, ContinentService continentService) {
        this.continentService = continentService;
    }

    @PostMapping
    public ResponseEntity<Void> postContinent(@RequestBody PostContinentRequest request, UriComponentsBuilder builder) {
        Continent continent = PostContinentRequest
                .dtoToEntityMapper()
                .apply(request);
        continentService.create(continent);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "continents", "{id}")
                        .buildAndExpand(continent.getName()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteContinent(@PathVariable("id") String id){
        Optional<Continent> continent = continentService.find(id);
        if(continent.isPresent()){
            continentService.delete(continent.get());
            return ResponseEntity.accepted().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
