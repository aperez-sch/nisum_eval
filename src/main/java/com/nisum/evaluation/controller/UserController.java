package com.nisum.evaluation.controller;

import com.nisum.evaluation.domain.User;
import com.nisum.evaluation.exception.DBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.nisum.evaluation.service.IUserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getUsers() {
        log.info("REST request to get Users");
        try {
            List<User> results = userService.getUserList();
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "Error al consultar la lista de usuarios");
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
    }
    
    @GetMapping("/userById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        log.info("REST request to get User by id : {} ", id);
        try {
            User result = userService.getUserById(id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "Error al consultar usuario por id: " + id);
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
        
    }
    
    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("REST request to get User by email : {} ", email);
        try {
            User result = userService.getUserByEmail(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "Error al consultar usuario por email: " + email);
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
    }
    
    @PostMapping("/insertUser")
    public ResponseEntity<?> insertUser(@RequestBody User user) {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) { 
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "El elemento a insertar no puede poseer un ID");
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
        try {
            userService.insertUser(user);
            return ResponseEntity.ok(user);
        } catch (DBException e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("mensaje: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
        
    }
    
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception {
        log.debug("REST request to update User : {}", user);
        if (user.getId() == null) { 
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "El elemento a actualizar debe poseer un ID");
            return ResponseEntity.status(HttpStatus.FOUND).body(message);
        }
        try {
            userService.updateUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "Error al actualizar usuario: " + user);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        log.debug("REST request to delete User with id : {}", id);
        try {
            userService.deleteUser(id);
            return (ResponseEntity<?>) ResponseEntity.ok();
        } catch (Exception e) {
            Map<String, String> message = new HashMap<String,String>();
            message.put("Error: ", "Error al eliminar usuario: " + id);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        
    }
}
