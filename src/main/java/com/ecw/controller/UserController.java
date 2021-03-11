package com.ecw.controller;

import com.ecw.model.User;
import com.ecw.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.of(Optional.of(userService.getUserById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUserList(){
        return ResponseEntity.of(Optional.of(userService.listUsers()));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestBody User user){
        User newUser = new User(user.getFirstName(),user.getLastName());
        userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.remoteUser(id);
    }
}
