/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 11/15/22
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function () {
    var dataList_coquan = localStorage.getItem('data_coquan');

    var jsoncq;
    if (dataList_coquan != null && dataList_coquan != "") {
        jsoncq = JSON.parse(localStorage.getItem('data_coquan'));
    } else {
        jsoncq = 0;
    }
    var coquan = jsoncq;
    console.log("cơ quan: " + coquan);
    if (coquan == 0 && coquan == "") {

        $("#btn_add_address_coquan").show();
        $("#btn_address_coquan").hide();
    } else {

        $("#btn_address_coquan").show();
        $("#btn_add_address_coquan").hide();
    }
    if (home == 0 && coquan == 0) {
        $("#sua").hide();
    } else if (home != 0 || coquan != 0) {
        $("#sua").show();
    }
});
var dataList_coquan = [];
$(document).ready(function () {
    $("#btn_address_coquan").on('click', function () {
        coquan();
        var A = $("#location-1").val();
        var B = $("#location-2").val();
        var diachi = $("#diachicoquan").val();
        if (A == "" && B == "") {
            $("#location-1").val(diachi)
            $("#clear_di").show();
        } else if (A == "" && B != "") {
            $("#location-1").val(diachi)
            $("#clear_di").show();
        }
        else if (A != "" && B == "") {
            $("#location-2").val(diachi)
            $("#clear_den").show();
        }
    });


    $("#add_coquan").on('click', function () {
        if (localStorage) {
            dataList_coquan.push({
                diachicoquan: document.getElementById('ip_add_coquan').value
            });
            localStorage.setItem('data_coquan', JSON.stringify(dataList_coquan));
            $("#btn_add_address_coquan").hide()
            $("#btn_address_coquan").show()
            alert("Đã thêm địa chỉ !")
            $("#add_address-coquan").hide();
            console.log("vienne: " + dataList_coquan)
        }
        dataList_coquan = JSON.parse(localStorage.getItem('data_coquan'))
        coquan()
    });

});
function coquan() {
    $("#listcoquan").empty();
    dataList_coquan = JSON.parse(localStorage.getItem('data_coquan'));

    for (var i = 0; i < dataList_coquan.length; i++) {
        var diachi = dataList_coquan[i].diachicoquan;
        $("#diachicoquan").val(diachi);
        document.getElementById("listcoquan").innerHTML += (
            "<div class='d-flex'> " +
                "  <i class='fa fa-building icon_suadiachi' id='close_coquan' style='color: green' aria-hidden='true'>" +
                "</i>  " +
                "   <div class='d-flex flex-column w-75'>  " +
                "   <strong> Cơ quan </strong>   " +
                "   <p class='w-100' style='background: #ffffff;  ' > " + diachi +
                "</p>" +
                "   </div>   " +
                "  <div class='delete m-auto' style='cursor: pointer;' onclick='del_coquan(" + i + ")' >" +
                "  <i class='fa fa-times-circle icon_suadiachi' id='close_home' aria-hidden='true'>" +
                "</div>" +
                "</i>" +
                "   </div>");
        console.log("aaaaaaaaaaaaaa" + diachi)
    }
}
function del_coquan(index) {

    dataList_coquan.splice(index, 1);
    localStorage.setItem("data_coquan", JSON.stringify(dataList_coquan));
    coquan();
    var coquana = dataList_coquan.length;
    console.log(coquana);
    if (coquana == 0 && coquana == "") {

        $("#btn_add_address_coquan").show();
        $("#btn_address_coquan").hide();
    } else {

        $("#btn_address_coquan").show();
        $("#btn_add_address_coquan").hide();
    }
}