var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');

gulp.task("js", function () {
    gulp.src("src/**/*")
        .pipe(concat("app.min.js"))
        .pipe(uglify())
        .pipe(gulp.dest("dist"))
})