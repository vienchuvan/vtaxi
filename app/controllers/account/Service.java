package controllers.account;

import controllers.tools.Utility;
import controllers.tools.VimassSercurityFunc;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.libs.WS;

import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Service {
    // Doanh Nghiep
    public static String KEY_DN = "keyDungLuuMaSoDN";
    /*public static String KEY_LUU_CHEDO = "wifi";*/
    public static String KEY_LUU_TRANGTHAI_VEMAYBAY = "ok";
    /*public static String KEY_SESSION_ID_DN = "keyDungLuuSessionIdNhanDuocTuServer";
    public static String KEY_TEN_DAI_DIEN_DN = "keyLuuTenDaiDien";
    public static String KEY_TYPE_ACC_DN = "keyLuuKieuTaiKhoan";
    public static String KEY_ACC_DN = "keyLuuSdtDangNhap";*/
    public static String KEY_MA_DN = "keyLuuMaDN";
    public static String KEY_EMAIL_LOGIN = "keyLuuWallLogin";
    public static String KEY_EMAIL = "keyLuuWallLogin";
    public static String KEY_NAME_LOGIN = "keyLuuTenLogin";
    //    TODO Key Main
    public static String KEY_SESSION_ID = "keyDungLuuSessionIdNhanDuocTuServer";
    public static String KEY_PHONE_ID = "keyDungLuuSoDienThoai";
    public static String KEY_DS_QUYEN = "keyDsQuyen";
    public static String KEY_V_ID = "keyDungLuuVID";
    public static String KEY_AMOUNT_VID = "keyDungLuuAMVID";
    public static String KEY_IS_TOKEN = "keyDungLuuTrangThaiCoTokenHayKhong";
    public static String KEY_PHONE_DANG_KY = "keyDungChoDangKySauKhiXacThucSeXoa";
    public static String KEY_PASS_DANG_KY = "keyDungChoDangKySauXacThucSeXoa";
    public static String KEY_TEN_DAI_DIEN = "keyDungLuuTenDaiDien";
    public static String KEY_TEN_DAI_DIEN_VID = "keyDungLuuTenDaiDienvID";
    public static String KEY_LINK_ANH_DAI_DIEN = "keyAnhDaiDien";
    public static String KEY_LINK_ANH_QR = "keyAnhQR";
    public static String KEY_TYPE_ACC = "keyLoaiTaiKhoan";
    public static String KEY_TRANG_THAI_KM = "keyDungLuuTrangThaiKM";

    public static String KEY_MODULE_GOI_XAC_THUC = "keyDungDeLuuModuleGoiXacThucOTP";
    public static String MODULE_QUEN_MAT_KHAU = "MODULE_QUEN_MAT_KHAU";
    public static String MODULE_TAO_TAI_KHOAN = "MODUlE_TAO_TAI_KHOAN";

    public static String CHUC_NANG_DANG_TIN = "CHUC_NANG_DANG_TIN";
    public static String KEY_CHUC_NANG_HIEN_TAI = "KEY_CHUC_NANG_HIEN_TAI";
    public static String KEY_DANG_KY_DONVI = "KEY_DANG_KY_DONVI";
    public static String KEY_CHECK_LOGIN = "keychecknhanvienvimass";
    public static String KEY_TYPE_DEVICE = "1";
    public static String KEY_MST_DN = "keymst";
    public static String KEY_LAY_DANH_SACH_GIAO_DICH_LAN_DAU = "KEY_LAY_DANH_SACH_GIAO_DICH_LAN_DAU";

    //  TODO LINK ĐĂNG KÝ v...v...
    /*public static String SERVICE_ROOT                    = "https://vimass.vn/vmbank/services/";*/
    public static String SERVICE_ROOT                      = "https://vimass.vn/vmbank/services/";

    public static String SERVICE_LOG_IN                    = SERVICE_ROOT + "account/loginVer2";
    public static String SERVICE_FACE_LOGIN                = SERVICE_ROOT + "account/loginVer3";
    public static String SERVICE_LOG_OUT                   = SERVICE_ROOT + "account/logOut";
    public static String SERVICE_REGISTER                  = SERVICE_ROOT + "account/createNewAcc1";
    public static String SERVICE_CONFIRM_OTP_DANG_KY       = SERVICE_ROOT + "account/confirmOTPToCreateNewAcc1";
    public static String SERVICE_CONFIRM_OTP_QUEN_MAT_KHAU = SERVICE_ROOT + "account/confirmOTPToForgetPassAcc1";
    public static String SERVICE_FORGOT_PASSWORD           = SERVICE_ROOT + "account/forgetPassAcc1";
    public static String SERVICE_CHECK_LOGIN               = SERVICE_ROOT + "account/checkLoginVer1";
    public static String SERVICE_CHANGE_PASSWORD           = SERVICE_ROOT + "account/changePass1";
    public static String SERVICE_DOI_TEN_HIEN_THI          = SERVICE_ROOT + "account/changeNameAlias";
    public static String SERVICE_LOGIN_WITH_FB_GG          = SERVICE_ROOT + "account/loginWithOtherApp";
    //  TODO LINK ĐĂNG KÝ DOANH NGHIEP
    public static  String SERVICE_DANGKYDOANHNGHIEP        = "http://192.168.100.8:8080/HDDT/services/fpt/regMerchant";
    public static String KEY_MST= "0105906169000";
    public static String KEY_SOHD="kaysohd"  ;
    public static String KEY_TENDOANHNGHIEP = "tenDoanhNghiep";
    public static String KEY_DIACHI = "keyDiaChi";
    public static String KEY_BANQUYEN= "keyBanQuyen";
    public static String KEY_CQT_CAPCUC= "keyCQTcapcuc";
    public static String KEY_CQT= "keyCQT";
    public static String KEY_GOIHOADON= "keyGoihoadon";


    //  TODO LINK PUSH CHAT
    public static String SERVICE_PUSH_CHAT_ROOT = "https://vimass.vn/pushNotification/services/adminPush/";
    public static String SERVICE_PUSH_CHAT_LAY_DANH_SACH_GIAO_DICH = SERVICE_PUSH_CHAT_ROOT + "getMess";
    public static String SERVICE_PUSH_CHAT_LAY_DANH_SACH_TAI_KHOAN_DA_GIAO_DICH = SERVICE_PUSH_CHAT_ROOT + "getContactChat1";
    public static String SERVICE_PUSH_CHAT_LAY_SO_LUONG_BAGDE = SERVICE_PUSH_CHAT_ROOT + "getBadge";
    public static String SERVICE_PUSH_CHAT_DANG_TIN_CHAT = SERVICE_PUSH_CHAT_ROOT + "chat";
    public static String SERVICE_PUSH_CHAT_GUI_THOI_DIEM_DOC_TIN = SERVICE_PUSH_CHAT_ROOT + "checkPoint";
    public static String SERVICE_PUSH_CHAT_XOA_TIN_CHAT = SERVICE_PUSH_CHAT_ROOT + "deleteMess";

    //  TODO LINK TÀI CHÍNH
    public static int KIEU_TAI_KHOAN_TONG_HOP = 0;
    public static int KIEU_TAI_KHOAN_VI = 1;
    public static int KIEU_TAI_KHOAN_NGAN_HANG = 2;
    public static int KIEU_TAI_KHOAN_THE = 3;
    public static int KIEU_TAI_KHOAN_NGAN_HANG_RUT_TIEN = 4;
    public static int KIEU_TAI_KHOAN_THE_RUT_TIEN = 5;

    public static String SERVICE_TAI_CHINH_CHUYEN_TIEN =  SERVICE_ROOT + "account/transactionMoney1";
    public static String SERVICE_TAI_CHINH_RUT_TIEN = SERVICE_ROOT + "";
    public static String SERVICE_TAI_CHINH_LAY_DANH_SACH_THUONG_DUNG = SERVICE_ROOT + "account/searchAccUsed";
    public static String SERVICE_TAI_CHINH_XOA_TAI_KHOAN_THUONG_DUNG = SERVICE_ROOT + "account/deleteAccUsed";
    public static String SERVICE_TAI_CHINH_CAP_NHAT_TAI_KHOAN_THUONG_DUNG = SERVICE_ROOT + "account/addAccUsed";
    public static String SERVICE_TAI_CHINH_CAP_NHAT_THONG_TIN_TAI_KHOAN_VI = "account/editAcc1";
    public static String SERVICE_TAI_CHINH_LAY_THONG_BAO = "";
    public static String SERVICE_TAI_CHINH_UPLOAD_ANH_BASE64 = SERVICE_ROOT + "media/upload";
    public static String SERVICE_TAI_CHINH_LAY_ANH_BASE64 = SERVICE_ROOT + "media/getImage"; //GET

    protected WS.WSRequestHolder request;
    protected ObjectMapper mapper = new ObjectMapper();

    //    TODO: Dịch vụ đăng nhập - đăng ký - quên mật khẩu - đổi tên hiển thị - check login - đổi mật khẩu
    public String login(String sUser, String sPass,String companyCode)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("user", sUser);
        dictPost.put("pass", sPass);
        dictPost.put("deviceId",4);
        dictPost.put("VimassMH",1);
        if(companyCode!=null&&companyCode!="")
        {
            dictPost.put("companyCode",companyCode);
        }
        dictPost.put("type", "1");
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_LOG_IN);
            Logger.info("login data:" + dictPost.toString());
            request.setContentType("application/json");
            request.setTimeout(300000);
            mapper.writeValue(sw, dictPost);
            String data = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix,VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,sw.toString()));
            Logger.info("data send = " + data);
            response = request.post(data).get();
            /*response = request.post(sw.toString()).get();*/
            Logger.info("login response: " + response.getBody());
            Logger.info("url login: " + response.getUri());
            sKetQua = response.getBody();
            Logger.info("goc = " + sKetQua);
            if(sKetQua != null && !sKetQua.contains("{") && sKetQua.contains("zzz_"))
            {
                sKetQua = VimassSercurityFunc.chuanHoaDuLieuNhan(sKetQua);
                sKetQua = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,sKetQua);
            }
            /*if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();
                Logger.info("goc = " + sKetQua);
                if(sKetQua != null && !sKetQua.contains("{") && sKetQua.contains("zzz_"))
                {
                    sKetQua = VimassSercurityFunc.chuanHoaDuLieuNhan(sKetQua);
                    sKetQua = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,sKetQua);
                }
            }*/
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
   // Dich vu dang nhap bang khuon mat
    public String facelogin(String sUser, String sPass,String companyCode,String param,  long timeLoginVer3, String ipLogin, String keySave, String cksValueSave ){
        String result="";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("user", sUser);
        dictPost.put("pass", sPass);
        dictPost.put("deviceId",4);
        dictPost.put("VimassMH",1);
        dictPost.put("param", param);
        dictPost.put("timeLoginVer3", timeLoginVer3);
        dictPost.put("ipLogin", ipLogin);
        dictPost.put("keySave",keySave);
        dictPost.put("cksValueSave",cksValueSave);
        if(companyCode!=null&&companyCode!="")
        {
            dictPost.put("companyCode",companyCode);
        }
        dictPost.put("type", "1");
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_FACE_LOGIN);
            Logger.info("login data:" + dictPost.toString());
            request.setContentType("application/json");
            request.setTimeout(300000);
            mapper.writeValue(sw, dictPost);
            String data = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix,VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,sw.toString()));
            Logger.info("data send = " + data);
            response = request.post(data).get();
            Logger.info("login response: " + response.getBody());
            Logger.info("url login: " + response.getUri());
            result = response.getBody();
            Logger.info("goc = " + result);
            if(result != null && !result.contains("{") && result.contains("zzz_"))
            {
                result = VimassSercurityFunc.chuanHoaDuLieuNhan(result);
                result = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,result);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return  result;
    }

    public String logout(String sUserName, String sSessionID)
    {
        String sKetQua = "";
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_LOG_OUT).setQueryParameter("id", sUserName);
            request = request.setQueryParameter("secssion", sSessionID);
            request.setContentType("application/json");
            request.setTimeout(20000);
            response = request.get().get();
            Logger.info("Service >> logout >> url: " + SERVICE_LOG_OUT + "--" + sUserName + "--" + sSessionID);
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }

    public String dangkydoanhnghiep(String mst, String tendn,String diachi,String banquyen,String cctcc,String cct,String goihd)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("maSoThue", mst);
        dictPost.put("tenDoanhNghiep", tendn);
        dictPost.put("diaChi", diachi);
        dictPost.put("banQuyen", banquyen);
        dictPost.put("coQuanThueCapCuc", cctcc);
        dictPost.put("coQuanThue", cct);
        dictPost.put("goiHoaDon", goihd);
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_DANGKYDOANHNGHIEP);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dictPost);
            Logger.info("url: " + SERVICE_DANGKYDOANHNGHIEP);
            Logger.info(" register put: " + sw.toString());
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {
                Logger.info("res = " + response.getBody());
                sKetQua = response.getBody();

            }
        }
        catch (Exception  ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public String register(String sSoDienThoai, String sMatKhau)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("id", sSoDienThoai);
        dictPost.put("pass", sMatKhau);
        dictPost.put("appId", 1);
        dictPost.put("deviceId", 4);
        dictPost.put("funcId", 21);
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_REGISTER);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dictPost);
            Logger.info("Service >> register >> url: " + SERVICE_REGISTER);
            Logger.info("Service >> register >> postdata: " + sw.toString());
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {
                Logger.info("res = " + response.getBody());
                sKetQua = response.getBody();
            }
        }
        catch (Exception  ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }

    public String forgotPass(String sPhone, String sNewPass)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dicPost = new HashMap<>();
        dicPost.put("phone", sPhone);
        dicPost.put("newPass", sNewPass);
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_FORGOT_PASSWORD);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dicPost);
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {

                sKetQua = response.getBody();

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }

    public String confirmOTP(String sPhone, String sOTP, String sModuleCallOTP)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("phone", sPhone);
        dictPost.put("otp", sOTP);
        try
        {
            WS.Response response = null;
            if(sModuleCallOTP.equals(Service.MODULE_TAO_TAI_KHOAN))
                request = WS.url(SERVICE_CONFIRM_OTP_DANG_KY);
            else
                request = WS.url(SERVICE_CONFIRM_OTP_QUEN_MAT_KHAU);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dictPost);
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }

    public String checkLoginState(String sId)
    {
        String sKQ = "";
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_CHECK_LOGIN).setQueryParameter("id", sId);
            request.setContentType("application/json");
            request.setTimeout(20000);
            response = request.get().get();
            if(Utility.isValidJSON(response.getBody()))
            {
                String sKetQua = response.getBody();
                Logger.info("Check Login - " + sKetQua);
                sKQ = sKetQua;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKQ;
    }

    public String changeNameAlias(String sPhone, String sPass, String sNameAlias)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("user", sPhone);
        dictPost.put("pass", sPass);
        dictPost.put("nameAlias", sNameAlias);
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_DOI_TEN_HIEN_THI);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dictPost);
            Logger.info("Doi ten hien thi >> post: " + sw.toString());
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }

    public String changPassword(String sPhone, String sPassCu, String sPassMoi)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dictPost = new HashMap<>();
        dictPost.put("user", sPhone);
        dictPost.put("pass", sPassCu);
        dictPost.put("newPass", sPassMoi);
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_CHANGE_PASSWORD);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dictPost);
            response = request.post(sw.toString()).get();
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public String capNhatThoiDiem(String sPhone, String sThoiDiemDocTinGanNhat, String sIdChat, String sFunctionId)
    {
        String sKetQua = "";
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_PUSH_CHAT_GUI_THOI_DIEM_DOC_TIN).setQueryParameter("id", sPhone);
            request = request.setQueryParameter("appId", "5");
            request = request.setQueryParameter("funcId", sFunctionId);
            request = request.setQueryParameter("time", sThoiDiemDocTinGanNhat);
            request = request.setQueryParameter("idDoiTac", sIdChat);
            request.setContentType("application/json");
            request.setTimeout(20000);
            response = request.get().get();
            Logger.info("capnhatThoiDiem:" + response.getUri());
            /*Logger.info("Service >> capNhatThoiDiem >> url: " + SERVICE_PUSH_CHAT_GUI_THOI_DIEM_DOC_TIN + "--" + sPhone + "--" + sThoiDiemDocTinGanNhat + "---" + sIdChat);*/
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public String layDsGiaoDich(String sPhone, int nFunctionId, String sSendTo, String sOffset, String sTime)
    {
        String sKetQua = "";
        try
        {
            WS.Response response = null;
            request = WS.url(SERVICE_PUSH_CHAT_LAY_DANH_SACH_GIAO_DICH).setQueryParameter("id", sPhone);
            request = request.setQueryParameter("appId", "5");
            request = request.setQueryParameter("funcId", String.valueOf(nFunctionId));
            request = request.setQueryParameter("time", sTime);
            request = request.setQueryParameter("offset", sOffset);
            request = request.setQueryParameter("limit", "200");
            request = request.setQueryParameter("send_to", sSendTo);
            request.setContentType("application/json");
            request.setTimeout(20000);
            response = request.get().get();
            Logger.info("Service >> layDsGiaoDich >> url: " + SERVICE_PUSH_CHAT_LAY_DANH_SACH_GIAO_DICH + "--" + sPhone + "---sendTo: " + sSendTo + " ---sOffset: " + sOffset + "---sTime: ");
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public String layDsTaiKhoanDaGiaoDich(String sPhone, int nFunctionId)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dicObj = new HashMap<>();
        dicObj.put("phone", sPhone);
        dicObj.put("appId", "5");
        dicObj.put("funcId", nFunctionId);
        try{
            WS.Response response = null;
            request = WS.url(SERVICE_PUSH_CHAT_LAY_DANH_SACH_TAI_KHOAN_DA_GIAO_DICH);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dicObj);
            response  = request.post(sw.toString()).get();
            Logger.info("Service >> layDsTaiKhoanDaGiaoDich >> url: " + SERVICE_PUSH_CHAT_LAY_DANH_SACH_TAI_KHOAN_DA_GIAO_DICH + "--" + sPhone + "---sendTo: " + nFunctionId);
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public String dangTinChat(String sPhone, String sSendTo, String sNoiDung, String sTitle, int nFunctionId)
    {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        HashMap<String, Object> dicObj = new HashMap<>();
        dicObj.put("phone", sPhone);
        dicObj.put("appId", "5");
        dicObj.put("funcId", String.valueOf(nFunctionId));
        dicObj.put("mess", sNoiDung);
        dicObj.put("title", sTitle);
        dicObj.put("send_to", sSendTo);
        try{
            WS.Response response = null;
            request = WS.url(SERVICE_PUSH_CHAT_DANG_TIN_CHAT);
            request.setContentType("application/json");
            request.setTimeout(20000);
            mapper.writeValue(sw, dicObj);
            response  = request.post(sw.toString()).get();
            Logger.info("Service >> dangTinChat >> url: " + SERVICE_PUSH_CHAT_DANG_TIN_CHAT + "--" + sPhone + "---send_To: " + sSendTo + "---functionId" + nFunctionId);
            if(Utility.isValidJSON(response.getBody()))
            {
                sKetQua = response.getBody();

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sKetQua;
    }
    public class KetQuaCheckLoginObj
    {
        public int msgCode;
        public Object result;
    }

    public class CheckLoginResultObject
    {
        public int isToken;
        public String nameAlias;
        public int type;
    }

//    TODO: END Dịch vụ đăng nhập - đăng ký - quên mật khẩu - đổi tên hiển thị - check login - đổi mật khẩu

}
