/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 9/13/22
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {


    $("#inpus_search").on('keyup', function () {
        var checknull = $("#inpus_search").val();

        if (checknull == "") {
            $("#loadding").show();
            $("#all").show();
            $("#search_qr").hide();
        }

    });

    /* $("#googleMap").kendoMap({
     zoom: 3,
     center: [0, 0],
     layers: [{
     type: "tile",
     urlTemplate: "http://a.tile.openstreetmap.org/#= zoom #/#= x #/#= y #.png",
     attribution: "&copy; OpenStreetMap"
     }],
     click: function(e) {
     e.sender.markers.add({
     location: e.location,
     tooltip: {
     content: "Foo"
     }
     });
     }
     });*/
    $("#car").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();
    });
    $("#taxi").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();


    });
    $("#7cho").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();
    });
    $("#xemay").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();
    });


    $("#btn-menu_dichuyen").on("click", function () {
        $(".form-list-td-xe").show()
    });
    $("#btn-close").on("click", function () {
        $(".form-list-td-xe").hide()
    });

    $("#saokegiaodich-taixe").on("click", function () {
        $(".form-list-td-saoke").show()
    });
    $("#btn-close-saoke").on("click", function () {
        $(".form-list-td-saoke").hide()
    });
    $("#selecttoado").on("click", function () {
        $("#listdanhsach").show()
    });
    $("#close_huongdan").on("click", function () {
        $("#listdanhsach").hide()
    });

    $(".open-form").on("click", function () {

        $(".form-list-td-xe").show()
        $(".close-form").show()
    });
    $(".close-form").on("click", function () {
        $(".form-list-td-xe").hide()
        $(".close-form").hide()
    });

    var kinhdo = $("#kinhdo1").val();
    var vido = $("#vido1").val();


});

