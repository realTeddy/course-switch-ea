var app = angular.module('app', []);

app.controller('MainController', function ($scope, $http) {
    var vm = this;

    $scope.currentBlockId;
    $scope.registrations = [];
    $scope.currentBlock;
    $scope.blockCourses = [];
    $scope.newBlockCourses = [];
    $scope.blocks = [];
    $scope.selectedNewBlockId = null;
    $scope.selectedCourseId = null;
    
    $http.get("getBlocks")
            .success(function (data, status, headers, config) {
                $scope.blocks = data;
            })
            .error(function (data, status, headers, config) {
                alert("AJAX failed!");
            });

    vm.changeCourses = function (block) {
        for (var i = 0; i < $scope.blocks.length; i++) {
            if ($scope.blocks[i].id == $scope.selectedNewBlockId) {
                $scope.newBlockCourses = $scope.blocks[i].courses;
            }
        }
    }
    
    vm.saveNewBlockCourse = function () {
        $http.get("addBlockCourse?blockId=" + $scope.selectedNewBlockId + "&courseId=" + $scope.selectedCourseId)
            .success(function (data, status, headers, config) {
                $scope.registrations.push(data);
            })
            .error(function (data, status, headers, config) {
                alert("AJAX failed!");
            });
    }

    $http.get("getRegistrations")
            .success(function (data, status, headers, config) {
                $scope.registrations = data;
            })
            .error(function (data, status, headers, config) {
                alert("AJAX failed!");
            });

    vm.showNewPreferencesModal = function (registration) {
        $('#addPreferrenceModal').modal('show');
        $scope.registration = registration;
        $http.get("getBlockCourses?blockId=" + registration.block.id + "&courseId=" + registration.course.id)
                .success(function (data, status, headers, config) {
                    $scope.blockCourses = data;
                })
                .error(function (data, status, headers, config) {
                    alert("AJAX failed!");
                });
    };

    vm.addPreferedCourse = function addPreferedCourse(course) {
        $http.get("addPreferedCourse?courseId=" + course.course.id + "&registrationId=" + $scope.registration.id)
                .success(function (data, status, headers, config) {
                    if (data) {
                        $scope.information = "The course you are looking for is currently available, you will be registered as soon as the administrator approved your request.";
                        course.isPendingApproval = true;
                    } else {
                        $scope.information = "The course you are looking for is currently not available, you will be notified as soon as a student who want to switch with you is found.";
                    }
                    $('#alertModal').modal('show');
                    $scope.registration.preferedCourses.push(course);
                    $('#addPreferrenceModal').modal('hide');
                })
                .error(function (data, status, headers, config) {
                    alert("AJAX failed!");
                });
    };
});