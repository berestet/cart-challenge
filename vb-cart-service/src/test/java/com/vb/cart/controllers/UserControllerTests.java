
package com.vb.cart.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vb.cart.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    /**
     * Testing that GET /users/test123 returns user:
     * userId: "test123"
     * password: "Password0"
     * @throws Exception
     */
    public void findUserByUserIdTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users/test123",
                String.class)).contains("test123").contains("Password0").doesNotContain("Bob");
    }
    
    @Test
    /**
     * Testing that POST /users/login for user:
     * userId: "test123"
     * password: "Password0"
     * returns UserService.AuthenticationStatus.AUTHENTICATION_SUCCESSFUL
     * 
     * @throws Exception
     */
    public void authenticateValidUser() throws Exception {
    	User user = new User("test123", "Password0", "Tester", "Tester123");
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/users/login", user, UserService.AuthenticationStatus.class) == UserService.AuthenticationStatus.AUTHENTICATION_SUCCESSFUL);   
        		
    }

    @Test
    /**
     * Testing that POST /users/login for user:
     * userId: "test123"
     * password: "Password10"
     * returns UserService.AuthenticationStatus.PASSWORD_DOES_NOT_MATCH
     * 
     * @throws Exception
     */
    public void authenticateIncorrectPassord() throws Exception {
    	User user = new User("test123", "Password10", "Tester", "Tester123");
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/users/login", user, UserService.AuthenticationStatus.class) == UserService.AuthenticationStatus.PASSWORD_DOES_NOT_MATCH);   
        		
    }
 
    @Test
    /**
     * Testing that POST /users/login for user:
     * userId: "test666"
     * password: "Password6"
     * returns UserService.AuthenticationStatus.NO_MATCHING_USER
     * 
     * @throws Exception
     */
    public void authenticateIncorrectUser() throws Exception {
    	User user = new User("test666", "Password6", "Tester", "Tester666");
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/users/login", user, UserService.AuthenticationStatus.class) == UserService.AuthenticationStatus.NO_MATCHING_USER);   
        		
    }
     
    
}



