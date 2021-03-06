package com.vb.cart.controllers;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.vb.cart.model.PendingOrder;
import com.vb.cart.model.User;

/**
*
* Business service for User entity related operations
*
*/
@Service
public class UserService {

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    
    public enum AuthenticationStatus {AUTHENTICATION_SUCCESSFUL, NO_MATCHING_USER, PASSWORD_DOES_NOT_MATCH};
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PendingOrderRepository pendingOrderRepository;

    
    /**
     * Try to find matching userId in the userRegistry and compare passwords
     *  
     * @param user
     * @return AuthenticationStatus
     */
    @Transactional(readOnly = true)
    public AuthenticationStatus authenticateUser(User user) {
       User matchingUser = userRepository.findByUserId(user.getUserId());
        if( null != matchingUser ) {
        	if( matchingUser.getPassword().equals(user.getPassword()) ) {
        		return AuthenticationStatus.AUTHENTICATION_SUCCESSFUL;
        	} else {
        		return AuthenticationStatus.PASSWORD_DOES_NOT_MATCH;
        	}
        } 
        	
        return AuthenticationStatus.NO_MATCHING_USER;
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
		return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUser(String userId) {
    	return userRepository.findByUserId(userId);
    }

    @Transactional
    public void savePendingOrder(PendingOrder order) {  		
		pendingOrderRepository.save(order);		
    }

    @Transactional(readOnly = true)
    public PendingOrder getPendingOrder(String userId) {
    	return pendingOrderRepository.findByUserId(userId);
    }
    
    @Transactional
    public void deletePendingOrder(String userId) {
    	PendingOrder order = pendingOrderRepository.findByUserId(userId);
    	if( null != order ) {
    		pendingOrderRepository.delete(order);
    	}
    }

    @Transactional
    public void deletePendingOrder(PendingOrder order) {
    	if( null != order ) {
    		pendingOrderRepository.delete(order);
    	}
    }
    
    @Transactional
    public String populateUsers() {
    	userRepository.deleteAll();

		// create initial set of users
    	userRepository.save(new User("test123", "Password0", "Generic", "Generic"));
    	userRepository.save(new User("alice@hotmail.com", "Password1", "Alice", "Bass"));
    	userRepository.save(new User("bob@hotmail.com", "Password2","Bob", "Smith"));
    	userRepository.save(new User("allan@hotmail.com", "Password3", "Allan", "Turing"));
		
        return "User repostiory is populated";
    }
    
	
	
}
