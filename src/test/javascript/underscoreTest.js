describe('underscore js test', function() {
    it('should load js in lib folder', function() {
        expect(_).toBeDefined();
    });

    it("should produces a new array of values by mapping each value in list through a transformation function"), function() {
        var arr = [1,2,3];
        var newArr = _.map(arr, function(num) { return num * 2});

        expect(newArr).toEqual([2,4,6]);
    }
});