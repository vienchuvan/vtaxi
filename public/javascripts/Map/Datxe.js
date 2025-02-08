/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 9/13/22
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {
    var A = $("#location-1").val();
    var B = $("#location-2").val();

    if (A == "" && B == "") {
        $("#clear_di").hide();
        $("#clear_den").hide();

    }
    $("#location-1").on('keyup', function () {
        $("#clear_di").show();
    });
    $("#location-2").on('keyup', function () {
        $("#clear_den").show();
    });


});


$(document).ready(function () {


    $("#close_coquan").on('click', function () {
        $("#add_address-coquan").hide();
    });
    $("#close_home").on('click', function () {
        $("#add_address-home").hide();
        $("#listdiachi").hide();
    });
    $("#close_gannhat").on('click', function () {
        $("#address-gannhat").hide();

    });

    $("#close_listdiachi").on('click', function () {
        $("#listdiachi").hide();
    });

    $("#inpus_search").on('keyup', function () {
        var checknull = $("#inpus_search").val();

        if (checknull == "") {
            $("#loadding").show();
            $("#all").show();
            $("#search_qr").hide();
        }

    });
    $("#sua").on('click', function () {
        $("#listdiachi").show();
        home();
        coquan();
    });


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

    $('body').on('click', '.btn-choose_xe', function () {
        $('.click').removeClass("active_choose-xe");
        $(this).addClass("active_choose-xe");

    });
    $("#btn-choose-thanhtoan").on("click", function () {
        $('.click1').removeClass("active_choose-xe");
        $(this).addClass("active_choose-xe");
    })

    $(".open-form").on("click", function () {

        $(".form-list-td-xe").show()
        $(".close-form").show()
    });
    $(".close-form").on("click", function () {
        $(".form-list-td-xe").hide()
        $(".close-form").hide()
    });

    $("#btn_datxe").click(function () {
        $(".toast_warring").stop().fadeIn(400).delay(3000).fadeOut(500); //fade out after 3 seconds
    });
    $("#btn_goiho").click(function () {
        $(".toast_warring").stop().fadeIn(400).delay(3000).fadeOut(500); //fade out after 3 seconds
    });

    $(".toast_warring").stop().fadeIn(400).delay(3000).fadeOut(500); //fade

    $("#btn_add_address_home").on('click', function () {

        $("#add_address-home").show();
        $("#add_address-coquan").hide();
        $("#address-gannhat").hide();
    })
    $("#btn_add_address_coquan").on('click', function () {
        $("#add_address-coquan").show();
        $("#add_address-home").hide();
        $("#address-gannhat").hide();
    })
    $("#btn_address_gannhat").on('click', function () {
        $("#address-gannhat").show();
        $("#add_address-coquan").hide();
        $("#add_address-home").hide();
        gannhat();
    })
    $("#btn_add_address_gannhat").on('click', function () {
        $("#btn_add_address_gannhat").hide();
        $("#btn_closse_address_gannhat").show();
        $("#frame_add_gannhat").show();
    });
    $("#btn_closse_address_gannhat").on('click', function () {
        $("#btn_add_address_gannhat").show();
        $("#btn_closse_address_gannhat").hide();
        $("#frame_add_gannhat").hide();
    });


});


$(document).ready(function () {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);

    }
    function showPosition(position) {
        var kinhdo = position.coords.latitude;
        var vido = position.coords.longitude;
        $("#latDon").val(kinhdo)
        $("#lngDon").val(vido)


        var myLatLng = { lat: +position.coords.latitude, lng: +position.coords.longitude};

        /*  var myLatLng = {
         lat: 38.736946,
         lng: -9.142685
         };*/
        var mapOptions = {
            center: myLatLng,
            zoom: 13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };


        document.getElementById("output").style.display = "none";


        var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);

        var vitri1 = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: 'https://maps.google.com/maps?saddr=&daddr=' + kinhdo + "," + vido + '&sensor=TRUE',
            icon: '/assets/images/icon/icon.png',
            draggable: true
        });
      /*  var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + kinhdo + "," + vido + "&sensor=false&key=AIzaSyBQFnM3EVH2jduYROPxdwgvKwg4RDIxobA";
        $.getJSON(url, function (data) {

            var adress = data.results[0].formatted_address;
            *//*$("#location-1").val(adress);*//*
        });*/
        var directionsService = new google.maps.DirectionsService();

        var directionsDisplay = new google.maps.DirectionsRenderer();


        directionsDisplay.setMap(map);

