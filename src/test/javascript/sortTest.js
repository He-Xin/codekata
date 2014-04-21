describe("sorts", function() {
  it("should do insertion sort", function() {
    var arr = [5,3,7,1];
    insertionSort(arr);
    expect(arr).toEqual([1,3,5,7]);
   });
});