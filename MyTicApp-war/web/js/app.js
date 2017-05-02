'use strict';

// Declare application level module which depends on services and directives
angular.module('myTicApp', ['myTicApp.services', 'myTicApp.directives']);

// configure application's routeProvider:
angular.module('myTicApp').config(['$routeProvider', function($routeProvider) 
  {
    // info page:
    $routeProvider.when('/info', 
        {
            templateUrl: 'partials/info.html', 
            controller: MainController
        });
     
     // help page:
    $routeProvider.when('/help', 
        { 
            templateUrl: 'partials/help.html', 
            controller: DepartmentController
        });
     
     // admin page:
    $routeProvider.when('/admin', 
        {
            templateUrl: 'partials/admin.html', 
            controller: CartController
        });
    
    // new page:
    $routeProvider.when('/new', 
        {
            templateUrl: 'partials/new.html', 
            controller: CheckoutController
        });
     
    // myticapp page:
    $routeProvider.when('/myticapp', 
        {
            templateUrl: 'partials/myticapp.html', 
            controller: myCtrl
        });
     
     //
     $routeProvider.when('/orders', 
        {
            templateUrl: 'partials/orders.html', 
            controller: CheckoutController
        });
     
     //
     $routeProvider.when('/doc', 
        {
            templateUrl: 'partials/document.html', 
            controller: CheckoutController
        });
    
    // default fallback page:
    $routeProvider.otherwise(
        {
            redirectTo: '/info'
        });
    
  }]);
