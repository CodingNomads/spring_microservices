package platofrom.codingnomads.co.moviecatalogservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import platofrom.codingnomads.co.moviecatalogservice.model.CatalogItem;
import platofrom.codingnomads.co.moviecatalogservice.model.Movie;
import platofrom.codingnomads.co.moviecatalogservice.model.UserRating;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
    
    private RestTemplate restTemplate;
    
    public CatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
        
        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());
        
    }
}
