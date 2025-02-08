/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/22/22
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function(){
    $("#car_hn").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hn").show();
        $("#frame_taxi_hn").hide();
        $("#frame_7cho_hn").hide();
        $("#frame_xemay_hn").hide();
    });
    $("#taxi_hn").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hn").hide();
        $("#frame_taxi_hn").show();
        $("#frame_7cho_hn").hide();
        $("#frame_xemay_hn").hide();
    });
    $("#7cho_hn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_hn").hide();
        $("#frame_taxi_hn").hide();
        $("#frame_7cho_hn").show();
        $("#frame_xemay_hn").hide();
    });
    $("#xemay_hn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_hn").hide();
        $("#frame_taxi_hn").hide();
        $("#frame_7cho_hn").hide();
        $("#frame_xemay_hn").show();
    });

//--------------------------------hcm___________________

    $("#car_hcm").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hcm").show();
        $("#frame_taxi_hcm").hide();
        $("#frame_7cho_hcm").hide();
        $("#frame_xemay_hcm").hide();
    });
    $("#taxi_hcm").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hcm").hide();
        $("#frame_taxi_hcm").show();
        $("#frame_7cho_hcm").hide();
        $("#frame_xemay_hcm").hide();
    });
    $("#7cho_hcm").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_hcm").hide();
        $("#frame_taxi_hcm").hide();
        $("#frame_7cho_hcm").show();
        $("#frame_xemay_hcm").hide();
    });
    $("#xemay_hcm").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_hcm").hide();
        $("#frame_taxi_hcm").hide();
        $("#frame_7cho_hcm").hide();
        $("#frame_xemay_hcm").show();
    });

// ----------------------------thanh hoa------------------

    $("#car_th").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_th").show();
        $("#frame_taxi_th").hide();
        $("#frame_7cho_th").hide();
        $("#frame_xemay_th").hide();
    });
    $("#taxi_th").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_th").hide();
        $("#frame_taxi_th").show();
        $("#frame_7cho_th").hide();
        $("#frame_xemay_th").hide();
    });
    $("#7cho_th").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_th").hide();
        $("#frame_taxi_th").hide();
        $("#frame_7cho_th").show();
        $("#frame_xemay_th").hide();
    });
    $("#xemay_th").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_th").hide();
        $("#frame_taxi_th").hide();
        $("#frame_7cho_th").hide();
        $("#frame_xemay_th").show();
    });

//  ----------------nghe an-----------------
    $("#car_na").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_na").show();
        $("#frame_taxi_na").hide();
        $("#frame_7cho_na").hide();
        $("#frame_xemay_na").hide();
    });
    $("#taxi_na").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_na").hide();
        $("#frame_taxi_na").show();
        $("#frame_7cho_na").hide();
        $("#frame_xemay_na").hide();
    });
    $("#7cho_na").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_na").hide();
        $("#frame_taxi_na").hide();
        $("#frame_7cho_na").show();
        $("#frame_xemay_na").hide();
    });
    $("#xemay_na").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_na").hide();
        $("#frame_taxi_na").hide();
        $("#frame_7cho_na").hide();
        $("#frame_xemay_na").show();
    });

//    -----------------------------------hue---------------------------------

    $("#car_hu").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hu").show();
        $("#frame_taxi_hu").hide();
        $("#frame_7cho_hu").hide();
        $("#frame_xemay_hu").hide();
    });
    $("#taxi_hu").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hu").hide();
        $("#frame_taxi_hu").show();
        $("#frame_7cho_hu").hide();
        $("#frame_xemay_hu").hide();
    });
    $("#7cho_hu").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_hu").hide();
        $("#frame_taxi_hu").hide();
        $("#frame_7cho_hu").show();
        $("#frame_xemay_hu").hide();
    });
    $("#xemay_hu").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_hu").hide();
        $("#frame_taxi_hu").hide();
        $("#frame_7cho_hu").hide();
        $("#frame_xemay_hu").show();
    });

//------------------------------------hai phong----------------


    $("#car_hp").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hp").show();
        $("#frame_taxi_hp").hide();
        $("#frame_7cho_hp").hide();
        $("#frame_xemay_hp").hide();
    });
    $("#taxi_hp").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_hp").hide();
        $("#frame_taxi_hp").show();
        $("#frame_7cho_hp").hide();
        $("#frame_xemay_hp").hide();
    });
    $("#7cho_hp").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_hp").hide();
        $("#frame_taxi_hp").hide();
        $("#frame_7cho_hp").show();
        $("#frame_xemay_hp").hide();
    });
    $("#xemay_hp").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_hp").hide();
        $("#frame_taxi_hp").hide();
        $("#frame_7cho_hp").hide();
        $("#frame_xemay_hp").show();
    });

//    ---------------------------------quang ning--------------------
    $("#car_qn").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_qn").show();
        $("#frame_taxi_qn").hide();
        $("#frame_7cho_qn").hide();
        $("#frame_xemay_qn").hide();
    });
    $("#taxi_qn").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_qn").hide();
        $("#frame_taxi_qn").show();
        $("#frame_7cho_qn").hide();
        $("#frame_xemay_qn").hide();
    });
    $("#7cho_qn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_qn").hide();
        $("#frame_taxi_qn").hide();
        $("#frame_7cho_qn").show();
        $("#frame_xemay_qn").hide();
    });
    $("#xemay_qn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_qn").hide();
        $("#frame_taxi_qn").hide();
        $("#frame_7cho_qn").hide();
        $("#frame_xemay_qn").show();
    });


//    ------------------------------  da nang----------------

    $("#car_dn").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").show();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_dn").show();
        $("#frame_taxi_dn").hide();
        $("#frame_7cho_dn").hide();
        $("#frame_xemay_dn").hide();
    });
    $("#taxi_dn").on("click", function () {

        $(".car_noactive").show();
        $(".taxi_noactive").hide();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").show();
        $(".7cho_active").hide();
        $(".xemay_active").hide();

        $("#frame_4cho_dn").hide();
        $("#frame_taxi_dn").show();
        $("#frame_7cho_dn").hide();
        $("#frame_xemay_dn").hide();
    });
    $("#7cho_dn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").hide();
        $(".xemay_noactive").show();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").show();
        $(".xemay_active").hide();

        $("#frame_4cho_dn").hide();
        $("#frame_taxi_dn").hide();
        $("#frame_7cho_dn").show();
        $("#frame_xemay_dn").hide();
    });
    $("#xemay_dn").on("click", function () {
        $(".car_noactive").show();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").hide();


        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();

        $("#frame_4cho_dn").hide();
        $("#frame_taxi_dn").hide();
        $("#frame_7cho_dn").hide();
        $("#frame_xemay_dn").show();
    });

});