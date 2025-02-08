/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/21/22
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function () {
    $("#car").on("click", function () {
        $(".car_noactive").hide();
        $(".taxi_noactive").show();
        $(".7cho_noactive").show();
        $(".xemay_noactive").show();
        $("#Form_Dangkyxe").show();
        $("#Form_Dangkyxemay").hide();


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
        $("#Form_Dangkyxe").hide();
        $("#Form_Dangkyxemay").show();

        $(".car_active").hide();
        $(".taxi_active").hide();
        $(".7cho_active").hide();
        $(".xemay_active").show();
    });



    $(".by_default").click(function(){
        $(this).parent().toggleClass("active");
        })

    $(".options li").click(function(){
        var defaultShare = $(this).html();
        $(".by_default li").html(defaultShare);

        $(this).parents(".box").removeClass("active");

        })

    $(".by_default_Xe").click(function(){
        $(this).parent().toggleClass("active_Xe");

    })  ;
    $(".options_Xe li").click(function(){
        var defaultShare = $(this).html();
        $(".by_default_Xe li").html(defaultShare);

        $(this).parents(".box_Xe").removeClass("active_Xe");
    })
});