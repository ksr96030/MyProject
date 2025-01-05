package com.santhosh.myproject.model;

import com.santhosh.myproject.converter.ListToStringConverter;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String subscriptionType;
    private String email;
    @Convert(converter = ListToStringConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> address;
    private String userName;
    private String password;
    private int totalListed;
    private int totalActive;
    private int totalSold;
    private int loginCount;
    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate;

    // Timestamp for last modification, automatically updated on every update
    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalListed() {
        return totalListed;
    }

    public void setTotalListed(int totalListed) {
        this.totalListed = totalListed;
    }

    public int getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public void updateLoginInfo() {
        this.loginCount++; // Incrementing the login count by 1
        this.lastLogin = Timestamp.valueOf(LocalDateTime.now()); // Updating the last login time to the current time
    }

}
