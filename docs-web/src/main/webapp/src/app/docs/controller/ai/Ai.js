'use strict';

angular.module('docs').controller('Ai', function($scope, $http) {
  $scope.messages = [];
  $scope.input = '';

  $scope.sendMessage = function() {
    if ($scope.input.trim() === '') return;
    var userMsg = { from: 'user', text: $scope.input };
    $scope.messages.push(userMsg);

    // 直接用 $http 向 DeepSeek API 发送请求
    $http({
      method: 'POST',
      url: 'https://api.deepseek.com/chat/completions',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer xxx'
      },
      data: JSON.stringify({
        model: 'deepseek-chat',
        messages: [
          { role: 'system', content: 'You are a helpful assistant.' },
          { role: 'user', content: $scope.input }
        ],
        stream: false
      }),
      transformRequest: []
    }).then(function(response) {
      var aiReply = response.data.choices[0].message.content;
      $scope.messages.push({ from: 'ai', text: aiReply });
    }, function() {
      $scope.messages.push({ from: 'ai', text: 'AI接口请求失败，请重试' });
    });

    $scope.input = '';
  };
});