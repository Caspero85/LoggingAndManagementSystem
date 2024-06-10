package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.services.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("parent/{parentId}/child")
    public ResponseEntity<?> saveChild(@RequestBody ChildDto childDto, @PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(childService.saveChild(childDto, parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/children")
    public ResponseEntity<List<ChildDto>> findAllChildren() {
        return ResponseEntity.ok(childService.findAllChildren());
    }

    @GetMapping("/children/active")
    public ResponseEntity<List<ChildDto>> findAllActiveChildren() {
        return ResponseEntity.ok(childService.findAllActiveChildren());
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<ChildDto> findChildById(@PathVariable Integer childId) {
        return ResponseEntity.ok(childService.findChildById(childId));
    }

    @PutMapping("/child/{childId}/edit")
    public ResponseEntity<?> editChildren(@PathVariable Integer childId, @RequestBody ChildDto childDto) {
        try {
            return ResponseEntity.ok(childService.editChildren(childDto, childId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/child/{childId}/activate")
    public ResponseEntity<ChildDto> activateChildren(@PathVariable Integer childId) {
        return ResponseEntity.ok(childService.activateChildren(childId));
    }

    @PutMapping("/child/{childId}/deactivate")
    public ResponseEntity<ChildDto> deactivateChildren(@PathVariable Integer childId) {
        return ResponseEntity.ok(childService.deactivateChildren(childId));
    }

    @PutMapping("/child/{childId}/verify-email")
    public ResponseEntity<ChildDto> verifyEmailAddress(@PathVariable Integer childId) {
        return ResponseEntity.ok(childService.verifyEmailAddress(childId));
    }

    @DeleteMapping("/child/{childId}/delete")
    public ResponseEntity<Void> deleteChild(@PathVariable Integer childId) {
        childService.deleteChild(childId);
        return ResponseEntity.noContent().build();
    }
}
