package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.dtos.ParentWithChildrenDto;
import Project.VirtualBanking.models.dtos.ParentWithPaymentMethodsDto;
import Project.VirtualBanking.services.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/parent")
    public ResponseEntity<ParentDto> saveParent(@RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(parentService.saveParent(parentDto));
    }

    @GetMapping("/parents")
    public ResponseEntity<List<ParentDto>> findAllParents() {
        return ResponseEntity.ok(parentService.findAllParents());
    }

    @GetMapping("/parents/active")
    public ResponseEntity<List<ParentDto>> findAllActiveParents() {
        return ResponseEntity.ok(parentService.findAllActiveParents());
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<ParentDto> findParentById(@PathVariable Integer parentId) {
        return ResponseEntity.ok(parentService.findParentById(parentId));
    }

    @PutMapping("/parent/{parentId}/edit")
    public ResponseEntity<ParentDto> editParent(@PathVariable Integer parentId, @RequestBody ParentDto parentDto) {
        return ResponseEntity.ok(parentService.editParent(parentId, parentDto));
    }

    @PutMapping("/parent/{parentId}/activate")
    public ResponseEntity<ParentDto> activateParent(@PathVariable Integer parentId) {
        return ResponseEntity.ok(parentService.activateParent(parentId));
    }

    @PutMapping("/parent/{parentId}/deactivate")
    public ResponseEntity<ParentDto> deactivateParent(@PathVariable Integer parentId) {
        return ResponseEntity.ok(parentService.deactivateParent(parentId));
    }

    @PutMapping("/parent/{parentId}/verify-email")
    public ResponseEntity<ParentDto> verifyEmailAddress(@PathVariable Integer parentId) {
        return ResponseEntity.ok(parentService.verifyEmailAddress(parentId));
    }

    @DeleteMapping("/parent/{parentId}/delete")
    public ResponseEntity<Void> deleteParent(@PathVariable Integer parentId) {
        parentService.deleteParent(parentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Child related endpoints
     */

    @GetMapping("/parents-with-children")
    public ResponseEntity<List<ParentWithChildrenDto>> findAllParentsWithChildren() {
        return ResponseEntity.ok(parentService.findAllParentsWithChildren());
    }

    @GetMapping("/parents-with-children/active")
    public ResponseEntity<List<ParentWithChildrenDto>> findAllActiveParentsWithActiveChildren() {
        return ResponseEntity.ok(parentService.findAllActiveParentsWithActiveChildren());
    }

    @GetMapping("/parent/{parentId}/children")
    public ResponseEntity<ParentWithChildrenDto> findParentWithChildrenByParentId(@PathVariable Integer parentId) {
        return ResponseEntity.ok(parentService.findParentWithChildrenByParentId(parentId));
    }

    @GetMapping("/parent/{parentId}/children/active")
    public ResponseEntity<ParentWithChildrenDto> findParentWithActiveChildrenByParentId(
            @PathVariable Integer parentId
    ) {
        return ResponseEntity.ok(parentService.findParentWithActiveChildrenByParentId(parentId));
    }

    /**
     * PaymentMethod related endpoints
     */

    @GetMapping("/parents-with-payment-methods")
    public ResponseEntity<List<ParentWithPaymentMethodsDto>> findAllParentsWithPaymentMethods() {
        return ResponseEntity.ok(parentService.findAllParentsWithPaymentMethods());
    }

    @GetMapping("/parents-with-payment-methods/active")
    public ResponseEntity<List<ParentWithPaymentMethodsDto>> findAllActiveParentsWithActivePaymentMethods() {
        return ResponseEntity.ok(parentService.findAllActiveParentsWithActivePaymentMethods());
    }

    @GetMapping("/parent/{parentId}/payment-methods")
    public ResponseEntity<ParentWithPaymentMethodsDto> findParentWithPaymentMethodsByParentId(
            @PathVariable Integer parentId
    ) {
        return ResponseEntity.ok(parentService.findParentWithPaymentMethodsByParentId(parentId));
    }

    @GetMapping("/parent/{parentId}/payment-methods/active")
    public ResponseEntity<ParentWithPaymentMethodsDto> findParentWithActivePaymentMethodsByParentId(
            @PathVariable Integer parentId
    ) {
        return ResponseEntity.ok(parentService.findParentWithActivePaymentMethodsByParentId(parentId));
    }
}