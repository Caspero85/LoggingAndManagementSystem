package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import Project.VirtualBanking.services.PaymentInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PaymentInfoController {

    PaymentInfoService paymentInfoService;

    public PaymentInfoController(PaymentInfoService paymentInfoService) {
        this.paymentInfoService = paymentInfoService;
    }

    @PostMapping("/parent/{parentId}/payment-info")
    public ResponseEntity<?> savePaymentMethod(
            @RequestBody PaymentInfoDto paymentInfoDto,
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(paymentInfoService.savePaymentMethod(paymentInfoDto, parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info")
    public ResponseEntity<?> findAllPaymentMethods() {
        try {
            return ResponseEntity.ok(paymentInfoService.findAllPaymentMethods());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info/active")
    public ResponseEntity<?> findAllActivePaymentMethods() {
        try {
            return ResponseEntity.ok(paymentInfoService.findAllActivePaymentMethods());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info/{paymentMethodId}")
    public ResponseEntity<?> findPaymentMethodById(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentInfoService.findPaymentMethodById(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/payment-info/{paymentMethodId}/activate")
    public ResponseEntity<?> activatePaymentMethod(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentInfoService.activatePaymentMethod(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/payment-info/{paymentMethodId}/deactivate")
    public ResponseEntity<?> deactivatePaymentMethod(@PathVariable Integer paymentMethodId) {
        try {
            return ResponseEntity.ok(paymentInfoService.deactivatePaymentMethod(paymentMethodId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/payment-info/{paymentMethodId}/delete")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Integer paymentMethodId) {
        paymentInfoService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}
