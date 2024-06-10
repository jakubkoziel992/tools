package pl.zostandevopsem.web_service_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    private final ToolRepository toolRepository;

    public ToolController(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }


    @GetMapping("tools")
    public ResponseEntity<Iterable<Tool>> getAllTools() {
        Iterable<Tool> tools = toolRepository.findAll();

        return ResponseEntity.ok(tools);
    }
}
