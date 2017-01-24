(function () {
    'use strict';

    angular.module('studentInternships')
        .config(['$routeProvider', '$httpProvider',
            function ($routeProvider, $httpProvider) {

                $routeProvider.when('/error', {
                    templateUrl: 'app/errorPage.html'
                });

                $routeProvider.when('/', {
                    templateUrl: 'app/home/loginForm.html',
                    controller: 'AuthController',
                    controllerAs: 'authCtrl'
                });

                $routeProvider.when('/home', {
                    templateUrl: 'app/home/home.html'
                });

                $routeProvider.when('/contact', {
                    templateUrl: 'app/common/contact.html'
                });

                $routeProvider.when('/trainees', {
                    templateUrl: 'app/student/studentList.html',
                    controller: 'StudentListController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/trainees/:id', {
                    templateUrl: 'app/student/studentForm.html',
                    controller: 'StudentDetailsController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/trainees/create', {
                    templateUrl: 'app/student/studentForm.html',
                    controller: 'StudentDetailsController',
                    controllerAs: 'studentCtrl'
                });

                $routeProvider.when('/firm', {
                    templateUrl: 'app/firm/firmList.html',
                    controller: 'FirmController',
                    controllerAs: 'firmCtrl'
                });

                $routeProvider.when('/firm/:id', {
                    templateUrl: 'app/firm/firmDetails.html',
                    controller: 'FirmDetailsController',
                    controllerAs: 'firmDetCtrl'
                });

                $routeProvider.when('/firm/create', {
                    templateUrl: 'app/firm/firmDetails.html',
                    controller: 'FirmDetailsController',
                    controllerAs: 'firmDetCtrl'
                });

                $routeProvider.when('/employee', {
                    templateUrl: 'app/firm/employeeList.html',
                    controller: 'EmployeeController',
                    controllerAs: 'empCtrl'
                });

                $routeProvider.when('/employee/:id', {
                    templateUrl: 'app/firm/employeeDetails.html',
                    controller: 'EmployeeDetailsController',
                    controllerAs: 'empDetCtrl'
                });

                $routeProvider.when('/employee/create', {
                    templateUrl: 'app/firm/employeeDetails.html',
                    controller: 'EmployeeDetailsController',
                    controllerAs: 'empDetCtrl'
                });

                $routeProvider.when('/tutors', {
                    templateUrl: 'app/tutor/tutorList.html',
                    controller: 'TutorListController',
                    controllerAs: 'tutorCtrl'
                });

                $routeProvider.when('/tutors/:id', {
                    templateUrl: 'app/tutor/tutorForm.html',
                    controller: 'TutorDetailsController',
                    controllerAs: 'tutorCtrl'
                });

                $routeProvider.when('/tutors/create', {
                    templateUrl: 'app/tutor/tutorForm.html',
                    controller: 'TutorDetailsController',
                    controllerAs: 'tutorCtrl'
                });

                $routeProvider.when('/internships/subjects/create', {
                    templateUrl: 'app/practice/definition/practiceDefinitionForm.html',
                    controller: 'PracticeDefinitionDetailsController',
                    controllerAs: 'practiceDefCtrl'
                });

                $routeProvider.when('/internships/subjects', {
                    templateUrl: 'app/practice/definition/practiceDefinitionList.html',
                    controller: 'PracticeDefinitionListController',
                    controllerAs: 'practiceDefCtrl'
                });

                $routeProvider.when('/internships/subjects/:id', {
                    templateUrl: 'app/practice/definition/practiceDefinitionForm.html',
                    controller: 'PracticeDefinitionDetailsController',
                    controllerAs: 'practiceDefCtrl'
                });

                $routeProvider.when('/internships/create', {
                    templateUrl: 'app/practice/practiceForm.html',
                    controller: 'PracticeDetailsController',
                    controllerAs: 'practiceCtrl'
                });

                $routeProvider.when('/internships/', {
                    templateUrl: 'app/practice/practiceList.html',
                    controller: 'PracticeListController',
                    controllerAs: 'practiceCtrl'
                });

                $routeProvider.when('/internships/:id', {
                    templateUrl: 'app/practice/practiceForm.html',
                    controller: 'PracticeDetailsController',
                    controllerAs: 'practiceCtrl'
                });

                $routeProvider.when('/internships/:id/certificate', {
                    templateUrl: 'app/practice/practiceCertificate.html',
                    controller: 'PracticeDetailsController',
                    controllerAs: 'practiceCtrl'
                });

                $routeProvider.when('/diary/', {
                    templateUrl: 'app/practice/timetable/diary.html',
                    controller: 'DiaryController',
                    controllerAs: 'diaryCtrl'
                });

                $routeProvider.when('/diary/:id', {
                    templateUrl: 'app/practice/timetable/diaryForm.html',
                    controller: 'DiaryDetailsController',
                    controllerAs: 'diaryDetCtrl'
                });

                $routeProvider.when('/diaryprint/:id', {
                    templateUrl: 'app/practice/timetable/diaryPrint.html',
                    controller: 'DiaryPrintController',
                    controllerAs: 'diaryPrintCtrl'
                });

                $routeProvider.when('/schedule/', {
                    templateUrl: 'app/practice/timetable/schedule.html',
                    controller: 'ScheduleController',
                    controllerAs: 'scheduleCtrl'
                });

                $routeProvider.when('/schedule/:id', {
                    templateUrl: 'app/practice/timetable/scheduleForm.html',
                    controller: 'ScheduleDetailsController',
                    controllerAs: 'scheduleDetCtrl'
                });

                $routeProvider.when('/scheduleprint/:id', {
                    templateUrl: 'app/practice/timetable/schedulePrint.html',
                    controller: 'SchedulePrintController',
                    controllerAs: 'schedulePrintCtrl'
                });

                $routeProvider.otherwise({
                    redirectTo: '/error'
                });

                $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
            }
        ]);

})();
