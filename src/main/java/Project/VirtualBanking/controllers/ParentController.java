package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.services.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/parent")
    public ResponseEntity<?> saveParent(@RequestBody ParentDto parentDto) {
        try {
            return ResponseEntity.ok(parentService.saveParent(parentDto));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parents")
    public ResponseEntity<?> findAllParents() {
        try {
            return ResponseEntity.ok(parentService.findAllParents());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parents/active")
    public ResponseEntity<?> findAllActiveParents() {
        try {
            return ResponseEntity.ok(parentService.findAllActiveParents());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<?> findParentById(@PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(parentService.findParentById(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/parent/{parentId}/edit")
    public ResponseEntity<?> editParent(@RequestBody ParentDto parentDto, @PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(parentService.editParent(parentDto, parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/parent/{parentId}/verify-email")
    public ResponseEntity<?> verifyEmailAddress(@PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(parentService.verifyEmailAddress(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/parent/{parentId}/deactivate")
    public ResponseEntity<?> deactivateParent(@PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(parentService.deactivateParent(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/parent/{parentId}/delete")
    public ResponseEntity<?> deleteParent(@PathVariable Integer parentId) {
        try {
            parentService.deleteParent(parentId);
            return ResponseEntity.ok("Rodzic został usunięty");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * Child related endpoints
     */

    @GetMapping("/parents-with-children")
    public ResponseEntity<?> findAllParentsWithChildren() {
        try {
            return ResponseEntity.ok(parentService.findAllParentsWithChildren());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parents-with-children/active")
    public ResponseEntity<?> findAllActiveParentsWithActiveChildren() {
        try {
            return ResponseEntity.ok(parentService.findAllActiveParentsWithActiveChildren());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parent/{parentId}/children")
    public ResponseEntity<?> findParentWithChildrenByParentId(@PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(parentService.findParentWithChildrenByParentId(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parent/{parentId}/children/active")
    public ResponseEntity<?> findParentWithActiveChildrenByParentId(
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(parentService.findParentWithActiveChildrenByParentId(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * PaymentMethod related endpoints
     */

    @GetMapping("/parents-with-payments-info")
    public ResponseEntity<?> findAllParentsWithPaymentsInfo() {
        try {
            return ResponseEntity.ok(parentService.findAllParentsWithPaymentsInfo());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parents-with-payments-info/active")
    public ResponseEntity<?> findAllActiveParentsWithActivePaymentsInfo() {
        try {
            return ResponseEntity.ok(parentService.findAllActiveParentsWithActivePaymentsInfo());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parent/{parentId}/payments-Info")
    public ResponseEntity<?> findParentWithPaymentsInfoByParentId(
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(parentService.findParentWithPaymentsInfoByParentId(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/parent/{parentId}/payments-info/active")
    public ResponseEntity<?> findParentWithActivePaymentsInfoByParentId(
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(parentService.findParentWithActivePaymentsInfoByParentId(parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}