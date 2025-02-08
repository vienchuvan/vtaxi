package controllers.account;

import com.google.gson.Gson;
import controllers.CallService;

import controllers.tools.EncryptionUtil;
import controllers.tools.ResponseMessage;
import controllers.tools.Utility;
import controllers.tools.listServices;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginController extends Controller {
    public static Result login()
    {

        Service service = new Service();
        DynamicForm requestData = Form.form().bindFromRequest();
        String sUserName = requestData.get("username_login");
        String captcha = requestData.get("captcha");
        String checkCapt = requestData.get("datalog");
        String temp = Utility.getMD5(captcha + "ok2conde");
        System.out.println("cap: " + captcha + " temp: " + temp + " check: " + checkCapt);
        if(!checkCapt.equals(temp))
        {
            return ok("Sai captcha!");
        }
        String sPass = requestData.get("pass_login");
        String companyCode = requestData.get("companyCode_login");

        String typeLogin = requestData.get("typeLogin");
        Logger.info("logincontroller >> login >> typeLogin: " + typeLogin);
        String sKetQuaDangNhap = "";
        if(typeLogin.equals("0") || typeLogin.equals("1"))
        {
            sKetQuaDangNhap = service.login(sUserName, sPass,companyCode);
        }
        else
        {
            // dang nhap vid
        }
        Logger.info("logincontroller >> login >> ketqua: " + sKetQuaDangNhap);
        if(sKetQuaDangNhap != "")
        {
            Login objKetQua = new Gson().fromJson(sKetQuaDangNhap,Login.class);
            if (objKetQua.msgCode == 1)
            {
                session().remove(Service.KEY_DN);
                ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan();
                try {
                    thongTinCaNhan = new Gson().fromJson(new Gson().toJson(objKetQua.result), ThongTinCaNhan.class);

                    if(thongTinCaNhan.getId().contains("dn_")||
                            thongTinCaNhan.getId().contains("DN_")||
                            thongTinCaNhan.getId().contains("D")||
                            thongTinCaNhan.getId().contains("d"))
                    {
                        CallService.luuThongTin("AccInfo" + thongTinCaNhan.getId() + thongTinCaNhan.getWalletLogin(), new Gson().toJson(thongTinCaNhan));
                    }
                    else
                    {
                        CallService.luuThongTin("AccInfo" + thongTinCaNhan.getId(), new Gson().toJson(thongTinCaNhan));
                    }
                    /*session().put("AccInfo",new Gson().toJson(thongTinCaNhan));*/
                }catch (Exception e)
                {
                    Logger.info("loi :" + e.getMessage());
                }

                if(thongTinCaNhan.getRoles()!=null&&thongTinCaNhan.getRoles()!="")
                {
                    session().put("RoleDN",thongTinCaNhan.getRoles());
                }
                if(companyCode!=null&&companyCode!="")
                {
                    session().put(Service.KEY_DN, thongTinCaNhan.getWalletLogin());
                    session().put(Service.KEY_MA_DN,thongTinCaNhan.getCompanyCode());
                    session().put(Service.KEY_EMAIL_LOGIN,thongTinCaNhan.getWalletLoginEmail());
                    session().put(Service.KEY_NAME_LOGIN,thongTinCaNhan.getWalletLoginName());
                }
                session().put(Service.KEY_EMAIL,thongTinCaNhan.getEmail());
                session().put(Service.KEY_PHONE_ID, thongTinCaNhan.getId());
                session().put(Service.KEY_SESSION_ID, thongTinCaNhan.getSecssion());
                session().put(Service.KEY_IS_TOKEN, String.valueOf(thongTinCaNhan.getToken()));
                session().put(Service.KEY_TEN_DAI_DIEN, thongTinCaNhan.getNameAlias());
                session().put(Utility.USER,thongTinCaNhan.getId());
                //check tai khoan  nhan vien vimass dang nhap toa do
                Checklogin(sUserName);
                /*Logger.info("thong tin: " + thongTinCaNhan.getAvatar());*/
                if(thongTinCaNhan.getAvatar()!=null&&thongTinCaNhan.getAvatar().length()>5)
                {
                    if(thongTinCaNhan.getAvatar().contains("http"))
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, thongTinCaNhan.getAvatar());
                    }
                    else if(thongTinCaNhan.getAvatar().contains("https"))
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, thongTinCaNhan.getAvatar());
                    }
                    else
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, listServices.SERVICE_LAY_ANH+thongTinCaNhan.getAvatar());
                    }

                }
                session().put(Service.KEY_LINK_ANH_QR,thongTinCaNhan.getLinkQR());
                session().put(Service.KEY_TYPE_ACC, String.valueOf(thongTinCaNhan.getAccType()));
                if(thongTinCaNhan.isPromotionStatus()==true)
                {
                    session().put(Service.KEY_TRANG_THAI_KM,"1");
                }
                else
                {
                    session().put(Service.KEY_TRANG_THAI_KM,"0");
                }
                Logger.info("KM :"+ thongTinCaNhan.isPromotionStatus());
                Logger.info("vIDCardController >> login >> luu session:  " + session().get(Service.KEY_PHONE_ID));
                Logger.info("companyCode:" + companyCode);
                Logger.info("companyCode tu kq:" + thongTinCaNhan.getCompanyCode());
                if(companyCode==null||companyCode=="")
                {
                    try {
                        if(Utility.VIP(thongTinCaNhan.getId()))
                        {
                            session().put("VIP",thongTinCaNhan.getId());
                        }
                        else
                        {
                            if(Utility.VIPNHNN(thongTinCaNhan.getId()))
                            {
                                session().put("VIPNHNN",thongTinCaNhan.getId());
                            }
                            else
                            {
                                session().remove("VIPNHNN");
                                session().remove("VIP");
                                Logger.info("clear VIP");
                            }
                        }
                    }catch (Exception e)
                    {
                        Logger.info("ex:" + e.getMessage());
                    }
                }
                String s1 = "";
                String s2 = "";
                try {
                    s1 = EncryptionUtil.encode(session().get(Service.KEY_PHONE_ID));
                    Logger.info("s1:" + s1);
                    s2 = session().get(Service.KEY_TEN_DAI_DIEN).trim();
                    Logger.info("s2:" + s2);
                }catch (Exception e)
                {
                    Logger.info("loi cmnr" + e.getMessage());
                }
                // check point dang nhap
                ResponseMessage responseMessage = (ResponseMessage) CallService.checkPointDangNhap("41", sUserName, "1", sUserName);
                if(responseMessage!=null&&responseMessage.getMsgCode()==1)
                {
                    Logger.info("check_point thanh cong");
                }
                /*return ok("SUCCESS:{\"phone\":\"" + s1 + "\",\"nameAlias\":\"" + s2 + "\"}");*/
                return ok("SUCCESS:");
            }
            else
            {
                return ok(objKetQua.msgContent);
            }
        }

        return ok("FAIL");
    }

    public static Result face_login(){
        Service service = new Service();
        DynamicForm requestData = Form.form().bindFromRequest();
        long timeLoginVer3 = new Date().getTime();
        String sUserName= requestData.get("username_login");;
        String sPass="";
        String param="Vimass";
        String ipLogin = requestData.get("ipLogin");
        System.out.println("ipLogin:" + ipLogin);
        String keySave = requestData.get("username_login");  //cái mà vừa lưu trên server lúc trao đổi thông tin
        System.out.println("keySave:" + keySave);
        String value =requestData.get("value");
        System.out.println("value: "+ value);
        String cksValueSave = Utility.getMD5(value + "8397248384fjdavjFWRG");

        System.out.println("cksValueSave: "+ cksValueSave);


        String sKetQuaDangNhap = "";
        String typeLogin =  requestData.get("typeLogin");
        String companyCode = requestData.get("companyCode_login");
        if(typeLogin.equals("0") || typeLogin.equals("1"))
        {
            sKetQuaDangNhap = service.facelogin(sUserName,sPass,companyCode,param,timeLoginVer3, ipLogin,keySave,cksValueSave);
        }
        else
        {
            // dang nhap vid
        }
        Logger.info("logincontroller >> login >> ketqua: " + sKetQuaDangNhap);
        if(sKetQuaDangNhap != "")
        {
            Login objKetQua = new Gson().fromJson(sKetQuaDangNhap,Login.class);
            if (objKetQua.msgCode == 1)
            {
                session().remove(Service.KEY_DN);
                ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan();
                try {
                    thongTinCaNhan = new Gson().fromJson(new Gson().toJson(objKetQua.result), ThongTinCaNhan.class);

                    if(thongTinCaNhan.getId().contains("dn_")||
                            thongTinCaNhan.getId().contains("DN_")||
                            thongTinCaNhan.getId().contains("D")||
                            thongTinCaNhan.getId().contains("d"))
                    {
                        CallService.luuThongTin("AccInfo" + thongTinCaNhan.getId() + thongTinCaNhan.getWalletLogin(), new Gson().toJson(thongTinCaNhan));
                    }
                    else
                    {
                        CallService.luuThongTin("AccInfo" + thongTinCaNhan.getId(), new Gson().toJson(thongTinCaNhan));
                    }
                    /*session().put("AccInfo",new Gson().toJson(thongTinCaNhan));*/
                }catch (Exception e)
                {
                    Logger.info("loi :" + e.getMessage());
                }

                if(thongTinCaNhan.getRoles()!=null&&thongTinCaNhan.getRoles()!="")
                {
                    session().put("RoleDN",thongTinCaNhan.getRoles());
                }
                if(companyCode!=null&&companyCode!="")
                {
                    session().put(Service.KEY_DN, thongTinCaNhan.getWalletLogin());
                    session().put(Service.KEY_MA_DN,thongTinCaNhan.getCompanyCode());
                    session().put(Service.KEY_EMAIL_LOGIN,thongTinCaNhan.getWalletLoginEmail());
                    session().put(Service.KEY_NAME_LOGIN,thongTinCaNhan.getWalletLoginName());
                }
                session().put(Service.KEY_EMAIL,thongTinCaNhan.getEmail());
                session().put(Service.KEY_PHONE_ID, thongTinCaNhan.getId());
                session().put(Service.KEY_SESSION_ID, thongTinCaNhan.getSecssion());
                session().put(Service.KEY_IS_TOKEN, String.valueOf(thongTinCaNhan.getToken()));
                session().put(Service.KEY_TEN_DAI_DIEN, thongTinCaNhan.getNameAlias());
                session().put(Utility.USER,thongTinCaNhan.getId());
                //check tai khoan  nhan vien vimass dang nhap toa do
                Checklogin(sUserName);
                /*Logger.info("thong tin: " + thongTinCaNhan.getAvatar());*/
                if(thongTinCaNhan.getAvatar()!=null&&thongTinCaNhan.getAvatar().length()>5)
                {
                    if(thongTinCaNhan.getAvatar().contains("http"))
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, thongTinCaNhan.getAvatar());
                    }
                    else if(thongTinCaNhan.getAvatar().contains("https"))
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, thongTinCaNhan.getAvatar());
                    }
                    else
                    {
                        session().put(Service.KEY_LINK_ANH_DAI_DIEN, listServices.SERVICE_LAY_ANH+thongTinCaNhan.getAvatar());
                    }

                }
                session().put(Service.KEY_LINK_ANH_QR,thongTinCaNhan.getLinkQR());
                session().put(Service.KEY_TYPE_ACC, String.valueOf(thongTinCaNhan.getAccType()));
                if(thongTinCaNhan.isPromotionStatus()==true)
                {
                    session().put(Service.KEY_TRANG_THAI_KM,"1");
                }
                else
                {
                    session().put(Service.KEY_TRANG_THAI_KM,"0");
                }
                Logger.info("KM :"+ thongTinCaNhan.isPromotionStatus());
                Logger.info("vIDCardController >> login >> luu session:  " + session().get(Service.KEY_PHONE_ID));
                Logger.info("companyCode:" + companyCode);
                Logger.info("companyCode tu kq:" + thongTinCaNhan.getCompanyCode());
                if(companyCode==null||companyCode=="")
                {
                    try {
                        if(Utility.VIP(thongTinCaNhan.getId()))
                        {
                            session().put("VIP",thongTinCaNhan.getId());
                        }
                        else
                        {
                            if(Utility.VIPNHNN(thongTinCaNhan.getId()))
                            {
                                session().put("VIPNHNN",thongTinCaNhan.getId());
                            }
                            else
                            {
                                session().remove("VIPNHNN");
                                session().remove("VIP");
                                Logger.info("clear VIP");
                            }
                        }
                    }catch (Exception e)
                    {
                        Logger.info("ex:" + e.getMessage());
                    }
                }
                String s1 = "";
                String s2 = "";
                try {
                    s1 = EncryptionUtil.encode(session().get(Service.KEY_PHONE_ID));
                    Logger.info("s1:" + s1);
                    s2 = session().get(Service.KEY_TEN_DAI_DIEN).trim();
                    Logger.info("s2:" + s2);
                }catch (Exception e)
                {
                    Logger.info("loi cmnr" + e.getMessage());
                }
                // check point dang nhap
                ResponseMessage responseMessage = (ResponseMessage) CallService.checkPointDangNhap("41", sUserName, "1", sUserName);
                if(responseMessage!=null&&responseMessage.getMsgCode()==1)
                {
                    Logger.info("check_point thanh cong");
                }
