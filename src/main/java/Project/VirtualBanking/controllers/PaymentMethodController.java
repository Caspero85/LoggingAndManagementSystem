package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import Project.VirtualBanking.services.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentMethodController {

    PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/parent/{parentId}/payment-method")
    public PaymentMethodDto savePaymentMethod(
            @RequestBody PaymentMethodDto paymentMethodDto,
            @PathVariable Integer parentId
    ) {
        return paymentMethodService.savePaymentMethod(paymentMethodDto, parentId);
    }

    @GetMapping("/payment-methods")
    public List<PaymentMethodDto> findAllPaymentMethods() {
        return paymentMethodService.findAllPaymentMethods();
    }

    @GetMapping("/payment-methods/active")
    public List<PaymentMethodDto> findAllActivePaymentMethods() {
        return paymentMethodService.findAllActivePaymentMethods();
    }

    @GetMapping("/payment-method/{paymentMethodId}")
    public PaymentMethodDto findPaymentMethodById(@PathVariable Integer paymentMethodId) {
        return paymentMethodService.findPaymentMethodById(paymentMethodId);
    }

    @PutMapping("/payment-method/{paymentMethodId}/edit")
    public PaymentMethodDto editPaymentMethod(
            @PathVariable Integer paymentMethodId,
            @RequestBody PaymentMethodDto paymentMethodDto
    ) {
        return paymentMethodService.editPaymentMethod(paymentMethodDto, paymentMethodId);
    }

    @PutMapping("/payment-method/{paymentMethodId}/activate")
    public PaymentMethodDto activatePaymentMethod(@PathVariable Integer paymentMethodId) {
        return paymentMethodService.activatePaymentMethod(paymentMethodId);
    }

    @PutMapping("/payment-method/{paymentMethodId}/deactivate")
    public PaymentMethodDto deactivatePaymentMethod(@PathVariable Integer paymentMethodId) {
        return paymentMethodService.deactivatePaymentMethod(paymentMethodId);
    }

    @DeleteMapping("/payment-method/{paymentMethodId}/delete")
    public void deletePaymentMethod(@PathVariable Integer paymentMethodId) {
        paymentMethodService.deletePaymentMethod(paymentMethodId);
    }
}
