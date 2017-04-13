'use strict';

angular.
  module('phonecatApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
      	when('/login', {
          template: '<login></login>'
        }).
        when('/phones', {
          template: '<phone-list></phone-list>'
        }).
        when('/order/details', {
          template: '<cart-detail></cart-detail>'
        }).        
        when('/phones/:phoneId', {
          template: '<phone-detail></phone-detail>'
        }).
        when('/order/confirmation', {
            template: '<order-confirmation></order-confirmation>'
          }).
        otherwise('/login');
    }
  ]);
