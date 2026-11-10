package help.bac.avis.controleur;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class DiagnosticControleur {

    @GetMapping("/diagnostic/{composant}")
    public ResponseEntity<String> diagnostic(@PathVariable String composant) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:3000/" + composant;

        ResponseEntity<String> resultats = restTemplate.getForEntity(url, String.class);

        return ResponseEntity
                .status(HttpStatus.OK)
//                .headers(resultats.getHeaders())
                .body(resultats.getBody());
    }
}
