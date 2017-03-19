package com.vb.cart.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

import com.vb.cart.controllers.PhoneService.OrderStatus;
import com.vb.cart.model.Order;
import com.vb.cart.model.Phone;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final AtomicLong counter = new AtomicLong();
        
    @Autowired
    PhoneService phoneService;


    @RequestMapping(method = RequestMethod.GET, value = "/populate")
    public String populatePhoneRepository() {
    	return phoneService.populatePhoneRepository();
    }
   
    
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<Phone> listAllPhones() {
        return phoneService.listAllPhones();
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/list/{phoneId}")
    public Phone findPhone(@PathVariable String phoneId) {    	
        return phoneService.findPhone(phoneId);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public OrderStatus addOrder(@RequestBody Order order) {
    	return phoneService.addOrder(order);
    }    
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(Exception exc) {
	    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
