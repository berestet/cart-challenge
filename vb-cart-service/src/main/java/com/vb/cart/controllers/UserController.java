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

import com.vb.cart.model.PendingOrder;
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

    /**
     * Get list of all registered users
     * 
     * @return List<User>
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.GET)
    public List<User> listUsers() {
		return userService.listUsers();
    }

    /**
     * Find if user is registered
     * 
     * @param userId
     * @return User (or null if not registered)
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User findUser(@PathVariable String userId) {
    	return userService.findUser(userId);
    }

    /**
     * Authenticate specified user
     * 
     * @param user
     * @return UserService.AuthenticationStatus
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public UserService.AuthenticationStatus authenticateUser(@RequestBody User user) {  		
		return userService.authenticateUser(user);		
    }

    /**
     * Store pending order, which includes userId
     * 
     * @param order
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.POST, value = "/pendingOrder")
    public void savePendingOrder(@RequestBody PendingOrder order) {  		
		userService.savePendingOrder(order);		
    }

    /**
     * Retrieve pending order for a specified userId (if any) and clear it in the database
     * 
     * @param userId
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/pendingOrder/{userId}")
    public PendingOrder getPendingOrder(@PathVariable String userId) {
    	PendingOrder order = userService.getPendingOrder(userId);
    	if( null != order ) {
    		userService.deletePendingOrder(order);
    	}
    	
    	return order;
    }

    /**
     * Clear pending order for specified userId (if any)
     * 
     * @param userId
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/pendingOrder")
    public void deletePendingOrder(@RequestBody String userId) {
    	userService.deletePendingOrder(userId);
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> errorHandler(Exception exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    
}
