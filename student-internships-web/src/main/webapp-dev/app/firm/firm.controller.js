(function () {
    'use strict';

    angular
        .module('firm')
        .controller('FirmController', FirmController);

    FirmController.$inject = ['$http', '$route', '$location'];

    /* @ngInject */
    function FirmController($http, $route, $location) {
        var vm = this;
        vm.firms = [];
        vm.createFirm = createFirm;
        vm.deleteFirm = deleteFirm;

        $http.get('/firm/').then(function (response) {
            vm.firms = response.data;
        });

        ////////////////

        function createFirm() {
            $location.path('/firm/create');
        }

        function deleteFirm(id) {
            if (confirm("Czy na pewno chcesz usunąć firmę?")) {
                $http.delete('/firm/' + id).then(function () {
                    $route.reload();
                });
            }
        }
    }

})();

