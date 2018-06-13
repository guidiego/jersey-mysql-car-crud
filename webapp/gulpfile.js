var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');

gulp.task("js", function () {
    gulp.src("src/**/*.js")
        .pipe(concat("app.min.js"))
        .pipe(uglify())
        .pipe(gulp.dest("dist"))
})

gulp.task("html", function () {
    gulp.src("src/**/*.html")
        .pipe(gulp.dest("dist"))
})

gulp.task("default", ["js", "html"]);

gulp.task("watch", function () {
    gulp.watch("src/**/*.js", ["js"]);
    gulp.watch("src/**/*.html", ["html"]);
})