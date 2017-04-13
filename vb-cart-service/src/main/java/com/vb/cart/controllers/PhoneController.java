package com.vb.cart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
        
    @Autowired
    PhoneService phoneService;


    @RequestMapping(method = RequestMethod.GET, value = "/populate")
    public String populatePhoneRepository() {
    	return phoneService.populatePhoneRepository();
    }
   
    /**
     * Get list of all phones in the database
     * 
     * @return List<Phone>
     */
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public HttpEntity<List<Phone>> listAllPhones() {
        return new ResponseEntity<List<Phone>>(phoneService.listAllPhones(), HttpStatus.OK);
    }

    /**
     * Find phone by phoneId in the database
     * 
     * @param phoneId
     * @return Phone
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list/{phoneId}")
    public Phone findPhone(@PathVariable String phoneId) {    	
        return phoneService.findPhone(phoneId);
    }

    /**
     * Add a new order to the OrderRepository. 
     * orderId must be unique.
     * 
     * @param order
     * @return OrderStatus
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)    
    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public OrderStatus addOrder(@RequestBody Order order) {
    	if( order.getPhoneList().isEmpty() ) {
    		return OrderStatus.ERROR_INVALID_ITEM;
    	}
    	
    	return phoneService.addOrder(order);
    }    
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(Exception exc) {
	    return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
