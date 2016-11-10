(function () {
    'use strict';

    var app = angular
        .module('tutor', []);

    app.controller('TutorListController', TutorListController);

    TutorListController.$inject = ['$http', '$route'];

    /* @ngInject */
    function TutorListController($http, $route) {
        var vm = this;
        vm.tutors = [];
        vm.remove = remove;

        $http.get('/promoters/').then(function (response) {
            vm.tutors = response.data;
        });

        function remove(id) {
            if(confirm("Czy na pewno chcesz usunąć promotora?")) {
                $http.delete('/promoters/' + id).then(function() {
                    $route.reload();
                });
            }
        }
    }

    app.controller('TutorDetailsController', TutorDetailsController);

    TutorDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function TutorDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.save = save;
        vm.isCreation = isCreation;
        vm.tutor = {};

        if(!isCreation()) {
            $http.get('/promoters/' + $routeParams.id).then(function (response) {
                vm.tutor = response.data;
            });
        }

        function save() {
            if(isCreation()) {
                $http.post('/promoters/', vm.tutor).then(onSuccess, onFailure);
            } else {
                $http.put('/promoters/', vm.tutor).then(onSuccess, onFailure);
            }
        }

        function isCreation() {
            return $routeParams.id === undefined || ($routeParams.id !== undefined && isNaN($routeParams.id));
        }

        function onSuccess(response) {
            $location.path("/tutors");
        }

        function onFailure() {
            alert("Nie udało się zapisać użytkownika. Sprawdź poprawność danych i spróbuj ponownie.");
        }

    }

})();

