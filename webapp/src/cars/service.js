'use strict';

(function () {
    angular
        .module('cars')
        .service('CarsService', CarsService);


    CarsService.$inject = ['$http'];

    function CarsService($http) {

    }
}());