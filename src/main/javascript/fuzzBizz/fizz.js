(function(exports) {
    exports.fizzbuzz = function(inputs, out) {
        var messages = ['fizz', 'buzz', 'whizz'];
        _.each(_.map(_.range(1, 100), function(i) {
            if (i.toString().indexOf(inputs[0]) != -1) return messages[0];
            var answer = _.filter(messages, function(msg, index) {return i % inputs[index] == 0;}).join('');
            return answer.length == 0 ? i.toString() : answer;
        }), out);
    }
})(this);