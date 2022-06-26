package com.api.ChallengeBackend.web.controllers;

import com.api.ChallengeBackend.domain.dto.GenderDTO;
import com.api.ChallengeBackend.web.payload.response.MessageResponse;
import com.api.ChallengeBackend.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/movies/{idMovie}/genders/{idGender}")
    public ResponseEntity<?> createGender(@PathVariable(value = "idMovie") Integer idMovie, @Valid @RequestBody GenderDTO genderDTO) {
        if (genderService.isName(genderDTO.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: este nombre de genero ya existe"));
        }
        if (genderService.isImage(genderDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen de genero ya existe"));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genderService.addGender(idMovie, genderDTO));
    }
}