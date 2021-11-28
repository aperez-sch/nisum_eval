package com.nisum.evaluation.controller;

import com.nisum.evaluation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.nisum.evaluation.service.IUserService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private IUserService userService;
    
    //@PostMapping("/login")
    @GetMapping("/")
    public String login(/*@RequestBody User user*/) {
        //TODO
        return "hola";
    }
    
    /**
     * get users without users disabled
     * @return list of users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        log.info("REST request to get Users");
        List<User> results = userService.getUserList();
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserByEmail(@PathVariable UUID id) {
        log.info("REST request to get User by id : {} ", id);
        User result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        log.info("REST request to get User by email : {} ", email);
        User result = userService.getUserByEmail(email);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/insertUser")
    public ResponseEntity<User> insertUser(@RequestBody User user) throws Exception {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) { throw new Exception("colocar excepcion"); }
        userService.insertUser(user);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        log.debug("REST request to update User : {}", user);
        if (user.getId() == null) { throw new Exception("colocar excepcion"); }
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity.BodyBuilder deleteUser(@PathVariable UUID id) {
        log.debug("REST request to delete User with id : {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok();
    }
}
