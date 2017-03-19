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
	      self.phones = response.data;
	      console.error("Got back list of phones: " + self.phones);
	    });
	    
	    self.addToCart = function addToCart(phone) {
	          console.error("Add to cart: " + phone.selectedQuantity + " of " + phone.id);	          
	          SelectedItems.addItem(phone);
	          
	          self.addItemMessageVisibility = "block";
	          self.addItemMessage = phone.selectedQuantity + '  ' + phone.name;
	    }
        
	  }]
	});


