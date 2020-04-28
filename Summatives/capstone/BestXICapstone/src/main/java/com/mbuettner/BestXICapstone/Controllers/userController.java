/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Services.MapValidationService;
import com.mbuettner.BestXICapstone.Services.userService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbuet
 */
@RestController
@RequestMapping("/bestxi/user")
@CrossOrigin
public class userController {

    @Autowired
    userService userService;

    @Autowired
    MapValidationService mapValidation;

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        User user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@Valid @RequestBody User user, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidation.MapValidation(result);
        if (errorMap != null) {
            return errorMap;
        }
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable int userid) {
        User user = userService.getUserById(userid);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/team/{teamid}")
    public ResponseEntity<?> getAllUsersByTeam(@PathVariable int teamid) {
        List<User> usersByTeam = userService.getAllUsersByTeam(teamid);
        return new ResponseEntity<List<User>>(usersByTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable int userid) {
        User user = userService.getUserById(userid);

        if (user != null) {
            userService.deleteUser(userid);

            return new ResponseEntity<String>("User '" + user.getUsername() + "' has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User not found.", HttpStatus.OK);
        }

    }
}
