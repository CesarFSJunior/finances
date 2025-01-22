package com.finances.finance.domain.controllers;

import com.finances.finance.domain.entities.user.*;
import com.finances.finance.errors.UserCreationError;
import com.finances.finance.infra.security.SecurityUtils;
import com.finances.finance.infra.security.TokenService;
import com.finances.finance.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    SecurityUtils securityUtils;

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserAuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new UserLoginResponseDTO(token));

    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto data) {

        if (userService.loadUserByUsername(data.email()) != null) throw new UserCreationError("User already exists");

        UserDto userDto = userService.register(data);

        return ResponseEntity.ok(userDto);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {

        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<UserDto> getMyUser() {

        User authenticatedUser = securityUtils.getAuthenticatedUser();
        return ResponseEntity.ok(new UserDto(authenticatedUser.getId(), authenticatedUser.getName(),
                authenticatedUser.getEmail(), authenticatedUser.getBirthday()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDto>> getAll() {

        List<UserDto> users = userService.getAll();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {

        User authenticatedUser = securityUtils.getAuthenticatedUser();
        if (authenticatedUser.getId() != id && authenticatedUser.getRole() != UserRole.ADMIN)
            return ResponseEntity.badRequest().build();

        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
