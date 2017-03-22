'use strict';

// Register `phoneList` component, along with its associated controller and template
angular.
	module('phoneList').
	component('phoneList', {
	  templateUrl: 'phone-list/phone-list.template.html',
	  controller: ['$http', 'SelectedItems', function PhoneListController($http, SelectedItems) {
	    const listPhonesUrl = 'http://localhost:8080/phones/list';
	    
		var self = this;
	    self.orderProp = 'age';
	    self.currentUser = SelectedItems.getCurrentUser();
	    self.addItemMessageVisibility = "none";
	    self.addItemMessage = "";
		    
	    $http.get(listPhonesUrl).then(function(response) {
	    	if (response.status == 200) {
		      self.phones = response.data;
		      console.log("Got back a list of " + self.phones.length + " phones");
		      
		      // initialize selectedQuantity to '0' for each phone in the list
		      self.phones.forEach(function(phone){
		    	  phone.selectedQuantity = 0;
		      });
	    	} else {
	    		console.log("Could not succesffuly retrieve list of phones. Bad response status: " + response.status);
	    	}
	    });
	    
	    self.addToCart = function addToCart(phone) {
	          console.log("Add to cart: " + phone.selectedQuantity + " of " + phone.id);
	          self.addItemMessageVisibility = "none";
	          
	          if( (phone.selectedQuantity == null) || (phone.selectedQuantity <= 0) || (phone.selectedQuantity > phone.quantity) ) {
		          self.addItemMessage = "You specified invalid quantity of \"" + phone.name + "\". Please specify valid quantity then Add to your cart";
	          } else {	          
	        	  SelectedItems.addItem(phone);
		          self.addItemMessage = phone.selectedQuantity + '  \"' + phone.name + "\" phones have been added to your cart. Follow \"view cart\" link for complete cart contents.";
	          }
	          
	          self.addItemMessageVisibility = "block";
	    };
	    
	    // Make sure that order details and current user are cleared
	    self.logout = function logout() {
	    	SelectedItems.clearSelectedItems();
	    	SelectedItems.clearCurrentUser();
		};

		
	  }]
	});


