(function () {
    'use strict';

    angular
        .module('timeTable').controller('DiaryController', DiaryController);

    DiaryController.$inject = ['$http'];

    /* @ngInject */
    function DiaryController($http) {
        var vm = this;

        activate();

        ////////////////

        function activate() {

        }
    }

})();

