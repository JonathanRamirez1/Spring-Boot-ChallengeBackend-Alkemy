package com.api.ChallengeBackend.web.payload.request;

import com.api.ChallengeBackend.services.MailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @Autowired
    MailService mailService;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}