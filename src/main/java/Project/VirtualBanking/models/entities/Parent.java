package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.models.EncryptEntities.EncryptParent;
import Project.VirtualBanking.models.dtos.ParentDto;
import jakarta.persistence.*;

import java.time.LocalDate;
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

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private LocalDate accountCreationDate;

    private boolean active;

    private String details;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "keyID", referencedColumnName = "keyID")
    private EncryptionKey encryptionKey;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Child> children;

    public Parent() {
    }

    public Parent(String name, String surname, LocalDate dateOfBirth, String emailAddress,
                  String phoneNumber, String password, String details, EncryptionKey encryptionKey) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountCreationDate = LocalDate.now();
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
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }
    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public List<Child> getChildren() {
        return children;
    }
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this.encryptionKey = encryptionKey;
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
        EncryptParent.encryptParent(parent, parent.getEncryptionKey().getEncryptionKey());
        return parent;
    }
}