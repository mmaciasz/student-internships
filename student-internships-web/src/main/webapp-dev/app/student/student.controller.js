(function () {
    'use strict';

    var app = angular
        .module('student', []);

    app.controller('StudentListController', StudentListController);

    StudentListController.$inject = ['$http', '$route'];

    /* @ngInject */
    function StudentListController($http, $route) {
        var vm = this;
        vm.students = [];
        vm.remove = remove;

        $http.get('/students/').then(function (response) {
            vm.students = response.data;
        });

        function remove(id) {
            if(confirm("Czy na pewno chcesz usunąć użytkownika?")) {
                $http.delete('/students/' + id).then(function() {
                    $route.reload();
                });
            }
        }
    }

    app.controller('StudentDetailsController', StudentDetailsController);

    StudentDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function StudentDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.save = save;
        vm.isCreation = isCreation;
        vm.student = {};

        if(!isCreation()) {
            $http.get('/students/' + $routeParams.id).then(function (response) {
                vm.student = response.data;
            });
        }

        function save() {
            if(isCreation()) {
                $http.post('/students/', vm.student).then(onSuccess, onFailure);
            } else {
                $http.put('/students/', vm.student).then(onSuccess, onFailure);
            }
        }

        function isCreation() {
            return $routeParams.id === undefined || ($routeParams.id !== undefined && isNaN($routeParams.id));
        }

        function onSuccess(response) {
            $location.path("/trainees");
        }

        function onFailure() {
            alert("Nie udało się zapisać użytkownika. Sprawdź poprawność danych i spróbuj ponownie.");
        }

    }

})();

