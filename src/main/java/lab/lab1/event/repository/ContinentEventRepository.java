package lab.lab1.event.repository;

import lab.lab1.entity.Continent;
import lab.lab1.event.dto.PostContinentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ContinentEventRepository  {

    private RestTemplate restTemplate;

    @Autowired
    public ContinentEventRepository(@Value("${countries.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Continent continent) {
        restTemplate.delete("/continents/{id}", continent.getName());
    }

    public void create(Continent continent) {
        restTemplate.postForLocation("/continents", PostContinentRequest.entityToDtoMapper().apply(continent));
    }
}

