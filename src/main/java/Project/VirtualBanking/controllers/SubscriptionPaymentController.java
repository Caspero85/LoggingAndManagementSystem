package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import Project.VirtualBanking.services.SubscriptionPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SubscriptionPaymentController {

    SubscriptionPaymentService subscriptionPaymentService;

    public SubscriptionPaymentController(SubscriptionPaymentService subscriptionPaymentService) {
        this.subscriptionPaymentService = subscriptionPaymentService;
    }

    @GetMapping("/subscription-payments")
    public ResponseEntity<?> findAllSubscriptionPayments() {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findAllSubscriptionPayments());
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscription-payment/{subscriptionPaymentId}")
    public ResponseEntity<?> findSubscriptionPaymentById(@PathVariable Integer subscriptionPaymentId) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findSubscriptionById(subscriptionPaymentId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscription-payment/{subscriptionPaymentId}/edit")
    public ResponseEntity<?> editSubscriptionPaymentDetails(
            @PathVariable Integer subscriptionPaymentId, @RequestBody SubscriptionPaymentDto subscriptionPaymentDto
    ) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.editSubscriptionPaymentDetails(subscriptionPaymentId, subscriptionPaymentDto));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/subscription-payment/{subscriptionPaymentId}/delete")
    public ResponseEntity<?> deleteSubscriptionPayment(@PathVariable Integer subscriptionPaymentId) {
        try {
            subscriptionPaymentService.deleteSubscriptionPayment(subscriptionPaymentId);
            return ResponseEntity.ok("Płatność została usunięta");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * Subscription related methods
     */

    @GetMapping("/subscription-payment/{subscriptionPaymentId}/subscription")
    public ResponseEntity<?> findSubscriptionBySubscriptionPaymentId(@PathVariable Integer subscriptionPaymentId) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findSubscriptionBySubscriptionPaymentId(subscriptionPaymentId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    /**
     * PaymentInfo related methods
     */

    @GetMapping("/subscription-payment/{subscriptionPaymentId}/payment-info")
    public ResponseEntity<?> findPaymentInfoBySubscriptionPaymentId(@PathVariable Integer subscriptionPaymentId) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findPaymentInfoBySubscriptionPaymentId(subscriptionPaymentId));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}

