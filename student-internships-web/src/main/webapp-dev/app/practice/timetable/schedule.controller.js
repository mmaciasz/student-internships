(function () {
    'use strict';

    var app = angular
        .module('timeTable');

    app.controller('ScheduleController', ScheduleController);

    ScheduleController.$inject = ['$http'];

    /* @ngInject */
    function ScheduleController($http) {
        var vm = this;
        vm.practices = [];

        $http.get('/practices/').then(function (response) {
            vm.practices = response.data;
        });

        ////////////////

    }

    app.controller('ScheduleDetailsController', ScheduleDetailsController);

    ScheduleDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function ScheduleDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.timetableNodes = [];
        vm.save = save;

        $http.get('/timetable/findSchedule/' + $routeParams.id).then(function (response) {
            vm.timetableNodes = response.data;
        });

        ////////////////

        function save() {
            $http.post('/timetable/saveSchedule', vm.timetableNodes).then(onSuccess, onFailure);
        }

        function onSuccess(response) {
            $location.path("/schedule/");
        }

        function onFailure() {
            alert("Nie udało się zapisać użytkownika. Sprawdź poprawność danych i spróbuj ponownie.");
        }

    }

})();

