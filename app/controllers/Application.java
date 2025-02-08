package controllers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.account.Service;
import controllers.account.ThongTinCaNhan;
import controllers.modal.Map.ObjBankinh;
import controllers.tools.ResponseMessage;
import entity.Goixe.ListCar;
import entity.Goixe.SearchCarResponse;
import entity.KetQuaUploadAnhObject;
import entity.Laixe.DangkyxeEntity;
import entity.Laixe.ObjectItem1DiemDen;
import entity.Province;
import entity.baiviet.baivietEntiy;
import entity.cameraEntity;
import org.joda.time.LocalTime;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.LaiXe.Dangkyxe;
import views.html.LaiXe.dangKyLaiXe;
import views.html.account.login_register;
import views.html.account.xacthuc;
import views.html.baiviet.dangkylaixe;
import views.html.baiviet.dangkytaikhoan;
import views.html.baiviet.dangkyxe;
import views.html.baiviet.huongdandvgoixe;
import views.html.*;
import views.html.itemLink_download.url_link;
import views.html.testcode.testcode1;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


import java.util.ArrayList;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("VTaxi"));
    }
    public static Result changeLang() {


        String lang = request().getQueryString("lang");
        Logger.info("language = " + lang);
        session().put("lang", lang);
        return ok("");
    }


    public static Result header_menu() {
        if (session().get("lang") == null || session().get("lang") == "") {
            session().put("lang", "vi");
        } else {
            if (session().get("lang").equals("en")) {
                session().put("lang", "en");
            } else {
                session().put("lang", "vi");
            }
        }
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }

        return ok(header_menu.render("", info));
    }
    public static Result Url_itemlink() {
        return ok(url_link.render());
    }

    public static Result login_register() {
        if (session().get("lang") == null || session().get("lang") == "") {
            session().put("lang", "vi");
        } else {
            if (session().get("lang").equals("en")) {
                session().put("lang", "en");
            } else {
                session().put("lang", "vi");
            }
        }
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        return ok(login_register.render("Tài khoản", "", "", info));
    }

    // ------------------------Lái xe-----------------------
    public static Result dangKyLaiXe() {
        ThongTinCaNhan info = new ThongTinCaNhan();
        String province = getListTinhThanh();
        if(session().get(Service.KEY_DN)!=null&&session().get(Service.KEY_DN)!="")
        {
            info = CallService.layThongTin("AccInfo"+session().get(Service.KEY_PHONE_ID)+session().get(Service.KEY_DN));
        }
        else
        {
            info = CallService.layThongTin("AccInfo"+session().get(Service.KEY_PHONE_ID));
        }
        info.setHome(info.getHome().split("___")[0]);
        if(info.getHome()!=null&&!info.getHome().equals(""))
        {
            if(info.getHome().contains("___gt:")&&info.getHome().contains("___qg:"))
            {
                String temp = info.getHome().split("___gt:")[1];
                String gioiTinh = temp.split("___qg:")[0];
                String quocTich = temp.split("___qg:")[1];
                if(!gioiTinh.equals(""))
                {
                    try{
                        info.setGioiTinh(Integer.valueOf(gioiTinh));
                    }catch (Exception e)
                    {

                    }

                }
                info.setQuocTich(quocTich);
                info.setHome(info.getHome().split("___gt:")[0]);
            }
        }
        String date="";
        String [] ngaySinh;
        if(info.getBirthday()!=null&&info.getBirthday()!=""){
             ngaySinh= info.getBirthday().split("-");
             date= ngaySinh[2]+"/"+ngaySinh[1]+"/"+ngaySinh[0];
        }
        return ok(dangKyLaiXe.render("Đăng ký lái xe",info,province,date));
    }
    private static ArrayList<String> dsAnh;
    private static String sDuongDanLinkAnh = "";
    public static Result DangKyLaiXePost()
    {
        System.out.println("day nhe");
        DynamicForm requestData = Form.form().bindFromRequest();
        int funcID = -47850;
        String user= session().get(Service.KEY_PHONE_ID);
        String token=requestData.get("token");
        String ten = requestData.get("acc_name");
        int gioiTinh=0;
        if(requestData.get("gioiTinh")!="")
        {
            gioiTinh =Integer.valueOf(requestData.get("gioiTinh")) ;
            System.out.println("nam " + gioiTinh);
        }
        String ngaySinh="";
        if(!requestData.get("datepicker1").equals(""))
        {
            String[] str = requestData.get("datepicker1").split("/");
            if(str.length>0)
            {
                ngaySinh = str[0]+"-"+str[1]+"-"+str[2];
            }
        }
        System.out.println("Ngày sinh: " + ngaySinh);
        String cccd = requestData.get("soCMND");
        String diaChi = requestData.get("noithuongtru");
        String sdtLienHe= requestData.get("soDt") ;
        int idViVimass=0;
        if (!requestData.get("soViTT").equals("")) {
            idViVimass = Integer.valueOf(requestData.get("soViTT"));   //Số ví mà người đăng ký nhập vào để trừ tiền
        }
        String email= requestData.get("email");
        String linkAnhChanDung = requestData.get("imgdata3");
        String linkAnhCCCD=requestData.get("imgdata1");
        long timeRequest = new Date().getTime();
        String checkSum = Utility.getMD5("38473489t3894t94r" + funcID + idViVimass + gioiTinh + ngaySinh + cccd + sdtLienHe + email + user + token + timeRequest);
        ResponseMessage responseMessage = (ResponseMessage)CallService.DKLaiXe( token,  user,  timeRequest,  idViVimass,  ten,  gioiTinh, ngaySinh, cccd,  diaChi,  sdtLienHe,  email,  linkAnhChanDung,  linkAnhCCCD,  checkSum);
        if(responseMessage.getMsgCode()==1&&responseMessage!=null)
        {
            return ok("ok la lá là la");
        }
        else
        {
            return ok("FAIL");
        }
    }


    private static void uploadAnhBase64(int nIdex)
    {
        if(nIdex < dsAnh.size()) {
            String sDuLieuImage = dsAnh.get(nIdex);
            if(!sDuLieuImage.equals("")) {
                if(!sDuLieuImage.startsWith("vm/") && !sDuLieuImage.startsWith("http://") && !sDuLieuImage.startsWith("https://")) {
                    String sKetQua = CallService.upLoadAnh(session().get(Service.KEY_PHONE_ID), sDuLieuImage);
                    if (sKetQua != null && sKetQua.length() > 0 && controllers.tools.Utility.isValidJSON(sKetQua)) {
                        KetQuaUploadAnhObject object = new Gson().fromJson(sKetQua, KetQuaUploadAnhObject.class);
                        if (object.getMsgCode() == 1) {
                            sDuongDanLinkAnh += object.getResult();
                            if (nIdex != dsAnh.size() - 1)
                                sDuongDanLinkAnh += ";";
                        } else {
                            if (nIdex != dsAnh.size() - 1)
                                sDuongDanLinkAnh += ";";
                        }
                    } else {
                        if (nIdex != dsAnh.size() - 1)
                            sDuongDanLinkAnh += ";";
                    }
                }
                else {
                    String[] temp = sDuLieuImage.split("=");
                    sDuongDanLinkAnh += temp[1];
                    Logger.info("link: " + temp[1]);
                    if (nIdex != dsAnh.size() - 1)
                        sDuongDanLinkAnh += ";";
                }
            }
            else
            {
                if(nIdex != dsAnh.size() -1 )
                    sDuongDanLinkAnh += ";";
            }
            nIdex++;
            uploadAnhBase64(nIdex);
        }
    }
    private static String locDuLieuAnhBase64(String sDuLieu)
    {
        String sKetQua = "";
        if(sDuLieu != null) {
            if(!sDuLieu.startsWith("vm/") && !sDuLieu.startsWith("http://") && !sDuLieu.startsWith("https://")) {
                int nFistIndex = sDuLieu.indexOf(",");
                sKetQua = sDuLieu.substring(nFistIndex + 1);
            }
            else{
                sKetQua = sDuLieu;
            }
        }
        return sKetQua;
    }
    public static String getListTinhThanh()
    {
        String line = "";
        StringBuilder builder = new StringBuilder();
        InputStream is = play.api.Play.current().classloader().getResourceAsStream("province.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);   // add everything to StringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Type ProvinceType = new TypeToken<List<Province>>(){}.getType();
        List<Province> provinceList = new Gson().fromJson(builder.toString(),ProvinceType);
        if(provinceList!=null&&provinceList.size()>0)
        {
            String htmlTinhThanh = "";
            Collections.sort(provinceList, new Comparator<Province>() {
                @Override
                public int compare(final Province object1, final Province object2) {
                    return object1.getORDER().compareTo(object2.getORDER());
                }
            });
            /*Logger.info("listProvince:"+provinceList.size());*/
            htmlTinhThanh += "<option value=\"0\">Chọn Tỉnh/ Thành phố</option>";
            for(Province province : provinceList)
            {
                htmlTinhThanh += "<option value=\""+province.getID()+"\">"+province.getNAME_VI()+"</option>";
            }
            return htmlTinhThanh;
        }
        else
        {
            return "";
        }
    }

    public static Result xacthukhuonmat() {

        return ok(xacthuc.render("Xác thực khuôn mặt"));
    }
    public static Result dangkykinhdoanh() {

        return ok(dangkykinhdoanh.render("Vtaxi - Đăng ký kinh doanh"));
    }


    public static Result Datxe() {
        String listxe = Listxe();
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        return ok(views.html.Dichvu.Datxe.render("", info, listxe));
    }

    public static Result Datxengay_Post(){
        DynamicForm requestData = Form.form().bindFromRequest();
        String html ="";
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        int funcId = -47891;
        String ip = "192.168.100.17";
        int thietBiGoi= 4;
        int ver = 1;
        String user = session().get(Service.KEY_PHONE_ID);;

//        diem don
        String latDon = requestData.get("latDon");
        String lngDon = requestData.get("lngDon");
        String diaChiMoTaDon = requestData.get("diachidon");

// diem den
        String latden = requestData.get("latden");
        String lngden = requestData.get("lngden");
        String diaChiden= requestData.get("diachiden");
        List<ListCar> listXe = null;
        System.out.println("latDon: " + latDon+ " lngDon: " + lngDon+ " diaChiden: "+ diaChiden + " latDen: "+ latden + " lngden: "+ lngden + " diaChiden: "+ diaChiden );
        ArrayList<ObjectItem1DiemDen> dsDiemDen = new ArrayList<ObjectItem1DiemDen>();
    /*    dsDiemDen.get(0).latDen = Double.parseDouble(latden);*/
        dsDiemDen.add(new ObjectItem1DiemDen(Double.parseDouble(latden),Double.parseDouble(lngden),diaChiden,0));
      /*  dsDiemDen.get(0).lngDen = Double.parseDouble(lngden);
        dsDiemDen.get(0).diaChiMoTaDen = diaChiden;
        dsDiemDen.get(0).km = 0;*/
        String totalKm = requestData.get("so_km");
        String timedichuyen = requestData.get("so_phut");


        /*String hours = timedichuyen.split("hour")[0];
        String minus = timedichuyen.split("hour")[1];
              String  time=   ((hours * 60 )+minus);
              System.out.println("thoi gian " + time);*/

        System.out.println("km: "+ totalKm+" so_phut "+ timedichuyen);
        long timeRequest= new Date().getTime();
        String loaiDichVu = "1";

        String checkSum = Utility.getMD5("sjkvklcae982594tfkdHJTKcxkac325" + funcId + totalKm + latDon + lngDon + loaiDichVu + user + ip   + thietBiGoi  + ver + timeRequest  + totalKm);


        ResponseMessage responseMessage = (ResponseMessage) CallService.Datxengay(funcId, ip, thietBiGoi, ver, timeRequest, user, latDon, lngDon, diaChiMoTaDon, dsDiemDen, totalKm, loaiDichVu, checkSum);


        if(responseMessage.getMsgCode()==1&&responseMessage!=null) {
            SearchCarResponse response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),SearchCarResponse.class);
                     listXe = response.getListXe();
            double L = Double.parseDouble(totalKm);
            double B0 = 12000;
            double B1 = 28000;
            double B2 = 28000;
            double B3 = 32000;

            double C0= 4400;
            double C1= 10000;
            double C2= 10000;
            double C3= 11500;

            double D0= 4400;
            double D1= 9500;
            double D2= 9500;
            double D3= 10500;



            double E = 500;
            double E1 = 550;



            double F= 20000;
            double Fm= 10000;
            double F1 = 0;


            double G = 10000;
            double Gm = 5000;
            double G1 = 0;

            double H = 10000;
            double Hm = 5000;
            double H1= 0;

            double I = 10000;
            double Im = 5000;
            double I1 = 0;

            double T = Double.parseDouble(timedichuyen);

            double T1 = 5;
            double T2 = 7;
               /* if( F1 == 1){
                    F = 10000;
                } else if(F1== 0){
                    F = 0;
                }*/

            LocalTime current = LocalTime.now();

            String date = String.valueOf(current);

            System.out.println("time nè hih: "+ date);

            LocalTime timeStartam = LocalTime.parse("6:00:00");
            LocalTime timeEndam = LocalTime.parse("21:59:00");


            LocalTime timeStartpm = LocalTime.parse("22:00:00");
            LocalTime timeEndpm = LocalTime.parse("5:59:00");

            if (!current.isBefore(timeStartam) && current.isBefore(timeEndam)) {
                F1 = 0;

                System.out.println("Ngày : " + current+ " F : " + F);

            } else  if (!current.isBefore(timeStartpm) && current.isBefore(timeEndpm)) {
                F1 = 1;

                System.out.println("Tối "+ current + " F : " + F);
            }


            tinhTienVTaxi( B0,  C0,  D0, E,  Fm,F1,  Gm,G1,  Hm,H1,  Im,I1,  L ,  T,  T1);
            tinhTienVTaxi( B1,  C1,  D1, E,  F, F1,  G, G1,  H, H1,  I, I1,  L ,  T,  T2);
            tinhTienVTaxi( B2,  C2,  D2, E, F, F1,  G, G1,  H, H1,  I, I1,  L ,  T,  T2);
            tinhTienVTaxi( B3,  C3,  D3, E1,  F, F1,  G, G1,  H, H1,  I, I1,  L ,  T,  T2);
          /*  tinhTienVTaxi( B,  C,  D, E,  F, F1,  G, G1,  H, H1,  I, I1,  L ,  T,  T1);*/




            for (int j = 0; j < 4; j++) {
                listXe.get(j).setImageXe("https://vimass.vn/vmbank/services/media/getImage?id=xe"+j+".png");


            }
            for (ListCar listCar : listXe) {



                System.out.println("thành công "+ responseMessage.getResult());
                System.out.println("maLoaiXe "+ listCar.getMaLoaiXe());
                System.out.println("tenLoaiXe "+ listCar.getTenLoaiXe());
                System.out.println("giaCuoc1 "+ listCar.getGiaCuoc1());
                System.out.println("giaCuoc1 "+ listCar.getGiaCuoc2());
                Double giacuoc = listCar.getGiaCuoc2();

               html+=  "                                        <button type =\"button\"class=\"btn_dichuyen btn-choose_xe click\">\n" +

                       "                                            <div class=\"car_active \" style=\"display: block\">\n" +
                       "                                                <div class=\"d-flex flex-column\">\n" +
                       "                                                    <img style=\"width: 35px ;     margin-left: 10px;\"   src="+listCar.getImageXe()+">\n" +
                       "                                                    <strong style=\"font-size: x-small;   color: #0000ff\"> "+listCar.getTenLoaiXe()+"  </strong>\n" +
                       "                                                    <strong style=\"font-size: x-small;   color: #0000ff\"> "+Utility.getVNDFormat(giacuoc) +" Vnđ"+" </strong>\n" +
                       "                                                </div>\n" +
                       "                                            </div>\n" +
                       "                                        </button>\n" ;

                System.out.println("i,age Anh = " + listCar.getImageXe());
            }

            return ok(html);
        }
        else
        {
            return ok("FAIL");
        }


}

    public static Result Datxengay_Tinhtien(){
        DynamicForm requestData = Form.form().bindFromRequest();
        String html ="";
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        int funcId = -47891;
        String ip = "192.168.100.17";
        int thietBiGoi= 4;
        int ver = 1;
        String user = session().get(Service.KEY_PHONE_ID);;

//        diem don
        String latDon = requestData.get("latDon");
        String lngDon = requestData.get("lngDon");
        String diaChiMoTaDon = requestData.get("diachidon");

// diem den
        String latden = requestData.get("latden");
        String lngden = requestData.get("lngden");
        String diaChiden= requestData.get("diachiden");
        List<ListCar> listXe = null;
        System.out.println("latDon: " + latDon+ " lngDon: " + lngDon+ " diaChiden: "+ diaChiden + " latDen: "+ latden + " lngden: "+ lngden + " diaChiden: "+ diaChiden );
        ArrayList<ObjectItem1DiemDen> dsDiemDen = new ArrayList<ObjectItem1DiemDen>();
    /*    dsDiemDen.get(0).latDen = Double.parseDouble(latden);*/
        dsDiemDen.add(new ObjectItem1DiemDen(Double.parseDouble(latden),Double.parseDouble(lngden),diaChiden,0));
      /*  dsDiemDen.get(0).lngDen = Double.parseDouble(lngden);
        dsDiemDen.get(0).diaChiMoTaDen = diaChiden;
        dsDiemDen.get(0).km = 0;*/
        String totalKm = requestData.get("so_km");
        System.out.println("km: "+ totalKm);
        long timeRequest= new Date().getTime();
        String loaiDichVu = "1";

        String checkSum = Utility.getMD5("sjkvklcae982594tfkdHJTKcxkac325" + funcId + totalKm + latDon + lngDon + loaiDichVu + user + ip   + thietBiGoi  + ver + timeRequest  + totalKm);


        ResponseMessage responseMessage = (ResponseMessage) CallService.Datxengay(funcId, ip, thietBiGoi, ver, timeRequest, user, latDon, lngDon, diaChiMoTaDon, dsDiemDen, totalKm, loaiDichVu, checkSum);


        if(responseMessage.getMsgCode()==1&&responseMessage!=null) {
            SearchCarResponse response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),SearchCarResponse.class);
            listXe = response.getListXe();
            for (int j = 0; j < 4; j++) {
                listXe.get(j).setImageXe("https://vimass.vn/vmbank/services/media/getImage?id=xe"+j+".png");
            }
            for (ListCar listCar : listXe) {


                double L = Double.parseDouble(totalKm);
                int B = 28000;
                int C= 10000;
                int D= 9500;
                int E = 500;
                int F= 10000;
                int F1 = 0;
                int G = 10000;
                int G1 = 0;
                int H = 10000;
                int H1= 0;
                int I = 10000;
                int I1 = 1;  int T = 5; int T1 = 7;
               /* if( F1 == 1){
                    F = 10000;
                } else if(F1== 0){
                    F = 0;
                }*/

                LocalTime current = LocalTime.now();

                String date = String.valueOf(current);

                System.out.println("time nè hih: "+ date);

                LocalTime timeStartam = LocalTime.parse("6:00:00");
                LocalTime timeEndam = LocalTime.parse("21:59:00");


                LocalTime timeStartpm = LocalTime.parse("22:00:00");
                LocalTime timeEndpm = LocalTime.parse("5:59:00");

                if (!current.isBefore(timeStartam) && current.isBefore(timeEndam)) {
                    F1 = 0;

                    System.out.println("Ngày : " + current+ " F : " + F);

                } else  if (!current.isBefore(timeStartpm) && current.isBefore(timeEndpm)) {
                    F1 = 1;

                    System.out.println("Tối "+ current + " F : " + F);
                }



               /* tinhTienVTaxi( B,  C,  D, E,  F, F1,  G, G1,  H, H1,  I, I1,  L ,  T,  T1);*/
                System.out.println("thành công "+ responseMessage.getResult());
                System.out.println("maLoaiXe "+ listCar.getMaLoaiXe());
                System.out.println("tenLoaiXe "+ listCar.getTenLoaiXe());
                System.out.println("giaCuoc1 "+ listCar.getGiaCuoc1());
                System.out.println("giaCuoc1 "+ listCar.getGiaCuoc2());
                Double giacuoc = listCar.getGiaCuoc2();

                html+=  "                                        <button type =\"button\"class=\"btn_dichuyen btn-choose_xe click\">\n" +

                        "                                            <div class=\"car_active \" style=\"display: block\">\n" +
                        "                                                <div class=\"d-flex flex-column\">\n" +
                        "                                                    <img style=\"width: 35px ;     margin-left: 10px;\"   src="+listCar.getImageXe()+">\n" +
                        "                                                    <strong style=\"font-size: x-small;   color: #0000ff\"> "+listCar.getTenLoaiXe()+"  </strong>\n" +
                        "                                                    <strong style=\"font-size: x-small;   color: #0000ff\"> "+Utility.getVNDFormat(giacuoc) +" Vnđ"+" </strong>\n" +
                        "                                                </div>\n" +
                        "                                            </div>\n" +
                        "                                        </button>\n" ;

                System.out.println("i,age Anh = " + listCar.getImageXe());
            }

            return ok(html);
        }
        else
        {
            return ok("FAIL");
        }


    }


    public static double tinhTienVTaxi(double B, double C, double D,double E, double F,double F1, double G,double G1, double H,double H1, double I,double I1, double L , double T, double T1)
    {

        double L1 = 2; double L2 = 12;
        double giaTien = 0;

        double if1 =0 ; double if2 =0;double if3 =0; double if4 =0;

//        if1
                        if(L>L1){
                            if1 = 1;
                           }else {
                            if1 = 0;
                           }
//        if2
                       if (L>L2){
                           if2 = L2-L1;


                       } else {
                           if2 = L-L1;

                       }
//        if3
                        if(L>L2){
                            if3 = 1;
                        }else {
                            if3 = 0;
                        }
//        if4
                        if(T>T1){
                            if4 = 1;
                        }else {
                            if4 = 0;
                        }
   /*    System.out.println(" if1 "+ if1 + " if2 "+ if2 +" if3 "+ if3 +" if4 "+ if4 );*/
        System.out.println(" B "+ B +" C "+ C +" D "+ D +" E "+ E +" F "+ F +" F1 "+ F1 +" G "+ G +" G1 "+ G1 +" H "+ H+" H1 "+ H1+" I "+ I +" I1 "+ I1 +" L "+ L +" T "+ T +" T1 "+ T1  );



               giaTien = ((B+C * if1 * if2 + D * if3 * (L -L2) + E * if4 * (T-T1) +F * F1 + G * G1 + H * H1 + I * I1)/(100*84)/1000)*1000 ;

        /*System.out.println(" giá tiền nè : "+ Utility.getVNDFormat(giaTien));*/
           /* String sotien = String.valueOf(giaTien).replace(".","");*/

        /*System.out.println("sssssssssssssssssssssssssssss " + Math.round(giaTien));*/
        /*System.out.println(" giá tiền nè : "+(double) Math.round(giaTien * 10000) / 1000);*/
        String gia = String.valueOf(Math.round(giaTien * 10000) / 1000).replace(".","");
        String fomatgiatien = Utility.getVNDFormat(Double.parseDouble(gia)*1000);
        System.out.println("gía tiền chưa format  " + gia);
        System.out.println("gía tiền nè  " + fomatgiatien);
        System.out.println("------------------------------------------------------------------------------");
        return giaTien;
    }


    public static String Listxe() {

        ObjBankinh bankinhList1 = new ObjBankinh();
        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }


        int funcId = -2; // 2

        String secssionLogin = session().get(Service.KEY_SESSION_ID); // neu ko dang nhap thi truyen ""
        String idViVimass = info.getId(); // IP cuar client
        long timeRequest = new Date().getTime();
        String checkSum = controllers.Utility.getMD5("teiakda904adGjri43289478he#T%g" + funcId + idViVimass + timeRequest + secssionLogin);
        ResponseMessage responseMessage = (ResponseMessage) CallService.danhsachtoado(funcId, idViVimass, timeRequest, secssionLogin, checkSum);
        if (responseMessage != null && responseMessage.getMsgCode() == 1) {
            System.out.println("run: " + responseMessage.getResult());


        } else {
            System.out.println("lỗi: " + responseMessage.getResult());
        }
        return "";

    }
    public static Result HopdongVtaxi(){
        return ok(hopdong.render(""));
    }
    public static Result Phaply(){
        return ok(phaply.render(""));
    }

    public static Result Banggia(){
        return ok(views.html.banggia.render("VTaxi - Bảng giá"));
    }

    public static Result Testcode(){
        return ok(testcode1.render(""));
    }

    public static Result CheckCam() throws IOException {
        DynamicForm requestData = Form.form().bindFromRequest();
        String appKey = requestData.get("appKey").trim();
        String appSecret = requestData.get("appSecret").trim();

        System.out.println("appKey: "+ appKey);
        System.out.println("appSecret: "+ appSecret);
        List<cameraEntity> cameraEntityList = null;
        String htmlCam = "";

        ResponseMessage responseMessage = (ResponseMessage) CallService.checkCamera(appKey, appSecret);
        System.out.println("cameraEntity responseMessage "+ responseMessage.getMsgCode());
        if(responseMessage.getMsgCode()==1&&responseMessage!=null)
        {
            cameraEntity response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),cameraEntity.class);
            System.out.println("cameraEntity "+ response.getCode());
            for (cameraEntity cam : cameraEntityList ){
                   if (cam.getCode().equals("200")){
                       htmlCam += " <option value=\"ezopen://open.ezviz.com/K22834441/1.hd.live\"> Test</option>";
                   }  else {
                       htmlCam += " <option value=\"https://qrvn.vn/live/22110901/\"> Test</option>";
                   }
                System.out.println("thành công "+ responseMessage.getResult());
                String json = new Gson().toJson(response);

                json = json.replaceAll("\"", "\\\'");
                System.out.println(" htmlCam : "+ htmlCam);
                return ok("OK___" + htmlCam);
            }

        }
        return ok(htmlCam);

    }

    public static Result POSTRequest() throws IOException {

        URL url = new URL("https://open.ezvizlife.com/api/lapp/token/get");
        Map<String,Object> params = new LinkedHashMap<>();
        DynamicForm requestData = Form.form().bindFromRequest();
        String appKey = requestData.get("appKey").trim();
        String appSecret = requestData.get("appSecret").trim();

        System.out.println("appKey: "+ appKey);
        System.out.println("appSecret: "+ appSecret);
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
        List<cameraEntity> cameraEntityList = null;
        String htmlCam = "";
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
            System.out.println("response : "+ response);
        Gson g = new Gson();
        cameraEntity cam = g.fromJson(response, cameraEntity.class);
        System.out.println("cameraEntity "+ cam.getCode());
        if (cam.getCode().equals("200")){
            htmlCam += " <option value=\"ezopen://open.ezviz.com/K22834441/1.hd.live\"> Test</option>";
            System.out.println(" html "+ htmlCam);
        }  else {
            htmlCam += " <option value=\"https://qrvn.vn/live/22110901/\"> Test</option>";
            System.out.println(" html "+ htmlCam);
        }
       /* json = json.replaceAll("\"", "\\\'");
        System.out.println("json "+ json);*/
        return ok(htmlCam);
    }


    public static Result Khachang(){
        return ok(views.html.khachhang.render(""));
    }

