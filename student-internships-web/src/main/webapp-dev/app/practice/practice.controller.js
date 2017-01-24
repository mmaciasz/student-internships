(function () {
    'use strict';

    var app = angular
        .module('practice', []);

    app.controller('PracticeListController', PracticeListController);

    PracticeListController.$inject = ['$http', '$route', '$rootScope', '$location', '$compile', '$scope', '$timeout'];

    /* @ngInject */
    function PracticeListController($http, $route, $rootScope, $location, $compile, $scope, $timeout) {
        var vm = this;
        vm.practices = [];
        vm.remove = remove;
        vm.markAsAccepted = markAsAccepted;
        vm.markAsRejected = markAsRejected;
        vm.markAsCancelled = markAsCancelled;

        vm.printCertificate = printCertificate;

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
            practice.firmEmployee = $rootScope.loggedUser.getUser();
            updatePracticeStatus(practice);
        }

        function markAsRejected(practice) {
            practice.status = $rootScope.dictionary.practiceStatus.REJECTED.code;
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

        function printCertificate(practiceId) {

            $http.get('/app/practice/practiceCertificate.html').then(function (response) {
                var template = response.data;

                $http.get('/practices/' + practiceId).then(function (response) {
                    $scope.practice = response.data;
                    var content = $compile(template)($scope);
                    $timeout(function(){
                        console.log(content.html());
                        var popupWin = window.open('', '_blank', 'width=640,height=480');
                        popupWin.document.open();
                        popupWin.document.write('<html><head></head><body onload="window.print()">' + content.html() + '</body></html>');
                        popupWin.document.close();
                    });
                });


            });
        }
    }

    app.controller('PracticeDetailsController', PracticeDetailsController);

    PracticeDetailsController.$inject = ['$http', '$routeParams', '$location', '$rootScope'];

    /* @ngInject */
    function PracticeDetailsController($http, $routeParams, $location, $rootScope) {
        var vm = this;
        vm.save = save;
        vm.saveAsCompleted = saveAsCompleted;
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
                $http.put('/practices/', vm.practice).then(onSuccess, onFailure);
            }
        }

        function saveAsCompleted() {
            vm.practice.status = $rootScope.dictionary.practiceStatus.COMPLETED.code;

            if(vm.practice.note && vm.practice.comment) {
                save();
            } else {
                alert("Zapis praktyki nie powiódł się. Sprawdź poprawność danych i spróbuj ponownie.");
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

