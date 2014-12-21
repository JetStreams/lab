require.config({
    baseUrl: './js',
    paths: {
        jquery: "jquery/jquery-1.11.1.min",
        jqueryui: "jquery/jquery-ui.min",
        primeui: "primeui/primeui-1.1.min",
        three: "threejs/three.min",
        stats: "threejs/stats.min",
        detector: "threejs/Detector",
        stlLoader: "threejs/STLLoader",
        typedGeometry: "threejs/TypedGeometry",
        jetstream: "app/jetstreams",
        webglUi: "appwebgl-ui"
        
    },
    shim: {
        primeui: {
            deps: ["jquery", "jqueryui"],
            exports: "$"
        },
        three: { exports: 'THREE' },
        detector: {exports: 'Detector'},
        stats: {exports: 'Stats'}
    }
});

require(["primeui"], function ($) {



});