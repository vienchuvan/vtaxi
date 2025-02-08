/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 7/15/22
 * Time: 8:35 AM
 * To change this template use File | Settings | File Templates.
 */


//--------------------------------------lay tao khoan ngan hang----------------------
$(document ).ready(function(){
    var NH = "@info.getAccBank";
    if(NH!=''&&NH.indexOf("-")!=-1)
    {
        var code = NH.split("-")[0];
        var stk = NH.split("-")[1];
        $("#select_bank" ).val(code);
        $("#select_bank" ).change();

        $("#maNganHang" ).val(code);
        var s = $("#soTaiKhoan" ).val(stk);
        console.log(s);
    }
});

$("#soTaiKhoan" ).on("blur",function(){
    $('.loadTK' ).hide();
    var soTK = $(this ).val();
    var NH = $("#select_bank option:selected" ).attr("id");
    $.ajax({
        type: 'GET',
        data:
        {
            soTK:soTK,maNH:NH
        },
        url:'/tracuutknh',
        success: function(data)
        {
            $('.loadTK' ).hide();
            if(data.indexOf("SUCCESS:")!=-1)
            {
                $("#chuTK" ).hide();
                $("#chuTK" ).val(data.split(":")[1]);
            }
        }
    });
});

// ------------------------Thay doi anh--------------------------

