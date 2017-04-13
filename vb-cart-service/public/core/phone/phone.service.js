'use strict';

angular.
  module('core.phone').
  factory('SelectedItems', function() {
	  var currentUser;
	  var selectedItems = new Array();
	  
	  function addItem(item) {
		  selectedItems.push(item)
	  }

	  function getAllItems() {
		  return selectedItems;
	  }
	  
	  function clearSelectedItems() {
		  selectedItems.length = 0;
	  }
	  
	  function setCurrentUser(user) {
		  currentUser = user;
	  }
	  
	  function getCurrentUser() {
		  return currentUser;
	  }

	  function clearCurrentUser() {
		  currentUser = undefined;
	  }
	  
	  return {
		  setCurrentUser: setCurrentUser,
		  getCurrentUser: getCurrentUser,
		  clearCurrentUser: clearCurrentUser,
		  addItem: addItem,
		  getAllItems: getAllItems,
		  clearSelectedItems: clearSelectedItems
	  } 
  });
