package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.SubscriptionType;

import java.time.LocalDateTime;

public class SubscriptionTypeDto {

    private Integer subscriptionTypeId;
    private String typeOfEducation;
    private Integer howManyMonths;
    private Double price;
    private LocalDateTime SubscriptionTypeCreationDate;
    private Boolean active;
    private String details;

    public SubscriptionTypeDto() {
    }

    public SubscriptionTypeDto(Integer subscriptionTypeId, String typeOfEducation, Integer howManyMonths, Double price,
                               LocalDateTime SubscriptionTypeCreationDate, Boolean active, String details) {
        this.subscriptionTypeId = subscriptionTypeId;
        this.typeOfEducation = typeOfEducation;
        this.howManyMonths = howManyMonths;
        this.price = price;
        this.SubscriptionTypeCreationDate = SubscriptionTypeCreationDate;
        this.active = active;
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
    public void setSubscriptionTypeCreationDate(LocalDateTime SubscriptionTypeCreationDate) {
        this.SubscriptionTypeCreationDate = SubscriptionTypeCreationDate;
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

    public static SubscriptionTypeDto fromEntity(SubscriptionType subscriptionType) {
        return new SubscriptionTypeDto(
                subscriptionType.getSubscriptionTypeId(),
                subscriptionType.getTypeOfEducation(),
                subscriptionType.getHowManyMonths(),
                subscriptionType.getPrice(),
                subscriptionType.getSubscriptionTypeCreationDate(),
                subscriptionType.isActive(),
                subscriptionType.getDetails()
        );
    }
}
