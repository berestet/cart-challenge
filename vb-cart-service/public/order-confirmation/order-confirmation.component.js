'use strict';

// Register `orderConfirmation` component, along with its associated controller and template
angular.
	module('orderConfirmation').
	component('orderConfirmation', {
	  templateUrl: 'order-confirmation/order-confirmation.template.html',
	  controller: ['$http', '$routeParams','SelectedItems',
	    function CartDetailController($http, $routeParams, SelectedItems) {
		    const phoneListUrl = 'http://localhost:8080/index.html#!/phones';
		    
		    var OrderCompletionCode = { ADD_SUCCESSFUL: "ADD_SUCCESSFUL", 
		    							ERROR_INSUFFICIENT_QUANTITY: "ERROR_INSUFFICIENT_QUANTITY", 
		    							ERROR_INVALID_ITEM: "ERROR_INVALID_ITEM",
		    							ERROR_UNABLE_TO_ADD: "ERROR_UNABLE_TO_ADD"
		    						  }
		    
		    
			var self = this;			    
		    self.phones = SelectedItems.getAllItems();
		    self.currentUser = SelectedItems.getCurrentUser();
		    self.addItemMessageVisibility = "none";
		    self.addItemMessage = "";	
		    
		    // Make sure that order details are cleared
		    self.clearOrderDetails = function clearOrderDetails() {
		    	SelectedItems.clearSelectedItems();
			}

		    // Make sure that order details and current user are cleared
		    self.logout = function logout() {
		    	SelectedItems.clearSelectedItems();
		    	SelectedItems.clearCurrentUser();
			}
		    
	    }]
	});
	      	        
