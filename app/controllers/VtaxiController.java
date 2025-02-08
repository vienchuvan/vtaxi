package controllers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import controllers.tools.ResponseMessage;
import controllers.tools.Utility;
import entity.AccViEntity;
import entity.createQR.AnhNenObject;
import entity.createQR.createQREntity;
import entity.createQR.createQRResponse;
import entity.saokeQR.ds;
import entity.thonhke.ObjectTk_doanhthu_CN;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class VtaxiController extends Controller {
    public static Result genCaptcha()
    {
        Random random = new Random();
        int text = random.nextInt(9999 - 0) + 9999;
        System.out.println("text: " + text);

        String capt_confirm = text + "ok2conde";
        return ok(text + "/" + Utility.getMD5(capt_confirm));
    }

        public static Result reCaptchaQR()
    {
        Random randomCaptcha = new Random();
        String text = String.valueOf(randomCaptcha.nextInt(9999 - 0) + 9999);
        System.out.println("text: " + text);
        String capt_confirm = text + "ok2conde";
        String captcha = controllers.Utility.getMD5(capt_confirm);
        return ok(captcha+":"+text);
    }
    public static Result getImageBackground()
    {
        System.out.println("abc"+request().getQueryString("idBackground"));
        int idDanhMuc = 0;
        if(request().getQueryString("idBackground")!=null&&request().getQueryString("idBackground")!="")
        {
            idDanhMuc = Integer.valueOf(request().getQueryString("idBackground"));
        }
        String hashtag = "";
        int limit = 20;
        int offset = 0;
        if(request().getQueryString("offset")!=null&&request().getQueryString("offset")!="")
        {
            offset = Integer.valueOf(request().getQueryString("offset"));

        }
        String html = "";
        ResponseMessage responseMessage = null;
        if(idDanhMuc==8)
        {
            responseMessage = (ResponseMessage) CallService.getImageBackGroundNewest(limit, offset);
        }
        else if(idDanhMuc==9)
        {
            responseMessage = (ResponseMessage) CallService.getImageBackGroundRandom(limit, offset);
        }
        else
        {
            responseMessage = (ResponseMessage) CallService.getImageBackGround(idDanhMuc, hashtag, limit, offset);
        }

        if(responseMessage!=null&&responseMessage.getMsgCode()==1)
        {
            Type collectionType = new TypeToken<List<AnhNenObject>>(){}.getType();
            List <AnhNenObject> list = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), collectionType);
            if(list!=null&&list.size()>0)
            {
                html = genHtmlAnhNen(list);
            }
        }
        return ok(html);
    }
    public static String genHtmlAnhNen(List <AnhNenObject> list)
    {
        String html = "";
        for(AnhNenObject item: list)
        {
            html +=
                    "   <div class=\"col-4 col-md-2 p-0\">\n" +
                            "       <a href=\"#\">" +
                            "         <img  class=\"apply-img img-thumbnail\"  data-for=\""+ item.idAnhNen +"\" src=\"https://vimass.vn/VimassMedia/services/VMBackground/getImageThumnail?id="+item.idAnhNen+"\">\n" +

                            "       </a>" +
                            "   </div>";
        }
        return html;
    }
