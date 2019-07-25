$(document).ready(function () {
    $('select').formSelect();
});

var datePickerOptions = {
    format: 'dd-mm-yyyy'
};

$(document).ready(function () {
    $('.datepicker').datepicker(datePickerOptions);
});

var timepickerOptions = {
    twelveHour: false
};

$(document).ready(function(){
    $('.timepicker').timepicker(timepickerOptions);
});

window.onload = function () {
    var elems = document.querySelectorAll("input[type=range]");
    M.Range.init(elems);
};