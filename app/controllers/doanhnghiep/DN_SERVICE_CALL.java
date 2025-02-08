package controllers.doanhnghiep;

import com.google.gson.Gson;
import controllers.tools.ResponseMessage;
import controllers.tools.Utility;
import controllers.tools.listServices;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.libs.WS;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/10/22
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class DN_SERVICE_CALL {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static WS.WSRequestHolder request;
    public static WS.Response response = null;

    public static Object dangKyViDoanhNghiep(String companyCode,String companyName,String nameRepresent,String walletId,String imageCompany1,String imageCompany2,String email)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("companyCode", companyCode);
        data.put("companyName", companyName);
        data.put("nameRepresent",nameRepresent);
        data.put("walletId", walletId);
        data.put("email", email);
        data.put("imageCompany1", imageCompany1);
        data.put("imageCompany2", imageCompany2);
        data.put("appId", 5);
        data.put("deviceId", 4);
        data.put("VMApp",4);
        data.put("funcId", 21);
        Logger.info("dang ky request:" + new Gson().toJson(data));
        request = WS.url(DN_LIST_SERVICE.SERVICE_REGISTER);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        ResponseMessage res = null;
        System.out.println("dang ky response: " + response.getBody());
        if(Utility.isValidJSON(response.getBody())){
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if(res!= null)
        {
            return res;
        }
        else
        {
            return null;
        }
    }

    public static Object uploadAnhDangKy(String name,String value,String maDoanhNghiep)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("value", value);
        data.put("name", name);
        Logger.info("upload data :" + new Gson().toJson(data));
        request = WS.url(DN_LIST_SERVICE.SERVICE_UPLOAD_ANH + maDoanhNghiep);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        ResponseMessage res = null;
        Logger.info("url :" + response.getUri());
        System.out.println("upload response: " + response.getBody());
        if(Utility.isValidJSON(response.getBody())){
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if(res!= null)
        {
            return res;
        }
        else
        {
            return null;
        }
    }

    public static Object laychitietgiaodich(String user,String companyCode,String maGiaoDich)
    {
        WS.WSRequestHolder request;
        request = WS.url(DN_LIST_SERVICE.SERVICE_LAY_CHITIET_GIAODICH);
        request.setQueryParameter("user",user);
        request.setQueryParameter("companyCode",companyCode);
        request.setQueryParameter("maGiaoDich",maGiaoDich);
        request.setContentType(listServices.CONTENT_TYPE);
        ResponseMessage res = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            Logger.info("lay chi tiet dg: " + response.getUri());

            String json = jsonNode.toString();
            if(Utility.isValidJSON(json)){
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("Lay chi tiet gd: " + json);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public static Object laydanhsachgiaodich(String user,String companyCode,long from,long to,int offset,int limit)
    {
        WS.WSRequestHolder request;
        request = WS.url(DN_LIST_SERVICE.SERVICE_LAY_DS_GIAODICH);
        request.setQueryParameter("user",user);
        request.setQueryParameter("companyCode",companyCode);
        request.setQueryParameter("from",String.valueOf(from));
        request.setQueryParameter("to",String.valueOf(to));
        request.setQueryParameter("offset",String.valueOf(offset));
        request.setQueryParameter("limit",String.valueOf(limit));
        request.setContentType(listServices.CONTENT_TYPE);
        ResponseMessage res = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            Logger.info("lay ds dg: " + response.getUri());

            String json = jsonNode.toString();
            if(Utility.isValidJSON(json)){
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("Lay ds gd: " + json);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
