describe('basic tests', function() {
    it('should have add function', function() {
        expect(add).toBeDefined();
        expect(add_).toBeDefined();

        var result = add_(1,2);
        expect(result).toBe(3);

        result = add(3,4);
        expect(result).toBe(7);
    });

    it("should have myForEach method for array object", function() {
        expect([].myForEach).toBeDefined();

        var result = [];

        [1,2,3].myForEach(function(item) { result.unshift(item) });

        expect(result).toEqual([3,2,1]);
    });

    it("should check if string is a rotation of another string", function() {
        expect("abc".isRotationOf("bca")).toBeTruthy();
        expect("abc".isRotationOf("bac")).toBeFalsy();
        expect("abc".isRotationOf("abcd")).toBeFalsy();
    });
});