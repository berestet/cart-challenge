'use strict';

describe('phoneList', function() {

  // Load the module that contains the `phoneList` component before each test
  beforeEach(module('phoneList'));

  // Test the controller
  describe('PhoneListController', function() {
    var $httpBackend, ctrl, selectedItems;

    beforeEach(inject(function($componentController, _$httpBackend_, SelectedItems) {
      $httpBackend = _$httpBackend_;
      $httpBackend.expectGET('http://localhost:8080/phones/list')
                  .respond([{name: 'Nexus S'}, {name: 'Motorola DROID'}]);

      ctrl = $componentController('phoneList');

      selectedItems = SelectedItems.getAllItems();
    }));

    it('should add one order of 2 motorola-xoom phones to the list of SelectedItems', function() {
      ctrl.addToCart({"id": "motorola-xoom", "selectedQuantity": "2"});
      expect(selectedItems.length).toBe(1);
    });

    it('should create a `phones` property with 2 phones fetched with `$http`', function() {
      jasmine.addCustomEqualityTester(angular.equals);

      // expect(ctrl.phones).toEqual([]);

      $httpBackend.flush();
      expect(ctrl.phones).toEqual([{name: 'Nexus S'}, {name: 'Motorola DROID'}]);
    });

    it('should set a default value for the `orderProp` property', function() {
      expect(ctrl.orderProp).toBe('age');
    });


  });

});