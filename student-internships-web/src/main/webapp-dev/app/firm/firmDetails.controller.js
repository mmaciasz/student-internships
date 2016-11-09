(function () {
    'use strict';

    angular
        .module('firm')
        .controller('FirmDetailsController', FirmDetailsController);

    FirmDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function FirmDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.firm = {};
        vm.edit = edit;
        vm.backToList = backToList;
        vm.save = save;

        onActive();
        ////////////////

        function onActive() {
            if(edit()) {
                $http.get('/firm/' + $routeParams.id).then(function (response) {
                    vm.firm = response.data;
                });
            }
        }

        function backToList() {
            $location.path('/firm');
        }

        function save() {
            if(!edit()) {
                $http.post('/firm/', vm.firm).then(onSuccess, onFailure);
            } else {
                $http.put('/firm/', vm.firm).then(onSuccess, onFailure);
            }
        }

        function edit() {
            return $routeParams.id !== undefined && !isNaN($routeParams.id);
        }

        function onSuccess() {
            $location.path("/firm");
        }

        function onFailure() {
            alert("Nie udało się zapisać firmy. Sprawdź poprawność danych i spróbuj ponownie.");
        }
    }

})();

