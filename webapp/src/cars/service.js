'use strict';

(function () {
    angular
        .module('app')
        .service('CarsService', CarsService);


    CarsService.$inject = ['$http'];

    function CarsService($http) {
        var baseUrl = API_PROTOCOL + API_URL + API_PORT + "/car"

        this.get = function () {
            return $http.get(baseUrl);
        }

        this.insert = function (model) {
            return $http.post(baseUrl, model);
        }

        this.update = function (model) {
            return $http.put(baseUrl, model);
        }

        this.remove = function (id) {
            return $http.delete(baseUrl + "/" + id);
        }
    }
}());