//    tạo qr
    public static Result create_qr()
    {
        String keyMd5 = "kLyTwOIeXVCz88HYuP5R0viF2PvDMNFgGSYqvhzodiBOJxyalcuRnGCEJpspA9qjEOfm6";
        String keyVerify = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm0gFe5URPPZf6QbY/z3EAm/WPjUUPnAkEl7jWT4HF11pe17CoeLXefHUfBjzpY2CJUjs+CrCEDerK9hNR/euLj3Vl+luAEyflxIXl8lZPKYiPL1Fn3QWyqV8Dml36DGJLRLCxy5+onjfdcPEDpD0+lA7lNODQhGLuEvj72n1V0n8xdOmGKo8Qxpp8E++OB/ihxUo6Zjy69bvWdSytW7P2r2ESonvFOFimU4yGWG46ilLHjK+sVEZG9rzhgmPr3bH22Pz6a+h6nrXoXCqmZSxtyUqoG3tgSkEtxJghsOPeV2+bVExeBUr8JNDxi5twJ/5S9Um00A5IKB6/WHpWeh8ZQIDAQAB";
        String KEY_SIGN_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbSAV7lRE89l/pBtj/PcQCb9Y+NRQ+cCQSXuNZPgcXXWl7XsKh4td58dR8GPOljYIlSOz4KsIQN6sr2E1H964uPdWX6W4ATJ+XEheXyVk8piI8vUWfdBbKpXwOaXfoMYktEsLHLn6ieN91w8QOkPT6UDuU04NCEYu4S+PvafVXSfzF06YYqjxDGmnwT744H+KHFSjpmPLr1u9Z1LK1bs/avYRKie8U4WKZTjIZYbjqKUseMr6xURkb2vOGCY+vdsfbY/Ppr6HqetehcKqZlLG3JSqgbe2BKQS3EmCGw495Xb5tUTF4FSvwk0PGLm3An/lL1SbTQDkgoHr9YelZ6HxlAgMBAAECggEBAJZz2vQZxAEISneTLe9o3iDqUXAmp72zlA/Epo2SXvaRiKGDeBu4mcTKvnGbD3/Js18C4K+3Zh6mgZBS3NYtMjQVr32oK+/YTgzm97sW2QK9iZDGZs4PggHJA4FHSWnNjTcB5X1uHD651PHaw3zFkMdzs7cIlTYPoAvqZChVxuRSBlGyS5u7aypLNE5HIXRhvCLfA1NSkbP6s2KLj0NMERYXy5EG71fYC2/5pDeVn8CPNBhW/3P6MwQIBhA/QsYRDqv05DTtVrK3PTlvu4OSNvpwXin7w3g2zYKZpf3XEqx5btckhMn0Y+bzEWfbGnvV4u6OENpBinhPwXZNuBK2QYUCgYEAypcC6WO6Bi/pRKk9K2p2sNf86MNWLu6gReKWoXVcU/zjw2xo8S6Dun23sInRf5ciEtsGG0P+dDNOi0UVCOc2h2Yj9X/wzfqYq9Sk3QrzlCTw0pmZfvIS04CSU7l6eegX9pj/pGikO6a1XG7tHvCqboE3azfrMG3uCYeruOXCr88CgYEAxDgcE+OfqTzVHFqVYvRqYFKYEgTdIT60X2KIVWFTw+4aL+je9yfs7dmBnSCwJPr3jv28AnFkXvfMuOqEaatEzvpE3gc9eQd47ncTx1GwfFJ9AZnI08RtWgTKWWliGA6IX2O2LMv/GZ1cS7DRdHSLy5fNaI4q45/ZPWRY1kUfSYsCgYBqZaL21hRVuYXmNIfWrGRHfjfqOuShY1uzOExp4Ywq4+7urJn0yLwQDoSRZ+AqBUK06mirmysg9e0zQYXJljzSIXeTMK1UUF8sJ1KqcFz6megMgvTVcecnQygWZpwHODiVn0brnrVaSrSHJg7MqBKqVJy5Z7XSSnmwcZtjUO/NiwKBgFnhadsidUxMdUIbrDo7w06EBuyaFcDYvD7FH7YccwZY1t4DSb7lJv58l4amJomJ/EQfrjNgRZ3K3JgHtMfCkC5PLaSWpG4AIkD5grHWlYUdM+27x+ZZH84QS7BROYHThlxLjHNQrnXJQzHYuNkjWbXUv16ZYMQLwLMCeD8OQpqpAoGBAJzPGYV1qDPpS9YBgalMksyBz6snZvcY156PWEMDO7GxtYagngGimS/y+163d8tHGKf+2aLAJktxTO0qHwpBtuEGFOM4eCc7KfkjrVK8h9O6W4OkLxZ1epQ/2KrFKxGbnrSKYJQzwqivD1cizzaR+2rkAJAhqan2VPcze5Ul5NzH";

        DynamicForm form = Form.form().bindFromRequest();

        String idViVimass;
        if(session()!=null&&session().get(Utility.USER)!="")
        {
            idViVimass = session().get(Utility.USER);
        }
        else
        {
            return ok("Vui lòng đăng nhập để sử dụng chức năng này");
        }
        int typeAuthenticate = 0;
        String token = form.get("token");
        String session = session().get(Utility.SEASON);
        System.out.println("Log id vi: " + idViVimass);

        String mcId = "qrvnMC1_4917";
        int funcId = 7;
        int typeQR = 5;
        if(form.get("typeQR")!=null&&form.get("typeQR")!="")
        {
            typeQR = Integer.valueOf(form.get("typeQR"));
        }
        String nameQR = Utility.VMencodeBase64(form.get("nameQR"));
        int logoIndex = 0;
        if(form.get("logoIndex")!=null&&form.get("logoIndex")!="")
        {
            logoIndex = Integer.valueOf(form.get("logoIndex"));
        }
        String line1 = Utility.VMencodeBase64(form.get("line1"));
        String line2 = "";

        String line3 = Utility.VMencodeBase64(form.get("walletname"));
        String line4 = "";

        String bankCode = form.get("bankCode");
        String bankNumber = form.get("bankNumber");
        String bankName = Utility.VMencodeBase64(form.get("bankName"));
        String descQR = form.get("descQR");

        int providerCode = 0;
        if(form.get("providerCode")!=null&&form.get("providerCode")!="")
        {
            providerCode = Integer.valueOf(form.get("providerCode"));
        }

        String customerCode = "";

        if(providerCode==8||providerCode==9||providerCode==10||providerCode==11)
        {
            customerCode = form.get("customerCode_Napvi");
        }
        else
        {
            customerCode = form.get("customerCode_Napvi");
        }
        int amount = 0;
        if(form.get("amount")!=null&&form.get("amount")!="")
        {
            amount = Integer.valueOf(form.get("amount").replace(".",""));
            System.out.println("amount: " + amount);
        }
        String contentQRNonFinal = "";
        if(form.get("contentQRNonFinal")!=null&&form.get("contentQRNonFinal")!="")
        {
            contentQRNonFinal = Utility.VMencodeBase64(form.get("contentQRNonFinal"));
        }
        else
        {
            contentQRNonFinal = line1 + "   " + descQR + line2 + " " + line4;
        }

        String cellPhoneNumber = "";
        String email = "";
        String mstExport = "";
        String mst = "";
        String nameCompany = "";
        String addCompany = "";

        String descLink = form.get("descLink");
      /*  String descLink = "";

        if( form.get("descLink")!=null&& form.get("descLink")!="")
        {
            String uploadData = "";
            if(form.get("descLink").contains(","))
            {
                uploadData = form.get("descLink").split(",")[1];
                ResponseMessage responseMessage_descLink = (ResponseMessage) CallService.taoAnh(uploadData);
                if(responseMessage_descLink!=null&&responseMessage_descLink.getMsgCode()==1)
                {
                    descLink = responseMessage_descLink.getResult().toString();
                    System.out.println("input image descLink: " + descLink);
                }
            }
            else
            {
                System.out.println("Khong co gia tri!");
            }
        }*/


        String base64ImageLogoCustomer = form.get("base64ImageLogoCustomer");
        String frameType = form.get("frameType");

        double lat = 0;
        if(form.get("Latitude")!=null&&form.get("Latitude")!=""){
            lat = Double.valueOf(form.get("Latitude"));
        }

        double lng = 0;
        if(form.get("Longitude")!=null&&form.get("Longitude")!=""){
            lng = Double.valueOf(form.get("Longitude"));
        }
        String StoreAdd = form.get("StoreAdd");
        String catId =""; // co the chọn nhiều catId, phân cách nhau bởi dấu ;
        int visiable = 1; //0 la an, 1 la hien thi
        String viNhanThongBao;


        String image1 = form.get("avtarLink");
       /* String image1 = "";


        if( form.get("avtarLink")!=null&& form.get("avtarLink")!="")
        {
            String uploadData = "";
            if(form.get("avtarLink").contains(","))
            {
                uploadData = form.get("avtarLink").split(",")[1];
                ResponseMessage responseMessage_avatar = (ResponseMessage) CallService.taoAnh(uploadData);
                if(responseMessage_avatar!=null&&responseMessage_avatar.getMsgCode()==1)
                {
                    image1 = responseMessage_avatar.getResult().toString();
                    System.out.println("input image avartar: " + image1);
                    System.out.println("anh avatar!" + image1);
                }
            }
            else
            {
                System.out.println("Khong co gia tri!");
            }
        }*/



        String sdt = form.get("sdt");
        String emailLienHe = form.get("emailLienHe");
        String bsx = form.get("bsx");

        String linkWeb = form.get("linkWeb ");
        String linkFB = form.get("linkFB ");
        String linkInstar = form.get("linkInstar");
        String linkVideo = form.get("linkVideo");
        String linkImageThumbnail = "";

        String hintLinkWeb = form.get("hintLinkWeb");
        String hintLinkFB = "";
        String hintLinkInstar ="";
        String hintLinkVideo = "";


        String checkSum = Utility.getMD5(keyMd5 + mcId
                + idViVimass
                + funcId
                + typeQR
                + nameQR
                + logoIndex
                + line1
                + line2
                + line4
                + line3
                + bankCode
                + bankNumber
                + bankName
                + providerCode
                + customerCode
                + amount
                + cellPhoneNumber
                + email
                + mstExport
                + mst
                + nameCompany
                + addCompany);

        String signData = Utility.signData(KEY_SIGN_RSA, checkSum);

        createQREntity entity = new createQREntity();
        entity.addCompany = addCompany;
        entity.amount = amount;
        entity.bankCode = bankCode;
        entity.bankName = bankName;
        entity.bankNumber = bankNumber;
        entity.cellPhoneNumber = cellPhoneNumber;
        entity.checkSum = checkSum;
        entity.contentQRNonFinal = contentQRNonFinal;
        entity.customerCode = customerCode;
        entity.descLink = descLink;
        entity.descQR = descQR;
        entity.email = email;
        entity.funcId = funcId;
        entity.idViVimass = idViVimass;
        entity.line1 = line1;
        entity.line2 = line2;
        entity.line3 = line3;
        entity.line4 = line4;
        entity.logoIndex = logoIndex;
        entity.mcId = mcId;
        entity.mst = mst;
        entity.mstExport = mstExport;
        entity.nameCompany = nameCompany;
        entity.nameQR = nameQR;
        entity.providerCode = providerCode;
        entity.session = session;
        entity.signData = signData;
        entity.token = token;
        entity.typeAuthenticate = typeAuthenticate;
        entity.typeQR = typeQR;

        entity.base64ImageLogoCustomer = base64ImageLogoCustomer;
        entity.frameType = frameType;

        entity.lat = lat;
        entity.lng = lng;
        entity.catId = catId;

        entity.StoreAdd = StoreAdd;
        entity.visiable = visiable;

        entity.image1 = image1;
        entity.sdt = sdt;
        entity.emailLienHe = emailLienHe;
        entity.bsx = bsx;


        entity.linkWeb = linkWeb;
        entity.linkFB = linkFB;
        entity.linkInstar = linkInstar;
        entity.linkVideo = linkVideo;
        entity.linkImageThumbnail= linkImageThumbnail;

        entity.hintLinkWeb = hintLinkWeb;
        entity.hintLinkFB = hintLinkFB;
        entity.hintLinkInstar = hintLinkInstar;
        entity.hintLinkVideo = hintLinkVideo;


        Logger.info("data tạo: " + new Gson().toJson(entity));
        String html = "";
        ResponseMessage responseMessage = (ResponseMessage) CallService.createQR(entity);
        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1)
            {
                createQRResponse response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),createQRResponse.class);
                html = "OK__"+ "https://vimass.vn/VimassQR/services/VMQR/getImage?id="+response.idQR;
            }
            else
            {
                html = responseMessage.getMsgContent();
            }
        }
        return ok(html);
    }
