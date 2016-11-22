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
    var mod = ng.module('c1Module', ['ngCrud', 'ui.router']);

    mod.constant('c1Model', {
        name: 'c1',
        displayName: 'C1',
		url: 'c1s',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            number: {
                displayName: 'Number',
                type: 'Integer',
                required: true
            },
            date: {
                displayName: 'Date',
                type: 'Date',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/c1/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('c1', {
                url: '/c1s?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'c1.tpl.html',
                        controller: 'c1Ctrl'
                    }
                },
                resolve: {
                    model: 'c1Model',
                    c1s: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('c1List', {
                url: '/list',
                parent: 'c1',
                views: {
                    c1View: {
                        templateUrl: basePath + 'list/c1.list.tpl.html',
                        controller: 'c1ListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c1New', {
                url: '/new',
                parent: 'c1',
                views: {
                    c1View: {
                        templateUrl: basePath + 'new/c1.new.tpl.html',
                        controller: 'c1NewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c1Instance', {
                url: '/{c1Id:int}',
                abstract: true,
                parent: 'c1',
                views: {
                    c1View: {
                        template: '<div ui-view="c1InstanceView"></div>'
                    }
                },
                resolve: {
                    c1: ['c1s', '$stateParams', function (c1s, $params) {
                            return c1s.get($params.c1Id);
                        }]
                }
            });
            $sp.state('c1Detail', {
                url: '/details',
                parent: 'c1Instance',
                views: {
                    c1InstanceView: {
                        templateUrl: baseInstancePath + 'detail/c1.detail.tpl.html',
                        controller: 'c1DetailCtrl'
                    }
                }
            });
            $sp.state('c1Edit', {
                url: '/edit',
                sticky: true,
                parent: 'c1Instance',
                views: {
                    c1InstanceView: {
                        templateUrl: baseInstancePath + 'edit/c1.edit.tpl.html',
                        controller: 'c1EditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c1Delete', {
                url: '/delete',
                parent: 'c1Instance',
                views: {
                    c1InstanceView: {
                        templateUrl: baseInstancePath + 'delete/c1.delete.tpl.html',
                        controller: 'c1DeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c1C3', {
                url: '/c3',
                parent: 'c1Detail',
                abstract: true,
                views: {
                    c1ChieldView: {
                        template: '<div ui-view="c1C3View"></div>'
                    }
                },
                resolve: {
                    c3: ['c1', function (c1) {
                            return c1.getList('c3');
                        }],
                    model: 'c3Model'
                }
            });
            $sp.state('c1C3List', {
                url: '/list',
                parent: 'c1C3',
                views: {
                    c1C3View: {
                        templateUrl: baseInstancePath + 'c3/list/c1.c3.list.tpl.html',
                        controller: 'c1C3ListCtrl'
                    }
                }
            });
            $sp.state('c1C3Edit', {
                url: '/edit',
                parent: 'c1C3',
                views: {
                    c1C3View: {
                        templateUrl: baseInstancePath + 'c3/edit/c1.c3.edit.tpl.html',
                        controller: 'c1C3EditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
	}]);
})(window.angular);
