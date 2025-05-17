'use strict';

/**
 * Settings user page controller.
 */
angular.module('docs').controller('SettingsUser', function($scope, $state, Restangular) {
  /**
   * Load users from server.
   */
  console.log("SettingsUser controller loaded");

  $scope.loadUsers = function() {
    Restangular.one('user/list').get({
      sort_column: 1,
      asc: true
    }).then(function(data) {
      $scope.users = data.users;
    });
  };

  $scope.loadUsers();
  
  $scope.loadMessages = function() {
    Restangular.all('signup/list').getList().then(function(data) {
      $scope.messages = data;
    }).catch(function(err){
      console.log('error:', err);
    })
  }

  $scope.loadMessages();

  /**
   * Edit a user.
   */
  $scope.editUser = function(user) {
    $state.go('settings.user.edit', { username: user.username });
  };
});