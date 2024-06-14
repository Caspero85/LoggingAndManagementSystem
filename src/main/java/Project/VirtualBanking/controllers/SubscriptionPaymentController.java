package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import Project.VirtualBanking.services.SubscriptionPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SubscriptionPaymentController {

    SubscriptionPaymentService subscriptionPaymentService;

    public SubscriptionPaymentController(SubscriptionPaymentService subscriptionPaymentService) {
        this.subscriptionPaymentService = subscriptionPaymentService;
    }

    @GetMapping("/subscriptionPayments")
    public ResponseEntity<?> findAllSubscriptionPayments() {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findAllSubscriptionPayments());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/subscriptionPayment/{subscriptionPaymentId}")
    public ResponseEntity<?> findSubscriptionPaymentById(Integer subscriptionPaymentId) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.findSubscriptionById(subscriptionPaymentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/subscriptionPayment/{subscriptionPaymentId}/edit")
    public ResponseEntity<?> editSubscriptionPaymentDetails(Integer subscriptionPaymentId, SubscriptionPaymentDto subscriptionPaymentDto) {
        try {
            return ResponseEntity.ok(subscriptionPaymentService.editSubscriptionPaymentDetails(subscriptionPaymentId, subscriptionPaymentDto));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/subscriptionPayment/{subscriptionPaymentId}/delete")
    public ResponseEntity<?> deleteSubscriptionPayment(Integer subscriptionPaymentId) {
        try {
            subscriptionPaymentService.deleteSubscriptionPayment(subscriptionPaymentId);
            return ResponseEntity.ok("Płatność została usunięta");
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