//    cap nhat qr
    public static Result edit_qr()
    {
        String keyMD5 = "kLyTwOIeXVCz88HYuP5R0viF2PvDMNFgGSYqvhzodiBOJxyalcuRnGCEJpspA9qjEOfm6";
        String keyVerify = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm0gFe5URPPZf6QbY/z3EAm/WPjUUPnAkEl7jWT4HF11pe17CoeLXefHUfBjzpY2CJUjs+CrCEDerK9hNR/euLj3Vl+luAEyflxIXl8lZPKYiPL1Fn3QWyqV8Dml36DGJLRLCxy5+onjfdcPEDpD0+lA7lNODQhGLuEvj72n1V0n8xdOmGKo8Qxpp8E++OB/ihxUo6Zjy69bvWdSytW7P2r2ESonvFOFimU4yGWG46ilLHjK+sVEZG9rzhgmPr3bH22Pz6a+h6nrXoXCqmZSxtyUqoG3tgSkEtxJghsOPeV2+bVExeBUr8JNDxi5twJ/5S9Um00A5IKB6/WHpWeh8ZQIDAQAB";
        String KEY_SIGN_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbSAV7lRE89l/pBtj/PcQCb9Y+NRQ+cCQSXuNZPgcXXWl7XsKh4td58dR8GPOljYIlSOz4KsIQN6sr2E1H964uPdWX6W4ATJ+XEheXyVk8piI8vUWfdBbKpXwOaXfoMYktEsLHLn6ieN91w8QOkPT6UDuU04NCEYu4S+PvafVXSfzF06YYqjxDGmnwT744H+KHFSjpmPLr1u9Z1LK1bs/avYRKie8U4WKZTjIZYbjqKUseMr6xURkb2vOGCY+vdsfbY/Ppr6HqetehcKqZlLG3JSqgbe2BKQS3EmCGw495Xb5tUTF4FSvwk0PGLm3An/lL1SbTQDkgoHr9YelZ6HxlAgMBAAECggEBAJZz2vQZxAEISneTLe9o3iDqUXAmp72zlA/Epo2SXvaRiKGDeBu4mcTKvnGbD3/Js18C4K+3Zh6mgZBS3NYtMjQVr32oK+/YTgzm97sW2QK9iZDGZs4PggHJA4FHSWnNjTcB5X1uHD651PHaw3zFkMdzs7cIlTYPoAvqZChVxuRSBlGyS5u7aypLNE5HIXRhvCLfA1NSkbP6s2KLj0NMERYXy5EG71fYC2/5pDeVn8CPNBhW/3P6MwQIBhA/QsYRDqv05DTtVrK3PTlvu4OSNvpwXin7w3g2zYKZpf3XEqx5btckhMn0Y+bzEWfbGnvV4u6OENpBinhPwXZNuBK2QYUCgYEAypcC6WO6Bi/pRKk9K2p2sNf86MNWLu6gReKWoXVcU/zjw2xo8S6Dun23sInRf5ciEtsGG0P+dDNOi0UVCOc2h2Yj9X/wzfqYq9Sk3QrzlCTw0pmZfvIS04CSU7l6eegX9pj/pGikO6a1XG7tHvCqboE3azfrMG3uCYeruOXCr88CgYEAxDgcE+OfqTzVHFqVYvRqYFKYEgTdIT60X2KIVWFTw+4aL+je9yfs7dmBnSCwJPr3jv28AnFkXvfMuOqEaatEzvpE3gc9eQd47ncTx1GwfFJ9AZnI08RtWgTKWWliGA6IX2O2LMv/GZ1cS7DRdHSLy5fNaI4q45/ZPWRY1kUfSYsCgYBqZaL21hRVuYXmNIfWrGRHfjfqOuShY1uzOExp4Ywq4+7urJn0yLwQDoSRZ+AqBUK06mirmysg9e0zQYXJljzSIXeTMK1UUF8sJ1KqcFz6megMgvTVcecnQygWZpwHODiVn0brnrVaSrSHJg7MqBKqVJy5Z7XSSnmwcZtjUO/NiwKBgFnhadsidUxMdUIbrDo7w06EBuyaFcDYvD7FH7YccwZY1t4DSb7lJv58l4amJomJ/EQfrjNgRZ3K3JgHtMfCkC5PLaSWpG4AIkD5grHWlYUdM+27x+ZZH84QS7BROYHThlxLjHNQrnXJQzHYuNkjWbXUv16ZYMQLwLMCeD8OQpqpAoGBAJzPGYV1qDPpS9YBgalMksyBz6snZvcY156PWEMDO7GxtYagngGimS/y+163d8tHGKf+2aLAJktxTO0qHwpBtuEGFOM4eCc7KfkjrVK8h9O6W4OkLxZ1epQ/2KrFKxGbnrSKYJQzwqivD1cizzaR+2rkAJAhqan2VPcze5Ul5NzH";

        DynamicForm form = Form.form().bindFromRequest();

        String maGiaoDich = form.get("maGiaoDich");
        String idViVimass;
        if(session()!=null&&session().get(Utility.USER)!="")
        {
            idViVimass = session().get(Utility.USER);
        }
        else
        {
            return ok("Vui lòng đăng nhập để sử dụng chức năng này");
        }
        int typeAuthenticate = 0;
        String token = form.get("token-edit");
        String session = session().get(Utility.SEASON);
        System.out.println("Log id vi: " + idViVimass);

        String mcId = "qrvnMC1_4917";
        int funcId = 9;
        int typeQR = 5;
        if(form.get("typeQR-edit")!=null&&form.get("typeQR-edit")!="")
        {
            typeQR = Integer.valueOf(form.get("typeQR-edit"));
        }
        String nameQR = Utility.VMencodeBase64(form.get("nameQR-edit"));
        int logoIndex = 0;
        if(form.get("logoIndex-edit")!=null&&form.get("logoIndex-edit")!="")
        {
            logoIndex = Integer.valueOf(form.get("logoIndex-edit"));
        }
        String line1 = Utility.VMencodeBase64(form.get("line1-edit"));
        String line2 = "";

        String line3 = Utility.VMencodeBase64(form.get("Walletname-edit"));
        String line4 = "";

        String bankCode = form.get("bankCode-edit");
        String bankNumber = form.get("bankNumber-edit");
        String bankName = Utility.VMencodeBase64(form.get("bankName-edit"));
        String descQR = form.get("descQR-edit");

        int providerCode = 0;
        if(form.get("providerCode-edit")!=null&&form.get("providerCode-edit")!="")
        {
            providerCode = Integer.valueOf(form.get("providerCode-edit"));
        }

        String customerCode = "";

        if(providerCode==8||providerCode==9||providerCode==10||providerCode==11)
        {
            customerCode = form.get("customerCode_Napvi-edit");
        }
        else
        {
            customerCode = form.get("customerCode_Napvi-edit");
        }
        int amount = 0;
        if(form.get("amount-edit")!=null&&form.get("amount-edit")!="")
        {
            amount = Integer.valueOf(form.get("amount-edit").replace(".",""));
        }
        String contentQRNonFinal = "";
        if(form.get("contentQRNonFinal-edit")!=null&&form.get("contentQRNonFinal-edit")!="")
        {
            contentQRNonFinal = Utility.VMencodeBase64(form.get("contentQRNonFinal-edit"));
        }
        else
        {
            contentQRNonFinal = line1 + "   " + descQR + line2 + " " + line4;
        }

        String cellPhoneNumber = "";
        String email = "";
        String mstExport = "";
        String mst = "";
        String nameCompany = "";
        String addCompany = "";

        String descLink = form.get("descLink-edit");
      /*  String descLink = "";
        if( form.get("descLink-edit")!=null&& form.get("descLink-edit")!="")
        {
            String uploadData = "";
            if(form.get("descLink-edit").contains(","))
            {
                uploadData = form.get("descLink-edit").split(",")[1];
                ResponseMessage responseMessage_descLink = (ResponseMessage) CallService.taoAnh(uploadData);
                if(responseMessage_descLink!=null&&responseMessage_descLink.getMsgCode()==1)
                {
                    descLink = responseMessage_descLink.getResult().toString();
                    System.out.println("input image descLink-edit: " + descLink);
                    System.out.println("anh descLink-edit!" + descLink);
                }
            }
            else
            {
                System.out.println("Khong co gia tri!");
            }
        }
*/

        String base64ImageLogoCustomer = form.get("base64ImageLogoCustomer-edit");
        String frameType = form.get("frameType-edit");

        double lat = 0;
        if(form.get("Latitude")!=null&&form.get("Latitude")!=""){
            lat = Double.valueOf(form.get("Latitude"));
        }
        double lng = 0;
        if(form.get("Latitude")!=null&&form.get("Longitude")!=""){
            lng = Double.valueOf(form.get("Longitude"));
        }
        String StoreAdd = form.get("StoreAdd-edit");
        String catId =""; // co the chọn nhiều catId, phân cách nhau bởi dấu ;
        int visiable = 1; //0 la an, 1 la hien thi
        String viNhanThongBao;

        String image1 = form.get("avtarLink-edit");
      /*  String image1 = "";

        if( form.get("image1_edit")!=null&& form.get("image1_edit")!="")
        {
            String uploadData = "";
            if(form.get("image1_edit").contains(","))
            {
                uploadData = form.get("image1_edit").split(",")[1];
                ResponseMessage responseMessage_avatar = (ResponseMessage) CallService.taoAnh(uploadData);
                if(responseMessage_avatar!=null&&responseMessage_avatar.getMsgCode()==1)
                {
                    descLink = responseMessage_avatar.getResult().toString();
                    System.out.println("input image avatar: " + descLink);
                    System.out.println("anh avatar!" + descLink);
                }
            }
            else
            {
                System.out.println("Khong co gia tri!");
            }
        }*/

        String sdt = form.get("sdt_edit");
        String emailLienHe = form.get("emailLienHe_edit");
        String bsx = form.get("bsx_edit");

        String linkWeb = form.get("linkWeb_edit");
        String linkFB = form.get("linkFB_edit ");
        String linkInstar = form.get("linkInstar_edit");
        String linkVideo = form.get("linkVideo_edit");
        String linkImageThumbnail = "";

        String hintLinkWeb = form.get("hintLinkWeb_edit");
        String hintLinkFB = "";
        String hintLinkInstar ="";
        String hintLinkVideo = "";

        String checkSum = Utility.getMD5(keyMD5 + maGiaoDich
                + mcId
                + idViVimass
                + funcId
                + typeQR
                + nameQR
                + logoIndex
                + line1
                + line2
                + line4
                + line3
                + bankCode
                + bankNumber
                + bankName
                + providerCode
                + customerCode
                + amount
                + cellPhoneNumber
                + email
                + mstExport
                + mst
                + nameCompany
                + addCompany);

        String signData = Utility.signData(KEY_SIGN_RSA, checkSum);

        createQREntity entity = new createQREntity();
        entity.maGiaoDich = maGiaoDich;
        entity.addCompany = addCompany;
        entity.amount = amount;
        entity.bankCode = bankCode;
        entity.bankName = bankName;
        entity.bankNumber = bankNumber;
        entity.cellPhoneNumber = cellPhoneNumber;
        entity.checkSum = checkSum;
        entity.contentQRNonFinal = contentQRNonFinal;
        entity.customerCode = customerCode;
        entity.descLink = descLink;
        entity.descQR = descQR;
        entity.email = email;
        entity.funcId = funcId;
        entity.idViVimass = idViVimass;
        entity.line1 = line1;
        entity.line2 = line2;
        entity.line3 = line3;
        entity.line4 = line4;
        entity.logoIndex = logoIndex;
        entity.mcId = mcId;
        entity.mst = mst;
        entity.mstExport = mstExport;
        entity.nameCompany = nameCompany;
        entity.nameQR = nameQR;
        entity.providerCode = providerCode;
        entity.session = session;
        entity.signData = signData;
        entity.token = token;
        entity.typeAuthenticate = typeAuthenticate;
        entity.typeQR = typeQR;

        entity.base64ImageLogoCustomer = base64ImageLogoCustomer;
        entity.frameType = frameType;

        entity.lat = lat;
        entity.lng = lng;
        entity.catId = catId;

        entity.StoreAdd = StoreAdd;
        entity.visiable = visiable;

        entity.image1 = image1;
        entity.sdt = sdt;
        entity.emailLienHe = emailLienHe;
        entity.bsx = bsx;


        entity.linkWeb = linkWeb;
        entity.linkFB = linkFB;
        entity.linkInstar = linkInstar;
        entity.linkVideo = linkVideo;
        entity.linkImageThumbnail= linkImageThumbnail;

        entity.hintLinkWeb = hintLinkWeb;
        entity.hintLinkFB = hintLinkFB;
        entity.hintLinkInstar = hintLinkInstar;
        entity.hintLinkVideo = hintLinkVideo;

        Logger.info("data edit: " + new Gson().toJson(entity));
        String html = "";
        ResponseMessage responseMessage = (ResponseMessage) CallService.editQR(entity);
        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1)
            {
                createQRResponse response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),createQRResponse.class);
                html = "OK__"+ "https://vimass.vn/VimassQR/services/VMQR/getImage?id="+response.idQR;
            }
            else
            {
                html = responseMessage.getMsgContent();
            }
        }
        return ok(html);
    }
    // get qr_idvi
    public  static Result lookup_qr_idvi(){
        String keyMd5 = "kLyTwOIeXVCz88HYuP5R0viF2PvDMNFgGSYqvhzodiBOJxyalcuRnGCEJpspA9qjEOfm6";
        String KEY_SIGN_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbSAV7lRE89l/pBtj/PcQCb9Y+NRQ+cCQSXuNZPgcXXWl7XsKh4td58dR8GPOljYIlSOz4KsIQN6sr2E1H964uPdWX6W4ATJ+XEheXyVk8piI8vUWfdBbKpXwOaXfoMYktEsLHLn6ieN91w8QOkPT6UDuU04NCEYu4S+PvafVXSfzF06YYqjxDGmnwT744H+KHFSjpmPLr1u9Z1LK1bs/avYRKie8U4WKZTjIZYbjqKUseMr6xURkb2vOGCY+vdsfbY/Ppr6HqetehcKqZlLG3JSqgbe2BKQS3EmCGw495Xb5tUTF4FSvwk0PGLm3An/lL1SbTQDkgoHr9YelZ6HxlAgMBAAECggEBAJZz2vQZxAEISneTLe9o3iDqUXAmp72zlA/Epo2SXvaRiKGDeBu4mcTKvnGbD3/Js18C4K+3Zh6mgZBS3NYtMjQVr32oK+/YTgzm97sW2QK9iZDGZs4PggHJA4FHSWnNjTcB5X1uHD651PHaw3zFkMdzs7cIlTYPoAvqZChVxuRSBlGyS5u7aypLNE5HIXRhvCLfA1NSkbP6s2KLj0NMERYXy5EG71fYC2/5pDeVn8CPNBhW/3P6MwQIBhA/QsYRDqv05DTtVrK3PTlvu4OSNvpwXin7w3g2zYKZpf3XEqx5btckhMn0Y+bzEWfbGnvV4u6OENpBinhPwXZNuBK2QYUCgYEAypcC6WO6Bi/pRKk9K2p2sNf86MNWLu6gReKWoXVcU/zjw2xo8S6Dun23sInRf5ciEtsGG0P+dDNOi0UVCOc2h2Yj9X/wzfqYq9Sk3QrzlCTw0pmZfvIS04CSU7l6eegX9pj/pGikO6a1XG7tHvCqboE3azfrMG3uCYeruOXCr88CgYEAxDgcE+OfqTzVHFqVYvRqYFKYEgTdIT60X2KIVWFTw+4aL+je9yfs7dmBnSCwJPr3jv28AnFkXvfMuOqEaatEzvpE3gc9eQd47ncTx1GwfFJ9AZnI08RtWgTKWWliGA6IX2O2LMv/GZ1cS7DRdHSLy5fNaI4q45/ZPWRY1kUfSYsCgYBqZaL21hRVuYXmNIfWrGRHfjfqOuShY1uzOExp4Ywq4+7urJn0yLwQDoSRZ+AqBUK06mirmysg9e0zQYXJljzSIXeTMK1UUF8sJ1KqcFz6megMgvTVcecnQygWZpwHODiVn0brnrVaSrSHJg7MqBKqVJy5Z7XSSnmwcZtjUO/NiwKBgFnhadsidUxMdUIbrDo7w06EBuyaFcDYvD7FH7YccwZY1t4DSb7lJv58l4amJomJ/EQfrjNgRZ3K3JgHtMfCkC5PLaSWpG4AIkD5grHWlYUdM+27x+ZZH84QS7BROYHThlxLjHNQrnXJQzHYuNkjWbXUv16ZYMQLwLMCeD8OQpqpAoGBAJzPGYV1qDPpS9YBgalMksyBz6snZvcY156PWEMDO7GxtYagngGimS/y+163d8tHGKf+2aLAJktxTO0qHwpBtuEGFOM4eCc7KfkjrVK8h9O6W4OkLxZ1epQ/2KrFKxGbnrSKYJQzwqivD1cizzaR+2rkAJAhqan2VPcze5Ul5NzH";
        long timeRequest = new Date().getTime();
        String mcId="qrvnMC1_4917";
        int funcId = 8;
        String idViVimass;
        if(session()!=null&&session().get(Utility.USER)!="")
        {
            idViVimass = session().get(Utility.USER);
        }
        else
        {
            return ok("Vui lòng đăng nhập để sử dụng chức năng này");
        }
        int offset = 0;
        if(request().getQueryString("offset") != null  && request().getQueryString("offset") != "") {
            offset = Integer.valueOf(request().getQueryString("offset"));
        }
//        int limit = getLimit();
        int limit = 50;
        String html = "";
        String html_phantrang="";
        String checkSum = Utility.getMD5(keyMd5 + timeRequest + mcId + funcId + idViVimass + offset + limit);
        String signData = Utility.signData(KEY_SIGN_RSA, checkSum);
        createQREntity entity = new createQREntity();

        ResponseMessage responseMessage = (ResponseMessage) CallService.lookup_qr_idvi(timeRequest, mcId, funcId, checkSum, idViVimass, offset, limit);


        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1)
            {
                Type collectionType = new TypeToken<List<createQREntity>>(){}.getType();
                List <createQREntity> list = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), collectionType);
                if(list!=null&&list.size()>0)
                {
                    Collections.sort(list, new Comparator<createQREntity>() {
                        public int compare(createQREntity one, createQREntity other) {
                            return one.nameQR.compareTo(other.nameQR);
                        }

                    });

                    for(createQREntity item : list)
                    {

                        String json = new Gson().toJson(item);
                        json = json.replaceAll("\"","\\\'");

                        int type = item.typeQR;

                        switch (type) {
                            case 1:
                                String loaivi ="";
                                if(item.providerCode == 8){
                                    loaivi ="Vimass";
                                }

                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\">\n" +
                                            "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                                "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                                    "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                                "</div>"+
                                                "<div class='col-8 ms-1 fs-qrlookup'>"+
                                                    "<div class=' m-0'>"+
                                                        "<div class='w-100 d-flex flex-row'>"+
                                                            "<div class='w-100'>"+ item.nameQR +"</div>"+
                                                        "</div>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row '>"+
                                                        "<span>Loại QR:</span>"+
                                                        "<span> Nạp ví</span>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row'>"+
                                                        "<span>Loại ví:</span>"+
                                                        "<span>"+loaivi+"</span>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row'>"+
                                                        "<span>Số ví:</span>"+
                                                        "<span>"+item.customerCode+"</span>"+
                                                    "</div>"+
                                                "</div>"+
                                                 "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                                        "<div>"+
                                                            "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                                        "</div>"+
                                                        "<div>"+
                                                            "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                                        "</div>"+
                                                  "</div>"+
                                                "</div>"+
                                        "</div>";
                                break;
                            case 3:
                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\" >\n"+
                                            "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                                "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                                    "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                                "</div>"+
                                                "<div class='col-8 ms-1 fs-qrlookup'>"+
                                                    "<div class=' m-0'>"+
                                                        "<div class='w-100 d-flex flex-row'>"+
                                                            "<div class='w-100'>"+ item.nameQR +"</div>"+

                                                        "</div>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row '>"+
                                                        "<span>Loại QR:</span>"+
                                                        "<span> Nhận tiền</span>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row'>"+
                                                        "<span>Ngân hàng:</span>"+
                                                        "<span>"+item.bankCode+"</span>"+
                                                    "</div>"+
                                                    "<div class=' m-0 d-flex flex-row'>"+
                                                        "<span>Số TK:</span>"+
                                                        "<span>"+item.bankNumber+"</span>"+
                                                    "</div>"+
                                                "</div>"+
                                                "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                                    "<div>"+
                                                        "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                                    "</div>"+
                                                    "<div>"+
                                                        "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                                    "</div>"+
                                                "</div>"+
                                            "</div>"+
                                        "</div>";
                                break;
                            default:
                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\" >\n"+
                                        "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                        "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                        "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                        "</div>"+
                                        "<div class='col-8 ms-1 fs-qrlookup'>"+
                                        "<div class=' m-0'>"+
                                        "<div class='w-100 d-flex flex-row'>"+
                                        "<div class='w-100'>"+ item.nameQR +"</div>"+

                                        "</div>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row '>"+
                                        "<span>Loại QR:</span>"+
                                        "<span> Nhận tiền</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Ngân hàng:</span>"+
                                        "<span>"+item.bankCode+"</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Số TK:</span>"+
                                        "<span>"+item.bankNumber+"</span>"+
                                        "</div>"+
                                        "</div>"+
                                        "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                        "<div>"+
                                        "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "<div>"+
                                        "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>";
                        }

                    }


                    if(list != null){

                        html_phantrang = genHtmlPhanTrang_TracuuQR(responseMessage.getTotal());
                    }
                }
            }
            else
            {
                html = responseMessage.getMsgContent();
            }

        }

        return ok(html+"___"+html_phantrang);
    }

    public  static Result lookup_qr_idvi_page(){
        String keyMd5 = "kLyTwOIeXVCz88HYuP5R0viF2PvDMNFgGSYqvhzodiBOJxyalcuRnGCEJpspA9qjEOfm6";
        String KEY_SIGN_RSA = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbSAV7lRE89l/pBtj/PcQCb9Y+NRQ+cCQSXuNZPgcXXWl7XsKh4td58dR8GPOljYIlSOz4KsIQN6sr2E1H964uPdWX6W4ATJ+XEheXyVk8piI8vUWfdBbKpXwOaXfoMYktEsLHLn6ieN91w8QOkPT6UDuU04NCEYu4S+PvafVXSfzF06YYqjxDGmnwT744H+KHFSjpmPLr1u9Z1LK1bs/avYRKie8U4WKZTjIZYbjqKUseMr6xURkb2vOGCY+vdsfbY/Ppr6HqetehcKqZlLG3JSqgbe2BKQS3EmCGw495Xb5tUTF4FSvwk0PGLm3An/lL1SbTQDkgoHr9YelZ6HxlAgMBAAECggEBAJZz2vQZxAEISneTLe9o3iDqUXAmp72zlA/Epo2SXvaRiKGDeBu4mcTKvnGbD3/Js18C4K+3Zh6mgZBS3NYtMjQVr32oK+/YTgzm97sW2QK9iZDGZs4PggHJA4FHSWnNjTcB5X1uHD651PHaw3zFkMdzs7cIlTYPoAvqZChVxuRSBlGyS5u7aypLNE5HIXRhvCLfA1NSkbP6s2KLj0NMERYXy5EG71fYC2/5pDeVn8CPNBhW/3P6MwQIBhA/QsYRDqv05DTtVrK3PTlvu4OSNvpwXin7w3g2zYKZpf3XEqx5btckhMn0Y+bzEWfbGnvV4u6OENpBinhPwXZNuBK2QYUCgYEAypcC6WO6Bi/pRKk9K2p2sNf86MNWLu6gReKWoXVcU/zjw2xo8S6Dun23sInRf5ciEtsGG0P+dDNOi0UVCOc2h2Yj9X/wzfqYq9Sk3QrzlCTw0pmZfvIS04CSU7l6eegX9pj/pGikO6a1XG7tHvCqboE3azfrMG3uCYeruOXCr88CgYEAxDgcE+OfqTzVHFqVYvRqYFKYEgTdIT60X2KIVWFTw+4aL+je9yfs7dmBnSCwJPr3jv28AnFkXvfMuOqEaatEzvpE3gc9eQd47ncTx1GwfFJ9AZnI08RtWgTKWWliGA6IX2O2LMv/GZ1cS7DRdHSLy5fNaI4q45/ZPWRY1kUfSYsCgYBqZaL21hRVuYXmNIfWrGRHfjfqOuShY1uzOExp4Ywq4+7urJn0yLwQDoSRZ+AqBUK06mirmysg9e0zQYXJljzSIXeTMK1UUF8sJ1KqcFz6megMgvTVcecnQygWZpwHODiVn0brnrVaSrSHJg7MqBKqVJy5Z7XSSnmwcZtjUO/NiwKBgFnhadsidUxMdUIbrDo7w06EBuyaFcDYvD7FH7YccwZY1t4DSb7lJv58l4amJomJ/EQfrjNgRZ3K3JgHtMfCkC5PLaSWpG4AIkD5grHWlYUdM+27x+ZZH84QS7BROYHThlxLjHNQrnXJQzHYuNkjWbXUv16ZYMQLwLMCeD8OQpqpAoGBAJzPGYV1qDPpS9YBgalMksyBz6snZvcY156PWEMDO7GxtYagngGimS/y+163d8tHGKf+2aLAJktxTO0qHwpBtuEGFOM4eCc7KfkjrVK8h9O6W4OkLxZ1epQ/2KrFKxGbnrSKYJQzwqivD1cizzaR+2rkAJAhqan2VPcze5Ul5NzH";
        long timeRequest = new Date().getTime();
        String mcId="qrvnMC1_4917";
        int funcId = 8;
        String idViVimass;
        if(session()!=null&&session().get(Utility.USER)!="")
        {
            idViVimass = session().get(Utility.USER);
        }
        else
        {
            return ok("Vui lòng đăng nhập để sử dụng chức năng này");
        }
        int offset = 0;
        if(request().getQueryString("offset") != null  && request().getQueryString("offset") != "") {
            offset = Integer.valueOf(request().getQueryString("offset"));
        }
        int limit = 50;
        String html = "";
        String checkSum = Utility.getMD5(keyMd5 + timeRequest + mcId + funcId + idViVimass + offset + limit);
        String signData = Utility.signData(KEY_SIGN_RSA, checkSum);
        createQREntity entity = new createQREntity();

        ResponseMessage responseMessage = (ResponseMessage) CallService.lookup_qr_idvi(timeRequest, mcId, funcId, checkSum, idViVimass, offset, limit);


        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1)
            {
                Type collectionType = new TypeToken<List<createQREntity>>(){}.getType();
                List <createQREntity> list = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), collectionType);
                if(list!=null&&list.size()>0)
                {
                    Collections.sort(list, new Comparator<createQREntity>() {
                        public int compare(createQREntity one, createQREntity other) {
                            return one.nameQR.compareTo(other.nameQR);
                        }

                    });

                    for(createQREntity item : list)
                    {

                        String json = new Gson().toJson(item);
                        json = json.replaceAll("\"","\\\'");

                        int type = item.typeQR;

                        switch (type) {
                            case 1:
                                String loaivi ="";
                                if(item.providerCode == 8){
                                    loaivi ="Vimass";
                                }

                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\">\n" +
                                        "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                        "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                        "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                        "</div>"+
                                        "<div class='col-8 ms-1 fs-qrlookup'>"+
                                        "<div class=' m-0'>"+
                                        "<div class='w-100 d-flex flex-row'>"+
                                        "<div class='w-100'>"+ item.nameQR +"</div>"+
                                        "</div>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row '>"+
                                        "<span>Loại QR:</span>"+
                                        "<span> Nạp ví</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Loại ví:</span>"+
                                        "<span>"+loaivi+"</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Số ví:</span>"+
                                        "<span>"+item.customerCode+"</span>"+
                                        "</div>"+
                                        "</div>"+
                                        "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                        "<div>"+
                                        "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "<div>"+
                                        "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>";
                                break;
                            case 3:
                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\" >\n"+
                                        "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                        "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                        "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                        "</div>"+
                                        "<div class='col-8 ms-1 fs-qrlookup'>"+
                                        "<div class=' m-0'>"+
                                        "<div class='w-100 d-flex flex-row'>"+
                                        "<div class='w-100'>"+ item.nameQR +"</div>"+

                                        "</div>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row '>"+
                                        "<span>Loại QR:</span>"+
                                        "<span> Nhận tiền</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Ngân hàng:</span>"+
                                        "<span>"+item.bankCode+"</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Số TK:</span>"+
                                        "<span>"+item.bankNumber+"</span>"+
                                        "</div>"+
                                        "</div>"+
                                        "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                        "<div>"+
                                        "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "<div>"+
                                        "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>";
                                break;
                            default:
                                html += "<div id='item-lookup-qr' class=\"col-12 col-md-6 col-xxl-6 w-item-100  item-lookup-qr\" data-for=\""+json+"\" >\n"+
                                        "<div class='p-2 w-100 d-flex flex-row flex-shrink-0 style-item-qr item-active'>"+
                                        "<div class='col-2 d-flex justify-content-center align-items-center'>"+
                                        "<img class='img-thumbnail max-img' src=\"/assets/images/qrvn.png\")\" >"+
                                        "</div>"+
                                        "<div class='col-8 ms-1 fs-qrlookup'>"+
                                        "<div class=' m-0'>"+
                                        "<div class='w-100 d-flex flex-row'>"+
                                        "<div class='w-100'>"+ item.nameQR +"</div>"+

                                        "</div>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row '>"+
                                        "<span>Loại QR:</span>"+
                                        "<span> Nhận tiền</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Ngân hàng:</span>"+
                                        "<span>"+item.bankCode+"</span>"+
                                        "</div>"+
                                        "<div class=' m-0 d-flex flex-row'>"+
                                        "<span>Số TK:</span>"+
                                        "<span>"+item.bankNumber+"</span>"+
                                        "</div>"+
                                        "</div>"+
                                        "<div class='col-2 d-flex flex-row justify-content-end'>"+
                                        "<div>"+
                                        "<img class=\"saoke\" data-for=\""+json+"\"  src=\"/assets/images/saoke.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "<div>"+
                                        "<img class=\"edit\" data-for=\""+json+"\"  src=\"/assets/images/edit.png\")\" style='max-width: 25px;'>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>"+
                                        "</div>";
                        }

                    }



                }
            }
            else
            {
                html = responseMessage.getMsgContent();
            }

        }

        return ok(html);
    }

    public static String genHtmlPhanTrang_TracuuQR(double tongsobannghi){
        String html ="";

        Logger.info("Tổng số bản nghi: " + tongsobannghi);
        int tongso = (int) tongsobannghi;
        if(tongso > 0){
            int t = 0;
            if(tongso%50 == 0){
                t = tongso/50;

            }else{
                t = (tongso/50) + 1;
            }

      /*      if(tongso%getLimit()==0)
            {
                t = tongso/getLimit();
            }
            else
            {
                t = (tongso/getLimit())+1;
            }
*/
            double soTrang = Math.round(t);
            Logger.info("t = " + t + " soTrang = " + soTrang);
            html += "<input type=\"hidden\" class=\"offset_input\" value=\"0\">";
            html += "<ul class=\"d-flex flex-wrap p-0 m-0\">";
            html += "<li class=\" d-flex justify-content-center align-items-center pointer back border-l\"><i class=\"fas fa-angle-double-left\"></i></li>";
            for(int i=0;i<soTrang;i++)
            {
                if(i<100)
                {
                    int offset = i*50;
                    if(i+1==1)
                    {

                        html+= "<li id=\""+offset+"\" class=\" item_page pointer choose_page\" >"+(i+1)+"</li>";
                    }
                    else
                    {
                        html+= "<li id=\""+offset+"\"  class=\" item_page pointer\" >"+(i+1)+"</li>";

                    }
                }
            }
            html += "<li class=\" d-flex justify-content-center align-items-center pointer next border-r\"><i class=\"fas fa-angle-double-right\"></i></li>";

        }
        html+= "</ul>";
        return html;


    }
    public static int getLimit()
    {
        int limit = Utility.limitTransaction;
        return limit;
    }


    // Sao ke QR
    public static Result saoke_gdqrvi(){
        DynamicForm form = Form.form().bindFromRequest();
        String html = "";
        int funcId = 15;
        long timeFrom = 0;
        long timeTo = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String lastDate = "";
        String firstDate = "";
        if (form.get("from") != null && form.get("from") != "") {
            firstDate = form.get("from");
        } else {

            firstDate = dateFormat.format(date.getTime());
        }
        if (form.get("to") != null && form.get("to") != "") {
            lastDate = form.get("to");
        } else {
            lastDate = dateFormat.format(date.getTime());
        }


        Logger.info("timeFrom=" + lastDate + " timeTo =" + firstDate);

        timeFrom = Utility.TimeConverterFrom(firstDate);
        timeTo = Utility.TimeConverterTo(lastDate);
        String idViSoHuu = session().get(controllers.account.Service.KEY_PHONE_ID);
        String secssion = session().get(controllers.account.Service.KEY_SESSION_ID);
        int offset = 0;
        int limit = 100;
        long timeRequest = new Date().getTime();
        String keySearch = "";
        if (form.get("keySearch") != null && form.get("keySearch") != "") {
            keySearch = form.get("keySearch");
        }
        String checkSum = Utility.getMD5("jdqpowrifioefiqo3289r79f" + funcId + timeFrom + timeTo + timeRequest + idViSoHuu + secssion + offset + limit);
        ResponseMessage responseMessage = (ResponseMessage) CallService.saoKeQRCuaVi(timeFrom, timeTo, idViSoHuu, secssion, offset, limit, checkSum, timeRequest, keySearch);
        if (responseMessage != null && responseMessage.getMsgCode() == 1) {


            ds listSaoKeQR = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), ds.class);

            if (listSaoKeQR != null && listSaoKeQR.dsHienThi.size() > 0) {

                for (int i = 0; i < listSaoKeQR.dsHienThi.size(); i++) {

                    String temp = Utility.convertLongTime2(listSaoKeQR.dsHienThi.get(i).tg);
                    listSaoKeQR.dsHienThi.get(i).thoigian = temp;
                    String[] time = temp.split(" ");

                    String noidung ="";
                    if(listSaoKeQR.dsHienThi.get(i).nd != null &&  listSaoKeQR.dsHienThi.get(i).nd != "" && !listSaoKeQR.dsHienThi.get(i).nd.equals("null")){
                        noidung = listSaoKeQR.dsHienThi.get(i).nd;

                    }else{
                        noidung="";
                    }

                    String json = new Gson().toJson(listSaoKeQR.dsHienThi.get(i)).replaceAll("\"", "'");
                    String json_data = new Gson().toJson(listSaoKeQR.dsHienThi.get(i));
                    System.out.println("json saoke QR tong: " + json_data );
                    html += "<tr class=\"chitietgdqr\">\n" +
                            "<td class=\"w-tt tb-h text-center\">"+(i+1)+"</td>\n" +
                            "<td class=\"w-time tb-\">\n" +
                            "<span class=\"float-start\">"+time[0]+"</span>\n" +
                            "<span class=\"float-end\">"+time[1]+"</span>\n" +
                            "</td>\n" +
                            "<td class=\"w-amount tb-h\">\n" +
                            "<span class=\"float-start\">+</span>\n" +
                            "<span class=\"float-end\">"+ Utility.getVNDFormat(listSaoKeQR.dsHienThi.get(i).tien)+"</span>\n" +
                            "</td>\n" +
                            "<td class=\"w-fees tb-h text-center\">"+ Utility.getVNDFormat(listSaoKeQR.dsHienThi.get(i).phi)+"</td>\n" +
                            "<td class=\"w-content tb-h\">"+noidung+"</td>\n" +
                            "<input type=\"hidden\" class=\"noidungchitiet\" value=\"" + json + "\">"+
                            "</tr>";
                }
            }
        }
        return ok(html + "ok2conde" + firstDate);
    }
