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
    public ResponseEntity<?> savePaymentInfo(
            @RequestBody PaymentInfoDto paymentInfoDto,
            @PathVariable Integer parentId
    ) {
        try {
            return ResponseEntity.ok(paymentInfoService.savePaymentInfo(paymentInfoDto, parentId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info")
    public ResponseEntity<?> findAllPaymentsInfo() {
        try {
            return ResponseEntity.ok(paymentInfoService.findAllPaymentsInfo());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info/active")
    public ResponseEntity<?> findAllActivePaymentsInfo() {
        try {
            return ResponseEntity.ok(paymentInfoService.findAllActivePaymentsInfo());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/payment-info/{paymentInfoId}")
    public ResponseEntity<?> findPaymentInfoById(@PathVariable Integer paymentInfoId) {
        try {
            return ResponseEntity.ok(paymentInfoService.findPaymentsInfoById(paymentInfoId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/payment-info/{paymentInfoId}/deactivate")
    public ResponseEntity<?> deactivatePaymentInfo(@PathVariable Integer paymentInfoId) {
        try {
            return ResponseEntity.ok(paymentInfoService.deactivatePaymentInfo(paymentInfoId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/payment-info/{paymentInfoId}/delete")
    public ResponseEntity<Void> deletePaymentInfo(@PathVariable Integer paymentInfoId) {
        paymentInfoService.deletePaymentInfo(paymentInfoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Parent related methods
     */

    @GetMapping("/payment-info/{paymentInfoId}/parent")
    public ResponseEntity<?> findParentByPaymentInfoId(@PathVariable Integer paymentInfoId) {
        try {
            return ResponseEntity.ok(paymentInfoService.findParentByPaymentInfoId(paymentInfoId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
