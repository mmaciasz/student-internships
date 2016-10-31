(function () {
    'use strict';

    angular.module('studentInternships')
        .config(['$routeProvider', '$httpProvider',
            function ($routeProvider, $httpProvider) {

                $routeProvider.when('/error', {
                    templateUrl: 'app/errorPage.html'
                });

                $routeProvider.when('/', {
                    templateUrl: 'app/home/loginForm.html',
                    controller: 'AuthController',
                    controllerAs: 'authCtrl'
                });

                $routeProvider.when('/home', {
                    templateUrl: 'app/home/home.html'
                });

                $routeProvider.when('/contact', {
                    templateUrl: 'app/common/contact.html'
                });

                $routeProvider.otherwise({
                    redirectTo: '/error'
                });

                $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
            }
        ]);

})();
