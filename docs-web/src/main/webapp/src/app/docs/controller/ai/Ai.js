'use strict';

angular.module('docs').controller('Ai', function($scope) {
  $scope.messages = [];
  $scope.input = '';

  $scope.sendMessage = function() {
    if ($scope.input.trim() === '') return;
    $scope.messages.push({ from: 'user', text: $scope.input });
    // 这里可以添加AI回复逻辑
    $scope.messages.push({ from: 'ai', text: 'AI回复示例：' + $scope.input });
    $scope.input = '';
  };
});