/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('c3Module', ['ngCrud', 'ui.router']);

    mod.constant('c3Model', {
        name: 'c3',
        displayName: 'C3',
		url: 'c3s',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            c1: {
                displayName: 'C1',
                type: 'Reference',
                model: 'c1Model',
                options: [],
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/c3/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('c3', {
                url: '/c3s?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'c3.tpl.html',
                        controller: 'c3Ctrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                c1: r.all('c1s').getList()
                            });
                        }],
                    model: 'c3Model',
                    c3s: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('c3List', {
                url: '/list',
                parent: 'c3',
                views: {
                    c3View: {
                        templateUrl: basePath + 'list/c3.list.tpl.html',
                        controller: 'c3ListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c3New', {
                url: '/new',
                parent: 'c3',
                views: {
                    c3View: {
                        templateUrl: basePath + 'new/c3.new.tpl.html',
                        controller: 'c3NewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c3Instance', {
                url: '/{c3Id:int}',
                abstract: true,
                parent: 'c3',
                views: {
                    c3View: {
                        template: '<div ui-view="c3InstanceView"></div>'
                    }
                },
                resolve: {
                    c3: ['c3s', '$stateParams', function (c3s, $params) {
                            return c3s.get($params.c3Id);
                        }]
                }
            });
            $sp.state('c3Detail', {
                url: '/details',
                parent: 'c3Instance',
                views: {
                    c3InstanceView: {
                        templateUrl: baseInstancePath + 'detail/c3.detail.tpl.html',
                        controller: 'c3DetailCtrl'
                    }
                }
            });
            $sp.state('c3Edit', {
                url: '/edit',
                sticky: true,
                parent: 'c3Instance',
                views: {
                    c3InstanceView: {
                        templateUrl: baseInstancePath + 'edit/c3.edit.tpl.html',
                        controller: 'c3EditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c3Delete', {
                url: '/delete',
                parent: 'c3Instance',
                views: {
                    c3InstanceView: {
                        templateUrl: baseInstancePath + 'delete/c3.delete.tpl.html',
                        controller: 'c3DeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
