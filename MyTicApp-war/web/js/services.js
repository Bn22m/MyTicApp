'use strict';
//
// create new module with its dependencies:
angular.module('myTicApp.services', ['ngResource']);

//var shoppingList = [];
//var priceList = [];
var ticNumber = document.form3.tnumber.value;
var n = 0;
//alert(ticNumber);
//add some services :

// help desk department service; offline version:
//angular.module('myTicApp.services').factory('ShopA', function($resource){
//  return $resource('json/products.json', {
//  });
//});

//  help desk service; online version:
//
angular.module('myTicApp.services').factory('Shop', function($resource){
  return $resource('http://localhost\::port/MyTicApp-war/welcome.jsp?yname=#/help', 
            { port:8080}, {});
});


//  myticapp service; online version:
//
//angular.module('myTicApp.services').factory('Shop', function($resource){
//  return $resource('http://localhost\::port/MyTicApp-war/welcome.jsp?yname=#/myticapp', 
//            {port:8080}, {});
//});
//

// make purchase service:
angular.module('myTicApp.services').factory('Purchase', function($resource){
  return $resource('http://localhost\::port/MyTicApp-war/welome.jsp?yname=#/help', 
            {port:8080}, {});
});

// make purchaseB service:
//angular.module('myTicApp.services').factory('PurchaseB', function($resource){
//  return $resource('http://localhost\::port/MyTicApp-war/welome.jsp?yname=#/myticapp', 
//            {port:8080}, {});
//});

// shopping cart service:
angular.module('myTicApp.services').factory('Cart', function(){
    var shoppingCart = function () {
        this.items = [];
        this.listeners = [];
        this.add = function(product) {
            this.items.push( product);
            //shoppingList.push(product);
            //priceList.push(product.price);
            alert(product.des +", added to shopping list : "+product.price);
            //this.items.push(price);
            document.form3.message.value +="//\n"+ ++n +" "+ product.des +", added to shopping list : "+product.price+"\n";
            this.fireChanges();
        };
        
        this.fireChanges = function() {
            for (var i = 0; i < this.listeners.length; i++) {
                this.listeners[i].call();
            }
        };
        this.remove = function(product) {
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i] === product) {
                    this.items.splice(i,1);
                    this.fireChanges();
                    return;
                }
            }
        };
        this.removeAll = function() {
            this.items = [];
            this.fireChanges();
        };
        this.size = function() {
            return this.items.length;
        };
        this.getProducts = function() {
            return this.items;
        };
        this.addListener = function(listener) {
            this.listeners.push( listener);
        };
        this.getTotal = function() {
            var total = 0;
            for (var i = 0; i < this.items.length; i++) {
                total += this.items[i].price;
            }
            return total.toFixed(2);
        };
        //this.getTotal = function(){
        //    var total = 0;
        //    for (var i = 0; i < priceList.length; i++) {
        //        total += priceList[i];
        //    }
        //    return total.toFixed(2);
        //};

    };
    return new shoppingCart();
});
