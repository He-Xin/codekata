describe("play fizz buzz game", function() {
    var getFizzBuzzAnswers = function(inputs) {
        var answers = [0];
        fizzbuzz(inputs, function(answer) {answers.push(answer)});
        return answers;
    }

    it("should define fizzbuzz", function() {
        expect(fizzbuzz).toBeDefined();
    });
    it("should answer fizz if number contains first input", function() {
        var answers = getFizzBuzzAnswers([3,5,7]);
        expect(answers[35]).toBe('fizz');
    });
    it("should answer fizzbuzz if number can be divided exactly by first two inputs", function() {
        var answers = getFizzBuzzAnswers([3,5,7]);
        expect(answers[15]).toBe('fizzbuzz');
    });
    it("should answer fizzbuzzwhizz if number can be divided exactly by all three inputs", function() {
        var answers = getFizzBuzzAnswers([2,3,5]);
        expect(answers[30]).toBe('fizzbuzzwhizz');
    });
    it("should return number if none of above rules match", function() {
        var answers = getFizzBuzzAnswers([3,5,7]);
        expect(answers[1]).toBe('1');
    });
});