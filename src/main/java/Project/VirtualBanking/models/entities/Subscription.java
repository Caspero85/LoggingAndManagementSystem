package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.models.dtos.SubscriptionDto;
import jakarta.persistence.*;

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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean active;

    private Boolean recursive;

    private String details;

    public Subscription() {
    }

    public Subscription(Child child, SubscriptionType subscriptionType, String details) {
        this.child = child;
        this.subscriptionType = subscriptionType;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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

    public static Subscription fromDto(SubscriptionDto subscriptionDto, Child child, SubscriptionType subscriptionType) {
        return new Subscription(
                child,
                subscriptionType,
                subscriptionDto.getDetails()
        );
    }
}
