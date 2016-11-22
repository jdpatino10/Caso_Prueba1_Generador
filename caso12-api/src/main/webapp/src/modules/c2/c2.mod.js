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
    var mod = ng.module('c2Module', ['ngCrud', 'ui.router']);

    mod.constant('c2Model', {
        name: 'c2',
        displayName: 'C2',
		url: 'c2s',
        fields: {
            test: {
                displayName: 'Test',
                type: 'String',
                required: true
            },
            number: {
                displayName: 'Number',
                type: 'Integer',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/c2/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('c2', {
                url: '/c2s?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'c2.tpl.html',
                        controller: 'c2Ctrl'
                    }
                },
                resolve: {
                    model: 'c2Model',
                    c2s: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('c2List', {
                url: '/list',
                parent: 'c2',
                views: {
                    c2View: {
                        templateUrl: basePath + 'list/c2.list.tpl.html',
                        controller: 'c2ListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c2New', {
                url: '/new',
                parent: 'c2',
                views: {
                    c2View: {
                        templateUrl: basePath + 'new/c2.new.tpl.html',
                        controller: 'c2NewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c2Instance', {
                url: '/{c2Id:int}',
                abstract: true,
                parent: 'c2',
                views: {
                    c2View: {
                        template: '<div ui-view="c2InstanceView"></div>'
                    }
                },
                resolve: {
                    c2: ['c2s', '$stateParams', function (c2s, $params) {
                            return c2s.get($params.c2Id);
                        }]
                }
            });
            $sp.state('c2Detail', {
                url: '/details',
                parent: 'c2Instance',
                views: {
                    c2InstanceView: {
                        templateUrl: baseInstancePath + 'detail/c2.detail.tpl.html',
                        controller: 'c2DetailCtrl'
                    }
                }
            });
            $sp.state('c2Edit', {
                url: '/edit',
                sticky: true,
                parent: 'c2Instance',
                views: {
                    c2InstanceView: {
                        templateUrl: baseInstancePath + 'edit/c2.edit.tpl.html',
                        controller: 'c2EditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c2Delete', {
                url: '/delete',
                parent: 'c2Instance',
                views: {
                    c2InstanceView: {
                        templateUrl: baseInstancePath + 'delete/c2.delete.tpl.html',
                        controller: 'c2DeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('c2C4', {
                url: '/c4',
                parent: 'c2Detail',
                abstract: true,
                views: {
                    c2ChieldView: {
                        template: '<div ui-view="c2C4View"></div>'
                    }
                },
                resolve: {
                    c4: ['c2', function (c2) {
                            return c2.getList('c4');
                        }],
                    model: 'c4Model'
                }
            });
            $sp.state('c2C4List', {
                url: '/list',
                parent: 'c2C4',
                views: {
                    c2C4View: {
                        templateUrl: baseInstancePath + 'c4/list/c2.c4.list.tpl.html',
                        controller: 'c2C4ListCtrl'
                    }
                }
            });
            $sp.state('c2C4Edit', {
                url: '/edit',
                parent: 'c2C4',
                views: {
                    c2C4View: {
                        templateUrl: baseInstancePath + 'c4/edit/c2.c4.edit.tpl.html',
                        controller: 'c2C4EditCtrl',
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
