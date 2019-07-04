$(document).ready(function () {
    $('textarea#description').characterCounter();
});

$(document).ready(function () {
    $('select').formSelect();
});

$(document).ready(function () {
    $('.datepicker').datepicker();
});

window.onload = function () {
    var elems = document.querySelectorAll("input[type=range]");
    M.Range.init(elems);
};