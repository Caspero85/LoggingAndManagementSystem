package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.models.dtos.SubscriptionTypeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class SubscriptionType {

    @Id
    @GeneratedValue
    private Integer subscriptionTypeId;

    @Column(nullable = false)
    private String typeOfEducation;

    @Column(nullable = false)
    private Integer howManyMonths;

    @Column(nullable = false)
    private Double price;

    private LocalDateTime SubscriptionTypeCreationDate;

    private Boolean active;

    private String details;

    public SubscriptionType() {
    }

    public SubscriptionType(String typeOfEducation, Integer howManyMonths, Double price, String details) {
        this.typeOfEducation = typeOfEducation;
        this.howManyMonths = howManyMonths;
        this.price = price;
        this.SubscriptionTypeCreationDate = LocalDateTime.now();
        this.active = true;
        this.details = details;
    }

    public Integer getSubscriptionTypeId() {
        return subscriptionTypeId;
    }
    public void setSubscriptionTypeId(Integer subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public String getTypeOfEducation() {
        return typeOfEducation;
    }
    public void setTypeOfEducation(String typeOfEducation) {
        this.typeOfEducation = typeOfEducation;
    }

    public Integer getHowManyMonths() {
        return howManyMonths;
    }
    public void setHowManyMonths(Integer howManyMonths) {
        this.howManyMonths = howManyMonths;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getSubscriptionTypeCreationDate() {
        return SubscriptionTypeCreationDate;
    }
    public void setSubscriptionTypeCreationDate(LocalDateTime subscriptionTypeCreationDate) {
        SubscriptionTypeCreationDate = subscriptionTypeCreationDate;
    }

    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public static SubscriptionType fromDto(SubscriptionTypeDto subscriptionTypeDto) {
        return new SubscriptionType(
                subscriptionTypeDto.getTypeOfEducation(),
                subscriptionTypeDto.getHowManyMonths(),
                subscriptionTypeDto.getPrice(),
                subscriptionTypeDto.getDetails()
        );
    }
}
