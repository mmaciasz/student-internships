(function () {
    'use strict';

    angular.module('studentInternships')
        .config(['$routeProvider',
            function ($routeProvider) {

                $routeProvider.when('/errorPage', {
                    templateUrl: 'app/errorPage.html'
                });

                $routeProvider.otherwise({
                    redirectTo: '/errorPage'
                });

            }
        ]);

})();
