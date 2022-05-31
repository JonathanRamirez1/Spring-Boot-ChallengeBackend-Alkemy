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

    @PostMapping("/add")
    public ResponseEntity<?> createGender(@Valid @RequestBody GenderDTO genderDTO) {
        if (genderService.isImage(genderDTO.getImage())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: esta imagen de genero ya existe"));
        }

        //TODO VERIFICAR PORQUE SE REVIENTA SI PONGO UN NAME IGUAL SIENDO QUE MO LE HE PUESTO UNIQUECONSTARAIN
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(genderService.addGender(genderDTO));
    }
}
