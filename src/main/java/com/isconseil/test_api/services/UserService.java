/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.isconseil.test_api.services;


import com.isconseil.test_api.models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Nolhan
 */
public interface UserService {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    User save(User u);
    
    List<User> findAll();
}
