package com.nisum.evaluation.controller;

import com.nisum.evaluation.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nisum.evaluation.service.IUserService;

@RestController
@Slf4j
public class Controller {
    
    @Autowired
    private IUserService userService;
    
    @GetMapping("/")
    public String login() {
        log.info("Entering Login");
        User user = new User();
        user.setEmail("system@system.cl");
        User update = userService.getUserByEmail(user);
        //update.setEmail("system@system.cl");
        //userService.updateUser(update);
        userService.disableUser(update);
        
        //return userService.getUserByEmail(user).toString();
        return update.toString();
    }
}
