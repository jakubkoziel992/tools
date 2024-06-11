package pl.zostandevopsem.web_service_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping("tools")
    public ResponseEntity<Tool> addTool(@RequestBody Tool tool) {
        toolRepository.save(tool);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(tool.getId())
                .toUri();

        return ResponseEntity.created(location).body(tool);
    }
}
