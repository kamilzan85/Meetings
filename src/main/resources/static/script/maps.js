var map;
var marker;
var lat;
var lng;

function createMap() {
    var options = {
        center: {
            lat: 43.654,
            lng: -79.383
        },
        disableDefaultUI: true,
        zoom: 15
    };
    map = new google.maps.Map(document.getElementById('map'), options);

    var input = document.getElementById("search");
    var searchBox = new google.maps.places.SearchBox(input);

    map.addListener('bounds-changed', function () {
        searchBox.setBounds(map.getBound());
    });
    var markers = [];

    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length === 0)
            return;

        markers.forEach(function (m) {
            m.setMap(null);
        });
        markers = [];

        var bounds = new google.maps.LatLngBounds();


        places.forEach(function (p) {
            if (!p.geometry)
                return;

            marker = new google.maps.Marker({
                map: map,
                title: p.name,
                // position: p.geometry.location
            });
            marker.setPosition(new google.maps.LatLng(p.geometry.location.lat(), p.geometry.location.lng()));
            markers.push(marker);
            lat = marker.getPosition().lat();
            lng = marker.getPosition().lng();

            $('#lat').val(lat);
            $('#lng').val(lng);

            if (p.geometry.viewport)
                bounds.union(p.geometry.viewport);
            else
                bounds.extend(p.geometry.location);
        });
        map.fitBounds(bounds);
    });
}

var message = /*[[${message}]]*/ 'default';
console.log(message);