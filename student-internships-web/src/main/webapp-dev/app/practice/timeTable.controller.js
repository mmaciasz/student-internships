(function () {
    'use strict';

    angular
        .module('timeTable')
        .controller('TimeTableController', TimeTableController);

    TimeTableController.$inject = ['$scope'];

    /* @ngInject */
    function TimeTableController($scope) {
        var vm = this;

        activate();

        ////////////////

        function activate() {

        }
    }

})();

