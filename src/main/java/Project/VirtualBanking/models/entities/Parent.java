package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.OtherMethods.EncryptionMethods.EncryptionMethods;
import Project.VirtualBanking.models.dtos.ParentDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Integer parentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String emailAddress;

    private Boolean emailAddressVerified;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private LocalDateTime accountCreationDate;

    private Boolean active;

    private String details;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "keyId", referencedColumnName = "keyId")
    private EncryptionKey encryptionKey;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Child> children;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PaymentInfo> paymentsInfo;

    public Parent() {
    }

    public Parent(String name, String surname, LocalDate dateOfBirth, String emailAddress,
                  String phoneNumber, String password, String details, EncryptionKey encryptionKey) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.emailAddressVerified = false;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountCreationDate = LocalDateTime.now();
        this.active = true;
        this.details = details;
        this.encryptionKey = encryptionKey;
    }

    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return EncryptionMethods.decryptData(emailAddress, encryptionKey.getEncryptionKey());
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = EncryptionMethods.encryptData(emailAddress, encryptionKey.getEncryptionKey());
    }

    public Boolean isEmailAddressVerified() {
        return emailAddressVerified;
    }
    public void setEmailAddressVerified(Boolean emailAddressVerified) {
        this.emailAddressVerified = emailAddressVerified;
    }

    public String getPhoneNumber() {
        return EncryptionMethods.decryptData(phoneNumber, encryptionKey.getEncryptionKey());
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = EncryptionMethods.encryptData(phoneNumber, encryptionKey.getEncryptionKey());
    }

    public String getPassword() {
        return EncryptionMethods.decryptData(password, encryptionKey.getEncryptionKey());
    }
    public void setPassword(String password) {
        this.password = EncryptionMethods.encryptData(password, encryptionKey.getEncryptionKey());
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }
    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
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

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public List<Child> getChildren() {
        return children;
    }
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<PaymentInfo> getPaymentInfo() {
        return paymentsInfo;
    }
    public void setPaymentInfo(List<PaymentInfo> paymentsInfo) {
        this.paymentsInfo = paymentsInfo;
    }

    public static void encryptParent(Parent parent, ParentDto parentDto) {
        parent.setEmailAddress(parentDto.getEmailAddress());
        parent.setPhoneNumber(parentDto.getPhoneNumber());
        parent.setPassword(parentDto.getPassword());
    }

    public static Parent fromDto(ParentDto parentDto, EncryptionKey encryptionKey){
        Parent parent = new Parent(
                parentDto.getName(),
                parentDto.getSurname(),
                parentDto.getDateOfBirth(),
                parentDto.getEmailAddress(),
                parentDto.getPhoneNumber(),
                parentDto.getPassword(),
                parentDto.getDetails(),
                encryptionKey
        );
        encryptParent(parent, parentDto);
        return parent;
    }
}