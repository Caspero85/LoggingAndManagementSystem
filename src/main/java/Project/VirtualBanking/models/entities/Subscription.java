package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.models.dtos.SubscriptionDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Subscription {

    @Id
    @GeneratedValue
    private Integer subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "childId", referencedColumnName = "childId")
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptionTypeId", referencedColumnName = "subscriptionTypeId")
    private SubscriptionType subscriptionType;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime subscriptionCreationDate;

    private Boolean active;

    private Boolean recursive;

    private String details;

    @OneToOne(mappedBy = "subscription", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private SubscriptionPayment subscriptionPayment;

    public Subscription() {
    }

    public Subscription(Child child, SubscriptionType subscriptionType, String details) {
        this.child = child;
        this.subscriptionType = subscriptionType;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusMonths(subscriptionType.getHowManyMonths());
        this.subscriptionCreationDate = LocalDateTime.now();
        this.active = false;
        this.recursive = false;
        this.details = details;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Child getChild() {
        return child;
    }
    public void setChild(Child child) {
        this.child = child;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getSubscriptionCreationDate() {
        return subscriptionCreationDate;
    }
    public void setSubscriptionCreationDate(LocalDateTime subscriptionCreationDate) {
        this.subscriptionCreationDate = subscriptionCreationDate;
    }

    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isRecursive() {
        return recursive;
    }
    public void setRecursive(Boolean recursive) {
        this.recursive = recursive;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public SubscriptionPayment getSubscriptionPayment() {
        return subscriptionPayment;
    }
    public void setSubscriptionPayment(SubscriptionPayment subscriptionPayment) {
        this.subscriptionPayment = subscriptionPayment;
    }

    public static Subscription fromDto(SubscriptionDto subscriptionDto, Child child, SubscriptionType subscriptionType) {
        return new Subscription(
                child,
                subscriptionType,
                subscriptionDto.getDetails()
        );
    }
}
