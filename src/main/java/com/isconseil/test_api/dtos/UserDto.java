/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isconseil.test_api.dtos;

import com.isconseil.test_api.models.ERole;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Nolhan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date userBirthday;
    private String country;
    private String phoneNumber;
    private String gender;
    private ERole userRole;
}
