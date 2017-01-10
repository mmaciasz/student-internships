(function () {
    'use strict';

    var app = angular
        .module('practiceDefinition', []);

    app.controller('PracticeDefinitionListController', PracticeDefinitionListController);

    PracticeDefinitionListController.$inject = ['$http', '$route', '$rootScope'];

    /* @ngInject */
    function PracticeDefinitionListController($http, $route, $rootScope) {
        var vm = this;
        vm.practiceDefinitions = [];
        vm.remove = remove;

        $http.get('/practiceDefinitions/').then(function (response) {
            vm.practiceDefinitions = response.data;
        });

        function remove(id) {
            if(confirm("Czy na pewno chcesz usunąć użytkownika?")) {
                $http.delete('/practiceDefinitions/' + id).then(function() {
                    $route.reload();
                });
            }
        }
    }

    app.controller('PracticeDefinitionDetailsController', PracticeDefinitionDetailsController);

    PracticeDefinitionDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function PracticeDefinitionDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.save = save;
        vm.isCreation = isCreation;
        vm.practiceDefinition = {};

        if(!isCreation()) {
            $http.get('/practiceDefinitions/' + $routeParams.id).then(function (response) {
                vm.practiceDefinition = response.data;
            });
        }

        function save() {
            if(isCreation()) {
                $http.post('/practiceDefinitions/', vm.practiceDefinition).then(onSuccess, onFailure);
            } else {
                $http.put('/practiceDefinitions/', vm.practiceDefinition).then(onSuccess, onFailure);
            }
        }

        function isCreation() {
            return $routeParams.id === undefined || ($routeParams.id !== undefined && isNaN($routeParams.id));
        }

        function onSuccess(response) {
            $location.path("/internships/subjects");
        }

        function onFailure() {
            alert("Nie udało się zapisać użytkownika. Sprawdź poprawność danych i spróbuj ponownie.");
        }

    }

})();

