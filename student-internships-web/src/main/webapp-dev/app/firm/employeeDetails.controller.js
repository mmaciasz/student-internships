(function () {
    'use strict';

    angular
        .module('firm')
        .controller('EmployeeDetailsController', EmployeeDetailsController);

    EmployeeDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function EmployeeDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.employee = {};
        vm.firms = [];
        vm.selectedFirm = {};
        vm.disableFields = false;
        vm.edit = edit;
        vm.backToList = backToList;
        vm.save = save;

        onActive();
        ////////////////

        function onActive() {
            $http.get('/firm/').then(function (response) {
                vm.firms = response.data;
            });
            if(edit()) {
                $http.get('/firm/employee/' + $routeParams.id).then(function (response) {
                    vm.employee = response.data;
                    vm.selectedFirm = vm.employee.firm;
                    vm.disableFields = true;
                });
            }
        }

        function backToList() {
            $location.path('/employee');
        }

        function save() {
            vm.employee.firm = vm.selectedFirm;
            if(!edit()) {
                $http.post('/firm/employee/', vm.employee).then(onSuccess, onFailure);
            } else {
                $http.put('/firm/employee/', vm.employee).then(onSuccess, onFailure);
            }
        }

        function edit() {
            return $routeParams.id !== undefined && !isNaN($routeParams.id);
        }

        function onSuccess() {
            $location.path("/employee");
        }

        function onFailure() {
            alert("Nie udało się zapisać użytkownika. Sprawdź poprawność danych i spróbuj ponownie.");
        }
    }

})();

