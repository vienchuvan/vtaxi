/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 11/15/22
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function () {
    var dataList_gannhat = localStorage.getItem('data_diachigannhat');

    var jsongannhat;
    if (dataList_gannhat != null && dataList_gannhat != "") {
        jsongannhat = JSON.parse(localStorage.getItem('data_diachigannhat'));
    } else {
        jsongannhat = 0;
    }
    var gannhat = jsongannhat;
    console.log("gần nhất : " + gannhat);
});
var dataList_gannhat = [];

$(document).ready(function () {
    $("#add_gannhat").on('click', function () {
        var idgannhat = Math.floor(Math.random() * 9999);
        if (localStorage) {
            dataList_gannhat.push({
                idgannhat: idgannhat,
                ghichu: document.getElementById('ip_add_ghichu').value,
                diachigannhat: document.getElementById('ip_add_gannhat').value
            });
            localStorage.setItem('data_gannhat', JSON.stringify(dataList_gannhat));

            alert("Đã thêm địa chỉ !")
            $("#frame_add_gannhat").hide();
            $("#btn_add_address_gannhat").show();
            $("#btn_closse_address_gannhat").hide();
            console.log("vienne: " + dataList_gannhat)
        }
        dataList_gannhat = JSON.parse(localStorage.getItem('data_gannhat'));
        gannhat() ;
    });


});

function gannhat() {
    $("#listgannhat").empty();
     dataList_gannhat = JSON.parse(localStorage.getItem('data_gannhat'));
  /*  dataList_gannhat = localStorage.getItem('data_diachigannhat');
    var jsongannhat;

    if (dataList_gannhat != null && dataList_gannhat != "") {
        jsongannhat = JSON.parse(localStorage.getItem('data_diachigannhat'));

    } else {
        document.getElementById("listgannhat").innerHTML += (
            "<div class='d-flex' style='text-align: center; margin-top: 10px'> " +
                " Bạn chưa thêm địa chỉ nào vào danh sách gần nhất ! " +

                "   </div>");
    }   */

        for (var i = 0; i < dataList_gannhat.length; i++) {
            var idgannhat = dataList_gannhat[i].idgannhat;
            var ghichu = dataList_gannhat[i].ghichu;
            var diachi = dataList_gannhat[i].diachigannhat;
            /*   $("#diachigannhat").val(diachi);*/
            document.getElementById("listgannhat").innerHTML += (
                "<div class='d-flex' id='choose_id-gannhat" + idgannhat + "' style='cursor: pointer; border-bottom: 1px solid #b9b9b9; margin-top: 10px'> " +
                    "  <i class='fa fa-street-view icon_suadiachi' style='color: #1bc3ff' aria-hidden='true'>" +
                    "</i>  " +
                    "   <div class='d-flex flex-column w-75'>  " +
                    "   <strong> " + ghichu + " </strong>   " +
                    "   <p class='w-100' style='background: #ffffff;  ' > " + diachi +
                    "</p>" +
                    "   </div>   " +
                    "  <div class='delete m-auto' style='cursor: pointer;' onclick='del_gannhat(" + i + ")' >" +
                    "  <i class='fa fa-times-circle icon_suadiachi' id='close_home' aria-hidden='true'>" +
                    "</div>" +
                    "</i>" +
                    "   </div>");
            console.log("aaaaaaaaaaaaaa" + diachi)
        }
        $('body').on('click', '#choose_id-gannhat' + idgannhat, function () {
            var A = $("#location-1").val();
            var B = $("#location-2").val();

            if (A == "" && B == "") {
                $("#clear_di").show();
                $("#location-1").val(diachi)
            } else if (A == "" && B != "") {
                $("#location-1").val(diachi)
                $("#clear_di").show();
            }
            else if (A != "" && B == "") {
                $("#location-2").val(diachi)
                $("#clear_den").show();
            }
        });


}
function del_gannhat(index) {

    dataList_gannhat.splice(index, 1);
    localStorage.setItem("data_gannhat", JSON.stringify(dataList_gannhat));
    gannhat();
    var gannhata = dataList_gannhat.length;
    console.log(gannhata);
}