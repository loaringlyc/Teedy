'use strict';

angular.module('docs').controller('Ai', function($scope, Restangular) {
  $scope.messages = [];
  $scope.input = '';

  $scope.sendMessage = function() {
    if ($scope.input.trim() === '') return;
    var userMsg = { from: 'user', text: $scope.input };
    $scope.messages.push(userMsg);

    // 调用 DeepSeek API
    Restangular.all('ai/chat').post(JSON.stringify({
      model: 'deepseek-chat',
      messages: [
        { role: 'system', content: 'You are a helpful assistant.' },
        { role: 'user', content: $scope.input }
      ],
      stream: false
    }),
    null,
    { 'Content-Type': 'application/json' },
    ).then(function(response) {
      var aiReply = response.choices[0].message.content;
      $scope.messages.push({ from: 'ai', text: aiReply });
    }, function() {
      $scope.messages.push({ from: 'ai', text: 'AI接口请求失败，请重试' });
    });

    $scope.input = '';
  };
});