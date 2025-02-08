package entity;

import controllers.CallService;
import play.mvc.Controller;
import play.mvc.Result;
import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 10/29/22
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class DichVuGoiChung extends Controller {

    public static Result layThongTinCnVi()
    {
        String idVi = request().getQueryString("idVi");
        if(idVi!=null&&idVi!="")
        {
            AccViEntity info = CallService.layThongTinCNVi(idVi);
            String json = new Gson().toJson(info);
            json = json.replace("\\","");
            return ok(json);
        }
        return ok("");
    }
}
