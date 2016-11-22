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
    var mod = ng.module('c4Module', ['ngCrud', 'ui.router']);

    mod.constant('c4Model', {
        name: 'c4',
        displayName: 'C4',
		url: 'c4s',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            c2: {
                displayName: 'C2',
                type: 'Reference',
                model: 'c2Model',
                options: [],
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/c4/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('c4', {
                url: '/c4s?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'c4.tpl.html',
                        controller: 'c4Ctrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                c2: r.all('c2s').getList()
                            });
                        }],
                    model: 'c4Model',
                    c4s: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('c4List', {
                url: '/list',
                parent: 'c4',
                views: {
                    c4View: {
                        templateUrl: basePath + 'list/c4.list.tpl.html',
                        controller: 'c4ListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c4New', {
                url: '/new',
                parent: 'c4',
                views: {
                    c4View: {
                        templateUrl: basePath + 'new/c4.new.tpl.html',
                        controller: 'c4NewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c4Instance', {
                url: '/{c4Id:int}',
                abstract: true,
                parent: 'c4',
                views: {
                    c4View: {
                        template: '<div ui-view="c4InstanceView"></div>'
                    }
                },
                resolve: {
                    c4: ['c4s', '$stateParams', function (c4s, $params) {
                            return c4s.get($params.c4Id);
                        }]
                }
            });
            $sp.state('c4Detail', {
                url: '/details',
                parent: 'c4Instance',
                views: {
                    c4InstanceView: {
                        templateUrl: baseInstancePath + 'detail/c4.detail.tpl.html',
                        controller: 'c4DetailCtrl'
                    }
                }
            });
            $sp.state('c4Edit', {
                url: '/edit',
                sticky: true,
                parent: 'c4Instance',
                views: {
                    c4InstanceView: {
                        templateUrl: baseInstancePath + 'edit/c4.edit.tpl.html',
                        controller: 'c4EditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c4Delete', {
                url: '/delete',
                parent: 'c4Instance',
                views: {
                    c4InstanceView: {
                        templateUrl: baseInstancePath + 'delete/c4.delete.tpl.html',
                        controller: 'c4DeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
