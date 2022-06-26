package com.api.ChallengeBackend.web.controllers;

import com.api.ChallengeBackend.domain.dto.MovieDTO;
import com.api.ChallengeBackend.domain.models.Gender;
import com.api.ChallengeBackend.domain.models.Movie;
import com.api.ChallengeBackend.web.payload.response.MessageResponse;
import com.api.ChallengeBackend.web.payload.response.MovieResponse;
import com.api.ChallengeBackend.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    HttpServletRequest requestMovie;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie) {
        if (movieService.isImage(movie.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen de pelicula ya esta en uso"));
        }
        if (movieService.isTitle(movie.getTitle())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: este titulo de pelicula ya esta en uso"));
        }
        try {
            if (movie.getQualification() >= 1 && movie.getQualification() <= 5) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(movieService.saveMovie(movie));
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: la calificación debe estar entre 1 y 5"));
            }
        } catch (Exception exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La imagen y/o la historia de personaje ya existe"));
        }
    }

    /**
     * Muestra el detalle de la pelicula
     **/
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/details")
    public ResponseEntity<?> getMovies(@Valid @RequestParam(required = false, value = "idMovie") Integer idMovie) {
        try {
            if (idMovie != null) {
                Movie movies = movieService.findMovieById(idMovie);
                return ResponseEntity
                        .ok()
                        .body(movies);
            } else {
                return ResponseEntity
                        .ok()
                        .body(movieService.findMovies());
            }
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("La pelicula con id " + idMovie + " no existe"));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<?> filterMovies(@RequestParam(required = false, value = "title") String title,
                                          @RequestParam(required = false, value = "idGender") Long idGender,
                                          @RequestParam(required = false, value = "order") String order) {

        if (requestMovie.getQueryString() != null) {
            if (title != null) {
                return getMovieByTitle(title);
            } else if (idGender != null) {
                return getMovieByIdGender(idGender);
            } else if (order != null) {
                return getMovieByOrder(order);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Verifique que la solicitud este bien"));
            }
        }
        return ResponseEntity
                .ok()
                .body(movieService.findMovies());
    }

    /**
     * Busca una pelicula por nombre
     **/
    public ResponseEntity<?> getMovieByTitle(String title) {
        List<Movie> movieByName = movieService.findMovieByTitle(title);
        List<MovieResponse> movieResponse = new ArrayList<>();

        for (Movie movie : movieByName) {
            MovieResponse movieResponses = new MovieResponse(movie.getImage(), movie.getTitle(), movie.getTimeStamp());
            movieResponse.add(movieResponses);
        }

        if (!movieByName.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(movieResponse);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No encontrado: La pelicula con titulo " + title + " no existe"));
        }
    }

    /**
     * Obtiene las peliculas por idGender
     **/
    public ResponseEntity<?> getMovieByIdGender(Long idGender) {
        List<Movie> movies = movieService.findMovies();
        List<MovieResponse> movieResponse = new ArrayList<>();

        for (Movie movie : movies) {
            Iterator<Gender> genderIterator = movie.getGenders().iterator();

            if (genderIterator.hasNext()) {
                if (idGender.equals(genderIterator.next().getIdGender())) {
                    MovieResponse movieResponses = new MovieResponse(movie.getImage(), movie.getTitle(), movie.getTimeStamp());
                    movieResponse.add(movieResponses);
                }
            }
        }
        if (!movieResponse.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(movieResponse);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No encontrado: La pelicula con id de genero " + idGender + " no existe"));
        }
    }

    /**
     * Filtra y ordena las peliculas
     **/
    public ResponseEntity<?> getMovieByOrder(String order) {
        List<Movie> orderMovie = movieService.orderMovie(order);
        List<MovieResponse> movieResponse = new ArrayList<>();

        for (Movie movie : orderMovie) {
            MovieResponse movieResponses = new MovieResponse(movie.getImage(), movie.getTitle(), movie.getTimeStamp());
            movieResponse.add(movieResponses);
        }
        return ResponseEntity
                .ok()
                .body(movieResponse);
    }

    /**
     * Actualiza una pelicula por id
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idMovie}")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable(value = "idMovie") Integer idMovie) {
        try {
            Movie movieResponse = movieService.updateMovie(movieDTO, idMovie);
            return ResponseEntity
                    .ok()
                    .body(movieResponse);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No encontrado: La pelicula con id " + idMovie + " no existe para ser actualizada"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idMovie}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value = "idMovie") Integer idMovie) {
        try {
            movieService.deleteMovie(idMovie);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Correcto: La pelicula con id " + idMovie + " se ha eliminado"));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No encontrado: La pelicula con id " + idMovie + " no existe"));
        }
    }
}