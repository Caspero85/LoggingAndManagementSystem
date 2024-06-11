package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import Project.VirtualBanking.services.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PaymentMethodController {

    PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/parent/{parentId}/payment-method")
    public ResponseEntity<?> savePaymentMethod(
            @RequestBody PaymentMethodDto paymentMethodDto,
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(paymentMethodService.savePaymentMethod(paymentMethodDto, parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-methods")
    public ResponseEntity<?> findAllPaymentMethods() {
        try {
            return ResponseEntity.ok(paymentMethodService.findAllPaymentMethods());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-methods/active")
    public ResponseEntity<?> findAllActivePaymentMethods() {
        try {
            return ResponseEntity.ok(paymentMethodService.findAllActivePaymentMethods());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<?> findPaymentMethodById(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentMethodService.findPaymentMethodById(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/payment-method/{paymentMethodId}/activate")
    public ResponseEntity<?> activatePaymentMethod(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentMethodService.activatePaymentMethod(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/payment-method/{paymentMethodId}/deactivate")
    public ResponseEntity<?> deactivatePaymentMethod(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentMethodService.deactivatePaymentMethod(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/payment-method/{paymentMethodId}/delete")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Integer paymentMethodId) {
        paymentMethodService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}
