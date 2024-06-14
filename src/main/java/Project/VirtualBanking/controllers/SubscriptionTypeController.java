package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.SubscriptionTypeDto;
import Project.VirtualBanking.services.SubscriptionTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SubscriptionTypeController {

    SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @PostMapping("/subscription-type")
    public ResponseEntity<?> saveSubscriptionType(@RequestBody SubscriptionTypeDto subscriptionTypeDto) {
        try {
            return ResponseEntity.ok(subscriptionTypeService.saveSubscriptionType(subscriptionTypeDto));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscription-types")
    public ResponseEntity<?> findAllSubscriptionTypes() {
        try {
            return ResponseEntity.ok(subscriptionTypeService.findAllSubscriptionTypes());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscription-types/active")
    public ResponseEntity<?> findAllActiveSubscriptionTypes() {
        try {
            return ResponseEntity.ok(subscriptionTypeService.findAllActiveSubscriptionTypes());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscription-type/{subscriptionTypeId}")
    public ResponseEntity<?> findSubscriptionTypeById(@PathVariable Integer subscriptionTypeId) {
        try {
            return ResponseEntity.ok(subscriptionTypeService.findSubscriptionTypeById(subscriptionTypeId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription-type/{subscriptionTypeId}/edit")
    public ResponseEntity<?> editSubscriptionTypeDetails(@PathVariable Integer subscriptionTypeId, @RequestBody SubscriptionTypeDto subscriptionTypeDto) {
        try {
            return ResponseEntity.ok(subscriptionTypeService.editSubscriptionTypeDetails(subscriptionTypeId, subscriptionTypeDto));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription-type/{subscriptionTypeId}/deactivate")
    public ResponseEntity<?> deactivateSubscriptionType(@PathVariable Integer subscriptionTypeId) {
        try {
            return ResponseEntity.ok(subscriptionTypeService.deactivateSubscriptionType(subscriptionTypeId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/subscription-type/{subscriptionTypeId}/delete")
    public ResponseEntity<Void> deleteSubscriptionType(@PathVariable Integer subscriptionTypeId) {
        subscriptionTypeService.deleteSubscriptionType(subscriptionTypeId);
        return ResponseEntity.noContent().build();
    }
}
