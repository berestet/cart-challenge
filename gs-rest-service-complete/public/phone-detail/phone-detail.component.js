'use strict';

// Register `phoneDetail` component, along with its associated controller and template
angular.
	module('phoneDetail').
	component('phoneDetail', {
	  templateUrl: 'phone-detail/phone-detail.template.html',
	  controller: ['$http', '$routeParams','SelectedItems',
	    function PhoneDetailController($http, $routeParams, SelectedItems) {
		  const phoneDetailsUrlBase = 'phones/';
		  
	      var self = this;
	
	      self.setImage = function setImage(imageUrl) {
	        self.mainImageUrl = imageUrl;
	      };
	
	      $http.get(phoneDetailsUrlBase + $routeParams.phoneId + '.json').then(function(response) {
	        self.phone = response.data;
	        self.setImage(self.phone.images[0]);
	      });
	      
	        
	      

	    }
	  ]
	});
