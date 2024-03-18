let map;
let marker;
let searchBox;

function initMap() {
    // Coordinates for Lahore
    const lahore = { lat: 31.5497, lng: 74.3436 };

    map = new google.maps.Map(document.getElementById('map'), {
        center: lahore,
        zoom: 12 // Zoom level adjusted for better visibility
    });

    marker = new google.maps.Marker({
        position: lahore,
        map: map,
        draggable: true
    });

    // Create the search box and link it to the UI element
    const input = document.getElementById('search');
    searchBox = new google.maps.places.SearchBox(input);

    // Bias the SearchBox results towards current map's viewport
    map.addListener('bounds_changed', function() {
        searchBox.setBounds(map.getBounds());
    });

    searchBox.addListener('places_changed', function() {
        const places = searchBox.getPlaces();
        if (places.length == 0) {
            return;
        }
        const place = places[0];

        // Center map on the selected place
        map.panTo(place.geometry.location);
        map.setZoom(15); // Adjust zoom level as needed

        // Move marker to the selected place
        marker.setPosition(place.geometry.location);

        // Update coordinates
        updateCoordinates(place.geometry.location.lat(), place.geometry.location.lng());
    });

    google.maps.event.addListener(marker, 'dragend', function() {
        updateCoordinates(marker.getPosition().lat(), marker.getPosition().lng());
    });

    google.maps.event.addListener(map, 'click', function(event) {
        marker.setPosition(event.latLng);
        updateCoordinates(event.latLng.lat(), event.latLng.lng());
    });

    // Update coordinates initially for Lahore
    updateCoordinates(lahore.lat, lahore.lng);
}

function updateCoordinates(latitude, longitude) {
    document.getElementById('latitude').textContent = 'Latitude: ' + latitude.toFixed(6);
    document.getElementById('longitude').textContent = 'Longitude: ' + longitude.toFixed(6);
}
