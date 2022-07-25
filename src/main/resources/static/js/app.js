(function(){

  var app = angular.module('notesApp',['ngRoute', 'ngMaterial']);

  app.config(['$locationProvider', '$routeProvider',
      function ($locationProvider, $routeProvider) {

        $routeProvider
          .when('/', {
            templateUrl: '/partials/notes-view.html',
            controller: 'notesController'
          })
          .when('/login', {
             templateUrl: '/partials/login.html',
             controller: 'loginController',
          })
          .otherwise('/');
      }
  ]);

  app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, AuthService) {
      $rootScope.$on('$routeChangeStart', function (event) {

          if ($location.path() == "/login"){
             return;
          }

          if (!AuthService.isLoggedIn()) {
              console.log('DENY');
              event.preventDefault();
              $location.path('/login');
          }
      });
  }]);


  app.service('AuthService', function($http){
        var loggedUser = null;

        function login (username, password){
            return $http.post("api/login", {username: username, password: password}).then(function(user){
                loggedUser = user;
            }, function(error){
                loggedUser = null;
            })
        }

        function isLoggedIn(){
            return loggedUser != null;
        }
        return {
            login : login,
            isLoggedIn: isLoggedIn
        }
  });

  app.service('NotesService', function($http){

        this.create = function (name, text){
            return $http.post("v1/note", {name: name, text: text}).then(function(note){
                console.log("created");
            }, function(error){
                console.log(error);
            })
        }

        this.update = function (name, text, id){
                    return $http.put("v1/note/"+ id, {name: name, text: text}).then(function(note){
                        console.log("updated");
                    }, function(error){
                        console.log(error);
                    })
                }

        this.deleteNote = function (id){
                            return $http.delete("v1/note/"+ id).then(function(note){
                                console.log("deleted");
                            }, function(error){
                                console.log(error);
                            })
                        }

        this.notesList = function (){
                    $http.get("v1/note").then(function(notes){
                        return notes.data;
                    }, function(error){
                        console.log(error);
                    })
                }

      });

  app.controller('loginController', function($scope, AuthService, $location){

    $scope.invalidCreds = false;
    $scope.login = {
        username : null,
        password : null
    };

    $scope.login = function(){
        AuthService.login($scope.login.username, $scope.login.password).then(function(user){
            console.log(JSON.stringify(user));
            $location.path("/");
        }, function(error){
            console.log(error);
            $scope.invalidCreds = true;
        });
    };
  });




  app.controller('notesController', function($scope, NotesService, $location, $http){

    $scope.note = {
            name : null,
            text : null
    };
    $scope.isEditCreateView = false;

    $scope.newNoteView = function(){
        $scope.isEditCreateView = true;
        if($scope.selectedId == -1) {
            $scope.note.name = null;
            $scope.note.text = null;
        }
    };



    $scope.selectedId = -1;

    $scope.notes = [];

    $scope.save = function(){
            if($scope.selectedId == -1) {
                NotesService.create($scope.note.name, $scope.note.text).then(function(note){
                    console.log(note);
                    $scope.readNoteList();
                    $location.path("/");
                }, function(error){
                    console.log(error);
                });
            } else {
                NotesService.update($scope.note.name, $scope.note.text, $scope.selectedId).then(function(note){
                        console.log(note);
                        $scope.readNoteList();
                        $location.path("/");
                    }, function(error){
                        console.log(error);
                });
            }
        };

    $scope.readNoteList = function(){
        $http.get("v1/note").then(function (response) {
            console.log("Fetching again");
            $scope.notes = response.data;
        });
    }
    $http.get("v1/note").then(function (response) {$scope.notes = response.data;});


    $scope.deleteNote = function (i) {
      var r = confirm("Are you sure you want to delete this note?");
      if (r == true){
        NotesService.deleteNote(i).then(function(note){
                            console.log(note);
                            $location.path("/");
                        }, function(error){
                            console.log(error);
                        });
                        $scope.readNoteList();
      }
    };

    $scope.viewNote = function(selectedId){
        $scope.selectedId = selectedId;
                $http.get("v1/note/"+selectedId).then(function (response) {
                    $scope.note = response.data;
                    $scope.isEditCreateView = true;
                });
    }
  });

})();