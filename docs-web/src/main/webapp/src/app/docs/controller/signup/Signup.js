'use strict';

angular.module('docs').controller('Signup', function($scope, Restangular) {
  $scope.signupData = {};
  $scope.signupError = '';
  $scope.signupSuccess = '';

  $scope.signup = function() {
    $scope.signupError = '';
    $scope.signupSuccess = '';

    Restangular.all('signup/create').post(JSON.stringify($scope.signupData), null, { 'Content-Type': 'application/json' })
      .then(function() {
        $scope.signupSuccess = '注册成功，请等待admin审核！';
      }, function(response) {
        $scope.signupError = response.data && response.data.error ? response.data.error : '注册失败';
      });
  };
});