// ------------------------Lái xe-----------------------




    public static Result Dangkyxe() {

        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }

        return ok(Dangkyxe.render("", info));
    }

    public static Result Dangkyxe_Post() {
        DynamicForm requestData = Form.form().bindFromRequest();

        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        int funcId = -47848;
        String idViVimass = session().get(Service.KEY_PHONE_ID);
        String token = requestData.get("token");
        int loaiXe = Integer.valueOf(requestData.get("loaixe"));
        String bangLaiXe = requestData.get("imgdata2");
        String hangXe = requestData.get("hangXe");
        String model = requestData.get("hangXe");
        String bienSoXe = requestData.get("bienSoXe");
        int soChoNgoi = Integer.valueOf(requestData.get("soChoNgoi"));

        int namSanXuat=0;
        if (!requestData.get("namSanXuat").equals("")){
             namSanXuat = Integer.valueOf(requestData.get("namSanXuat"));
        }
        String linkAnhXe1 = requestData.get("imgdata3");
        String linkAnhXe2 = requestData.get("imgdata1");
        long timeRequest= new Date().getTime();
        int hinhThucPhatToaDo = 1;
        String idThietBiVietMap = "";
        String checkSum = Utility.getMD5("38473489t3894t94r" + funcId + idViVimass + token + timeRequest+ loaiXe + bangLaiXe + hangXe + model + bienSoXe + soChoNgoi + namSanXuat );


        ResponseMessage responseMessage = (ResponseMessage) CallService.dangkyxe(funcId,idViVimass,token,timeRequest,loaiXe,bangLaiXe,hangXe,model,bienSoXe,soChoNgoi,namSanXuat,linkAnhXe1,linkAnhXe2,hinhThucPhatToaDo,idThietBiVietMap,checkSum);


            if(responseMessage.getMsgCode()==1&&responseMessage!=null)
            {
                DangkyxeEntity response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),DangkyxeEntity.class);
                System.out.println("thành công "+ responseMessage.getResult());
                String json = new Gson().toJson(response);

                json = json.replaceAll("\"", "\\\'");

                return ok("OK___" + json);
            }
            else
            {
                return ok("FAIL");
            }
        }
    public static Result Dangkyxemay_Post() {
        DynamicForm requestData = Form.form().bindFromRequest();

        ThongTinCaNhan info = new ThongTinCaNhan();
        if (session().get(Service.KEY_DN) != null && session().get(Service.KEY_DN) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        } else if (session().get(Service.KEY_PHONE_ID) != null && session().get(Service.KEY_PHONE_ID) != "") {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }
        int funcId = -47848;
        String idViVimass = session().get(Service.KEY_PHONE_ID);
        String token = requestData.get("token");
        int loaiXe = 2;
        String bangLaiXe = requestData.get("imgdata2");
        String hangXe = requestData.get("hangXe");
        String model = requestData.get("hangXe");
        String bienSoXe = requestData.get("bienSoXe");
        int soChoNgoi =2;

        int namSanXuat=0;
        if (!requestData.get("namSanXuat").equals("")){
            namSanXuat = Integer.valueOf(requestData.get("namSanXuat"));
        }
        String linkAnhXe1 = requestData.get("imgdata3");
        String linkAnhXe2 = requestData.get("imgdata1");
        long timeRequest= new Date().getTime();
        int hinhThucPhatToaDo = 1;
        String idThietBiVietMap = "";
        String checkSum = Utility.getMD5("38473489t3894t94r" + funcId + idViVimass + token + timeRequest+ loaiXe + bangLaiXe + hangXe + model + bienSoXe + soChoNgoi + namSanXuat );


        ResponseMessage responseMessage = (ResponseMessage) CallService.dangkyxe(funcId,idViVimass,token,timeRequest,loaiXe,bangLaiXe,hangXe,model,bienSoXe,soChoNgoi,namSanXuat,linkAnhXe1,linkAnhXe2,hinhThucPhatToaDo,idThietBiVietMap,checkSum);


        if(responseMessage.getMsgCode()==1&&responseMessage!=null)
        {
            DangkyxeEntity response = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()),DangkyxeEntity.class);
            System.out.println("thành công "+ responseMessage.getResult());
            String json = new Gson().toJson(response);

            json = json.replaceAll("\"", "\\\'");

            return ok("OK___" + json);
        }
        else
        {
            return ok("FAIL");
        }
    }



