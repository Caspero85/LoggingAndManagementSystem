package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.SubscriptionDto;
import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import Project.VirtualBanking.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
public class SubscriptionController {

    SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/child/{childId}/subscription-type/{subscriptionTypeId}/subscription")
    public ResponseEntity<?> saveSubscription(
            @PathVariable Integer childId, @PathVariable Integer subscriptionTypeId) {
        try {
            return ResponseEntity.ok(subscriptionService.saveSubscription(childId, subscriptionTypeId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<?> findAllSubscriptions() {
        try {
            return ResponseEntity.ok(subscriptionService.findAllSubscriptions());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscriptions/active")
    public ResponseEntity<?> findAllActiveSubscriptions() {
        try {
            return ResponseEntity.ok(subscriptionService.findAllActiveSubscriptions());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscriptions/recursive")
    public ResponseEntity<?> findAllRecursiveSubscriptions() {
        try {
            return ResponseEntity.ok(subscriptionService.findAllRecursiveSubscriptions());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<?> findSubscriptionById(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.findSubscriptionById(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription/{subscriptionId}/edit")
    public ResponseEntity<?> editSubscriptionDetails(
            @PathVariable Integer subscriptionId, @RequestBody SubscriptionDto subscriptionDto) {
        try {
            return ResponseEntity.ok(subscriptionService.editSubscriptionDetails(subscriptionId, subscriptionDto));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription/{subscriptionId}/recursive")
    public ResponseEntity<?> recursiveSubscription(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.recursiveSubscription(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription/{subscriptionId}/not-recursive")
    public ResponseEntity<?> notRecursiveSubscription(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.notRecursiveSubscription(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription/{subscriptionId}/deactivate")
    public ResponseEntity<?> deactivateSubscription(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.deactivateSubscription(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/subscription/{subscriptionId}/delete")
    public ResponseEntity<?> deleteSubscription(@PathVariable Integer subscriptionId) {
        try {
            subscriptionService.deleteSubscription(subscriptionId);
            return ResponseEntity.ok("Subskrypcja została usunięta");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * Child related methods
     */

    @GetMapping("/subscription/{subscriptionId}/child")
    public ResponseEntity<?> findChildBySubscriptionId(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.findChildBySubscriptionId(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * SubscriptionType related methods
     */

    @GetMapping("/subscription/{subscriptionId}/subscription-type")
    public ResponseEntity<?> findSubscriptionTypeBySubscriptionId(@PathVariable Integer subscriptionId) {
        try {
            return ResponseEntity.ok(subscriptionService.findSubscriptionTypeBySubscriptionId(subscriptionId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
