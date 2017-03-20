package com.vb.cart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vb.cart.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

	
    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/populate")
    public String populateUsers() {		
        return userService.populateUsers();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.GET)
    public List<User> listUsers() {
		return userService.listUsers();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User findUser(@PathVariable String userId) {
    	return userService.findUser(userId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public UserService.AuthenticationStatus authenticateUser(@RequestBody User user) {  		
		return userService.authenticateUser(user);		
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    
}
