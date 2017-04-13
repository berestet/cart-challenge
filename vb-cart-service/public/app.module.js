'use strict';

// Define the `phonecatApp` module
angular.module('phonecatApp', [
  'ngAnimate',
  'ngRoute',
  'ngCookies',
  'core',
  'login',
  'cartDetail',
  'phoneDetail',
  'phoneList',
  'orderConfirmation'
])
.controller('PhonecatAppController', ['$http', '$scope', 'SelectedItems', function($http, $scope, SelectedItems) {  
    const pendingOrderUrl = '/users/pendingOrder';

	$scope.handleScopeDestroy = function handleScopeDestroy() {		
		console.log("Cart app scope destroy is called.");
		
		// @pendingOrder support
		return;

		var currentUser = SelectedItems.getCurrentUser();
		var phones = SelectedItems.getAllItems();
		
		// only attempt storing pending order, if all pending order data is in tact
		if( (null == currentUser) || (phones.length == 0) ) {
			return;
		}
		
	    var phonesListJSON = JSON.stringify(phones.map(function(phone) {
			return { id: phone.id, quantity: phone.selectedQuantity};		    	
			})
	    );		    
		
	    var orderJSON = "{ \"userId\": " + '\"' + currentUser + '\", \"phoneList\": ' +
				 	phonesListJSON + '}';
		    	
	    console.log( orderJSON );
	    
        $http({
            method: 'POST',
            url: pendingOrderUrl,
            data: orderJSON,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain, application/json"
            }
        })
        .then(function (response) {
        	if (response.status == 200) {
            	console.log("Successfully stored pending order.");
            } else {
            	console.log("Could not succesffuly store pending order.");
            }
        });
	};
	
}]);



