'use strict';

describe('login', function() {

  // Load the module that contains the `login` component before each test
  beforeEach(module('login'));

  // Test the controller
  describe('LoginController', function() {
    var $httpBackend, ctrl;
    var userData = {
      userId: 'test123',
      password: 'Password0'
    };

    beforeEach(inject(function($componentController, _$httpBackend_) {
      $httpBackend = _$httpBackend_;
      $httpBackend.expectPOST('http://localhost:8080/users/login')
                  .respond(200, "AUTHENTICATION_SUCCESSFUL");

      ctrl = $componentController('login');
    }));


   it('should login user test123/Password0', function() {
    jasmine.addCustomEqualityTester(angular.equals);

    ctrl.user = userData;

    ctrl.login();

    $httpBackend.flush();

    expect(ctrl.loginResponse).toBe("AUTHENTICATION_SUCCESSFUL");
   });

  });

});
