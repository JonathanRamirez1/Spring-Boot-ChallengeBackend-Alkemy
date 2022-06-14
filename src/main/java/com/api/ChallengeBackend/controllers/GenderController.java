package com.api.ChallengeBackend.controllers;

import com.api.ChallengeBackend.dto.GenderDTO;
import com.api.ChallengeBackend.payload.response.MessageResponse;
import com.api.ChallengeBackend.security.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @PostMapping("/movies/{idMovie}/gender/{idGender}")
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