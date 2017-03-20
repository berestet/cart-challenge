'use strict';

describe('cartDetail', function() {

  // Load the module that contains the `phoneDetail` component before each test
  beforeEach(module('cartDetail'));

  // Test the controller
  describe('CartDetailController', function() {
    var $httpBackend, ctrl;
    var phone1 = 
    {
      id: "motorola-xoom",
      selectedQuantity: 3
    };
    var phone2 = 
     {
      id: "nexus-s",
      selectedQuantity: 5
     };
    var userData = {
      userId: 'test123',
      password: 'Password0'
    };
     

    beforeEach(inject(function($componentController, _$httpBackend_, $routeParams) {
      $httpBackend = _$httpBackend_;
 
      $httpBackend.expectPOST('http://localhost:8080/phones/order')
                  .respond(200, "ADD_SUCCESSFUL");

      ctrl = $componentController('cartDetail');
    }));

    it('should submit order', function() {
     ctrl.phones.push(phone1);
     ctrl.phones.push(phone2);
     ctrl.currentUser = userData;

     ctrl.submitOrder();

     $httpBackend.flush();
     
     expect(ctrl.submitResponse).toBe("ADD_SUCCESSFUL");
    });

  });

});
