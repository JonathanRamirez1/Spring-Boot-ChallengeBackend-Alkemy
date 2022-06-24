package com.api.ChallengeBackend.services;

import com.api.ChallengeBackend.util.Constants;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${api.app.sendGrid}")
    private String sendGridApiKey;

    public String sendTextEmail(String email) throws IOException {
        //El correo electrónico del remitente debe ser el mismo que usamos para crear una verificación de remitente único en SendGrid
        Email from = new Email(Constants.SEND_GRID_SENDER_EMAIL);
        String subject = "Bienvenido";
        Email to = new Email(email);
        Content content = new Content("text/plain", "Te damos la bienvenida a la API con Spring Boot de Jonathan Javier Ramirez");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sendGrid = new SendGrid(sendGridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(Constants.SEND_GRID_ENDPOINT);
        request.setBody(mail.build());
        Response response = sendGrid.api(request);
        logger.info(response.getBody());
        return response.getBody();
    }
}