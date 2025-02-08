package controllers;

import com.google.gson.Gson;
import controllers.account.Service;
import controllers.account.ThongTinCaNhan;
import controllers.doanhnghiep.DN_LIST_SERVICE;
import controllers.thaydoi.VimassManagerSecssion;
import controllers.thongBao.ObjectResponse;
import controllers.thongBao.ThongBaoDetailResponse;
import controllers.thongBao.ThongBaoResponse;
import controllers.tools.ResponseMessage;
import controllers.tools.Utility;
import controllers.tools.VimassSercurityFunc;
import controllers.tools.listServices;
import entity.AccViEntity;
import entity.Laixe.ObjectItem1DiemDen;
import entity.ObjectPhanQuyen;
import entity.ResponseCameraEZ;
import entity.baiviet.itemCacheBaiViet;
import entity.createQR.createQREntity;
import entity.listService;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.libs.WS;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class CallService {
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static WS.WSRequestHolder request;
    public static WS.Response response = null;

    public static Object luuThongTin(String key,String value)
    {

        ResponseMessage res = VimassManagerSecssion.saveSecssion(key, value);
        return res;
    }

    public static Object checkPointDangNhap(String deviceOS,String phone,String appId,String deviceId)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("deviceOS", deviceOS);
        data.put("phone", phone);
        data.put("appId", 5);
        data.put("deviceId", deviceId);
        data.put("VimassMH",1); // pass value
        Logger.info("check post:" + new Gson().toJson(data));
        request = WS.url(listServices.SERVICE_CHECK_POINT);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;
        System.out.println("check response: " + response.getBody());
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
    public static Object PheduyettoadoQr(String id,int funcId,   String lat, String lng, long timeRequest,String user, String token, String accQL, String listImage, String checkSum, String ip) {


        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", id);
        data.put("funcId", funcId);
        data.put("lat", lat);
        data.put("lng", lng);
        data.put("timeRequest", timeRequest);
        data.put("user", user);
        data.put("token", token);



        data.put("accQL", accQL);
        data.put("listImage", listImage);


        data.put("checkSum", checkSum);

        data.put("ip", ip);

        Logger.info("Phe duyet toa do post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url("http://103.21.150.8:62168/toaDoVimass/services/toaDoVimass/requestCommand");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("Toa do json response: " + json);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object reject_confirm(String id, int funcId,long timeRequest, String user, String token,String checkSum, String ip) {

        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", id);
        data.put("funcId", funcId);
        data.put("timeRequest", timeRequest);
        data.put("user", user);
        data.put("token", token);
        data.put("checkSum", checkSum);
        data.put("ip", ip);

        Logger.info("reject_Toado post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url("http://103.21.150.8:62168/toaDoVimass/services/toaDoVimass/requestCommand");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("Reject_Toado response: " + json);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object traCuuThongTinQRVN(long timeRequest, String checkSum, String maQR, int funcId, String mcId) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("mcId", mcId);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);
        data.put("maQR", maQR);

      /*  Logger.info("traCuuThongTinQRVN request :" + new Gson().toJson(data));*/
        System.out.println();
        request = WS.url(listService.SERVICES_TRACUU_QRVN);
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);


        ResponseMessage res = null;


     /*Logger.info("traCuuThongTinQRVN response:" + response.getBody());*/


        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);

        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object CheckloginVimass( String user, int funcId, String secssionLogin, Long timeRequest,String checkSum,String rootCompanyCode ) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("user", user);
        data.put("secssionLogin", secssionLogin);
        data.put("checkSum", checkSum);
        data.put("timeRequest", timeRequest);
        data.put("timeRequest", rootCompanyCode);

        Logger.info("checklogin=== post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url(controllers.listService.SERVICE_Check_Login);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            /*System.out.println("checklogin response: " + json);*/
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object laytoado(String checkSum, Long timeRequest, String id, int funcId) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("checkSum", checkSum);
        data.put("timeRequest", timeRequest);

        data.put("id", id);
        data.put("funcId", funcId);
        Logger.info("toa do post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url(controllers.listService.SERVICE_LAY_TOA_DO);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            /*System.out.println("listtoado response: " + json);*/
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object danhsachtoado(int funcId, String idViVimass, Long timeRequest, String secssionLogin, String checkSum ) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("id", idViVimass);
        data.put("checkSum", checkSum);
        data.put("secssionLogin", secssionLogin);
        data.put("timeRequest", timeRequest);


/*
       Logger.info("toadoqr=== post:" + data.toString());
*/
//        request.setTimeout(100000);
        request = WS.url(listService.SERVICE_List_Xe);
        request.setContentType(listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);

            System.out.println("lisxe response: " + json);

        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object tracuubankinh(int funcId, double km, double lat, double lng, String keySearch, int limit, int offset, long timeRequest, String checkSum, String user, String ip) {


        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("km", km);
        data.put("lat", lat);
        data.put("lng", lng);
        data.put("keySearch", keySearch);
        data.put("limit", limit);
        data.put("offset", offset);

        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);
        data.put("user", user);
        data.put("ip", ip);

        Logger.info("tra cuu bán kinh post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url(listService.SERVICE_LAY_TOA_DO);
        request.setContentType(listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
/*
            System.out.println("json toado "+ json);
*/
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object toadoqr(int funcId, String id, int typeLL, String checkSum, Long timeRequest) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", funcId);
        data.put("id", id);
        data.put("typeLL", typeLL);
        data.put("checkSum", checkSum);
        data.put("timeRequest", timeRequest);


/*
       Logger.info("toadoqr=== post:" + data.toString());
*/
//        request.setTimeout(100000);
        request = WS.url(listService.SERVICE_Tra_Cuu_Toa_Do_QR);
        request.setContentType(listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
/*
            System.out.println("checktoado2 response: " + json);
*/
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object objVitritrungtam(String sdtNhanVien) {

        WS.WSRequestHolder request;
        request = WS.url("http://103.21.150.9:8080/vimass-staff/services/chi-nhanh/nhan-vien");
        request.setQueryParameter("sdtNhanVien", sdtNhanVien);

        request.setContentType(listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("cbnv request:" + response.getBody());
            String res = response.getBody();
            if (res != null && res != "") {
                ResponseMessage rm = new Gson().fromJson(res, ResponseMessage.class);
               /* System.out.println("===========" + rm);*/
                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ThongTinCaNhan layThongTin(String key)
    {
        ThongTinCaNhan info = new ThongTinCaNhan();
        ResponseMessage res = VimassManagerSecssion.getSecssion(key);
        if(res.getMsgCode()==1)
        {
            String json = res.getResult().toString();
            if(Utility.isValidJSON(json)){
                info = new Gson().fromJson(json, ThongTinCaNhan.class);

            }
        }
        return info;
    }
    public static Object dangnhapvid(String idVid, String pass)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idVid",idVid);
        data.put("pass",pass);
        data.put("VimassMH",1); // pass value
        data.put("VMApp",4);
        Logger.info("dangnhap vid post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url(listServices.SERVICE_DANGNHAP_VID);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        System.out.println("dangnhap vid response: " + response.getBody());
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

    public static Object getAmount(String id)
    {
        WS.WSRequestHolder request;
        request = WS.url(listServices.SERVICE_LAY_SO_DU);
        request.setQueryParameter("id",id);
        request.setContentType(listServices.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            System.out.println("Lay so du request: " + response.getUri());
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            if(Utility.isValidJSON(json)){
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("Lay so du response: " + json);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static Object checkTKNH(String user,String session,String soTK,String maNganHang,String soThe)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user",user);
        data.put("session",session);
        data.put("soTaiKhoan",soTK);
        data.put("maNganHang",maNganHang);
        data.put("soThe",soThe);
        data.put("appId",5);
        data.put("VMApp",4);
        data.put("VimassMH",1); // pass value
        Logger.info("tra cuu TKNH post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("https://vimass.vn/vmbank/services/autoBank/searchTK");
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        String json = response.getBody();
        if(json != null && !json.contains("{") && json.contains("zzz_"))
        {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1,VimassSercurityFunc.Key2,VimassSercurityFunc.Key3,json);
        }
        System.out.println("tra cuu TKNH response: " + json);
        if(Utility.isValidJSON(json)){
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
// dich vu tao qr
    public static Object createQR(createQREntity entity) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("addCompany", entity.addCompany);
        data.put("amount", entity.amount);
        data.put("bankCode", entity.bankCode);
        data.put("bankName", entity.bankName);
        data.put("bankNumber", entity.bankNumber);
        data.put("cellPhoneNumber", entity.cellPhoneNumber);
        data.put("checkSum", entity.checkSum);
        data.put("contentQRNonFinal", entity.contentQRNonFinal);
        data.put("customerCode", entity.customerCode);
        data.put("descLink", entity.descLink);
        data.put("descQR", entity.descQR);
        data.put("email", entity.email);
        data.put("funcId", entity.funcId);
        data.put("idViVimass", entity.idViVimass);
        data.put("line1", entity.line1);
        data.put("line2", entity.line2);
        data.put("line3", entity.line3);
        data.put("line4", entity.line4);
        data.put("logoIndex", entity.logoIndex);
        data.put("mcId", entity.mcId);
        data.put("mst", entity.mst);
        data.put("mstExport", entity.mstExport);
        data.put("nameCompany", entity.nameCompany);
        data.put("nameQR", entity.nameQR);
        data.put("providerCode", entity.providerCode);
        data.put("session", entity.session);
        data.put("signData", entity.signData);
        data.put("token", entity.token);
        data.put("typeAuthenticate", entity.typeAuthenticate);
        data.put("typeQR", entity.typeQR);
        data.put("frameType", entity.frameType);

        data.put("lat", entity.lat);
        data.put("lng", entity.lng);
        data.put("catId",entity.catId);

        data.put("StoreAdd", entity.StoreAdd);
        data.put("visiable", entity.visiable);

        data.put("image1", entity.image1);
        data.put("sdt", entity.sdt);
        data.put("emailLienHe", entity.emailLienHe);
        data.put("bsx", entity.bsx);

        data.put("linkWeb", entity.linkWeb);
        data.put("linkFB", entity.linkFB);
        data.put("linkInstar", entity.linkInstar);
        data.put("linkVideo", entity.linkVideo);
        data.put("linkImageThumbnail", entity.linkImageThumbnail);

        data.put("hintLinkWeb", entity.hintLinkWeb);
        data.put("hintLinkFB", entity.hintLinkFB);
        data.put("hintLinkInstar", entity.hintLinkInstar);
        data.put("hintLinkVideo", entity.hintLinkVideo);

        Logger.info("createQR request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/VimassQR/services/VMDonHang/requestCommand");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);


        Logger.info("createQR response:" + response.getBody());
        ResponseMessage res = null;
        Logger.info("taoQRThanhToan response:" + response.getBody());
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
// dich cu tra cuu qr theo id vi
    public static Object lookup_qr_idvi(long timeRequest, String mcId, int funcId, String checkSum, String idViVimass, int offset, int limit) {
    StringWriter sw = new StringWriter();
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("timeRequest", timeRequest);
    data.put("mcId", mcId);
    data.put("funcId", funcId);
    data.put("checkSum", checkSum);
    data.put("idViVimass", idViVimass);
    data.put("offset", offset);
    data.put("limit", limit);
/*
    Logger.info("tra cuu post:" + new Gson().toJson(data));
*/
    request = WS.url("https://vimass.vn/VimassQR/services/VMDonHang/requestCommand");
    request.setContentType(listServices.CONTENT_TYPE);
    try {
        mapper.writeValue(sw, data);
    } catch (IOException e) {
        e.printStackTrace();
    }

    long timeout = 100000;
    response = request.post(sw.toString()).get(timeout);
    ResponseMessage res = null;
    System.out.println("tra cuu response: " + response.getBody());
    // decrpit data
    String json = response.getBody();
    if (Utility.isValidJSON(json)) {
        res = new Gson().fromJson(json, ResponseMessage.class);
        /*Logger.info("json tra cuu qr theo id ví: " + json);*/
    }
    if (res != null) {
        return res;
    } else {
        return null;
    }
}

//  Cập nhật lại QR
    public static Object editQR(createQREntity entity) {
        StringWriter sw = new StringWriter();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("maGiaoDich", entity.maGiaoDich);
            data.put("addCompany", entity.addCompany);
            data.put("amount", entity.amount);
            data.put("bankCode", entity.bankCode);
            data.put("bankName", entity.bankName);
            data.put("bankNumber", entity.bankNumber);
            data.put("cellPhoneNumber", entity.cellPhoneNumber);
            data.put("checkSum", entity.checkSum);
            data.put("contentQRNonFinal", entity.contentQRNonFinal);
            data.put("customerCode", entity.customerCode);
            data.put("descLink", entity.descLink);
            data.put("descQR", entity.descQR);
            data.put("email", entity.email);
            data.put("funcId", entity.funcId);
            data.put("idViVimass", entity.idViVimass);
            data.put("line1", entity.line1);
            data.put("line2", entity.line2);
            data.put("line3", entity.line3);
            data.put("line4", entity.line4);
            data.put("logoIndex", entity.logoIndex);
            data.put("mcId", entity.mcId);
            data.put("mst", entity.mst);
            data.put("mstExport", entity.mstExport);
            data.put("nameCompany", entity.nameCompany);
            data.put("nameQR", entity.nameQR);
            data.put("providerCode", entity.providerCode);
            data.put("session", entity.session);
            data.put("signData", entity.signData);
            data.put("token", entity.token);
            data.put("typeAuthenticate", entity.typeAuthenticate);
            data.put("typeQR", entity.typeQR);
            data.put("frameType", entity.frameType);

            data.put("lat", entity.lat);
            data.put("lng", entity.lng);
            data.put("catId",entity.catId);

            data.put("StoreAdd", entity.StoreAdd);
            data.put("visiable", entity.visiable);

            data.put("image1", entity.image1);
            data.put("sdt", entity.sdt);
            data.put("emailLienHe", entity.emailLienHe);
            data.put("bsx", entity.bsx);

            data.put("linkWeb", entity.linkWeb);
            data.put("linkFB", entity.linkFB);
            data.put("linkInstar", entity.linkInstar);
            data.put("linkVideo", entity.linkVideo);
            data.put("linkImageThumbnail", entity.linkImageThumbnail);

            data.put("hintLinkWeb", entity.hintLinkWeb);
            data.put("hintLinkFB", entity.hintLinkFB);
            data.put("hintLinkInstar", entity.hintLinkInstar);
            data.put("hintLinkVideo", entity.hintLinkVideo);



        Logger.info("editQR request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/VimassQR/services/VMDonHang/requestCommand");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);


        Logger.info("editQR response:" + response.getBody());
        ResponseMessage res = null;
        Logger.info("editQR response:" + response.getBody());
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }


    public static Object sua_thong_tin_ca_nhan(ThongTinCaNhan object) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        if (object != null) {
            data.put("id", object.getId());
            data.put("linkBackIdCard", object.getLinkBackIdCard());
            data.put("linkFrontIdCard", object.getLinkFrontIdCard());
            data.put("linkSignature", object.getLinkSignature());
            data.put("avatar", object.getAvatar());
            data.put("nameAlias", object.getNameAlias());
            data.put("email", object.getEmail());
            data.put("acc_name", object.getAcc_name());
            data.put("idCard", object.getIdCard());
            data.put("birthday", object.getBirthday());
            data.put("dateIdCard", object.getDateIdCard());
            data.put("placeIdCard", object.getPlaceIdCard());
            data.put("home", object.getHome());
            data.put("token", object.getToken());
            data.put("otpConfirm", object.getOtpConfirm());
            data.put("typeAuthenticate", object.getTypeAuthenticate());
            data.put("phoneAuthenticate", object.getPhoneAuthenticate());
            data.put("accBank", object.getAccBank());
            //them cho doanh nghiep
            data.put("companyCode", object.getCompanyCode());
            data.put("companyName", object.getCompanyName());
            data.put("nameRepresent", object.getNameRepresent());
            data.put("walletId", object.getWalletId());
            data.put("imageCompany1", object.getImageCompany1());
            data.put("imageCompany2", object.getImageCompany2());
            data.put("dsLap", object.getDsLap());
            data.put("dsDuyet", object.getDsDuyet());
            data.put("VMApp", 4);
            data.put("appId", 5);
            data.put("tKRutTien", object.gettKRutTien());
            data.put("hienThiNoiDungThanhToanQR", object.getHienThiNoiDungThanhToanQR());
            data.put("imageBHYT", object.getImageBHYT());
            data.put("valueBHYT", object.getValueBHYT());
            data.put("maSoThue", object.getMaSoThue());
            data.put("diaChiDoanhNghiep", object.getDiaChiDoanhNghiep());
            data.put("tenDoanhNghiep", object.getTenDoanhNghiep());
            data.put("VimassMH", 1); // pass value
            data.put("cauHinhChuyenTienTuDong", object.getCauHinhChuyenTienTuDong());
            data.put("lat", object.getLat());
            data.put("lng", object.getLng());
            data.put("visiable", "1");

        }

        Logger.info("sua doi tk post:" + new Gson().toJson(data));
        request = WS.url(listServices.SERVICE_SUA_THONG_TIN_VI);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));
        Logger.info("data : " + dataSend);
        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;

        // decrpit data
        String json = response.getBody();
        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
        }
        if (Utility.isValidJSON(json)) {
            System.out.println("sua doi tk response: " + json);
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static String upLoadAnh(String sId, String duLieuAnh) {
        String sKetQua = "";
        try {
            WS.Response response = null;
            request = WS.url(listServices.SERVICE_UPLOAD_ANH + "/" + sId);

            long timeout = 100000;
            response = request.post(duLieuAnh).get(timeout);
            if (Utility.isValidJSON(response.getBody())) {
                sKetQua = response.getBody();
                Logger.info("upload image: " + sKetQua);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sKetQua;
    }

 // dich vu goi anh nen qr
    public static Object getImageBackGround(int idDanhMuc, String hashtag, int limit, int offset) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idDanhMuc", idDanhMuc);
        data.put("hashtag", hashtag);
        data.put("limit", limit);
        data.put("offset", offset);

        Logger.info("getImageBackGround request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/VimassMedia/services/VMBackground/getListBackground");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);


        ResponseMessage res = null;
        Logger.info("getImageBackGround response:" + response.getBody());
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object getImageBackGroundNewest(int limit, int offset) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("limit", limit);
        data.put("offset", offset);

        Logger.info("getImageBackGroundNewest request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/VimassMedia/services/VMBackground/getNewImage");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);


        ResponseMessage res = null;
        Logger.info("getImageBackGroundNewest response:" + response.getBody());
        String json = response.getBody();

        if (Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
// dich vu goi anh nen random qr
    public static Object getImageBackGroundRandom(int limit, int offset) {
    StringWriter sw = new StringWriter();
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("limit", limit);
    data.put("offset", offset);

    Logger.info("getImageBackGroundRandom request :" + new Gson().toJson(data));

    request = WS.url("https://vimass.vn/VimassMedia/services/VMBackground/getRandomImage");
    request.setContentType("application/json");

    try {
        mapper.writeValue(sw, data);
    } catch (IOException e) {
        e.printStackTrace();
    }

    long timeout = 100000;

    response = request.post(sw.toString()).get(timeout);


    ResponseMessage res = null;
    Logger.info("getImageBackGroundRandom response:" + response.getBody());
    String json = response.getBody();

    if (Utility.isValidJSON(json)) {
        res = new Gson().fromJson(json, ResponseMessage.class);
    }

    if (res != null) {
        return res;
    } else {
        return null;
    }
}
    // dich vu tao anh
    public static Object taoAnh(String value) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("value", value);
        Logger.info("gui anh post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url(listServices.SERVICE_TAOANH);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        System.out.println("gui anh response: " + response.getBody());
        if (Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object taoAnh_infor(String idVi,String base64)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idVi",idVi);
        data.put("base64",base64);
        Logger.info("gui anh post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        //Utility.clearInputData(data);
        request = WS.url(controllers.listService.SERVICE_TAOANH_info);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        System.out.println("gui anh response: " + response.getBody());
        if(controllers.Utility.isValidJSON(response.getBody())){
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

// dich vu sao ke tat ca qr
    public static Object saoKeQRCuaVi(long timeFrom, long timeTo, String idViSoHuu, String secssion, int offset, int limit, String checkSum, long timeRequest, String keySearch) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", 15);
        data.put("timeFrom", timeFrom);
        data.put("timeTo", timeTo);
        data.put("idViSoHuu", idViSoHuu);
        data.put("secssion", secssion);
        data.put("offset", offset);
        data.put("limit", limit);
        data.put("checkSum", checkSum);
        data.put("timeRequest", timeRequest);
        data.put("keySearch", keySearch);
        data.put("ver",1);
        Logger.info("saoKeQRCuaVi post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("https://vimass.vn/VimassQR/services/VMQR/requestCommand");
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        System.out.println("saoKeQRCuaVi response: " + response.getBody());
        if (Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
// dich vu sao ke cua 1 qr
    public static Object saoKeQR(long timeFrom, long timeTo, String idViSoHuu, String secssion, int offset, int limit, String maQR, String checkSum, long timeRequest, String keySearch) {
    StringWriter sw = new StringWriter();
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("funcId", 16);
    data.put("timeFrom", timeFrom);
    data.put("timeTo", timeTo);
    data.put("idViSoHuu", idViSoHuu);
    data.put("secssion", secssion);
    data.put("offset", offset);
    data.put("limit", limit);
    data.put("maQR", maQR);
    data.put("checkSum", checkSum);
    data.put("timeRequest", timeRequest);
    data.put("keySearch", keySearch);
    data.put("ver", 1);
    Logger.info("saoKeQR gd post:" + new Gson().toJson(data));
    ResponseMessage res = null;
    request = WS.url("https://vimass.vn/VimassQR/services/VMQR/requestCommand");
    request.setContentType(listServices.CONTENT_TYPE);
    try {
        mapper.writeValue(sw, data);
    } catch (IOException e) {
        e.printStackTrace();
    }
    response = request.post(sw.toString()).get();
    System.out.println("saoKeQR giao dich response: " + response.getBody());
    if (Utility.isValidJSON(response.getBody())) {
        String json = response.getBody();
        res = new Gson().fromJson(json, ResponseMessage.class);
    }
    if (res != null) {
        return res;
    } else {
        return null;
    }
}
    public static Object saoke_moi(String phone, long fromDate, long toDate, int start, int limit, int type, String idVimass, String transId, String VaNumber, String idRequestMc) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", phone);
        data.put("fromDate", fromDate);
        data.put("toDate", toDate);
        data.put("start", start);
        data.put("limit", limit);
        data.put("type", type);
        data.put("session", Service.KEY_SESSION_ID);
        data.put("idVimass", idVimass);
        data.put("transId", transId);
        data.put("VaNumber", VaNumber);
        data.put("idRequestMc", idRequestMc);
        data.put("VMApp", 4);
        data.put("appId", 5);
        data.put("ver", 1);
        data.put("VimassMH", 1); // pass value
        Logger.info("saoke moi request:" + data.toString());
        request = WS.url(listServices.SERVICE_INQUIRY1);
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 30000;
        response = request.post(dataSend).get(timeout);
        Logger.info("sao ke url:" + response.getUri());
        ResponseMessage res = null;

        // decrpit data
        String json = response.getBody();

        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
            Logger.info("hahahah: " + json);
        }
        if (controllers.Utility.isValidJSON(json)) {


            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        Logger.info("saoke response: " + json);


        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static String layThongTinQuyenLapDuyet(String checkSum, String user, String companyCode, String type) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/pQDoanhNghiep/getDataLapDuyet");
        request.setQueryParameter("checkSum", checkSum);
        request.setQueryParameter("user", user);
        request.setQueryParameter("companyCode", companyCode);
        request.setQueryParameter("type", type);

        request.setContentType(listServices.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("layThongTinQuyenLapDuyet request:" + response.getUri());
            System.out.println("layThongTinQuyenLapDuyet response: " + response.getBody());
            String res = response.getBody();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object acc_info(String id, String user) {
        WS.WSRequestHolder request;
        request = WS.url("https://vimass.vn/vmbank/services/admin/NHNNGetWalletInfor");
        request.setQueryParameter("id", id);
        request.setQueryParameter("user", user);
        request.setContentType(listServices.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            Logger.info("Lay info url: " + response.getUri());
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();

            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ResponseMessage.class);
                Logger.info("Lay info: " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static String ObjChinhanh() {

        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/quanTriHeThong/quanTriThuChiHo");
        request.setQueryParameter("pass", "84935896gjskfvjfhgQEWRHGs");
        request.setQueryParameter("param", "29_xxx_YYYYYYYYYY");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            /*Logger.info("Chinhanh request:" + response.getBody());*/
            String res = response.getBody();

            if (res != null && res != "") {

                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String layThongTinQuyenXemBaoCao(String checkSum, String user, String companyCode) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/pQDoanhNghiep/getDataXBCao");
        request.setQueryParameter("checkSum", checkSum);
        request.setQueryParameter("user", user);
        request.setQueryParameter("companyCode", companyCode);

        request.setContentType(listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("layThongTinQuyenXemBaoCao request:" + response.getUri());
            System.out.println("layThongTinQuyenXemBaoCao response: " + response.getBody());
            String res = response.getBody();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object layDSDoanhNghiepCon(String user, String secssionLogin, String rootCompanyCode, long timeRequest, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("secssionLogin", secssionLogin);
        data.put("rootCompanyCode", rootCompanyCode);
        data.put("funcId", 3);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);


        Logger.info("layDSDoanhNghiepCon request :" + new Gson().toJson(data));

        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/requestComand");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);

        ResponseMessage res = null;
        Logger.info("layDSDoanhNghiepCon response:" + response.getBody());
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object layDSPhanQuyenCuaDN(String user, String secssionLogin, String rootCompanyCode, long timeRequest, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("secssionLogin", secssionLogin);
        data.put("rootCompanyCode", rootCompanyCode);
        data.put("funcId", 4);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);


        Logger.info("layDSPhanQuyenCuaDN request :" + new Gson().toJson(data));

        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/requestComand");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);

        ResponseMessage res = null;
        Logger.info("layDSPhanQuyenCuaDN response:" + response.getBody());
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object setupLapDuyet(String user, String token, String companyCode, ArrayList<ObjectPhanQuyen> dsQuyen, long timeCreate, long timeRequest, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("token", token);
        data.put("companyCode", companyCode);
        data.put("dsQuyen", dsQuyen);
        data.put("timeCreate", timeCreate);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);


        Logger.info("setupLapDuyet request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/vmbank/services/pQDoanhNghiep/setupLapDuyet");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);

        ResponseMessage res = null;
        Logger.info("setupLapDuyet response:" + response.getBody());
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object setupXemBaoCao(String user, String token, String companyCode, ArrayList<ObjectPhanQuyen> dsQuyen, long timeRequest, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("token", token);
        data.put("companyCode", companyCode);
        data.put("dsQuyen", dsQuyen);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", checkSum);


        Logger.info("setupXemBaoCao request :" + new Gson().toJson(data));

        request = WS.url("https://vimass.vn/vmbank/services/pQDoanhNghiep/setupXBCao");
        request.setContentType("application/json");

        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long timeout = 100000;

        response = request.post(sw.toString()).get(timeout);

        ResponseMessage res = null;
        Logger.info("setupXemBaoCao response:" + response.getBody());
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static AccViEntity layThongTinAvatarVi(String idVi) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/getAvt");
        request.setQueryParameter("pass", "8249539tgsdlka");
        request.setQueryParameter("param", idVi);

        request.setContentType(listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("layThongTinAvatarVi request:" + response.getUri());
            System.out.println("layThongTinAvatarVi response: " + response.getBody());
            String res = response.getBody();
            if (res != null && res != "") {
                AccViEntity acc = new Gson().fromJson(res, AccViEntity.class);
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object saoke(String phone, long fromDate, long toDate, int start, int limit, int type, String idVimass, String transId, String VaNumber, String idRequestMc) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", phone);
        data.put("fromDate", fromDate);
        data.put("toDate", toDate);
        data.put("start", start);
        data.put("limit", limit);
        data.put("type", type);
        data.put("session", Service.KEY_SESSION_ID);
        data.put("idVimass", idVimass);
        data.put("transId", transId);
        data.put("VaNumber", VaNumber);
        data.put("idRequestMc", idRequestMc);
        data.put("VMApp", 4);
        data.put("appId", 5);
        data.put("VimassMH", 1); // pass value
        Logger.info("saoke data:" + data.toString());
        request = WS.url(controllers.listService.SERVICE_INQUIRY);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        Logger.info("sao ke url:" + response.getUri());
        ResponseMessage res = null;

        // decrpit data
        String json = response.getBody();
        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
        }
        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        Logger.info("saoke response: " + json);


        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object GuiEcexlEmail(String phone, String email, long fromDate, long toDate, int limit, int type) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", phone);
        data.put("email", email);
        data.put("fromDate", fromDate);
        data.put("toDate", toDate);
        data.put("limit", limit);
        data.put("type", type);
        data.put("VimassMH", 1); // pass value
        Logger.info("gui email post:" + new Gson().toJson(data));
        /*request = WS.url(ListServices.SERVICE_GUI_EXCEL_EMAIL);*/
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/sendInquiryToEmail");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;

        // decrpit data
        String json = response.getBody();
        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
        }
        if (controllers.Utility.isValidJSON(json)) {
            System.out.println("gui email response: " + json);
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object layDsCtyDN(String user, String secssionLogin, String rootCompanyCode) {
        long timeRequest = new Date().getTime();
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", 2);
        data.put("user", user);
        data.put("secssionLogin", secssionLogin);
        data.put("timeRequest", timeRequest);
        data.put("rootCompanyCode", rootCompanyCode);
        data.put("checkSum", controllers.Utility.getMD5("235948rfddasda6578ikjndsdafvFGNHRkvkj" + user + "2" + secssionLogin + timeRequest));
        Logger.info("layDsCty post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/requestComand");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        System.out.println("layDsCty response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object thongKeChung(String userLogin, String secssion, String idList, long fromDate, long toDate, int type) {
        long timeRequest = new Date().getTime();
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userLogin", userLogin);
        data.put("secssion", secssion);
        data.put("idList", idList);
        data.put("fromDate", fromDate);
        data.put("toDate", toDate);
        data.put("type", type);
        data.put("VimassMH", 1);
        data.put("start ", 0);
        data.put("limit ", 50);
        data.put("timeRequest", timeRequest);

        data.put("checkSum", controllers.Utility.getMD5("9874397635976839" + userLogin + secssion + idList + fromDate + toDate + "50" + "1" + type + timeRequest));
        Logger.info("thongKeChung post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/inquiry2");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        System.out.println("thongKeChung response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object layDsCty(String user, String secssionLogin) {
        long timeRequest = new Date().getTime();
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", 1);
        data.put("user", user);
        data.put("secssionLogin", secssionLogin);
        data.put("timeRequest", timeRequest);
        data.put("checkSum", controllers.Utility.getMD5("235948rfddasda6578ikjndsdafvFGNHRkvkj" + user + "1" + secssionLogin + timeRequest));
        Logger.info("layDsCty post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/requestComand");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        System.out.println("layDsCty response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object thongKeChung1(String userLogin, String secssion, String idList, long fromDate, long toDate, int type) {
        long timeRequest = new Date().getTime();
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userLogin", userLogin);
        data.put("secssion", secssion);
        data.put("idList", idList);
        data.put("fromDate", fromDate);
        data.put("toDate", toDate);
        data.put("type", type);
        data.put("VimassMH", 1);
        data.put("start ", 0);
        data.put("limit ", 50);
        data.put("timeRequest", timeRequest);

        data.put("checkSum", controllers.Utility.getMD5("9874397635976839" + userLogin + secssion + idList + fromDate + toDate + "50" + "1" + type + timeRequest));
        Logger.info("thongKeChung post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/inquiry4");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        System.out.println("thongKeChung response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object laydanhsachthongbao(int funcId, int appId, String id, long time, int offset, int limit, int typeSearch, int phanLoaiTinNhan) {

        System.out.println("Data input: " + " funcId " + funcId + " appId " + appId + " id " + id + " time " + time + " offset " + offset + " limit " + limit + " typeSearch " + typeSearch + " phanLoaiTinNhan " + phanLoaiTinNhan);
        WS.WSRequestHolder request;
        request = WS.url(controllers.listService.SERVICE_LAY_DS_TIN);
        request.setQueryParameter("funcId", String.valueOf(funcId));
        request.setQueryParameter("appId", String.valueOf(5));
        request.setQueryParameter("id", String.valueOf(id));
        request.setQueryParameter("time", String.valueOf(time));
        request.setQueryParameter("offset", String.valueOf(offset));
        request.setQueryParameter("limit", String.valueOf(limit));
        request.setQueryParameter("typeSearch", String.valueOf(typeSearch));
        request.setQueryParameter("session", Service.KEY_SESSION_ID);
        request.setQueryParameter("phanLoaiTinNhan", String.valueOf(phanLoaiTinNhan));
        request.setQueryParameter("VMApp", "4");
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ThongBaoResponse res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
           /* System.out.println("Đây");*/
            JsonNode jsonNode = response.asJson();
          /*  System.out.println("Lay ds thong bao2: " + jsonNode);*/
            String json = jsonNode.toString();
           /* System.out.println("Lay ds thong bao1: " + json);*/
          /*  Logger.info("lay ds thong bao input:" + response.getUri());*/
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ThongBaoResponse.class);
                /*System.out.println("Lay ds thong bao: " + json);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Object laychitietthongbao(String id, String messId) {
        WS.WSRequestHolder request;
        request = WS.url(listService.SERVICE_LAY_CHI_TIET_TIN);
        request.setQueryParameter("messId", messId);
        request.setQueryParameter("id", id);
      /*  request.setQueryParameter("session", session().get(QRVN_Service.KEY_SESSION_ID));*/
        request.setQueryParameter("VMApp", "4");
        request.setContentType(listService.CONTENT_TYPE);
        ThongBaoDetailResponse res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            System.out.println("json " + json);
            Logger.info("lay ctds thong bao input:" + response.getUri());
            if (Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ThongBaoDetailResponse.class);
                Logger.info("lay ctds thong bao input:" + response.getUri());
                System.out.println("Lay chi tiet thong bao: " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static Object layChiTietThongbao(String user, String companyCode, String maGiaoDich) {
        WS.WSRequestHolder request;
        // Lấy phần 1 của chi tiết thông báo
        request = WS.url("https://vimass.vn/vmbank/services/company/chiTietGiaoDich");
        request.setQueryParameter("user", user);
        request.setQueryParameter("companyCode", companyCode);
        request.setQueryParameter("maGiaoDich", maGiaoDich);
        /*System.out.println("123: " + user +" " + companyCode+ " "+ maGiaoDich);*/
        /*  request.setQueryParameter("session", session().get(QRVN_Service.KEY_SESSION_ID));*/
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ObjectResponse res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            Logger.info("lay ctds thong bao input:" + response.getUri());
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ObjectResponse.class);
                Logger.info(" layChiTietThongbao:" + response.getUri());
                Logger.info(" layChiTietThongbao22:" + res.getResult());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static Object laychitietgiaodich(String user,String companyCode,String maGiaoDich)
    {
        WS.WSRequestHolder request;
        request = WS.url(DN_LIST_SERVICE.SERVICE_LAY_CHITIET_GIAODICH);
        request.setQueryParameter("user",user);
        request.setQueryParameter("companyCode",companyCode);
        request.setQueryParameter("maGiaoDich",maGiaoDich);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ResponseMessage res = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            Logger.info("lay chi tiet dg: " + response.getUri());

            String json = jsonNode.toString();
            if(controllers.Utility.isValidJSON(json)){
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("Lay chi tiet gd: " + json);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static Object xoathongbao(String username, ArrayList<String> id) {

        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", username);
        data.put("id", id);
        data.put("appId", 5);
        data.put("VimassMH", 1); // pass value
        Logger.info("xoa thong bao post:" + new Gson().toJson(data));
        request = WS.url(controllers.listService.SERVICE_XOA_THONG_BAO);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;
        System.out.println("xoa thong bao response: " + response.getBody());
        // decrpit data
        String json = response.getBody();
        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
        }
        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object tracuu_hoadon(int kieuThanhToan, String maHoaDon, String user, String secsion) {
        /*THANH_TOAN_TRA_CUU_DIEN_LUC_HN = 0;
        THANH_TOAN_TRA_CUU_DIEN_LUC_MIEN_TRUNG = 1;
        THANH_TOAN_TRA_CUU_DIEN_LUC_HCM = 2;
        THANH_TOAN_TRA_CUU_DIEN_LUC_TINH_KHAC = 3;*/
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("kieuThanhToan", kieuThanhToan);
        data.put("maKhachHang", maHoaDon);
        data.put("user", user);
        data.put("secsion", secsion);
        data.put("VMApp", 4);
        data.put("appId", 5);
        data.put("VimassMH", 1); // pass value
        Logger.info("tra cuu post:" + new Gson().toJson(data));
        request = WS.url(controllers.listService.SERVICE_TRACUU_HOADON);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;
        System.out.println("tra cuu response: " + response.getBody());
        // decrpit data
        String json = response.getBody();
        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);
        }
        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            Logger.info("json tra cuu: " + json);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object duyetGiaoDich(String user, String token, String otpConfirm, int typeAuthenticate, int funcId, String companyCode, List<String> dsGiaoDich, String comment) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("token", token);
        data.put("otpConfirm", otpConfirm);
        data.put("appId", 1);
        data.put("typeAuthenticate", typeAuthenticate);
        data.put("funcId", funcId);
        data.put("companyCode", companyCode);
        data.put("dsGiaoDich", dsGiaoDich);
        data.put("lyDoDuyetThatBai", comment);
        data.put("VMApp", 4);
        data.put("appId", 5);
        Logger.info("duyet request:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url(DN_LIST_SERVICE.SERVICE_DUYET_GIAODICH);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();

        System.out.println("duyet response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }

        if (res != null) {
            return res;
        } else {
            return null;
        }
    }


    public static Object huyGiaoDich(String user, String token, String otpConfirm, int typeAuthenticate, int funcId, String companyCode, List<String> dsGiaoDich, String comment) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("user", user);
        data.put("token", token);
        data.put("otpConfirm", otpConfirm);
        data.put("appId", 1);
        data.put("typeAuthenticate", typeAuthenticate);
        data.put("funcId", funcId);
        data.put("VMApp", 4);
        data.put("appId", 5);
        data.put("companyCode", companyCode);
        data.put("dsGiaoDich", dsGiaoDich);
        data.put("lyDoDuyetThatBai", comment);
        Logger.info("huy request:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url(DN_LIST_SERVICE.SERVICE_HUY_GIAODICH);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();

        System.out.println("huy response: " + response.getBody());
        if (controllers.Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object phanloaithongbao() {
        WS.WSRequestHolder request;
        request = WS.url(controllers.listService.SERVICE_PHANLOAI_THONGBAO);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            /*Logger.info("ds anh request:" + response.getUri());*/
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("ds anh response: " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static Object laydanhsach_taikhoan_thuongdung(String phone, int type) {
        String sKetQua = "";
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phoneOwner", phone);
        data.put("type", type);
        data.put("appId", 5);
        data.put("VimassMH", 1); // pass value
        Logger.info("tk thuongdung post:" + data.toString());
//        request.setTimeout(100000);
        request = WS.url(controllers.listService.SERVICE_TK_THUONGDUNG);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix, VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, sw.toString()));

        long timeout = 100000;
        response = request.post(dataSend).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (json != null && !json.contains("{") && json.contains("zzz_")) {
            json = VimassSercurityFunc.chuanHoaDuLieuNhan(json);
            json = VimassSercurityFunc.decryptTripleDES(VimassSercurityFunc.Key1, VimassSercurityFunc.Key2, VimassSercurityFunc.Key3, json);

        }
        if (controllers.Utility.isValidJSON(json)) {

            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("tk thuongdung response: " + json);

        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }


    // thong ke doanh so cua chi nhanh
    public static Object thongkedoanhsocuachinhanh(long timeFrom, long timeTo, String userSearch, String userLogin, String secssionLogin, String checkSum, long timeRequest) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("funcId", 17);
        data.put("timeFrom", timeFrom);
        data.put("timeTo", timeTo);
        data.put("userSearch", userSearch);
        data.put("userLogin", userLogin);
        data.put("secssionLogin", secssionLogin);
        data.put("checkSum", checkSum);
        data.put("timeRequest", timeRequest);
        Logger.info("thongkedoanhsocuachinhanh post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        request = WS.url("https://vimass.vn/VimassQR/services/VMQR/requestCommand");
        request.setContentType(listServices.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = request.post(sw.toString()).get();
        System.out.println("thongkedoanhsocuachinhanh response: " + response.getBody());
        if (Utility.isValidJSON(response.getBody())) {
            String json = response.getBody();
            res = new Gson().fromJson(json, ResponseMessage.class);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }




    public static Object Laylistdoanhnghiepcha(){
        WS.WSRequestHolder request;
        request = WS.url("http://103.21.150.9:8080/vimass-tmdt/services/doanh-nghiep-cha") ;
        request.setContentType(controllers.listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);


            String res = response.getBody();
            if (res != null && res != "") {
                ResponseMessage rm = new Gson().fromJson(res, ResponseMessage.class);

                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object laydoanhnghieptheomst(String mst) {

        WS.WSRequestHolder request;
        request = WS.url("http://103.21.150.9:8080/vimass-tmdt/services/doanh-nghieps");
        request.setQueryParameter("mst", mst);

        request.setContentType(controllers.listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("DN request1:" + response.getBody());
            String res = response.getBody();
            if (res != null && res != "") {
                ResponseMessage rm = new Gson().fromJson(res, ResponseMessage.class);
                System.out.println("===========" + rm);
                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object laychitietdoanhnghieptheomst(String mst) {

        WS.WSRequestHolder request;
        request = WS.url("http://103.21.150.9:8080/vimass-tmdt/services/doanh-nghiep");
        request.setQueryParameter("mst", mst);

        request.setContentType(controllers.listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("DN request2:" + response.getBody());
            String res = response.getBody();
            if (res != null && res != "") {
                ResponseMessage rm = new Gson().fromJson(res, ResponseMessage.class);

                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object laydanhsachsanpham(String mst) {

        WS.WSRequestHolder request;
        request = WS.url("http://103.21.150.9:8080/vimass-tmdt/services/menu");
        request.setQueryParameter("mst", mst);

        request.setContentType(controllers.listService.CONTENT_TYPE);
        WS.Response response = null;
        try {
            long timeout = 100000;
            response = request.get().get(timeout);

            String res = response.getBody();
            if (res != null && res != "") {
                ResponseMessage rm = new Gson().fromJson(res, ResponseMessage.class);

                return rm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object laychitietbaiviet(String id, String langId)
    {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", id);
        data.put("langId",langId);

        Logger.info("lay chi tiet post:" + new Gson().toJson(data));
        ResponseMessage res = null;
        //Utility.clearInputData(data);
        request = WS.url(controllers.listService.service_laychitietbaiviet);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = request.post(data.toString()).get();
        //System.out.println("lay chi tiet response: " + response.getBody());
        if(controllers.Utility.isValidJSON(response.getBody())){
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
    public static ResponseMessage layThongTinDoanhNghiepInDB(String maSoThue)
    {
        WS.WSRequestHolder request;
        request = WS.url(controllers.listService.SERVICES_TRACUU_THONGTIN_DN_IN_DB);
        request.setQueryParameter("maSoThue",maSoThue);

        request.setContentType(controllers.listService.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            /*response = request.get().get();*/
            Logger.info("lay thong tin: " + response.getUri());
            Logger.info("lay thong tin: " + response.getBody());
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            if(controllers.Utility.isValidJSON(json)){
                res = new Gson().fromJson(json, ResponseMessage.class);
                if(res!=null&&res.getMsgCode()==1)
                {
                    Logger.info("lay thanh cong");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    public static AccViEntity layThongTinCNVi(String idVi) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/getAvt");
        request.setQueryParameter("pass", "8249539tgsdlka");
        request.setQueryParameter("param", idVi);

        request.setContentType(listServices.CONTENT_TYPE);
        WS.Response response = null;
        try {

            long timeout = 100000;
            response = request.get().get(timeout);
            Logger.info("layThongTinAvatarVi request:" + response.getUri());
            System.out.println("layThongTinAvatarVi response: " + response.getBody());
            String res = response.getBody();
            if (res != null && res != "") {
                AccViEntity acc = new Gson().fromJson(res, AccViEntity.class);
                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // dich vu luu ho kieu dang nhap
    public static Object Type_login(String param1, String param2, String param3) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/web1");
        request.setQueryParameter("param1", param1);
        request.setQueryParameter("param2", param2);
        request.setQueryParameter("param3",param3);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
          /*  Logger.info("lay ds thong bao input:" + response.getUri());*/
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ResponseMessage.class);
                /*System.out.println("json: " + json);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    // dich vu tra ve kieu dang nhap
    public static Object Login_results(String param1, String param2) {
        WS.WSRequestHolder request;
        request = WS.url("http://118.69.84.243:8080/vmNoiBo/services/account/web2");
        request.setQueryParameter("param1", param1);
        request.setQueryParameter("param2", param2);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            response = request.get().get();
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
          /*  Logger.info("lay ds thong bao input:" + response.getUri());*/
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("json kieu dang nhap tra ve: " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }
//tu 29092022
    public static Object list_trans_by_money(long fromDate, String amount, int limit, int offset, long toDate, int typeTranfer, String session, int VMApp, double amountFrom, double amountTo, String user) {
        WS.WSRequestHolder request;
        request = WS.url(listServices.SERVICE_LIST_TRANS_BY_MONEY);
        request.setQueryParameter("fromDate", String.valueOf(fromDate));
        request.setQueryParameter("toDate", String.valueOf(toDate));
        request.setQueryParameter("typeTranfer", String.valueOf(typeTranfer));
        request.setQueryParameter("session", session);
        request.setQueryParameter("VMApp", String.valueOf(VMApp));
        request.setQueryParameter("user", user);
        String checkSum = controllers.Utility.getMD5("8725834534853" + user + session);
        request.setQueryParameter("checkSum", checkSum);
        request.setQueryParameter("amount", amount);
        request.setQueryParameter("amountFrom", String.valueOf(amountFrom));
        request.setQueryParameter("amountTo", String.valueOf(amountTo));
        request.setQueryParameter("limit", String.valueOf(limit));
        request.setQueryParameter("offset", String.valueOf(offset));
        request.setQueryParameter("ver", String.valueOf(1));
        request.setContentType(listServices.CONTENT_TYPE);
        ResponseMessage res = null;
        WS.Response response = null;
        Gson gson = new Gson();
        try {
            long timeout = 50000;
            /*request.setTimeout(timeout);*/
            response = request.get().get(timeout);
            System.out.println("Lay ds gd theo tien url: " + response.getUri());
            JsonNode jsonNode = response.asJson();
            String json = jsonNode.toString();
            if (controllers.Utility.isValidJSON(json)) {
                res = new Gson().fromJson(json, ResponseMessage.class);
                System.out.println("Lay ds gd theo tien: " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Object Datxengay(int funcId, String ip, int thietBiGoi, int ver, long timeRequest, String user, String latDon, String lngDon,  String diaChiMoTaDon, ArrayList<ObjectItem1DiemDen> dsDiemDen, String totalKm, String loaiDichVu,   String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("funcId",funcId);
        data.put("ip", ip);
        data.put("timeRequest", timeRequest);
        data.put("thietBiGoi", thietBiGoi);
        data.put("ver", ver);
        data.put("user", user);
        data.put("latDon", latDon);
        data.put("lngDon", lngDon);
        data.put("diaChiMoTaDon", diaChiMoTaDon);
        data.put("dsDiemDen", dsDiemDen);
       /* data.put("lngDen", dsDiemDen.lngDen);
        data.put("diaChiMoTaDen", dsDiemDen.diaChiMoTaDen);
        data.put("km", dsDiemDen.km);*/
        data.put("totalKm", totalKm);
        data.put("loaiDichVu", loaiDichVu);
        data.put("checkSum", checkSum);



        Logger.info("Datxengay post:" + new Gson().toJson(data));
        request = WS.url(listServices.TOA_DO_DONG);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("Datxengay response: " + json);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }




    public static Object dangkyxe(int funcId, String idViVimass, String token, long timeRequest, int loaiXe, String bangLaiXe, String hangXe, String model, String bienSoXe, int soChoNgoi, int namSanXuat, String linkAnhXe1, String linkAnhXe2, int hinhThucPhatToaDo, String idThietBiVietMap, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();

            data.put("funcId",funcId);
            data.put("idViVimass", idViVimass);
            data.put("timeRequest", timeRequest);
            data.put("token", token);
            data.put("loaiXe", loaiXe);
            data.put("bangLaiXe", bangLaiXe);
            data.put("hangXe", hangXe);
            data.put("model", model);
            data.put("bienSoXe", bienSoXe);
            data.put("soChoNgoi", soChoNgoi);
            data.put("namSanXuat", namSanXuat);
            data.put("linkAnhXe1", linkAnhXe1);
            data.put("linkAnhXe2", linkAnhXe2);
            data.put("hinhThucPhatToaDo", hinhThucPhatToaDo);
            data.put("idThietBiVietMap", idThietBiVietMap);
            data.put("checkSum", checkSum);



        Logger.info("dang ky xe post:" + new Gson().toJson(data));
        request = WS.url(listServices.TOA_DO_DONG);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("dang ky xe post response: " + json);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }
    public static Object checkCamera(String appKey, String appSecret) throws IOException{
        URL url = new URL("https://open.ezvizlife.com/api/lapp/token/get");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("appKey", appKey);
        params.put("appSecret", appSecret);


        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println("response : "+ response);

        return response;

    }


    public static Object DKLaiXe(String token, String user, long timeRequest, int idViVimass, String ten, int gioiTinh,String ngaySinh, String cccd, String diaChi, String sdtLienHe, String email, String linkAnhChanDung, String linkAnhCCCD, String checkSum) {
        StringWriter sw = new StringWriter();
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("funcId",-47850);
        data.put("id", "");
        data.put("user", user);
        data.put("token", token);
        data.put("timeRequest", timeRequest);
        data.put("idViVimass", idViVimass);
        data.put("ten", ten);
        data.put("gioiTinh", gioiTinh);
        data.put("ngaySinh",ngaySinh);
        data.put("cccd", cccd);
        data.put("diaChi", diaChi);
        data.put("sdtLienHe", sdtLienHe);
        data.put("email", email);
        data.put("linkAnhChanDung", linkAnhChanDung);
        data.put("linkAnhCCCD", linkAnhCCCD);
        data.put("checkSum", checkSum);

        Logger.info("dang ky lai xe post:" + new Gson().toJson(data));
        request = WS.url(listServices.TOA_DO_DONG);
        request.setContentType(controllers.listService.CONTENT_TYPE);
        try {
            mapper.writeValue(sw, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // encript data

        long timeout = 100000;
        response = request.post(sw.toString()).get(timeout);
        ResponseMessage res = null;
        // decrpit data
        String json = response.getBody();

        if (controllers.Utility.isValidJSON(json)) {
            res = new Gson().fromJson(json, ResponseMessage.class);
            System.out.println("dang lai ky xe post response: " + json);
        }
        if (res != null) {
            return res;
        } else {
            return null;
        }
    }

    public static Object laychitietdanhmuc(String id,String langId,int offset, int limit)
    {
        String key = id+langId+offset+limit;
        long timeSearch = new Date().getTime();
        itemCacheBaiViet itemSearch = managerCacheBaiViet.getCacheBaiViet(key);
        long timeCache = 1000l * 60 * 60 * 24;
        ResponseMessage res = null;
        if(itemSearch==null||itemSearch.time < timeSearch - timeCache)
        {
            StringWriter sw = new StringWriter();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id",id);
            data.put("langId",langId);
            data.put("offset",offset);
            data.put("limit",limit);
            Logger.info("lay chi tiet danh muc post:" + new Gson().toJson(data));
            controllers.Utility.clearInputData(data);
            request = WS.url(controllers.listService.service_laychitietdanhmuc);
            request.setContentType(controllers.listService.CONTENT_TYPE);
            try {
                mapper.writeValue(sw, data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            response = request.post(sw.toString()).get();
            System.out.println("lay chi tiet danh muc response: " + response.getBody());
            if(controllers.Utility.isValidJSON(response.getBody())){
                String json = response.getBody();
                managerCacheBaiViet.addCacheBaiViet(key,json);
                res = new Gson().fromJson(json, ResponseMessage.class);
            }
        }
        else
        {
            res = new Gson().fromJson(itemSearch.value, ResponseMessage.class);
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
}
