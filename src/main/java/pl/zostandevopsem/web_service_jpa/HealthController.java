package pl.zostandevopsem.web_service_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<ApplicationStatus> checkHealth() {
        return ResponseEntity.ok(new ApplicationStatus("Application is working"));
    }
}
