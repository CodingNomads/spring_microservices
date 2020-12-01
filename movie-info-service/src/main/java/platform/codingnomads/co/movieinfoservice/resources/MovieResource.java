package platform.codingnomads.co.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import platform.codingnomads.co.movieinfoservice.models.Movie;
import platform.codingnomads.co.movieinfoservice.models.MovieInfoSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    
    @Value("${api.key}")
    private String apiKey;
    
    
    private RestTemplate restTemplate;
    
    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieInfoSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieInfoSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
        
    }
    
}
