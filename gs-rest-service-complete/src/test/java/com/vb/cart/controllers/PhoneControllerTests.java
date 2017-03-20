
package com.vb.cart.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vb.cart.model.Order;
import com.vb.cart.model.Phone;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PhoneControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    /**
     * Testing that GET /phones/list returns various phones from the database:
     * @throws Exception
     */
    public void listAllPhonesTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/phones/list",
                String.class)).contains("motorola-xoom-with-wi-fi").contains("motorola-atrix-4g").contains("dell-streak-7").doesNotContain("iPhone-7");
    }

    @Test
    /**
     * Testing that GET /phones/populate for user:
     * returns "Phone repository populated"
     * 
     * @throws Exception
     */
    public void populatePhoneRepositoryTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/phones/populate", String.class).contains("Phone repository populated"));   
        		
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
    	List<Phone> listOfPhones = new ArrayList<Phone>();
    	listOfPhones.add(new Phone("motorola-xoom-with-wi-fi", 2));
    	listOfPhones.add(new Phone("dell-streak-7", 1));
    	
    	Order order = new Order("test123", listOfPhones); 
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/phones/order", order, PhoneService.OrderStatus.class) == PhoneService.OrderStatus.ADD_SUCCESSFUL);           		
    }

    
}



