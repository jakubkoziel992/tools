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
        Tool savedTool = toolRepository.save(tool);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedTool.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedTool);
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


    @PatchMapping("tools/{id}")
    public ResponseEntity<Tool> partiallyUpdateTool(@PathVariable Integer id, @RequestBody Tool tool){
        return toolRepository.findById(id)
                .map(existingTool -> {
                    if (tool.getToolName() != null) existingTool.setToolName(tool.getToolName());
                    if (tool.getDescription() != null) existingTool.setDescription(tool.getDescription());
                    if (tool.getStartDate() != null) existingTool.setStartDate(tool.getStartDate());

                    return toolRepository.save(existingTool);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("tools/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Integer id) {
        if (!toolRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        toolRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
