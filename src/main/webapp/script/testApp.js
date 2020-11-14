angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {

            fetchData();

            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                    console.log(response.data);
                    $scope.states = response.data;
                    
                    //setting a default state value for the dropdown list
                    $scope.selectedState= Object.values(response.data)[0];
                    console.log($scope.selectedState);
                    
                    
                }, function (reason) {
                    console.log('error ' + reason)
                });
            }

        }]);
