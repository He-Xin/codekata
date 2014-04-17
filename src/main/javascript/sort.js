(function(exports) {

    function _swap(array, i, j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //insertion sort
    var insertionSort = function(array) {
        var i, j, length = array.length;
        for (i = 1; i < length; i++) {
            var v = array[i];
            for (j = i -1; j >= 0; j--) {
                if (v < array[j]) {
                    _swap(array, j, j + 1);
                } else {
                    break;
                }
            }
        }
    };

    exports.insertionSort = insertionSort;

}) (this);