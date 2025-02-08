/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 11/15/22
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function () {
    var dataList_home = localStorage.getItem('data_home');

    var jsonhome;
    if (dataList_home != null && dataList_home != "") {
        jsonhome = JSON.parse(localStorage.getItem('data_home'));
    } else {
        jsonhome = 0;
    }
    var home = jsonhome;
    console.log("home: " + home);
    if (home == 0 && home == "") {

        $("#btn_add_address_home").show();
        $("#btn_address_home").hide();
    } else {

        $("#btn_address_home").show();
        $("#btn_add_address_home").hide();
    }
    if (home == 0 && home == 0) {
        $("#sua").hide();
    } else if (home != 0 || home != 0) {
        $("#sua").show();
    }
});
var dataList_home = [];
$(document).ready(function () {
    $("#btn_address_home").on('click', function () {
        home();
        var A = $("#location-1").val();
        var B = $("#location-2").val();
        var diachi = $("#diachinha").val();
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


    $("#add_home").on('click', function () {
        if (localStorage) {
            dataList_home.push({
                diachihome: document.getElementById('ip_add_home').value
            });
            localStorage.setItem('data_home', JSON.stringify(dataList_home));
            $("#btn_add_address_home").hide()
            $("#btn_address_home").show()
            alert("Đã thêm địa chỉ !")
            $("#add_address-home").hide();
            console.log("vienne: " + dataList_home)
        }
        dataList_home = JSON.parse(localStorage.getItem('data_home'))
        home()
    });

});
function home() {
    $("#listhome").empty();
    dataList_home = JSON.parse(localStorage.getItem('data_home'));

    for (var i = 0; i < dataList_home.length; i++) {
        var diachi = dataList_home[i].diachihome;
        $("#diachinha").val(diachi);
        document.getElementById("listhome").innerHTML += (
            "<div class='d-flex'> " +
                "  <i class='fa fa-home icon_suadiachi' id='close_home' style='color: deepskyblue' aria-hidden='true'>" +
                "</i>  " +
                "   <div class='d-flex flex-column w-75'>  " +
                "   <strong> Nhà </strong>   " +
                "   <p class='w-100' style='background: #ffffff;  ' > " + diachi +
                "</p>" +
                "   </div>   " +
                "  <div class='delete m-auto' style='cursor: pointer;' onclick='del_home(" + i + ")' >" +
                "  <i class='fa fa-times-circle icon_suadiachi' id='close_home' aria-hidden='true'>" +
                "</div>" +
                "</i>" +
                "   </div>");
        console.log("aaaaaaaaaaaaaa" + diachi)
    }
}
function del_home(index) {

    dataList_home.splice(index, 1);
    localStorage.setItem("data_home", JSON.stringify(dataList_home));
    home();
    var homea = dataList_home.length;
    console.log(homea);
    if (homea == 0 && homea == "") {

        $("#btn_add_address_home").show();
        $("#btn_address_home").hide();
    } else {

        $("#btn_address_home").show();
        $("#btn_add_address_home").hide();
    }
}