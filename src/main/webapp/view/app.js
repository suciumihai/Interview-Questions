var app = angular.module('app',[]);

app.service('CandidateCRUDService',['$http', function ($http) {

    this.getCandidate = function getCandidate(id){
    return $http({
    method: 'GET',
    url: 'candidates/'+id
    });
    }

    this.addCandidate = function addCandidate(id, name, surName, email, phone){
        return $http({
          method: 'POST',
          url: 'candidates',
          data: {id:id, name:name, surName:surName, email:email, phone:phone}
        });
    }

    this.deleteCandidate = function deleteCandidate(id){
        return $http({
          method: 'DELETE',
          url: 'candidates/'+id
        })
    }

    this.updateCandidate = function updateCandidate(id, name, surName, email, phone){
        return $http({
          method: 'PUT',
          url: 'candidates/'+id,
          data: {id:id, name:name, surName:surName, email:email, phone:phone}
        })
    }

    this.getCandidates = function getCandidates(){
        return $http({
          method: 'GET',
          url: 'candidates'
        });
    }

}])

app.controller('CandidateCRUDCtrl', ['$scope','CandidateCRUDService', function ($scope,CandidateCRUDService)) {

        $scope.updateCandidate = function() {
        CandidateCRUDService.updateCandidate($scope.candidate.id,$scope.candidate.name,$scope.candidate.surName,$scope.candidate.email,$scope.candidate.phone)
        .then(function success(response){
        $scope.message = 'candidate data updated';
        $scope.errorMessage = '';
        },
        function error(response){
                      $scope.errorMessage = 'Error updating user!';
                      $scope.message = '';
                      });
        }

         $scope.getCandidate = function () {
                var id = $scope.user.id;
                CandidateCRUDService.getCandidate($scope.candidate.id)
                  .then(function success(response){
                      $scope.user = response.data;
                      $scope.candidate.id = id;
                      $scope.message='';
                      $scope.errorMessage = '';
                  },
                  function error (response ){
                      $scope.message = '';
                      if (response.status === 404){
                          $scope.errorMessage = 'candidate not found!';
                      }
                      else {
                          $scope.errorMessage = "Error getting candidate!";
                      }
                  });
            }

            $scope.addCandidate = function () {
                if ($scope.candidate != null && $scope.candidate.name) {
                    CandidateCRUDService.addCandidate($scope.candidate.id,$scope.candidate.name,$scope.candidate.surName,$scope.candidate.email,$scope.candidate.phone)
                      .then (function success(response){
                          $scope.message = 'candidate added!';
                          $scope.errorMessage = '';
                      },
                      function error(response){
                          $scope.errorMessage = 'Error adding candidate!';
                          $scope.message = '';
                    });
                }
                else {
                    $scope.errorMessage = 'Please enter a name!';
                    $scope.message = '';
                }
            }

            $scope.deleteCandidate = function () {
                CandidateCRUDService.deleteCandidate($scope.candidate.id)
                  .then (function success(response){
                      $scope.message = 'candidate deleted!';
                      $scope.candidate = null;
                      $scope.errorMessage='';
                  },
                  function error(response){
                      $scope.errorMessage = 'Error deleting candidate!';
                      $scope.message='';
                  })
            }

            $scope.getCandidates = function () {
                CandidateCRUDService.getCandidates()
                  .then(function success(response){
                      $scope.candidates = response.data._embedded.candidates;
                      $scope.message='';
                      $scope.errorMessage = '';
                  },
                  function error (response ){
                      $scope.message='';
                      $scope.errorMessage = 'Error getting candidates!';
                  });
            }



}]);