'use strict';

(function () {
    angular
        .module('app', [])
        .factory('responseHandle', responseHandle)
        .config(AppSetup);

    AppSetup.$inject = ['$httpProvider'];
    function AppSetup($httpProvider) {
        $httpProvider.interceptors.push('responseHandle');
    };

    responseHandle.$inject = ['$q'];
    function responseHandle($q) {
        return {
            response: function (response) {
                if (response.status != 200) {
                    return $q.reject(response)
                }

                return response.data;
            }
        };
    };
}());