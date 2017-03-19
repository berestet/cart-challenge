'use strict';

// Register `cartDetail` component, along with its associated controller and template
angular.
	module('cartDetail').
	component('cartDetail', {
	  templateUrl: 'cart-detail/cart-detail.template.html',
	  controller: ['$http', '$routeParams','SelectedItems',
	    function CartDetailController($http, $routeParams, SelectedItems) {
		    const submitOrderUrl = 'http://localhost:8080/phones/order';
		    const phoneListUrl = 'http://localhost:8080/index.html#!/phones';
		    
		    var OrderCompletionCode = { ADD_SUCCESSFUL: "ADD_SUCCESSFUL", 
		    							ERROR_INSUFFICIENT_QUANTITY: "ERROR_INSUFFICIENT_QUANTITY", 
		    							ERROR_INVALID_ITEM: "ERROR_INVALID_ITEM",
		    							ERROR_UNABLE_TO_ADD: "ERROR_UNABLE_TO_ADD"
		    						  }
		    
		    
			var self = this;			    
		    self.phones = SelectedItems.getAllItems();
		    
		    self.removeFromCart = function removeFromCart(phone) {
			    console.error("Phones in my cart before removing " + phone.id + " is: " + self.phones);	
			    
			    var i = self.phones.indexOf(phone);
			    if( i != -1 ) {
			    	self.phones.splice(i, 1);
			    }
		    	
			    console.error("Phones in my cart after removing " + phone.id + " is: " + self.phones);	
			    
		    }

		    
		    // Process "Submit Order" action - call POST for "/phones/order"
		    self.submitOrder = function submitOrder() {
			    console.error("Phones in my cart: " + self.phones);			   
			    
			    var phonesListJSON = JSON.stringify(self.phones.map(function(phone) {
						return { id: phone.id, quantity: phone.selectedQuantity};		    	
			    	})
			    );		    
			    		
			    var orderJSON = "{ \"userId\": " + '\"' + SelectedItems.getCurrentUser() + '\", \"phoneList\": ' +
			    				 phonesListJSON + '}';
			    console.error( orderJSON );
			    
                $http({
                    method: 'POST',
                    url: submitOrderUrl,
                    data: orderJSON,
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "text/plain, application/json"
                    }
                })
                .then(function (response) {
                    if (response.status == 200) {
                    	console.error("Successful response details: " + response.data);
                    	switch( response.data ) {
	                    	case OrderCompletionCode.ERROR_INSUFFICIENT_QUANTITY:
	                    		console.log("Too many items requestd - not enough in stock.");
	                        	window.alert("Your order contained more items that we can have in stock at the moment. Please updated your order and resumbit.");
	                        	break;
	                        	
	                    	case OrderCompletionCode.ERROR_INVALID_ITEM:
	                    		console.log("Invalid item in the order.");
	                        	window.alert("Your order contained an items that we no longer carry. Please updated your order and resumbit.");
	                        	break;
	                        	
	                    	case OrderCompletionCode.ERROR_UNABLE_TO_ADD:
	                    		console.log("Couldn't add order to our Database.");
	                        	window.alert("Something went really wrong with your order. Please try resubmitting later.");
	                        	break; 
	                        	
	                    	case OrderCompletionCode.ADD_SUCCESSFUL:
	                    		console.log("Order sucessfully submitted for processing");
	                        	window.alert("Well done! Your order has been successfully submitted for fulfillment. Confirmation e-mail will be sent to you shortly.")
	                        	break;
	                	}

                    	SelectedItems.clearSelectedItems();
                    	window.location.replace(phoneListUrl);
                    }
                    else {
                    	console.error("Error response details: " + response.data);
                    	window.alert("Ooops, something went horribly wrong and your order couldn't be fullfiled. Please try again later.");
                    	window.location.replace(phoneListUrl);
                    }
                });
			}; // end submitOrder()
			
			
            self.preparePhonesDto = function preparePhonesDto(phones) {
            	return phones.map(function(phone) {
                    return {
                        id: phone.id,
                        age: phone.age,
                        imageUrl: phone.imageUrl,
                        name: phone.name,
                        quantity: phone.quantity
                    }
                });
            }; // end preparePhonesDto()
			
	    }]
	});
	      	        
