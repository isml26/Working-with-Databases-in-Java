package com.example.demo.Movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    public List<Movie> listMovies(){
        return movieService.getMovies();
    }
    @GetMapping("{id}")
    public Movie getMovieId(@PathVariable("id") Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie){
        try {
            movieService.addNewMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully inserted movie");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") Integer id){
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.valueOf(202)).body("Successfully deleted movie");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
