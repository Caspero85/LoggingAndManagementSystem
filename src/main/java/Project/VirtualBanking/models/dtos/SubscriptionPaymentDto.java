package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.SubscriptionPayment;

import java.time.LocalDateTime;

public class SubscriptionPaymentDto {

    private Integer subscriptionPaymentId;
    private Integer subscriptionId;
    private Integer paymentInfoId;
    private Double amount;
    private Boolean paid;
    private LocalDateTime paymentDate;
    private String details;

    public SubscriptionPaymentDto() {
    }

    public SubscriptionPaymentDto(Integer subscriptionPaymentId, Integer subscriptionId, Integer paymentInfoId,
                                  Double amount, Boolean paid, LocalDateTime paymentDate, String details) {
        this.subscriptionPaymentId = subscriptionPaymentId;
        this.subscriptionId = subscriptionId;
        this.paymentInfoId = paymentInfoId;
        this.amount = amount;
        this.paid = paid;
        this.paymentDate = paymentDate;
        this.details = details;
    }

    public Integer getSubscriptionPaymentId() {
        return subscriptionPaymentId;
    }
    public void setSubscriptionPaymentId(Integer subscriptionPaymentId) {
        this.subscriptionPaymentId = subscriptionPaymentId;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getPaymentInfoId() {
        return paymentInfoId;
    }
    public void setPaymentInfoId(Integer paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean isPaid() {
        return paid;
    }
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public static SubscriptionPaymentDto fromEntity(SubscriptionPayment subscriptionPayment) {
        return new SubscriptionPaymentDto(
                subscriptionPayment.getSubscriptionPaymentId(),
                subscriptionPayment.getSubscription().getSubscriptionId(),
                subscriptionPayment.getPaymentInfo().getPaymentInfoId(),
                subscriptionPayment.getAmount(),
                subscriptionPayment.isPaid(),
                subscriptionPayment.getPaymentDate(),
                subscriptionPayment.getDetails()
        );
    }
}