// Sao ke gd QR

    public static Result saoke_gdqr() {
        DynamicForm form = Form.form().bindFromRequest();
        String html = "";
        int funcId = 16;
        long timeFrom = 0;
        long timeTo = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String lastDate = "";
        String firstDate = "";
        if (form.get("from") != null && form.get("from") != "") {
            firstDate = form.get("from");
        } else {

            firstDate = dateFormat.format(date.getTime());
        }
        if (form.get("to") != null && form.get("to") != "") {
            lastDate = form.get("to");
        } else {
            lastDate = dateFormat.format(date.getTime());
        }


        Logger.info("timeFrom=" + lastDate + " timeTo =" + firstDate);

        timeFrom = Utility.TimeConverterFrom(firstDate);
        timeTo = Utility.TimeConverterTo(lastDate);
        String idViSoHuu = session().get(controllers.account.Service.KEY_PHONE_ID);
        String secssion = session().get(controllers.account.Service.KEY_SESSION_ID);
        int offset = 0;
        int limit = 100;
        long timeRequest = new Date().getTime();
        String keySearch = "";

        String maQR = form.get("maQR");
        System.out.println("ma QR:" + maQR);
        System.out.println("ma QR:" + form.get("keySearch"));
        if (form.get("keySearch") != null && form.get("keySearch") != "") {
            keySearch = form.get("keySearch");
        }
        String checkSum = Utility.getMD5("jdqpowrifioefiqo3289r79f" + funcId + timeFrom + timeTo + timeRequest + idViSoHuu + secssion + offset + limit + maQR);
        ResponseMessage responseMessage = (ResponseMessage) CallService.saoKeQR(timeFrom, timeTo, idViSoHuu, secssion, offset, limit, maQR, checkSum, timeRequest, keySearch);

        ds listSaoKeQR = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), ds.class);

        if (listSaoKeQR != null && listSaoKeQR.dsHienThi.size() > 0) {
            System.out.println("danh sach QR sk: "+ new Gson().toJson(listSaoKeQR.dsHienThi.get(0)));

            for (int i = 0; i < listSaoKeQR.dsHienThi.size(); i++) {

                String temp = Utility.convertLongTime2(listSaoKeQR.dsHienThi.get(i).tg);
                listSaoKeQR.dsHienThi.get(i).thoigian = temp;
                String[] time = temp.split(" ");

                String noidung ="";
                if(listSaoKeQR.dsHienThi.get(i).nd != null &&  listSaoKeQR.dsHienThi.get(i).nd != "" && !listSaoKeQR.dsHienThi.get(i).nd.equals("null")){
                    noidung = listSaoKeQR.dsHienThi.get(i).nd;
                    System.out.println("noi dung qr sk:" +noidung );

                }else{
                    noidung="";
                }

                String json = new Gson().toJson(listSaoKeQR.dsHienThi.get(i)).replaceAll("\"", "'");
                System.out.println("json saoke QR" + json );
                html += "<tr class=\"chitietgdqr_sk\">\n" +
                        "<td class=\"w-tt tb-h text-center\">"+(i+1)+"</td>\n" +
                        "<td class=\"w-time tb-\">\n" +
                        "<span class=\"float-start\">"+time[0]+"</span>\n" +
                        "<span class=\"float-end\">"+time[1]+"</span>\n" +
                        "</td>\n" +
                        "<td class=\"w-amount tb-h\">\n" +
                        "<span class=\"float-start\">+</span>\n" +
                        "<span class=\"float-end\">"+ Utility.getVNDFormat(listSaoKeQR.dsHienThi.get(i).tien)+"</span>\n" +
                        "</td>\n" +
                        "<td class=\"w-fees tb-h text-center\">"+ Utility.getVNDFormat(listSaoKeQR.dsHienThi.get(i).phi)+"</td>\n" +
                        "<td class=\"w-content tb-h\">"+noidung+"</td>\n" +
                        "<input type=\"hidden\" class=\"noidungchitiet_sk\" value=\"" + json + "\">"+
                        "</tr>";
            }
        }


        return ok(html + "ok2conde" + firstDate);
    }


    public static Result thongkedoanhsocuachinhanh() {
        DynamicForm form = Form.form().bindFromRequest();
        String html = "";
        int funcId = 17;
        long timeFrom = 0;
        long timeTo = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String lastDate = "";
        String firstDate = "";
        if (form.get("from") != null && form.get("from") != "") {
            firstDate = form.get("from");
        } else {

            firstDate = dateFormat.format(date.getTime());
        }
        if (form.get("to") != null && form.get("to") != "") {
            lastDate = form.get("to");
        } else {
            lastDate = dateFormat.format(date.getTime());
        }

        Logger.info("timeFrom=" + lastDate + " timeTo =" + firstDate);

        timeFrom = Utility.TimeConverterFrom(firstDate);
        timeTo = Utility.TimeConverterTo(lastDate);

        String userSearch = form.get("userSearch");


        String userLogin = form.get("userLogin");

        String secssionLogin = session().get(controllers.account.Service.KEY_SESSION_ID);
        long timeRequest = new Date().getTime();

        String checkSum = Utility.getMD5("jdqpowrifioefiqo3289r79f" + funcId + timeFrom + timeTo + timeRequest + userSearch + userLogin + secssionLogin);
        ResponseMessage responseMessage = (ResponseMessage) CallService.thongkedoanhsocuachinhanh(timeFrom, timeTo, userSearch, userLogin, secssionLogin, checkSum, timeRequest);
        System.out.println("thongkedoanhsocuachinhanh " + responseMessage );
        if(responseMessage!=null)
        {

            if(responseMessage.getMsgCode()==1)
            {

            ObjectTk_doanhthu_CN response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),ObjectTk_doanhthu_CN.class);
            html +="  <div class=\"d-flex flex-column fs-5\">\n" +
                        " <div class=\"d-flex flex-row\">\n" +
                            " <strong>Tổng số giao dịch:</strong>\n" +
                            " <span class=\"ms-1\">"+ Utility.getVNDFormat(response.total)+"</span>\n" +
                        "</div>\n" +
                        " <div class=\"d-flex flex-row\">\n" +
                            "<strong>Tổng số tiền:</strong>\n" +
                            "<span class=\"ms-1\">"+ Utility.getVNDFormat(response.totalAmount)+" đ"+"</span>\n" +
                        "</div>\n" +
                        "<div class=\"d-flex flex-row\">\n" +
                            "<strong>Tổng số phí:</strong>\n" +
                            " <span class=\"ms-1\">"+ Utility.getVNDFormat(response.totalFee)+" đ"+"</span>\n" +
                        "</div>\n" +
                    "</div>";
            }else {
                html = responseMessage.getMsgContent();
            }
        }
        return ok(html + "ok2conde" + firstDate);
    }

    public static Result layThongTinCnVi()
    {
        String idVi = request().getQueryString("idVi");
        if(idVi!=null&&idVi!="")
        {
            AccViEntity info = CallService.layThongTinCNVi(idVi);
//            System.out.println("Namdd" + info);
            String json = new Gson().toJson(info);
            json = json.replace("\\","");
            return ok(json);
        }

        return ok("");
    }


}
