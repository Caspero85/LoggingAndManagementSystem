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
    public ResponseEntity<?> findAllChildren() {
        try {
            return ResponseEntity.ok(childService.findAllChildren());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/children/active")
    public ResponseEntity<?> findAllActiveChildren() {
        try {
            return ResponseEntity.ok(childService.findAllActiveChildren());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/child/{childId}")
    public ResponseEntity<?> findChildById(@PathVariable Integer childId) {
        try {
            return ResponseEntity.ok(childService.findChildById(childId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
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

    @PutMapping("/child/{childId}/verify-email")
    public ResponseEntity<?> verifyEmailAddress(@PathVariable Integer childId) {
        try {
            return ResponseEntity.ok(childService.verifyEmailAddress(childId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());

        }
    }

    @PutMapping("/child/{childId}/deactivate")
    public ResponseEntity<?> deactivateChildren(@PathVariable Integer childId) {
        try {
            return ResponseEntity.ok(childService.deactivateChildren(childId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/child/{childId}/delete")
    public ResponseEntity<?> deleteChild(@PathVariable Integer childId) {
        try {
            childService.deleteChild(childId);
            return ResponseEntity.ok("Dziecko zostało usunięte.");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * Parent related methods
     */

    @GetMapping("/child/{childId}/parent")
    public ResponseEntity<?> findParentByChildId(@PathVariable Integer childId) {
        try {
            return ResponseEntity.ok(childService.findParentByChildId(childId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
