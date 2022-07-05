/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isconseil.test_api.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Builder;

/**
 *
 * @author Nolhan
 */
@Builder
@Entity 
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date userBirthday;
    @Column(nullable = false)
    private String country;
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private String gender;
    private ERole userRole;

    public User(Long id, String username, String password, String email, Date userBirthday, String country, String phoneNumber, String gender, ERole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userBirthday = userBirthday;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userRole = userRole;
    }
    
     public User(String username, String password, String email, Date userBirthday, String country, String phoneNumber, String gender, ERole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userBirthday = userBirthday;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.userRole = userRole;
    }

    public User() {
    }
     
     

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ERole getUserRole() {
        return userRole;
    }

    public void setUserRole(ERole userRole) {
        this.userRole = userRole;
    }
    
    
}