//               return ok("SUCCESS:{\"phone\":\"" + s1 + "\",\"nameAlias\":\"" + s2 + "\"}");
                return ok("SUCCESS:");
            }
            else
            {
                return ok(objKetQua.msgContent);
            }
        }

        return ok("FAIL");
    }

    public static Result logout()
    {
        if(session() != null)
        {
            session().remove(Service.KEY_PHONE_ID);
            session().remove(Service.KEY_SESSION_ID);
            session().remove(Service.KEY_IS_TOKEN);
            session().remove(Service.KEY_TEN_DAI_DIEN);
            session().remove(Service.KEY_CHUC_NANG_HIEN_TAI);
            session().remove(Service.KEY_MODULE_GOI_XAC_THUC);
            session().remove(Service.KEY_TYPE_ACC);
            session().remove(Service.KEY_LINK_ANH_DAI_DIEN);
            session().remove(Service.KEY_LINK_ANH_QR);
            session().remove(Service.KEY_DANG_KY_DONVI);
            session().remove(Service.KEY_CHECK_LOGIN);
        }
        return redirect(controllers.routes.Application.index());
    }

    public static Result register()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String sUserName = requestData.get("sdt");
        String sPass = requestData.get("mk");

        Service service = new Service();
        String sKetQua = service.register(sUserName, sPass);
        if(sKetQua != "")
        {
            RegisterObject obj = new Gson().fromJson(sKetQua, RegisterObject.class);
            if (obj.msgCode == 1)
            {
                session().put(Service.KEY_PHONE_DANG_KY, sUserName);
                session().put(Service.KEY_PASS_DANG_KY, sPass);
                session().put(Service.KEY_MODULE_GOI_XAC_THUC, Service.MODULE_TAO_TAI_KHOAN);
                return ok("SUCCESS");
            }
            else if(obj.msgCode == 9){
                return ok("OTP");
            }else{
                return ok(obj.msgContent);
            }
        }
        return ok("FAIL");
    }

    public static Result dangkydoanhnghiep()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String mst = requestData.get("maSoThue");
        String tendn = requestData.get("tenDoanhNghiep");
        String diachi = requestData.get("diaChi");
        String banquyen = requestData.get("banQuyen");
        String cqtcc = requestData.get("coquanthue");
        String cqt = requestData.get("coquanthue");
        String goihd = requestData.get("goiHoaDon");
        System.out.println("ma so thue: " + mst);
        System.out.println("ten doanh nghiep: " + tendn);
        System.out.println("dia chi: " + diachi);
        System.out.println("ban dsQuyen: " + banquyen);
        System.out.println("co quan thue: " + cqtcc);
        System.out.println("cqt: " + cqt);
        System.out.println("goi hd: " + goihd);

        Service service = new Service();
        String sKetQua = service.dangkydoanhnghiep(mst, tendn,diachi,banquyen,cqtcc,cqt,goihd);

        if(sKetQua != "")
        {
            RegisterObject obj = new Gson().fromJson(sKetQua, RegisterObject.class);
            if (obj.msgCode == 1)
            {

                session().put(Service.KEY_MST, mst );
                session().put(Service.KEY_TENDOANHNGHIEP, tendn);
                session().put(Service.KEY_DIACHI, diachi);
                session().put(Service.KEY_BANQUYEN, banquyen);
                session().put(Service.KEY_CQT_CAPCUC, cqtcc);
                session().put(Service.KEY_CQT, cqtcc);
                session().put(Service.KEY_GOIHOADON, goihd);
                session().put(Service.KEY_MODULE_GOI_XAC_THUC, Service.MODULE_TAO_TAI_KHOAN);
                return ok("SUCCESS");
            }
            else{
                return ok(obj.msgContent);
            }
        }
        return ok("FAIL");
    }
    public static Result confirmOTP()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String sOTP = requestData.get("otp");
        Service service = new Service();
        String sKetQuaDangKy = "";
        if(session().get(Service.KEY_MODULE_GOI_XAC_THUC) != null)
        {
            if (session().get(Service.KEY_MODULE_GOI_XAC_THUC).equals(Service.MODULE_TAO_TAI_KHOAN))
                sKetQuaDangKy = service.confirmOTP(session().get(Service.KEY_PHONE_DANG_KY), sOTP, Service.MODULE_TAO_TAI_KHOAN);
            else if (session().get(Service.KEY_MODULE_GOI_XAC_THUC).equals(Service.MODULE_QUEN_MAT_KHAU))
                sKetQuaDangKy = service.confirmOTP(session().get(Service.KEY_PHONE_DANG_KY), sOTP, Service.MODULE_QUEN_MAT_KHAU);
        }
        if(sKetQuaDangKy != "")
        {
            ConfirmOTPObject objSuccess = new Gson().fromJson(sKetQuaDangKy, ConfirmOTPObject.class);
            if (objSuccess.msgCode == 1)
            {

                return ok("SUCCESS");
            }
            else
            {
                return ok(objSuccess.msgContent);
            }
        }
        return ok("FAIL");
    }
    public static Result forgotPassword()
    {
        DynamicForm dataForm = Form.form().bindFromRequest();
        String sPhone = dataForm.get("phone");
        String sNewPass = dataForm.get("newpass");
        Service service = new Service();
        String sKetQua = service.forgotPass(sPhone, sNewPass);
        if(sKetQua != "")
        {
            ForgotPassSuccess obj = new Gson().fromJson(sKetQua, ForgotPassSuccess.class);
            if(obj.msgCode == 1)
            {
                session().put(Service.KEY_PHONE_DANG_KY, sPhone);
                session().put(Service.KEY_PASS_DANG_KY,sNewPass);
                session().put(Service.KEY_MODULE_GOI_XAC_THUC, Service.MODULE_QUEN_MAT_KHAU);
                return ok("SUCCESS");
            }
            else
            {
                return ok(obj.msgContent);
            }
        }
        return ok("FAIL");
    }
    public static void Checklogin(String user) {

        System.out.println("tài khoản: "+user);
        int funcId = 215;
        String secssionLogin = session().get(Service.KEY_SESSION_ID);
        String rootCompanyCode = "0105906169";

        long timeRequest = new Date().getTime();
        String checkSum = controllers.Utility.getMD5("235948rfddasda6578ikjndsdafvFGNHRkvkj" + user + funcId + secssionLogin + timeRequest);
        ResponseMessage responseMessage = (ResponseMessage) CallService.CheckloginVimass(user, funcId, secssionLogin, timeRequest, checkSum, rootCompanyCode);
        if (responseMessage != null && responseMessage.getMsgCode() == 1) {
            System.out.println("Đây là nhân viên vimass");

            session().put(Service.KEY_CHECK_LOGIN, "1");
            System.out.println("check1: "+ session().get(Service.KEY_CHECK_LOGIN));
        } else if (responseMessage != null && responseMessage.getMsgCode() == 0) {

            System.out.println("đây  KO PHẢI NHÂN VIÊN ");
            session().put(Service.KEY_CHECK_LOGIN, "0");

            System.out.println("check0: "+ session().get(Service.KEY_CHECK_LOGIN));
        }
    }

    public class ForgotPassSuccess
    {
        int msgCode;
        String msgContent;
    }

    public class ConfirmOTPObject
    {
        int msgCode;
        String msgContent;
    }

    public class objectKetQuaFB{
        Object data;
    }

    public class linkFBObject{
        String url;
        int width;
        int height;
    }

    public class Login
    {
        public int msgCode;
        public String msgContent;
        public Object result;
    }
    public class Logout
    {
        int msgCode;
        String msgContent;
        String result;
    }
    public class RegisterObject
    {
        public int msgCode;
        public String msgContent;
    }
     /*kieu dnag nhap */
    public static Result type_login()
    {
        String param1 = "8249539tgsdlka";
        String param2 = request().getQueryString("param2");
        String param3 = request().getQueryString("param3");
        ResponseMessage responseMessage = (ResponseMessage) CallService.Type_login(param1, param2, param3);
        if(responseMessage!=null)
        {
            if(responseMessage !=null)
            {
                return ok("ok");
            }else{
                return ok("fail");
            }
        }
        return ok("");
    }
     /*ket qua tra ve kieu dang nhap*/

    public static Result login_results()
    {
        String param1 = "8249539tgsdlka";
        String param2 = request().getQueryString("param2");
        String html="";
        ResponseMessage responseMessage = (ResponseMessage) CallService.Login_results(param1, param2);


        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1&&responseMessage.getResult()!=null)
            {
                html += responseMessage.getResult();
                System.out.println("result kieu dang nhap: " + responseMessage.getResult());
            }else{
                System.out.println("Gọi lại web1");
            }
        }
        return ok(html);
    }
}
