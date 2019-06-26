$(window).on('resize', function () {
    var video = $('#myVideo');
    var image = $('#myImage');
    var screenWidth = $(window).width();
    if (screenWidth < 700) {
        video.removeAttr('autoplay');
        video.css("visibility", "hidden");
        image.css("visibility", "visible");
    } else {
        video.css("visibility", "visible");
        image.css("visibility", "hidden");
        video.attr('autoplay');
    }
}).trigger('resize');