function myMassp() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);

    }
    function showPosition(position) {
        var kinhdo = position.coords.latitude;
        var vido = position.coords.longitude;
        $("#kinhdo1").val(kinhdo)
        $("#vido1").val(vido)



        var vitri = { lat: +position.coords.latitude, lng: +position.coords.longitude};

        var mapProp = {
            center: new google.maps.LatLng(car),
            center: new google.maps.LatLng(vitri),
            zoom: 13
        };


        var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

        var geocoder = new google.maps.Geocoder();
        var infowindow = new google.maps.InfoWindow();
        var vitri1 = new google.maps.Marker({
            position: vitri,
            map: map,
            title: 'https://maps.google.com/maps?saddr=&daddr=' + kinhdo + "," + vido + '&sensor=TRUE',
            icon: '/assets/images/icon/icon.png',

            draggable: true
        });
        var vitri2 = new google.maps.Circle({

            position: vitri,
            radius: 10 * 1000,
            strokeColor: '#00abe7',
            strokeOpacity: 2,
            strokeWeight: 3,
            fillOpacity: 0.0,

            map: map,

            center: new google.maps.LatLng(vitri)

        });


        var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + kinhdo + "," + vido + "&sensor=false&key=AIzaSyBlFdnYFlrqq9-2NzLEgzAmyYOGmILp_cU";
        $.getJSON(url, function (data) {

            var adress = data.results[0].formatted_address;
            $("#ip-vitricuatui").val(adress);
        });

        var infoWindow = new google.maps.InfoWindow();

        var input_string = "<form class='form-toado' style='display: flex;' id='pheduyet_from' action='#' method='POST'>" +

            "<div class='fr-qr ' style='display : block;'>" +
            "<div class='fr-thongtinQR'>" +
            "<div class='tt-colum'>" +
            "<strong class='txt-thongtin'>Nguyễn Khắc Dương</strong>" +
            "<strong class='txt-thongtin'>29S - 888.88</strong>" +
            "<img class='img-user'   src='https://vimass.vn/vmbank/services/media/getImage?id=Frame.png'>" +
            "</div>" +

            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "<img class='img-icon'   src='https://vimass.vn/vmbank/services/media/getImage?id=phone.png'>" +
            "<strong class='txt-thongtin' > 03216512315</strong>" +
            "</div>" +


            "<div class='d-flex flex-column'>" +
            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "<img class='img-icon'   src='https://vimass.vn/vmbank/services/media/getImage?id=caractive.png'>" +
            "<strong class='txt-thongtin' > Mai Linh</strong>" +
            "</div>" +

            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "<img class='img-icon'   src='https://vimass.vn/vmbank/services/media/getImage?id=bank.png'>" +
            "<strong class='txt-thongtin' >Số chuyến: 300</strong>" +
            "</div>" +

            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "<img class='img-icon'   src='https://vimass.vn/vmbank/services/media/getImage?id=bank.png'>" +
            "<input id='vitri-taixe' value='' disabled>" +
            "</div>" +

            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "<strong class='txt-thongtin' >Đánh giá: </strong>" +
            "  <span class='fa fa-star checked'></span>        " +
            "<span class='fa fa-star checked'></span>            " +
            "  <span class='fa fa-star checked'></span>         " +
            "   <span class='fa fa-star checked'></span>        " +
            "   <span class='fa fa-star'></span>               " +

            "</div>" +
            "<div style='DISPLAY: Flex; margin-top: 5%'>" +
            "   <p>4.1 sao trên 254 đánh giá.</p>       " +
            "   <hr style='border:3px solid #f1f1f1'>        " +
            "</div>" +

            "       <div class='row'>                       " +
            "           <div class='side'>              " +
            "               <div>5 Sao</div>          " +
            "           </div>                       " +
            "           <div class='middle'>        " +
            "               <div class='bar-container'>      " +
            "                   <div class='bar-5'></div>       " +
            "               </div>    " +
            "           </div>      " +
            "           <div class='side right'>           " +
            "               <div>150</div>       " +
            "           </div>                    " +
            "           <div class='side'>      " +
            "               <div>4 Sao</div>      " +
            "           </div>                     " +
            "           <div class='middle'>        " +
            "               <div class='bar-container'>   " +
            "                   <div class='bar-4'></div>   " +
            "               </div>            " +
            "           </div>                     " +
            "           <div class='side right'>   " +
            "               <div>63</div>       " +
            "           </div>                  " +
            "           <div class='side'>      " +
            "               <div>3 Sao</div>     " +
            "           </div>                 " +
            "           <div class='middle'>     " +
            "               <div class='bar-container'>     " +
            "                   <div class='bar-3'></div>    " +
            "               </div>         " +
            "           </div>                  " +
            "           <div class='side right'>    " +
            "               <div>15</div>         " +
            "           </div>                             " +
            "           <div class='side'>                   " +
            "               <div>2 Sao</div>                  " +
            "           </div>                                   " +
            "           <div class='middle'>                       " +
            "               <div class='bar-container'>              " +
            "                   <div class='bar-2'></div>              " +
            "               </div>                                       " +
            "           </div>                                             " +
            "           <div class='side right'>                             " +
            "               <div>6</div>                                       " +
            "           </div>                                                   " +
            "           <div class='side'>                                         " +
            "               <div>1 Sao</div>                                        " +
            "           </div>                                                         " +
            "           <div class='middle'>                                             " +
            "               <div class='bar-container'>                                    " +
            "                   <div class='bar-1'></div>                                    " +
            "               </div>                                                             " +
            "           </div>                                                                   " +
            "           <div class='side right'>                                                   " +
            "               <div>20</div>                                                            " +
            "           </div>                                                                        " +
            "       </div>" +
            "</div>" +
            "</div>" +
            " </div>" +
            " </div>" +
            " </div>" +


            "</form>";

        var vitrixe = new google.maps.InfoWindow({
            content: input_string
        });
        car.addListener("click", function () {
            vitrixe.open(map, car);
        });
        $("#btn-tt-car").on("click", function () {
            vitrixe.open(map, car);
        });
        /*   var km = 50;

         if ($("#input_Km").val() != "") {
         km = $("#input_Km").val();
         }
         vitri2.setRadius(0);

         vitri2 = new google.maps.Circle({

         position: vitri,
         radius: km * 1000,
         strokeColor: '#00abe7',
         strokeOpacity: 2,
         strokeWeight: 3,
         fillOpacity: 0.0,
         map: map,

         center: new google.maps.LatLng(vitri)

         });*/
        $("#dialog").dialog();


        var input = /** @type {HTMLInputElement} */(document.getElementById('ip-diemden'));
        var searchBox = new google.maps.places.SearchBox(input);
        var markers = [];

        google.maps.event.addListener(searchBox, 'places_changed', function () {

            var infowindow = new google.maps.InfoWindow();
            $("#places").html('');
            var places = searchBox.getPlaces();

            for (var i = 0, marker; marker = markers[i]; i++) {
                marker.setMap(null);
            }

            markers = [];
            var bounds = new google.maps.LatLngBounds();
            var marker;
            for (var i = 0, place; place = places[i]; i++) {
                var image = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                marker = new google.maps.Marker({
                    map: map,
                    icon: image,
                    title: place.name,
                    position: place.geometry.location
                });

                markers.push(marker);

                bounds.extend(place.geometry.location);
                var name = place.name;
                google.maps.event.addListener(marker, 'click', function () {

                    infowindow.close();
                    infowindow.setContent(name);
                    infowindow.open(map, marker);
                });

                $("<li>")
                    .text(place.name)
                    .appendTo("#places")
                    .click(function () {

                        new google.maps.event.trigger(marker, 'click');

                        map.setZoom(14);
                        map.setCenter(marker.getPosition());
                    });


            }

            map.fitBounds(bounds);
        });

        google.maps.event.addListener(map, 'bounds_changed', function () {
            var bounds = map.getBounds();
            searchBox.setBounds(bounds);
        });
    }

    google.maps.event.addDomListener(window, 'load', myMap);


}


