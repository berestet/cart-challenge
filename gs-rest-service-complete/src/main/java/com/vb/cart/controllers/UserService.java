package com.vb.cart.controllers;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	
	
	
}
