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

                $routeProvider.when('/trainees', {
                    templateUrl: 'app/student/studentList.html',
                    controller: 'StudentListController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/trainees/:id', {
                    templateUrl: 'app/student/studentForm.html',
                    controller: 'StudentDetailsController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/trainees/create', {
                    templateUrl: 'app/student/studentForm.html',
                    controller: 'StudentDetailsController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/firm', {
                    templateUrl: 'app/firm/firmList.html',
                    controller: 'FirmController',
                    controllerAs: 'firmCtrl'
                });

                $routeProvider.when('/firm/:id', {
                    templateUrl: 'app/firm/firmDetails.html',
                    controller: 'FirmDetailsController',
                    controllerAs: 'firmDetCtrl'
                });

                $routeProvider.when('/firm/create', {
                    templateUrl: 'app/firm/firmDetails.html',
                    controller: 'FirmDetailsController',
                    controllerAs: 'firmDetCtrl'
                });

                $routeProvider.when('/employee', {
                    templateUrl: 'app/firm/employeeList.html',
                    controller: 'EmployeeController',
                    controllerAs: 'empCtrl'
                });

                $routeProvider.when('/employee/:id', {
                    templateUrl: 'app/firm/employeeDetails.html',
                    controller: 'EmployeeDetailsController',
                    controllerAs: 'empDetCtrl'
                });

                $routeProvider.when('/employee/create', {
                    templateUrl: 'app/firm/employeeDetails.html',
                    controller: 'EmployeeDetailsController',
                    controllerAs: 'empDetCtrl'
                });

                $routeProvider.otherwise({
                    redirectTo: '/error'
                });

                $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
            }
        ]);

})();
