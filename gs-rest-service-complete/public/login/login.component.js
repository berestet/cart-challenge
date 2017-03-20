'use strict';

// Register `cartDetail` component, along with its associated controller and template
angular.
	module('login').
	component('login', {
	  templateUrl: 'login/login.template.html',
	  controller: ['$http', '$routeParams','SelectedItems',
	    function LoginController($http, $routeParams, SelectedItems) {
		    const loginUrl = 'http://localhost:8080/users/login';
		    const phoneListUrl = 'http://localhost:8080/index.html#!/phones';
		    var AuthenticationReturnCode = { SUCCESS: "AUTHENTICATION_SUCCESSFUL", 
		    							     NO_MATCHING_USER: "NO_MATCHING_USER", 
		    							     PASSWORD_DOES_NOT_MATCH: "PASSWORD_DOES_NOT_MATCH"
		    }
		    
			var self = this;
			var fieldWithFocus;
			
			self.user = {
					userId: '',
					password: ''
			}
			    
	        self.vm = {
	        		submitted: false,
	                errorMessages: []
	        };

	        self.focus = function (fieldName) {
	            fieldWithFocus = fieldName;
	        };

	        self.blur = function (fieldName) {
	            fieldWithFocus = undefined;
	        };

	        self.isMessagesVisible = function (fieldName) {
	            return fieldWithFocus === fieldName || self.vm.submitted;
	        };

	        self.preparePostData = function () {
	        	return "{ \"userId\": \"" + self.user.userId + "\", \"password\": \"" + self.user.password + "\" }";
	        }
	        
		    self.login = function login() {
		    	var postData = self.preparePostData();
			    console.log("Login Details are: " + postData);
			    console.log("Login URL: " + loginUrl);

                $http({
                    method: 'POST',
                    url: loginUrl,
                    data: postData,
				    headers: {
                        "Content-Type": "application/json"
                    }
                })
                .then(function (response) {
                	self.loginResponse = response.data; // for testing
                    if (response.status == 200) {
                    	switch( response.data ) {
                        	case AuthenticationReturnCode.NO_MATCHING_USER:
                        		console.log("Autentication Failure: " + authenticationReturnCode.NO_MATCHING_USER);
                        		return;
                        	case AuthenticationReturnCode.PASSWORD_DOES_NOT_MATCH:
                        		console.log("Autentication Failure: " + authenticationReturnCode.PASSWORD_DOES_NOT_MATCH);                        		
                        		return;
                        	case AuthenticationReturnCode.SUCCESS:
                            	console.log("Phone list URL: " + phoneListUrl);                            	
                            	SelectedItems.setCurrentUser(self.user.userId);
                            	window.location.replace(phoneListUrl);
                        		return;
                    	}
                    }                    	
                    else {
                    	console.log("Error response details: " + self.loginResponse);
                    }
                });
			}; // end submitOrder

	    }]
	});
	      	        
