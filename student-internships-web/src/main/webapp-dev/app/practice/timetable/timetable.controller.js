(function () {
    'use strict';

    var app = angular
        .module('timeTable');

    app.controller('ScheduleController', ScheduleController);

    ScheduleController.$inject = ['$http', '$rootScope'];

    /* @ngInject */
    function ScheduleController($http, $rootScope) {
        var vm = this;
        vm.practices = [];

        $http.get('/practices/').then(function (response) {
            angular.forEach(response.data, function (value) {
                if (value.status === $rootScope.dictionary.practiceStatus.APPROVED.code) {
                    vm.practices.push(value);
                }
            })
        });

        ////////////////

    }

    app.controller('ScheduleDetailsController', ScheduleDetailsController);

    ScheduleDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function ScheduleDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.timetableNodes = [];
        vm.editingData = [];
        vm.save = save;
        vm.modify = modify;
        vm.update = update;
        vm.backToSchedule = backToSchedule;

        $http.get('/timetable/findSchedule/' + $routeParams.id).then(function (response) {
            var i = 0;
            angular.forEach(response.data, function (value) {
                value.startDt = new Date(value.startDt);
                value.stopDt = new Date(value.stopDt);
                vm.timetableNodes.push(value);
                vm.editingData[i] = false;
                i++;
            });
        });

        ////////////////

        function save() {
            $http.post('/timetable/saveTimetable', vm.timetableNodes).then(onSuccess, onFailure);
        }

        function onSuccess(response) {
            $location.path("/schedule/");
        }

        function onFailure() {
            alert("Nie udało się zapisać harmonogramu. Sprawdź poprawność danych i spróbuj ponownie.");
        }

        function modify(id) {
            vm.editingData[id] = true;
        }

        function update(id) {
            vm.editingData[id] = false;
        }

        function backToSchedule() {
            $location.path("/schedule/");
        }

    }

    app.controller('DiaryController', DiaryController);

    DiaryController.$inject = ['$http', '$rootScope'];

    /* @ngInject */
    function DiaryController($http, $rootScope) {
        var vm = this;
        vm.practices = [];

        $http.get('/practices/').then(function (response) {
            angular.forEach(response.data, function (value) {
                if (value.status === $rootScope.dictionary.practiceStatus.APPROVED.code) {
                    vm.practices.push(value);
                }
            })
        });

        ////////////////

    }

    app.controller('DiaryDetailsController', DiaryDetailsController);

    DiaryDetailsController.$inject = ['$http', '$routeParams', '$location'];

    /* @ngInject */
    function DiaryDetailsController($http, $routeParams, $location) {
        var vm = this;
        vm.timetableNodes = [];
        vm.editingData = [];
        vm.save = save;
        vm.modify = modify;
        vm.update = update;
        vm.backToDiary = backToDiary;

        $http.get('/timetable/findDiary/' + $routeParams.id).then(function (response) {
            var i = 0;
            angular.forEach(response.data, function (value) {
                value.startDt = new Date(value.startDt);
                value.stopDt = new Date(value.stopDt);
                vm.timetableNodes.push(value);
                vm.editingData[i] = false;
                i++;
            });
        });

        ////////////////

        function save() {
            $http.post('/timetable/saveTimetable', vm.timetableNodes).then(onSuccess, onFailure);
        }

        function onSuccess(response) {
            $location.path("/diary/");
        }

        function onFailure() {
            alert("Nie udało się zapisać harmonogramu. Sprawdź poprawność danych i spróbuj ponownie.");
        }

        function modify(id) {
            vm.editingData[id] = true;
        }

        function update(id) {
            vm.editingData[id] = false;
        }

        function backToDiary() {
            $location.path("/diary/");
        }

    }

    app.controller('DiaryPrintController', DiaryPrintController);

    DiaryPrintController.$inject = ['$rootScope', '$http', '$routeParams'];

    /* @ngInject */
    function DiaryPrintController($rootScope, $http, $routeParams) {
        var vm = this;
        vm.timetableNodes = [];

        $http.get('/timetable/findDiary/' + $routeParams.id).then(function (response) {
            $rootScope.isPrintVar = true;
            angular.forEach(response.data, function (value) {
                value.startDt = new Date(value.startDt);
                value.stopDt = new Date(value.stopDt);
                vm.timetableNodes.push(value);
            });
        });
    }

    app.controller('SchedulePrintController', SchedulePrintController);

    SchedulePrintController.$inject = ['$rootScope', '$http', '$routeParams', '$window', '$timeout'];

    /* @ngInject */
    function SchedulePrintController($rootScope, $http, $routeParams) {
        var vm = this;
        vm.timetableNodes = [];

        $http.get('/timetable/findSchedule/' + $routeParams.id).then(function (response) {
            $rootScope.isPrintVar = true;
            angular.forEach(response.data, function (value) {
                value.startDt = new Date(value.startDt);
                value.stopDt = new Date(value.stopDt);
                vm.timetableNodes.push(value);
            });
        });

    }

})();

