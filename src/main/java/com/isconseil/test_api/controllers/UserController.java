/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isconseil.test_api.controllers;


import com.isconseil.test_api.dtos.UserDto;
import com.isconseil.test_api.models.ERole;
import com.isconseil.test_api.models.User;
import com.isconseil.test_api.security.JwtResponse;
import com.isconseil.test_api.security.JwtUtils;
import com.isconseil.test_api.security.LoginRequest;
import com.isconseil.test_api.security.UserDetailsImpl;
import com.isconseil.test_api.services.UserService;
import com.isconseil.test_api.utils.MessageResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nolhan
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.get(0)));
    } 

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto signUpRequest) {
        if (signUpRequest.getCountry().equalsIgnoreCase("France")) {
            if (userService.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }
            if (userService.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }
            // Create new user's account
            
            User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getEmail(),
                    signUpRequest.getUserBirthday(),
                    signUpRequest.getCountry(),
                    signUpRequest.getPhoneNumber(),
                    signUpRequest.getGender(),
                    ERole.ADMIN //temporaly fixed
            );

            userService.save(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("You do not have registration riht"));
        }
    }

}
