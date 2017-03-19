package com.vb.cart.controllers;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vb.cart.model.Order;
import com.vb.cart.model.Phone;

/**
*
* Business service for User entity related operations
*
*/
@Service
public class PhoneService {
   
    public enum OrderStatus {ADD_SUCCESSFUL, ERROR_ORDER_ALREADY_EXISTS, ERROR_INVALID_ITEM, ERROR_INSUFFICIENT_QUANTITY, ERROR_UNABLE_TO_ADD, ERROR_USER_NOT_FOUND};
    public enum StockQuantity {SUFFICIENT, INSUFFICIENT, INVALID_ITEM};
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PhoneRepository phoneRepository;
    
    
    /**
     * Try to add Order to the OrderRepository
     * Decrease the total quantity of each item in the PhoneRepository by quantity in the order
     *  
     * @param Order
     * @return OrderStatus
     */
    @Transactional()
    public OrderStatus addOrder(Order order) {
    	// make sure that order is unique
    	if( (null != order.getOrderId()) && (null != orderRepository.findByOrderId(order.getOrderId())) ) {
    		return OrderStatus.ERROR_ORDER_ALREADY_EXISTS;
    	} else {
    		// Make sure we have sufficient inventory quantity for each phone in the order    		
    		if( StockQuantity.INSUFFICIENT == updateStockQuantity(order) ) {
    			return OrderStatus.ERROR_INSUFFICIENT_QUANTITY;
    		}
        		
    		// add order to the orderRepository		
    		if( null != orderRepository.save(order) ) {
    			return OrderStatus.ADD_SUCCESSFUL;
    		}
    	}
    	        	
        return OrderStatus.ERROR_UNABLE_TO_ADD;
    }

    @Transactional
	private StockQuantity updateStockQuantity(Order order) {
		Predicate <Phone> insufficientQuantity = phone -> {
			Phone phoneInStock = phoneRepository.findById(phone.getId());
			if( null == phoneInStock ) {
				return true;
			} else {
				if( phoneInStock.getQuantity() < phone.getQuantity() ) {
					return true;
				} else {
					return false;
				}
			}
		};
			
		// Make sure that each phone in the order has sufficient quantity in stock
		if( order.getPhoneList().stream().anyMatch(insufficientQuantity) ) {
			return StockQuantity.INSUFFICIENT;
		} else {
			// update quantity in stock based on the order
			order.getPhoneList().stream().forEach(phone -> {
				Phone phoneInStock = phoneRepository.findById(phone.getId());
				if( phoneInStock != null ) {
					phoneInStock.setQuantity(phoneInStock.getQuantity() - phone.getQuantity());
					phoneRepository.save(phoneInStock); 
				}
			});
		}
		
		return StockQuantity.SUFFICIENT;
	}

    @Transactional(readOnly = true)
    public List<Phone> listAllPhones() {
		return phoneRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Phone findPhone(String phoneId) {
    	return phoneRepository.findById(phoneId);
    }

    public String populatePhoneRepository() {
		phoneRepository.deleteAll();
		orderRepository.deleteAll();

		// populate phone repository
		phoneRepository.save(new Phone(0, 
								  "motorola-xoom-with-wi-fi", 
								  "img/phones/motorola-xoom-with-wi-fi.0.jpg", 
								  "Motorola XOOM\u2122 with Wi-Fi", 
								  "The Next, Next Generation\r\n\r\nExperience the future with Motorola XOOM with Wi-Fi, the world's first tablet powered by Android 3.0 (Honeycomb).",
								  53
						));

		phoneRepository.save(new Phone(1, 
								  "motorola-xoom", 
								  "img/phones/motorola-xoom.0.jpg", 
								  "MOTOROLA XOOM\u2122",
								  "The Next, Next Generation\n\nExperience the future with MOTOROLA XOOM, the world's first tablet powered by Android 3.0 (Honeycomb).",
								  26
						));

		phoneRepository.save(new Phone(2, 
				  				  "motorola-atrix-4g", 
				  				  "img/phones/motorola-atrix-4g.0.jpg", 
				  				  "MOTOROLA ATRIX\u2122 4G",
				  				  "MOTOROLA ATRIX 4G the world's most powerful smartphone.",
				  				  31
						));
		phoneRepository.save(new Phone(3, 
								  "dell-streak-7", 
								  "img/phones/dell-streak-7.0.jpg", 
								  "Dell Streak 7",
								  "Introducing Dell\u2122 Streak 7. Share photos, videos and movies together. It\u2019s small enough to carry around, big enough to gather around.",
								  18
						));
		phoneRepository.save(new Phone(4, 
								  "samsung-gem", 
								  "img/phones/samsung-gem.0.jpg", 
								  "Samsung Gem\u2122",
								  "The Samsung Gem\u2122 brings you everything that you would expect and more from a touch display smart phone \u2013 more apps, more features and a more affordable price.",
								  43
						));
		phoneRepository.save(new Phone(5, 
								  "dell-venue", 
								  "img/phones/dell-venue.0.jpg", 
								  "Dell Venue",
								  "The Dell Venue; Your Personal Express Lane to Everything.",
								  31
						));
		phoneRepository.save(new Phone(6, 
								  "nexus-s", 
								  "img/phones/nexus-s.0.jpg", 
								  "Nexus S",
								  "Fast just got faster with Nexus S. A pure Google experience, Nexus S is the first phone to run Gingerbread (Android 2.3), the fastest version of Android yet..",
								  63
						));
		phoneRepository.save(new Phone(7, 
								  "lg-axis", 
								  "img/phones/lg-axis.0.jpg", 
								  "LG Axis",
								  "Android Powered, Google Maps Navigation, 5 Customizable Home Screens.",
								  13
						));
		phoneRepository.save(new Phone(8, 
								  "samsung-galaxy-tab", 
								  "img/phones/samsung-galaxy-tab.0.jpg", 
								  "Samsung Galaxy Tab\u2122",
								  "Feel Free to Tab\u2122. The Samsung Galaxy Tab\u2122 brings you an ultra-mobile entertainment experience through its 7\u201d display, high-power processor and Adobe\u00ae Flash\u00ae Player compatibility.",
								  21
						));
		phoneRepository.save(new Phone(9, 
								  "samsung-showcase-a-galaxy-s-phone", 
								  "img/phones/samsung-showcase-a-galaxy-s-phone.0.jpg", 
								  "Samsung Showcase\u2122 a Galaxy S\u2122 phone",
								  "he Samsung Showcase\u2122 delivers a cinema quality experience like you\u2019ve never seen before. Its innovative 4\u201d touch display technology provides rich picture brilliance, even outdoors.",
								  42
						));

		
        return "Phone repository populated";
    }

	
	
}
