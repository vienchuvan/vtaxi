package controllers.doanhnghiep;

import controllers.tools.ResponseMessage;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 6/13/22
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DoanhNghiepController extends Controller {
    @BodyParser.Of(value = BodyParser.FormUrlEncoded.class, maxLength = 10 * 1024 * 1024)
    public static Result dangKyViDoanhNghiep()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String maDoanhNghiep = requestData.get("maDN");
        Logger.info(maDoanhNghiep);
        String tenDoanhNghiep = requestData.get("tenDN");
        String tenDaiDien = requestData.get("tenDaiDien");
        String soDienThoai = requestData.get("sdt");
        String tenanh1 = requestData.get("tenanh1");
        String anh1 = requestData.get("dulieuanh1").split(",")[1];
        String tenanh2 = requestData.get("tenanh2");
        String anh2 = requestData.get("dulieuanh2").split(",")[1];
        String email = requestData.get("emaildangky");

        ResponseMessage responseMessage_anh1 = (ResponseMessage) DN_SERVICE_CALL.uploadAnhDangKy(tenanh1, anh1, maDoanhNghiep);
        if(responseMessage_anh1!=null)
        {
            if(responseMessage_anh1.getMsgCode()==1)
            {
                String maAnh1 = responseMessage_anh1.getResult().toString();
                ResponseMessage responseMessage_anh2 = (ResponseMessage) DN_SERVICE_CALL.uploadAnhDangKy(tenanh2, anh2, maDoanhNghiep);
                if(responseMessage_anh2!=null)
                {
                    if(responseMessage_anh2.getMsgCode()==1)
                    {
                        String maAnh2 = responseMessage_anh2.getResult().toString();
                        ResponseMessage responseMessage_dangky = (ResponseMessage) DN_SERVICE_CALL.dangKyViDoanhNghiep(maDoanhNghiep, tenDoanhNghiep, tenDaiDien, soDienThoai, maAnh1, maAnh2, email);
                        if(responseMessage_dangky!=null)
                        {
                            if(responseMessage_dangky.getMsgCode()==1)
                            {
                                return ok("OK:"+responseMessage_dangky.getMsgContent());
                            }
                            else
                            {
                                return ok(responseMessage_dangky.getMsgContent());
                            }
                        }
                        else
                        {
                            return ok("Đăng ký không thành");
                        }
                    }
                    else
                    {
                        return ok(responseMessage_anh2.getMsgContent());
                    }
                }
                else
                {
                    return ok("Đăng ký không thành");
                }
            }
            else
            {
                return ok(responseMessage_anh1.getMsgContent());
            }
        }
        else
        {
            return ok("Đăng ký không thành");
        }
    }


}
