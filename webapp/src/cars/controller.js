'use strict';

(function () {
    angular
        .module('app')
        .controller('CarsController', CarsController);


    CarsController.$inject = ["$scope", "CarsService"];

    function CarsController($scope, CarsService) {
        $scope.cars = [];
        $scope.models = {};

        // SET CAR TO LIST OF MODEL
        $scope.editCar = function (id) {
            var car = find($scope.cars, function (c) {
                return c.id == id;
            });

            $scope.models[car.id] = car;
        }

        // SAVE CAR
        $scope.saveCar = function (model) {
            if (model.id) {
                return CarsService.update(model).then(function () {
                    $scope.cars = $scope.cars.map(function (car) {
                        if (car.id == model.id) {
                            return model;
                        }
                    });
                });
            }

            return CarsService.insert(model).then(function (car) {
                $scope.cars.push(car);
            });
        }

        $scope.deleteCar = function (id) {
            CarsService.remove(id).then(function () {
                $scope.cars = $scope.cars.filter(function (car) {
                    return car.id != id;
                });
            });
        }

        // Load Cars
        CarsService.get().then(function (cars) {
            $scope.cars = cars;
        });
    }

    // Util Function
    function find(array, callback) {
        return array.filter(callback)[0];
    }
}());