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

        // nie wiem gdzie to wrzucic, moze jest na to lepsze miejsce...
        $rootScope.isAuthorized = function() {
            return $rootScope.loggedUser !== undefined && $rootScope.loggedUser !== null;
        }

        function authenticate(credentials, callback) {

            var headers = credentials ? {authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)} : {};

            $http.get('user', {headers: headers}).then(function (response) {
                $rootScope.loggedUser = new LoggedUser(response.data.name, response.data.authorities[0].authority);
                callback && callback();
            }, function () {
                callback && callback();
            });
        }

        function login() {
            authenticate(vm.credentials, function () {
                if ($rootScope.isAuthorized()) {
                    $location.path("/home");
                    vm.error = false;
                } else {
                    $location.path("/");
                    vm.error = true;
                }
            });
        }

        function logout() {
            $http.post('logout', {}).finally(function() {
                delete $rootScope.loggedUser;
                $location.path("/");
            });
        }

        function checkAuthorize() {
            authenticate(null, function () {
                if ($rootScope.isAuthorized()) {
                    $location.path("/home");
                    vm.error = false;
                } else {
                    $location.path("/");
                }
            });
        }
    }

})();

