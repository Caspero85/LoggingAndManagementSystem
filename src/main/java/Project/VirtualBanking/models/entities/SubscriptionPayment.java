package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SubscriptionPayment {

    @Id
    @GeneratedValue
    private Integer subscriptionPaymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptionId", referencedColumnName = "subscriptionId")
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentInfoId", referencedColumnName = "paymentInfoId")
    private PaymentInfo paymentInfo;

    private Double amount;

    private Boolean paid;

    private LocalDateTime paymentDate;

    private String details;

    public SubscriptionPayment() {
    }

    public SubscriptionPayment(Subscription subscription, PaymentInfo paymentInfo, String details) {
        this.subscription = subscription;
        this.paymentInfo = paymentInfo;
        this.amount = subscription.getSubscriptionType().getPrice();
        this.paid = false;
        this.paymentDate = LocalDateTime.now();
        this.details = details;
    }

    public Integer getSubscriptionPaymentId() {
        return subscriptionPaymentId;
    }
    public void setSubscriptionPaymentId(Integer subscriptionPaymentId) {
        this.subscriptionPaymentId = subscriptionPaymentId;
    }

    public Subscription getSubscription() {
        return subscription;
    }
    public void setSubscription(Subscription subscriptionId) {
        this.subscription = subscriptionId;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
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

    public static SubscriptionPayment fromDto(
            SubscriptionPaymentDto subscriptionPaymentDto, Subscription subscription, PaymentInfo paymentInfo
    ) {
        return new SubscriptionPayment(
                subscription,
                paymentInfo,
                subscriptionPaymentDto.getDetails()
        );
    }
}