$(document).ready(function(){
    $("#apply_info" ).on("click", function(){

        var base64="";
        var id_apply =  $(this ).attr("data-for");
        var href_apply =  $(this ).attr("href");
        if(id_apply == "1"){
            $(".avartar_account" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#imgdata3" ).empty();
            $("#imgdata3" ).val(href_apply);
        }
        else if(id_apply == "2"){
            $(".anhMTCCCD" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#imgdata1" ).empty();
            $("#imgdata1" ).val(href_apply);
        }
        else if(id_apply == "3"){
            $(".anhMSCCCD" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#imgdata2" ).empty();
            $("#imgdata2" ).val(href_apply)
        }
        else if(id_apply == "4"){
            $(".img-avater-dn" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#img_avartar_dn").empty();
            $("#img_avartar_dn").val(href_apply)
        }
        else if(id_apply == "5"){
            $(".imagedk1" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#imgdata1").empty();
            $("#imgdata1").val(href_apply);
        }
        else if(id_apply == "6"){
            $(".imagedk2" ).attr("src",href_apply);
            $("#getCroppedCanvasModal" ).hide();
            $("#imgdata2").empty();
            $("#imgdata2").val(href_apply);
        }

        $("#modal-crop-img-tt" ).hide();


    });


    $(".close-modal-crop" ).on("click", function(){
        $("#modal-crop-img-tt" ).hide();
    });

    $(".close-modal-crop-img" ).on("click", function(){
        $("#getCroppedCanvasModal" ).hide();
    });

});
//btn cap nhat
$(document ).ready(function(){
//------------------------update doanh nghiep--------------------------------
    $("#btnEdit_dn").on('click', function(e){

        $("#error_message_edit_dn").empty();
        var id = $("#id_dn").val();
        var walletId = $("#walletId").val();
        var companyCode = $("#companyCode").val();
        var companyName = $("#companyName").val();
        var nameAlias = $("#nameAlias_dn").val();
        console.log("nameAlias: " + nameAlias);
        var nameRepresent = $("#nameRepresent").val();
        var phone = $("#phone").val();
        var email = $("#email_dn" ).val();
        var home = $("#home").val();
        var idBank = $("#select_bank" ).val();
        var bankNumber =$("#bankNumber" ).val();
        var img_avatar =$("#img_avartar_dn" ).val();
        var img_DKKD =$("#img_DKKD").val();
        var img_DKKD1 =$("#img_DKKD1").val();
        var token = $("#token_dn" ).val();
        var vido = $("#latidue_dn").val();
        var kinhdo = $("#longtidue_dn").val();

//$('.loading' ).show();
        $.ajax({
            type: 'POST',
            data: {
                'id':id,
                'walletId': walletId,
                'nameAlias':nameAlias,
                'companyCode':companyCode,
                'companyName':companyName,
                'nameRepresent':nameRepresent,
                'home':home,
                'typeAuthenticate':0,
                'email':email,
                'maNganHang':idBank,
                'soTaiKhoan':bankNumber,
                'imgdata3':img_avatar,
                'imgdata1':img_DKKD,
                'imgdata2':img_DKKD1,
                'lat':vido,
                'lng':kinhdo,
                'token':token
            },

            url: '/editprofile',
            success: function(data)
            {
                if(data.indexOf("SUCCESS:") != -1)
                {
                    alert("Sửa thông tin ví thành công");

                    window.location.reload();
                }
                else if(data == "NotLogin")
                {
                    window.location = ' /login_register  ';
                }
                else if(data == "FAIL")
                {
                    $("#error_message_edit_dn").empty ( ) ;
                    $("#error_message_edit_dn").append ("Đã có lỗi xảy ra khi kết nối đến server") ;
                    $("#error_message_edit_dn").show ( ) ;
                }
                else
                {
                    $("#error_message_edit_dn").empty ( ) ;
                    $("#error_message_edit_dn").append (data) ;
                    $("#error_message_edit_dn").show ( ) ;
                }
//                $('.loading' ).hide();

            }
        });
    });
//------------------------update ca nhan--------------------------------
    $("#btnEdit" ).on('click', function(e){

        $("#error_message_edit").empty();
        var soVi=   $("#sovi" ).val();
        var tenHienThi = $("#nameAlias" ).val();
        var tenCmnd = $("#acc_name" ).val();
        var ngaysing = $("#birthday" ).val();
        var gioitinh = $("#gioiTinhNam" ).is(':checked') ? $("#gioiTinhNu" ).val() : $("#gioiTinhNam" ).val();
        var quoctich = $("#quocGia" ).val();
        var soCmnd = $("#soCMND" ).val();
        var noithuongchu =$("#noithuongtru" ).val();
        var noicap =$("#select_province" ).val();
        var ngaycap = $("#ngaycap" ).val();
        var sdt = $("#soDt" ).val();
        var email = $("#email" ).val();
        var idNganhang = $("#select_bank" ).val();
        var soTk =$("#soTaiKhoan" ).val();
        var valueBHYT = $("#valueBHYT" ).val();
        var anhMattrcCCCD =$("#imgdata1" ).val();
        var anhMatsauCCCD =$("#imgdata2" ).val();
        var anhDaidien =$("#imgdata3" ).val();
        var token = $("#token" ).val();
        $.ajax({
            type: 'POST',
            data: {
                'id':soVi,
                'nameAlias':tenHienThi,
                'acc_name':tenCmnd,
                'birthday':ngaysing,
                'gioiTinh':gioitinh,
                'quocGia':quoctich,
                'idCard':soCmnd,
                'home':noithuongchu,
                'placeIdCard':noicap,
                'dateIdCard':ngaycap,
                'phoneAuthenticate':sdt,
                'email':email,
                'maNganHang':idNganhang,
                'soTaiKhoan':soTk,
                'valueBHYT':valueBHYT,
                'imgdata3':anhDaidien,
                'imgdata2':anhMatsauCCCD,
                'imgdata1':anhMattrcCCCD,
                'token':token
            },

            url: '/editprofile',
            success: function(data)
            {
                $('.loadingpopup' ).hide();
                if(data.indexOf("SUCCESS:") != -1)
                {
                    alert("Sửa thông tin ví thành công");
                    window.location.reload();
                }
                else if(data == "NotLogin")
                {
                    window.location = ' /login_register  ';
                }
                else if(data == "FAIL")
                {
                    $("#error_message_edit").empty ( ) ;
                    $("#error_message_edit").append ("Đã có lỗi xảy ra khi kết nối đến server") ;
                    $("#error_message_edit").show ( ) ;
                }
                else
                {
                    $("#error_message_edit").empty ( ) ;
                    $("#error_message_edit").append (data) ;
                    $("#error_message_edit").show ( ) ;
                }
            }
        });
    })


});
