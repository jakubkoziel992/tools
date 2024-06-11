package pl.zostandevopsem.web_service_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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

    @GetMapping("tools/{id}")
    public ResponseEntity<Tool> getUser(@PathVariable Integer id ){
        return toolRepository.findById(id)
                 .map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PutMapping("tools/{id}")
    public ResponseEntity<Tool> updateTool(@PathVariable Integer id, @RequestBody Tool tool){
        return toolRepository.findById(id)
                .map(existingTool -> {
                    existingTool.setToolName(tool.getToolName());
                    existingTool.setDescription(tool.getDescription());
                    existingTool.setStartDate(tool.getStartDate());

                    return toolRepository.save(existingTool);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
