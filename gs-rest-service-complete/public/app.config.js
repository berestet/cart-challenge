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
        when('/cart', {
          template: '<cart-detail></cart-detail>'
        }).
        
        when('/phones/:phoneId', {
          template: '<phone-detail></phone-detail>'
        }).
        otherwise('/login');
    }
  ]);
