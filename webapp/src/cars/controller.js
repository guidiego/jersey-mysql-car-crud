'use strict';

(function () {
    angular
        .module('cars')
        .controller('CarsController', CarsController);


    CarsController.$inject = ["CarsService"];

    function CarsController(CarsService) {

    }
}());