$(document).ready(function () {
    var myLatLng = {
        lat: 38.736946,
        lng: -9.142685
    };
    var mapOptions = {
        center: myLatLng,
        zoom: 1,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };


    document.getElementById("output").style.display = "none";


    var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);

    var directionsService = new google.maps.DirectionsService();

    var directionsDisplay = new google.maps.DirectionsRenderer();

    directionsDisplay.setMap(map);

    $("#btn_timkiem_xe").on('click', function () {

        var request = {
            origin: document.getElementById("location-1").value,
            destination: document.getElementById("location-2").value,
            travelMode: google.maps.TravelMode.DRIVING,
            unitSystem: google.maps.UnitSystem.METRIC
        }

        // Routing
        directionsService.route(request, function (result, status) {
            if (status == google.maps.DirectionsStatus.OK) {


                document.getElementById("output").style.display = "block";
                $("#output").html(" <div class='d-flex'>" + "<div class='d-flex align-items-center'>" +
                    " <i class='fa fa-map-signs' aria-hidden='true'>" + "</i>" +
                    " <input class='ip-text' id='so_km' disabled name='so_km' value='" + result.routes[0].legs[0].distance.text + "'> " +
                    "  </div>  " +
                    " <div class='d-flex align-items-center'> " +
                    "  <i class='fa fa-clock-o'aria-hidden='true'>" +
                    " </i>   " +
                    " <input class='ip-text' id='so_phut' disabled name='so_phut' value='" + result.routes[0].legs[0].duration.text + "'>   " +
                    " </div>" +
                    " </div>"
                );

                directionsDisplay.setDirections(result);
            } else {

                directionsDisplay.setDirections({
                    routes: []
                });

                map.setCenter(myLatLng);


                alert("Không tìm thấy đường! Vui lòng thử lại!");
                clearRoute();
            }
        });

    });

// Clear results

    function clearRoute() {
        document.getElementById("output").style.display = "none";
        document.getElementById("location-1").value = "";
        document.getElementById("location-2").value = "";
        directionsDisplay.setDirections({
            routes: []
        });

    }


    var options = {};

    var input1 = document.getElementById("location-1");
    var autocomplete1 = new google.maps.places.Autocomplete(input1, options);

    var input2 = document.getElementById("location-2");
    var autocomplete2 = new google.maps.places.Autocomplete(input2, options);
});






/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/26/22
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