//        check điều kiện tìm kiếm
        var A = $("#location-1").val();
        var B = $("#location-2").val();


        $("#btn_timkiem_xe").on('click', function () {


            var geocoder = new google.maps.Geocoder();
            var address = $("#location-2").val()

            geocoder.geocode({ 'address': address}, function (results, status) {

                if (status == google.maps.GeocoderStatus.OK) {
                    var latitude = results[0].geometry.location.lat();
                    var longitude = results[0].geometry.location.lng();
                    $("#latDen").val(latitude);
                    $("#lngDen").val(longitude);
                }
            });
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
                    var time = ($("#so_phut").val().replace('mins', '') ).replace('phút','');
                      /*  var rep_time = time.replace('phút','');*/
                    var search = "hour";
                    var searchgio = "giờ";
                    var searchohut = "phút"
                    var hours;
                    var minus;
                    var so_phut;
                    if (time.toLowerCase().indexOf(search.toLowerCase()) != -1) {
                        hours = time.split("hour")[0];
                        minus = (time.split("hour")[1]).replace('s','');
                        so_phut = ((parseInt(hours) * 60) + parseInt(minus));
                    }  else  if(time.toLowerCase().indexOf(searchgio.toLowerCase()) != -1){
                        hours = time.split("giờ")[0];
                        minus = (time.split("giờ")[1]).replace('phút','');
                        so_phut = ((parseInt(hours) * 60) + parseInt(minus));
                    }  else{
                        so_phut = time;
                    }


                    /*  if (hours =="" || hours =="undefined"){
                     hours = 0;
                     } else if(minus =="" || minus =="undefined"){
                     minus = 0;
                     }*/

                    console.log("------------------" + so_phut);
                    var so_km2 = $("#so_km").val().replace('km', '');
                    var search3 = ",";
                    var so_km = "";
                    console.log("so_km 1 " + so_km2)
                    if (so_km2.toLowerCase().indexOf(search3.toLowerCase()) != -1) {
                        so_km = so_km2.replace(',', '.');
                        console.log("so_km 2 " + so_km)
                      /*  if(so_km > 1000 ){
                            alert("vượt quá số km quy định")
                        }*/
                    } else {
                        so_km = so_km2;
                       /* if(so_km > "999" && so_km < "1000"){
                            alert("vượt quá số km quy định")
                        }
*/
                    }

                    var latDon = $("#latDon").val();
                    var lngDon = $("#lngDon").val();
                    var diachidon = $("#location-1").val();
                    var latden = $("#latDen").val();
                    var lngden = $("#lngDen").val();
                    var diachiden = $("#location-2").val();
                    console.log(so_km + " " + latDon + " " + lngDon + " " + diachidon + " " + latden + " " + lngden + " " + diachiden +""+ so_phut)
                    $.ajax({
                        type: "POST",
                        data: {
                            so_phut: so_phut,
                            so_km: so_km,
                            latDon: latDon,
                            lngDon: lngDon,
                            diachidon: diachidon,
                            latden: latden,
                            lngden: lngden,
                            diachiden: diachiden
                        },
                        url: "/goixengay",

                        success: function (data1) {


                            $("#render_banggia").empty();
                            $("#render_banggia").append(data1);
                            $("#cuocxe").show();
                            $("#btn_timkiem_xe").hide();


                        }
                    });
                    directionsDisplay.setDirections(result);
                } else {

                    directionsDisplay.setDirections({
                        routes: []
                    });

                    map.setCenter(myLatLng);


                    alert("Không tìm thấy đường! Vui lòng thử lại!");
                    $("#btn_timkiem_xe").show();

                    clearRoute();
                }
            });


        });

// Clear results

        $("#btn_goilai").on('click', function () {
            $("#cuocxe").hide();
            $("#btn_timkiem_xe").show();

            document.getElementById("output").style.display = "none";
            document.getElementById("location-1").value = "";
            document.getElementById("location-2").value = "";
            /* document.getElementById("ip_add_home").value = "";*/
            directionsDisplay.setDirections({
                routes: []
            });

        });
        $("#clear_di").on('click', function () {
            $("#clear_di").hide();

            $("#cuocxe").hide();
            $("#btn_timkiem_xe").show();

            document.getElementById("output").style.display = "none";
            document.getElementById("location-1").value = "";

            /* document.getElementById("ip_add_home").value = "";*/
            directionsDisplay.setDirections({
                routes: []
            });

        });
        $("#clear_den").on('click', function () {
            $("#cuocxe").hide();
            $("#clear_den").hide();
            $("#btn_timkiem_xe").show();

            document.getElementById("output").style.display = "none";
            document.getElementById("location-2").value = "";

            /* document.getElementById("ip_add_home").value = "";*/
            directionsDisplay.setDirections({
                routes: []
            });

        });


        var options = {};

        var input1 = document.getElementById("location-1");
        var autocomplete1 = new google.maps.places.Autocomplete(input1, options);

        var input2 = document.getElementById("location-2");
        var autocomplete2 = new google.maps.places.Autocomplete(input2, options);


        var input3 = document.getElementById("ip_add_home");
        var autocomplete3 = new google.maps.places.Autocomplete(input3, options);

        var input4 = document.getElementById("ip_add_coquan");
        var autocomplete4 = new google.maps.places.Autocomplete(input4, options);

        var input5 = document.getElementById("ip_add_gannhat");
        var autocomplete5 = new google.maps.places.Autocomplete(input5, options);
        /*  var kinhdo= autocomplete2.position.coords.latitude;
         $("#latDen").val(kinhdo)*/
    }
});





