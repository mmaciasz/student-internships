(function () {
    'use strict';

    angular
        .module('firm')
        .controller('EmployeeController', EmployeeController);

    EmployeeController.$inject = ['$http', '$location', '$route'];

    /* @ngInject */
    function EmployeeController($http, $location, $route) {
        var vm = this;
        vm.employees = [];
        vm.createEmployee = createEmployee;
        vm.deleteEmployee = deleteEmployee;

        onActivate();

        ////////////////

        function onActivate() {
            $http.get('/firm/employee/').then(function (response) {
                vm.employees = response.data;
            });
        }

        function createEmployee() {
            $location.path('/employee/create');
        }

        function deleteEmployee(id) {
            if (confirm("Czy na pewno chcesz usunąć użytkownika?")) {
                $http.delete('/firm/employee/' + id).then(function () {
                    $route.reload();
                });
            }
        }
    }

})();

