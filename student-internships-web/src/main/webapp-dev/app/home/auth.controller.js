(function () {
    'use strict';

    angular
        .module('auth')
        .controller('AuthController', AuthController);

    AuthController.$inject = ['$rootScope', '$http', '$location'];

    /* @ngInject */
    function AuthController($rootScope, $http, $location) {
        var vm = this;
        vm.credentials = {};

        vm.authenticate = authenticate;
        vm.logout = logout;
        vm.login = login;

        checkAuthorize();
        ////////////////

        function authenticate(credentials, callback) {

            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};

            $http.get('user', {headers: headers}).then(function (response) {
                $rootScope.authenticated = !!response.data.name;
                callback && callback();
            }, function () {
                $rootScope.authenticated = false;
                callback && callback();
            });
        }

        function login() {
            authenticate(vm.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    vm.error = false;
                } else {
                    $location.path("/home");
                    vm.error = true;
                }
            });
        }

        function logout() {
            $http.post('logout', {}).finally(function() {
                $rootScope.authenticated = false;
                $location.path("/home");
            });
        }

        function checkAuthorize() {
            authenticate(null, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    vm.error = false;
                } else {
                    $location.path("/home");
                }
            });
        }
    }

})();

