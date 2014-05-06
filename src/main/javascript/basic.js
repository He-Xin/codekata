// for each function of array
[1,2,3].forEach(function(item) { console.log("item is: " + item ); })

// simple function definition
function add(a, b) {
    return a + b;
}

// use anonymous function
var add_ = function (a, b) {
    return a + b;
}

Array.prototype.myForEach = function(f, context) {
    var i, l = this.length;
    for(i=0; i<l; i++) {
        f.call(context, this[i], this)
    }
}

String.prototype.isRotationOf = function(str) {
    return str.concat(str).indexOf(this) >= 0 && str.length === this.length;
}
