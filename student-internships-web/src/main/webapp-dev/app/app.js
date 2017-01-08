(function () {
    'use strict';

    var app = angular
        .module('studentInternships', [
            'ngRoute',
            'pascalprecht.translate',
            'ui.bootstrap',
            'auth',
            'student',
            'firm',
            'tutor',
            'practiceDefinition',
            'practice'
        ]);

    app.directive('datepicker', function() {
        return {
            restrict: 'E',
            template: '<p class="input-group"><input type="text" class="form-control" placeholder="{{dpplaceholder}}" ng-model="model" required /></p>',
            scope: {
                model: '=ngModel',
                dpplaceholder: '@',
                dpid: '@'
            },
            require: 'ngModel',
            replace: true,
            link: function (scope, element, attrs, ngModelCtrl) {
                var dpInput = $(element).children();
                dpInput.datepicker({
                    dateFormat: 'yy-mm-dd',
                    onSelect: function (date) {
                        scope.model = date;
                    }
                });
            }
        };
    });

    app.run(function($rootScope) {
        $rootScope.dictionary = {};

        $rootScope.dictionary.practiceDefStatus = {
            ACTIVE: {
                code: "ACTIVE",
                label: "Aktywny"
            },
            INACTIVE: {
                code: "INACTIVE",
                label: "Nieaktywny"
            },
            REJECTED: {
                code: "REJECTED",
                label: "Odrzucony"
            }
        };

        $rootScope.dictionary.practiceStatus = {
            WAITING_FOR_APPROVAL: {
                code: "WAITING_FOR_APPROVAL",
                label: "Czeka na akceptację"
            },
            APPROVED: {
                code: "APPROVED",
                label: "Zaakceptowana"
            },
            REJECTED: {
                code: "REJECTED",
                label: "Odrzucona"
            },
            CANCELLED: {
                code: "CANCELLED",
                label: "Anulowana"
            },
            COMPLETED: {
                code: "COMPLETED",
                label: "Zakończona"
            }
        };
    });

})();