//    ---------------------Bài viết------------------------

    public static Result huongdandangkytaikhoan() {
        String html_baiviet = "";
        html_baiviet = getHtmlBaiViet("1666862838071wmik9");
        return ok(dangkytaikhoan.render("VTaxi- Đăng ký tài khoản ", html_baiviet));

    }
    public static Result huongdandvgoixe() {
        String html_baiviet = "";
        html_baiviet = getHtmlBaiViet("1666580875445wx61a");
        return ok(huongdandvgoixe.render("VTaxi- Dịch vụ ", html_baiviet));

    }
    public static Result huongdandangkylaixe() {
        String html_baiviet = "";
        html_baiviet = getHtmlBaiViet("1666864840913zczhj");
        return ok(dangkylaixe.render("VTaxi- Đăng ký lái xe ", html_baiviet));

    }
    public static Result huongdandangkyxe() {
        String html_baiviet = "";
        html_baiviet = getHtmlBaiViet("1666868178314y05nl");
        return ok(dangkyxe.render("VTaxi- Đăng ký xe ", html_baiviet));

    }

    public static String getHtmlBaiViet(String idBaiViet) {
        String html = "";
        String langId = "1";

        if (session().get("lang") == null || session().get("lang") == "") {
            session().put("lang", "vi");
        } else {
            if (session().get("lang").equals("en")) {
                langId = "2";
            }
        }
        ResponseMessage responseMessage = (ResponseMessage) CallService.laychitietbaiviet(idBaiViet, langId);
        if (responseMessage != null && responseMessage.getMsgCode() == 1) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            baivietEntiy baiViet = gson.fromJson(gson.toJson(responseMessage.getResult()), baivietEntiy.class);
            if (baiViet != null) {
                Logger.info("gen bai viet gioi thieu= " + gson.toJson(baiViet));
                if (langId.equals("2")) {
                    html += "<div style=\"width: 100%;max-width: 100%;margin: 0;display: block;text-align: center;\">\n" +
                            "<h6 class=\"heading\" style=\"font-weight: bold;\">" + controllers.Utility.VMdecodeBase64(baiViet.getTitle_en()) + "</h6>\n" +
                            "</div>\n" +
                            "<div class=\"group\">\n";
                    String img = baiViet.getImg_en();
                    if (idBaiViet.equals("1622544846735wosyx")) {
                        img = "";
                    }
                    if (img != null && img.length() > 5) {
                        Logger.info("img_en = " + img);

                        html += "<div class=\"w-100\">\n" +
                                controllers.Utility.VMdecodeBase64(baiViet.getContent_en()) +
                                "</div>\n" +
                                "</div>\n" +
                                "<div class=\"clear\"></div>";
                    } else {
                        html += "<div class=\"w-100\">\n" +
                                controllers.Utility.VMdecodeBase64(baiViet.getContent_en()) +
                                "</div>\n" +
                                "</div>\n" +
                                "<div class=\"clear\"></div>";
                    }
                } else {
                    html += "<div style=\"width: 100%;max-width: 100%;margin: 0;display: block;text-align: center;\">\n" +
                            "<h6 class=\"heading\" style=\"font-weight: bold;\">" + controllers.Utility.VMdecodeBase64(baiViet.getTitle_vi()) + "</h6>\n" +
                            "</div>\n" +
                            "<div class=\"group\">\n";
                    String img = baiViet.getImg_vi();
                    if (idBaiViet.equals("1622544846735wosyx")) {
                        img = "";
                    }
                    if (img != null && img.length() > 5) {

                        html += "<div  class=\"w-100\">\n" +
                                controllers.Utility.VMdecodeBase64(baiViet.getContent_vi()) +
                                "</div>\n" +
                                "</div>\n" +
                                "<div class=\"clear\"></div>";
                    } else {
                        html += "<div class=\"w-100\" >\n" +
                                controllers.Utility.VMdecodeBase64(baiViet.getContent_vi()) +
                                "</div>\n" +
                                "</div>\n" +
                                "<div class=\"clear\"></div>";
                    }

                }
            }
        }
        if (html.length() > 10) {
            System.out.println("ok2conde");
        }
        return html;
    }

//  Audio
    public static Result getAudio() {
        return ok(views.html.Audio.render("Audio"));

    }

}
