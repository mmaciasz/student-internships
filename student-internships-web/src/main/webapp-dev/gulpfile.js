'use strict';

/**
 * Taski do zbudowania frontendu:
 * - clean        - czy�ci katalog docelowy src/main/resources/static
 * - build-debug  - buduje strukture aplikacji w src/main/resources/static
 * - build        - buduje strukture aplikacji w src/main/resources/static + minifikuje css i pakuje js
 * - watch        - monitoruje wszystkie pliki js,css i html w katalogu app oraz pliki index.html i login.html
 *                  je�li ulegn� zmianie automatycznie uruchamia task build-debug -- przydatny tylko w czasie developmentu
 */

var gulp = require('gulp');
var inject = require('gulp-inject');
var watch = require('gulp-watch');
var batch = require('gulp-batch');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var cleanCSS = require('gulp-clean-css');
var del = require('del');
var merge = require('merge-stream');

var cfg = require('./gulp.config.json');

cfg.dist.dir.relativeBaseAppPath = cfg.dist.dir.relativeBasePath + cfg.dist.dir.app;
cfg.dist.dir.assets              = cfg.dist.dir.relativeBasePath + 'assets/';
cfg.dist.dir.fonts              = cfg.dist.dir.relativeBasePath + 'fonts/';
cfg.dist.filename.js             = cfg.dist.filename.main + '.js';
cfg.dist.filename.css            = cfg.dist.filename.main + '.css';
cfg.dist.filename.minJs          = cfg.dist.filename.main + '-min.js';
cfg.dist.filename.minCss         = cfg.dist.filename.main + '-min.css';


gulp.task('watch', function () {
    watch(cfg.filesToWatch, batch(function (events, done) {
        gulp.start('build-debug', done);
    }));
});

gulp.task('clean', function(){
    return del([
        cfg.dist.dir.relativeBasePath +'**/*',
        '!'+cfg.dist.dir.relativeBasePath +'.gitignore'
    ], {force: true});
});

gulp.task('create-single-css', function(){
    return gulp.src( cfg.includes.css )
        .pipe(concat( cfg.dist.filename.css ))
        .pipe(gulp.dest( cfg.dist.dir.relativeBaseAppPath ));
});

gulp.task('create-single-js', function(){
    return gulp.src( cfg.includes.js )
        .pipe(concat( cfg.dist.filename.js ))
        .pipe(gulp.dest( cfg.dist.dir.relativeBaseAppPath ));
});

gulp.task('copy-assets', function(){
    return gulp.src([
        'assets/**/*'
    ]).pipe( gulp.dest( cfg.dist.dir.assets ) );
});

gulp.task('copy-fonts', function(){
    return gulp.src([
        'bower_components/bootstrap/fonts/*'
    ]).pipe( gulp.dest( cfg.dist.dir.fonts ) );
});

gulp.task('copy-view-html-files', function(){
    return gulp.src('app/**/*.html')
        .pipe(gulp.dest( cfg.dist.dir.relativeBaseAppPath ));
});

gulp.task('create-index', function(){
    var fileToInject = [
        cfg.dist.dir.app + cfg.dist.filename.js,
        cfg.dist.dir.app + cfg.dist.filename.css
    ];

    return gulp.src('index.html')
        .pipe(inject(gulp.src(fileToInject, {read: false}), {addRootSlash: false}))
        .pipe(gulp.dest( cfg.dist.dir.relativeBasePath ));
});

gulp.task('create-index-debug', ['create-index'], function(){
    return gulp.src(cfg.dist.dir.relativeBasePath+'index.html')
        .pipe(rename({basename: "index-debug"}))
        .pipe(gulp.dest( cfg.dist.dir.relativeBasePath ));
});


gulp.task('build-debug', [
    'create-single-css',
    'create-single-js',
    'copy-assets',
    'copy-fonts',
    'copy-view-html-files',
    'create-index-debug'
]);


gulp.task('minify-css', ['build-debug'], function(){
    return gulp.src( cfg.dist.dir.relativeBaseAppPath+'app.css' )
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(rename({
            suffix: '-min',
            extname: '.css'
        }))
        .pipe(gulp.dest( cfg.dist.dir.relativeBaseAppPath ));
});

gulp.task('uglify-js', ['build-debug'], function(){
    return gulp.src( cfg.dist.dir.relativeBaseAppPath+'app.js' )
        .pipe(uglify())
        .pipe(rename({
            suffix: '-min',
            extname: ".js"
        }))
        .pipe(gulp.dest( cfg.dist.dir.relativeBaseAppPath ));
});


gulp.task('prepare-prd-index', ['minify-css', 'uglify-js'], function(){
    var fileToInject = [
        cfg.dist.dir.app + cfg.dist.filename.minJs,
        cfg.dist.dir.app + cfg.dist.filename.minCss
    ];

    return gulp.src('index.html')
        .pipe( inject(gulp.src(fileToInject, {read: false}), {addRootSlash: false}) )
        .pipe(gulp.dest(cfg.dist.dir.relativeBasePath));

});

gulp.task('build', ['prepare-prd-index']);

gulp.task('default', ['build']);