(function () {
    'use strict';

    var app = angular
        .module('practice', []);

    app.controller('PracticeListController', PracticeListController);

    PracticeListController.$inject = ['$http', '$route', '$rootScope', '$location'];

    /* @ngInject */
    function PracticeListController($http, $route, $rootScope, $location) {
        var vm = this;
        vm.practices = [];
        vm.remove = remove;
        vm.markAsAccepted = markAsAccepted;
        vm.markAsRejected = markAsRejected;
        vm.markAsCompleted = markAsCompleted;
        vm.markAsCancelled = markAsCancelled;

        $http.get('/practices/').then(function (response) {
            vm.practices = response.data;
        });

        function remove(id) {
            if(confirm("Czy na pewno chcesz usunąć praktykę?")) {
                $http.delete('/practices/' + id).then(function() {
                    $route.reload();
                });
            }
        }

        function markAsAccepted(practice) {
            practice.status = $rootScope.dictionary.practiceStatus.APPROVED.code;
            updatePracticeStatus(practice);
        }

        function markAsRejected(practice) {
            practice.status = $rootScope.dictionary.practiceStatus.REJECTED.code;
            updatePracticeStatus(practice);
        }

        function markAsCompleted(practice) {
            practice.status = $rootScope.dictionary.practiceStatus.COMPLETED.code;
            updatePracticeStatus(practice);
        }

        function markAsCancelled(practice) {
            practice.status = $rootScope.dictionary.practiceStatus.CANCELLED.code;
            updatePracticeStatus(practice);
        }

        function updatePracticeStatus(practice) {
            $http.put('/practices/', practice).then(onSuccess, onFailure);
        }

        function onSuccess(response) {
            $location.path("/internships");
        }
    }

    app.controller('PracticeDetailsController', PracticeDetailsController);

    PracticeDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function PracticeDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.save = save;
        vm.isCreation = isCreation;
        vm.promoters = [];
        vm.practice = {};

        $http.get('/promoters/').then(function (response) {
            vm.promoters = response.data;
        });

        if(isCreation()) {
            $http.get('/practiceDefinitions/' + $routeParams.defId).then(function (response) {
                vm.practice.practiceDefinition = response.data;
            });
        } else {
            $http.get('/practices/' + $routeParams.id).then(function (response) {
                vm.practice = response.data;
                if(vm.practice.promoter) {
                    vm.linkedWithPromoter = true;
                    vm.promoterId = vm.practice.promoter.userId;
                }
            });
        }

        function save() {

            if(!validate(vm.practice)) {
                alert("Zapis praktyki nie powiódł się. Sprawdź poprawność danych i spróbuj ponownie.");
                return;
            }

            if(vm.linkedWithPromoter) {
                vm.practice.promoter = vm.practice.promoter || {};
                vm.practice.promoter.promoterId = vm.promoterId
            }

            if(isCreation()) {
                $http.post('/practices/', vm.practice).then(onSuccess, onFailure);
            } else {
                $http.put('/practices/', practice).then(onSuccess, onFailure);
            }
        }

        function validate(practice) {
            if(vm.linkedWithPromoter && (vm.promoterId === undefined || vm.promoterId == null)) {
                return false;
            }

            return true;
        }

        function isCreation() {
            return $routeParams.id === undefined || ($routeParams.id !== undefined && isNaN($routeParams.id));
        }

        function onSuccess(response) {
            $location.path("/internships");
        }

    }

    function onFailure() {
        alert("Nie udało się zapisać praktyki. Sprawdź poprawność danych i spróbuj ponownie.");
    }

})();

