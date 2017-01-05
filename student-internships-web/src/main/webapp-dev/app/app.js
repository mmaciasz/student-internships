(function () {
    'use strict';

    angular
        .module('studentInternships', [
            'ngRoute',
            'pascalprecht.translate',
            'ui.bootstrap',
            'auth',
            'student',
            'firm',
            'tutor',
            'timeTable'
        ]);

})();