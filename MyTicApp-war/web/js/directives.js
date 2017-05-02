'use strict';
//
angular.module('myTicApp.directives', []).
  directive('bootstrapDropdown', [function() {
    return function(scope, element, attrs) {
        jQuery('html').on('click', function () {
            element.removeClass('open');
        });
        
        jQuery('.dropdown-toggle', element).on('click', function(e) {
            element.toggleClass('open');
            return false;
        });
    };
  }]);
