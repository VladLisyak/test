/**
 * Created by Влад on 22.11.2015.
 */
var app = angular.module('test.controllers', [ 'ngRoute', 'ngResource' ]);
app.filter('startFrom', function(){
    return function(input, start){
        start = +start;
        return input.slice(start);
    }
});
        app.controller('home', ['$scope', 'UsersFactory', 'UserFactory', '$location',
            function ($scope, UsersFactory, UserFactory, $location) {



                // callback for ng-click 'editUser':
                $scope.editUser = function (userId) {
                    $location.path('/edit-user/' + userId);
                };

                // callback for ng-click 'deleteUser':
                $scope.deleteUser = function (userId) {
                    UserFactory.delete({ id: userId });

                    $scope.users = UsersFactory.query();
                    //$scope.users = UsersFactory.query();
                };

                // callback for ng-click 'createUser':
                $scope.createNewUser = function () {
                    $location.path('/user-creation');
                };

                $scope.users = UsersFactory.query();

                $scope.currentPage = 0;
                $scope.itemsPerPage = 10;


                $scope.firstPage = function() {
                    return $scope.currentPage == 0;
                }
                $scope.lastPage = function() {
                    var lastPageNum = Math.ceil($scope.users.length / $scope.itemsPerPage - 1);
                    return $scope.currentPage == lastPageNum;
                }
                $scope.numberOfPages = function(){
                    return Math.ceil($scope.users.length / $scope.itemsPerPage);
                }
                $scope.startingItem = function() {
                    return $scope.currentPage * $scope.itemsPerPage;
                }
                $scope.pageBack = function() {
                    $scope.currentPage = $scope.currentPage - 1;
                }
                $scope.pageForward = function() {
                    $scope.currentPage = $scope.currentPage + 1;
                }


            }]);
        app.controller('editCtrl', ['$scope', '$routeParams', 'UserFactory', '$location',
            function ($scope, $routeParams, UserFactory, $location) {


                $scope.updateUser = function () {
                    UserFactory.update($scope.user);
                    $location.path('/');

                };

                $scope.cancel = function () {
                    $location.path('/');
                };

                $scope.user = UserFactory.show({id: $routeParams.id});

            }]);
        app.controller('addCtrl', ['$scope', 'UsersFactory', '$location',
            function ($scope, UsersFactory, $location) {

                $scope.createNewUser = function () {
                    UsersFactory.create($scope.user);
                    $location.path('/');
                };

                $scope.cancel = function () {
                    $location.path('/');
                };

            }]);

var services = angular.module('test.services', ['ngResource']);

services.factory('UsersFactory', function ($resource) {
    return $resource('/user', {}, {
        query: { method: 'GET', isArray:true},
        create: { method: 'POST' }
    })
});

services.factory('UserFactory', function ($resource) {
    return $resource('/user/:id', {}, {
        show: { method: 'GET', params: {id: '@id'}},
        update: { method: 'PUT', params: {id: '@id'}},
        delete: { method: 'DELETE', params: {id: '@id'}}
    })
});


angular.module('test', ['test.controllers', 'test.services' ])
    .config(function($routeProvider) {

        $routeProvider.when('/', {
            templateUrl : 'static/list-of-users.htm',
            controller : 'home'
        }).when('/add-user', {
            templateUrl : 'static/add-user-form.htm',
            controller : 'addCtrl'
        }).when('/edit-user/:id', {
            templateUrl : 'static/edit-user-form.htm',
            controller : 'editCtrl'
        }).otherwise('/');


    });
