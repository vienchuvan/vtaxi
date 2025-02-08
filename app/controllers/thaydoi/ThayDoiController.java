package controllers.thaydoi;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import controllers.CallService;
import controllers.TaiKhoanThuongDungObject;
import controllers.account.Service;
import controllers.account.ThongTinCaNhan;
import controllers.doanhnghiep.DN_SERVICE_CALL;
import controllers.modal.lichChuyenTienEntity;
import controllers.tools.ResponseMessage;
import controllers.tools.Utility;
import controllers.tools.listServices;
import entity.*;
import play.Logger;
import play.api.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.account.thaydoithongtin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/9/22
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThayDoiController extends Controller {
    private static List<TaiKhoanThuongDungObject> list;
    private static ArrayList<String> dsAnh;
    private static String sDuongDanLinkAnh = "";
    public static Result getAccountInfo()
    {

        ThongTinCaNhan info = new ThongTinCaNhan();
        if(session().get(Service.KEY_DN)!=null&&session().get(Service.KEY_DN)!="")
        {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        }
        else
        {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }

        if(info.getId().toLowerCase().startsWith("dn_")||info.getId().toLowerCase().startsWith("d")){
            System.out.println("==================== DN ==========================");
        }else {
            System.out.println("==================== CN ==========================");
        }
        String coutries = "";
        List<AGRChiNhanh> listContries = Utility.getListContries();
        for(AGRChiNhanh item : listContries)
        {
            if(item.getValue().equals("VN"))
            {
                coutries += "<option selected value=\""+item.getValue()+"\">" + item.getText() + "</option>";
            }
            else
            {
                coutries += "<option value=\""+item.getValue()+"\">" + item.getText() + "</option>";
            }
        }

        String province = getListTinhThanh();
        double vido= 0;
        double kinhdo = 0;

        String id = info.getId();
        int funcId = 1;
        long timeRequest = new Date().getTime();
        String checkSum = Utility.getMD5("kjshfkdlfjow958924jkfhEFWRRJXARY" + timeRequest + id + funcId);
        ResponseMessage responseMessage2 = (ResponseMessage) CallService.laytoado(checkSum, timeRequest, id, funcId);
        System.out.println("Đậu xanh "+ new Gson().toJson(responseMessage2.getResult()));
        LocationResponse location = new LocationResponse();
        if(responseMessage2!=null&&responseMessage2.getMsgCode()==1)

        {
            location = new Gson().fromJson( new Gson().toJson(responseMessage2.getResult()),LocationResponse.class);
            vido = location.getLat();
            kinhdo = location.getLng();

        }
        info.setLat(info.getLat());
        info.setLng(info.getLng());
        String listBank = "";
        if(info.getAccBank()!=null&&info.getAccBank()!="")
        {
            String bankCode = "";
            try {
                if(info.getAccBank().contains("-"))
                {
                    bankCode = info.getAccBank().split("-")[0];
                }
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }



            List<Bank> bankList = getListBank();
            listBank += "<option id=\"9999\">Chọn ngân hàng</option>";
            for(Bank bank : bankList)
            {
                if(bank.getSMS().equals(bankCode))
                {
                    listBank += "<option selected id=\""+bank.getID()+"\" value=\""+bank.getSMS()+"\">" + bank.getNAME_VI()+"</option>";
                }
                else
                {
                    listBank += "<option id=\""+bank.getID()+"\" value=\""+bank.getSMS()+"\">" + bank.getNAME_VI()+"</option>";
                }
            }
        }
        else
        {
            List<Bank> bankList = getListBank();
            listBank += "<option id=\"9999\">Chọn ngân hàng</option>";
            for(Bank bank : bankList)
            {
                listBank += "<option id=\""+bank.getID()+"\" value=\""+bank.getSMS()+"\">" + bank.getNAME_VI()+"</option>";
            }
        }

        String htmlLichChuyenTien = "";
        lichChuyenTienEntity lichChuyenTien = new lichChuyenTienEntity();
        if(info.getCauHinhChuyenTienTuDong()!=null)
        {
            lichChuyenTien = info.getCauHinhChuyenTienTuDong();
        }
        htmlLichChuyenTien += "<input type=\"hidden\" name=\"id_cauHinh\" id=\"id_cauHinh\" value=\""+lichChuyenTien.id+"\">";
        if(lichChuyenTien.time1>0)
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\" class=\"form-check-input\" value=\"990800\" name=\"time1\" id=\"time1\" checked>\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time1\">8 giờ sáng</label>\n" +
                            "   </div>\n";
        }
        else
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\" class=\"form-check-input\" value=\"990800\" name=\"time1\" id=\"time1\">\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time1\">8 giờ sáng</label>\n" +
                            "   </div>\n";
        }
        if (lichChuyenTien.time2>0)
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\" class=\"form-check-input\" value=\"991100\" name=\"time2\" id=\"time2\" checked>\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time2\">11 giờ trưa</label>\n" +
                            "   </div>\n";
        }
        else
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\"  class=\"form-check-input\" value=\"991100\" name=\"time2\" id=\"time2\">\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time2\">11 giờ trưa</label>\n" +
                            "   </div>\n";
        }
        if (lichChuyenTien.time3>0)
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\"  class=\"form-check-input\"  value=\"991400\" name=\"time3\" id=\"time3\" checked>\n" +
                            "       <label class=\"form-check-label  ms-1\"  for=\"time3\">2 giờ chiều</label>\n" +
                            "   </div>\n";
        }
        else
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\"  class=\"form-check-input\" value=\"991400\" name=\"time3\" id=\"time3\">\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time3\">2 giờ chiều</label>\n" +
                            "   </div>\n";
        }
        if (lichChuyenTien.time4>0)
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\"  class=\"form-check-input\" value=\"991700\" name=\"time4\" id=\"time4\" checked>\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time4\">5 giờ chiều</label>\n" +
                            "   </div>\n";
        }
        else
        {
            htmlLichChuyenTien +=
                    "   <div class=\"form-check d-flex align-items-center w-50\">\n" +
                            "       <input type=\"checkbox\"  class=\"form-check-input\" value=\"991700\" name=\"time4\" id=\"time4\">\n" +
                            "       <label class=\"form-check-label  ms-1\" for=\"time1\">5 giờ chiều</label>\n" +
                            "   </div>\n";
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
        return ok(thaydoithongtin.render("Thông tin",info,province,coutries, listBank,htmlLichChuyenTien,vido,kinhdo));
    }

    public static List<Bank> getListBank()
    {
        String line = "";
        StringBuilder builder = new StringBuilder();
        InputStream is = Play.current().classloader().getResourceAsStream("bank.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);   // add everything to StringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String buider = builder.toString();

        Type BankType = new TypeToken<List<Bank>>(){}.getType();

        List<Bank> bankList = new Gson().fromJson(buider,BankType);
        if(bankList!=null&&bankList.size()>0)
        {

            return bankList;
        }
        else
        {
            return null;
        }

    }

    public static String getListTinhThanh()
    {
        String line = "";
        StringBuilder builder = new StringBuilder();
        InputStream is = Play.current().classloader().getResourceAsStream("province.txt");
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

    public static Result checkTKNH()
    {
        String user = session().get(Service.KEY_PHONE_ID);
        String session = session().get(Service.KEY_SESSION_ID);
        String soTK = request().getQueryString("soTK");
        String maNganHang = request().getQueryString("maNH");
        String soThe = "";
        ResponseMessage responseMessage = (ResponseMessage) CallService.checkTKNH(user, session, soTK, maNganHang, soThe);
        if(responseMessage!=null)
        {
            if(responseMessage.getMsgCode()==1&&responseMessage.getResult()!=null)
            {
                return ok(responseMessage.getResult().toString());
            }
        }
        return ok("");
    }
    @BodyParser.Of(value = BodyParser.FormUrlEncoded.class, maxLength = 10 * 1024 * 1024)
    public static Result editAccountInfo()
    {
        ThongTinCaNhan info;
        if(session().get(Service.KEY_DN)!=null&&session().get(Service.KEY_DN)!="")
        {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN));
        }
        else
        {
            info = CallService.layThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID));
        }

        if(info.getId().toLowerCase().startsWith("dn_")||info.getId().toLowerCase().startsWith("d")){
            System.out.println("==================== DN 1 ==========================");
        }else {
            System.out.println("==================== CN 1 ==========================");
        }

        DynamicForm requestData = Form.form().bindFromRequest();
        String username = session().get(Service.KEY_PHONE_ID);
        ResponseMessage objResponse = null;
        String birthDay = "";
        String DateIdCard = "";
        lichChuyenTienEntity lichChuyenTien = new lichChuyenTienEntity();
        ThongTinCaNhan objInfo = new ThongTinCaNhan();
        String anhcanhan = "";


        if(username!=null&&!username.equals(""))
        {
            if(username.toLowerCase().startsWith("dn_")||username.toLowerCase().startsWith("d"))
            {

                if( requestData.get("imgdata3")!=null&& requestData.get("imgdata3")!="")
                {
                    String uploadData = "";
                    if(requestData.get("imgdata3").contains(","))
                    {
                        uploadData = requestData.get("imgdata3").split(",")[1];
                        ResponseMessage responseMessage_anh3 = (ResponseMessage) DN_SERVICE_CALL.uploadAnhDangKy("imagedata3", uploadData, requestData.get("companyCode") );
                        if(responseMessage_anh3!=null&&responseMessage_anh3.getMsgCode()==1)
                        {
                            anhcanhan = responseMessage_anh3.getResult().toString();
                        }
                    }
                    else
                    {
                        anhcanhan = info.getAvatar();
                    }
                }
                String anh1 = "";
                if( requestData.get("imgdata1")!=null&& requestData.get("imgdata1")!="")
                {
                    String uploadData = "";
                    if(requestData.get("imgdata1").contains(","))
                    {
                        uploadData = requestData.get("imgdata1").split(",")[1];
                        ResponseMessage responseMessage_anh1 = (ResponseMessage) DN_SERVICE_CALL.uploadAnhDangKy("imagedata1", uploadData, requestData.get("companyCode"));
                        if(responseMessage_anh1!=null&&responseMessage_anh1.getMsgCode()==1)
                        {
                            anh1 = responseMessage_anh1.getResult().toString();
                            Logger.info("image1 sau upload" + anh1);
                        }
                    }
                    else
                    {
                        anh1 = info.getImageCompany1();
                        Logger.info("tu set anh1" + anh1);
                    }
                }
                else
                {
                    return ok("Vui lòng chọn ảnh ĐKKD");
                }
                String anh2 = "";
                if(requestData.get("imgdata2")!=null&&requestData.get("imgdata2")!="")
                {
                    String uploadData = "";
                    if(requestData.get("imgdata2").contains(","))
                    {
                        uploadData =  requestData.get("imgdata2").split(",")[1];
                        ResponseMessage responseMessage_anh2 = (ResponseMessage) DN_SERVICE_CALL.uploadAnhDangKy("imagedata2", uploadData, requestData.get("companyCode"));
                        if(responseMessage_anh2!=null&&responseMessage_anh2.getMsgCode()==1)
                        {
                            anh2 = responseMessage_anh2.getResult().toString();
                            Logger.info("image2 sau upload" + anh2);
                        }
                    }
                    else
                    {
                        anh2 = info.getImageCompany2();
                    }
                }
                  objInfo.setId(requestData.get("id_dn"));

                  objInfo.setAvatar(anhcanhan);
                  objInfo.setImageCompany1(anh1);
                  objInfo.setImageCompany2(anh2);

//                  objInfo.setMaSoThue(requestData.get("maSoThue"));
                  objInfo.setTenDoanhNghiep(requestData.get("companyName"));
                  objInfo.setDiaChiDoanhNghiep(requestData.get("home"));
                  objInfo.setNameAlias(requestData.get("nameAlias"));
                  objInfo.setCompanyCode(requestData.get("companyCode"));
                  objInfo.setCompanyName(requestData.get("companyName"));
                  objInfo.setNameRepresent(requestData.get("nameRepresent"));
                  objInfo.setWalletId(requestData.get("walletId"));
                  objInfo.setEmail(requestData.get("email_dn"));
                  objInfo.setDsLap(requestData.get("dsLap"));
                  objInfo.setTypeAuthenticate(0);
                  objInfo.setDsDuyet(requestData.get("dsDuyet"));
                  objInfo.setToken(requestData.get("token"));
                  objInfo.setOtpConfirm(requestData.get("otpConfirm"));
                  objInfo.setLat(Double.parseDouble(requestData.get("lat")));
                  objInfo.setLng(Double.parseDouble(requestData.get("lng")));
                objInfo.setVisiable(Integer.parseInt(requestData.get("visiable")));
                if(!requestData.get("home").equals(""))
                {
                      objInfo.setHome(requestData.get("home"));
                }
                else
                {
                      objInfo.setHome(requestData.get("home"));
                }

                // tk rut tien
                TaiKhoanThuongDungObject TKTDObject = new TaiKhoanThuongDungObject();

                  objInfo.settKRutTien(TKTDObject);

                String bankCode = requestData.get("maNganHang");
                String bankNumber = requestData.get("bankNumber");
//                String AccOwnerName = requestData.get("chuTK");
                if(bankCode!=""&&bankNumber!="")
                {
                      objInfo.setAccBank(bankCode + "-" + bankNumber);
                }
                /*lich chuyen tien theo phien*/
                long time1 = 0;
                long time2 = 0;
                long time3 = 0;
                long time4 = 0;
                if(requestData.get("time1")!=null&&requestData.get("time1")!="")
                {
                    time1 = Long.valueOf(requestData.get("time1"));
                }
                if(requestData.get("time2")!=null&&requestData.get("time2")!="")
                {
                    time2 = Long.valueOf(requestData.get("time2"));
                }
                if(requestData.get("time3")!=null&&requestData.get("time3")!="")
                {
                    time3 = Long.valueOf(requestData.get("time3"));
                }
                if(requestData.get("time4")!=null&&requestData.get("time4")!="")
                {
                    time4 = Long.valueOf(requestData.get("time4"));
                }
//                String token =  requestData.get("token");
                String companyCode = requestData.get("companyCode");
                if(companyCode.indexOf("dn")==-1)
                {
                    companyCode = "dn_"+companyCode;
                }
//                String user = info.getWalletLogin();
                String id = requestData.get("id_cauHinh");
                if(time1!=0&&time2!=0&&time3!=0&&time4!=0)
                {
                    id = info.getId();
                }


                lichChuyenTien.companyCode = companyCode;
                lichChuyenTien.id = id;
                lichChuyenTien.time1 = time1;
                lichChuyenTien.time2 = time2;
                lichChuyenTien.time3 = time3;
                lichChuyenTien.time4 = time4;

                  objInfo.setCauHinhChuyenTienTuDong(lichChuyenTien);

               }
            else
            {

                sDuongDanLinkAnh = "";
                if(dsAnh == null)
                    dsAnh = new ArrayList<String>();
                dsAnh.clear();
                for(int i = 1; i < 6; i++)
                {
                    dsAnh.add(locDuLieuAnhBase64(requestData.get("imgdata" + i)));
                }
                uploadAnhBase64(0);
                  objInfo.setId(requestData.get("id"));
//                objInfo.setLat(Double.parseDouble(requestData.get("lat")));
//                objInfo.setLng(Double.parseDouble(requestData.get("lng")));
//                objInfo.setVisiable(Integer.parseInt(requestData.get("visiable")));
                if(sDuongDanLinkAnh!="")
                {
                    String[] link = sDuongDanLinkAnh.split(";");
                    if(link.length>0&&link[0].length()>0)
                    {
                          objInfo.setLinkFrontIdCard(link[0]);
                    }
                    else
                    {
                          objInfo.setLinkFrontIdCard(info.getLinkFrontIdCard());
                    }
                    if(link.length>1&&link[1].length()>0)
                    {
                          objInfo.setLinkBackIdCard(link[1]);
                    }
                    else
                    {
                          objInfo.setLinkBackIdCard(info.getLinkBackIdCard());
                    }
                    if(link.length>2&&link[2].length()>0)
                    {
                          objInfo.setAvatar(link[2]);
                    }
                    else
                    {
                          objInfo.setAvatar(info.getAvatar());
                    }
                    if(link.length>3&&link[3].length()>0)
                    {
                          objInfo.setLinkSignature(link[3]);
                    }
                    else
                    {
                          objInfo.setLinkSignature(info.getLinkSignature());
                    }
                    if(link.length>3&&link[4].length()>0)
                    {
                          objInfo.setImageBHYT(link[4]);
                    }
                    else
                    {
                          objInfo.setImageBHYT(info.getImageBHYT());
                    }
                }
                else
                {
                      objInfo.setLinkFrontIdCard(info.getLinkFrontIdCard());
                      objInfo.setLinkBackIdCard(info.getLinkBackIdCard());
                      objInfo.setAvatar(info.getAvatar());
                      objInfo.setLinkSignature(info.getLinkSignature());
                      objInfo.setImageBHYT(info.getImageBHYT());
                }


                  objInfo.setNameAlias(requestData.get("nameAlias"));
                  objInfo.setEmail(requestData.get("email"));
                  objInfo.setPhoneAuthenticate(requestData.get("phoneAuthenticate"));
                objInfo.setTypeAuthenticate(0);
                if(!requestData.get("acc_name").equals(""))
                {
                      objInfo.setAcc_name(requestData.get("acc_name"));
                }
                else
                {
                      objInfo.setAcc_name(requestData.get("acc_name"));
                }
                  objInfo.setIdCard(requestData.get("idCard"));
                if(!requestData.get("birthday").equals(""))
                {
                    String[] str = requestData.get("birthday").split("-");
                    if(str.length>0)
                    {
                        birthDay = str[0]+"-"+str[1]+"-"+str[2];
                    }
                      objInfo.setBirthday(birthDay);
                }
//                objInfo.setBirthday(requestData.get("birthday"));
                  objInfo.setHome(requestData.get("home"));
                System.out.println("gioiTinh " + requestData.get("gioiTinh") + "   quocGia " + requestData.get("quocGia"));
                String gioiTinh = "";
                if(requestData.get("gioiTinh")!="")
                {
                    gioiTinh = "___gt:"+requestData.get("gioiTinh");
                    System.out.println("nam " + gioiTinh);
                }
                String quocGia = "";
                if(requestData.get("quocGia")!="")
                {
                    quocGia = "___qg:"+requestData.get("quocGia");
                }
                  objInfo.setHome(  objInfo.getHome()+gioiTinh+quocGia);

                if(!requestData.get("dateIdCard").equals(""))
                {
                    String[] str = requestData.get("dateIdCard").split("-");
                    if(str.length>0)
                    {
                        DateIdCard = str[0] + "-" + str[1] + "-" + str[2];
                    }
                      objInfo.setDateIdCard(DateIdCard);
                }
//                objInfo.setDateIdCard(requestData.get("dateIdCard"));
                  objInfo.setToken(requestData.get("token"));
                  objInfo.setOtpConfirm(requestData.get("otpConfirm"));
                  objInfo.setPlaceIdCard(requestData.get("placeIdCard"));
                int hienThiNoiDungThanhToanQR = 0;
                if(requestData.get("hienThiNoiDungThanhToanQR")!=null&&requestData.get("hienThiNoiDungThanhToanQR")!="")
                {
                    hienThiNoiDungThanhToanQR = Integer.valueOf(requestData.get("hienThiNoiDungThanhToanQR"));
                }
                  objInfo.setHienThiNoiDungThanhToanQR(hienThiNoiDungThanhToanQR);

                String bankCode = requestData.get("maNganHang");
                String bankNumber = requestData.get("soTaiKhoan");
                if(bankCode!=""&&bankNumber!="")
                {
                      objInfo.setAccBank(bankCode+"-"+bankNumber);
                }
                  objInfo.setValueBHYT(requestData.get("valueBHYT"));
                  objInfo.setMaSoThue(requestData.get("maSoThue"));
                  objInfo.setTenDoanhNghiep(requestData.get("tenDoanhNghiep"));
                  objInfo.setDiaChiDoanhNghiep(requestData.get("diaChiDoanhNghiep"));
            }
            objResponse = (ResponseMessage) CallService.sua_thong_tin_ca_nhan(objInfo);
            if(objResponse!=null)
            {
                if(objResponse.getMsgCode()== 1)
                {
                    if(objInfo.getId().toLowerCase().startsWith("dn_")||objInfo.getId().toLowerCase().startsWith("d"))
                    {
                        info.setIsToken(info.getIsToken());
                        info.setId(requestData.get("id_dn"));
                        info.setNameAlias(requestData.get("nameAlias"));
                        session().put(Service.KEY_TEN_DAI_DIEN, requestData.get("nameAlias"));
                        info.setImageCompany1(objInfo.getImageCompany1());
                        info.setImageCompany2(objInfo.getImageCompany2());
                        info.setCompanyCode(requestData.get("companyCode"));
                        info.setCompanyName(requestData.get("companyName"));
                        info.setNameRepresent(requestData.get("nameRepresent"));
                        info.setWalletId(requestData.get("walletId"));
                        info.setEmail(requestData.get("email_dn"));
                        info.setDsLap(requestData.get("dsLap"));
                        info.setTypeAuthenticate(0);
                        info.setDsDuyet(requestData.get("dsDuyet"));
                        info.setToken(requestData.get("token"));
                        info.setOtpConfirm(requestData.get("otpConfirm"));
                        info.setAvatar(anhcanhan);
                        if(anhcanhan.contains("http"))
                        {
                            session().put(Service.KEY_LINK_ANH_DAI_DIEN, anhcanhan);
                        }
                        else if(anhcanhan.contains("https"))
                        {
                            session().put(Service.KEY_LINK_ANH_DAI_DIEN, anhcanhan);
                        }
                        else
                        {
                            session().put(Service.KEY_LINK_ANH_DAI_DIEN, listServices.SERVICE_LAY_ANH+anhcanhan);
                        }
                        info.setLat(Double.parseDouble(requestData.get("lat")));
                        info.setLng(Double.parseDouble(requestData.get("lng")));
                        info.setVisiable(Integer.parseInt(requestData.get("visiable")));
                        info.setCauHinhChuyenTienTuDong(lichChuyenTien);
                        System.out.println("gioiTinh " + requestData.get("gioiTinh") + "   quocGia " + requestData.get("quocGia"));
                        String gioiTinh = "";

                        if(requestData.get("gioiTinh")!="")
                        {
                            gioiTinh = "___gt:"+requestData.get("gioiTinh");
                        }
                        String quocGia = "";
                        if(requestData.get("quocGia")!="")
                        {
                            quocGia = "___qg:"+requestData.get("quocGia");
                        }
                        info.setHome(  objInfo.getHome()+gioiTinh+quocGia);


                        TaiKhoanThuongDungObject TKTDObject = new TaiKhoanThuongDungObject();
                        System.out.println("ojbject: "+TKTDObject);
                        TKTDObject.setBankNumber(requestData.get("bankNumber"));
                        TKTDObject.setAccOwnerName(requestData.get("accOwnerName"));
                        TKTDObject.setId(requestData.get("idtkrt"));
                        TKTDObject.setType(4);
                        TKTDObject.setPhoneOwner(requestData.get("phoneAuthenticate"));
                        TKTDObject.setAliasName("");
                        if(requestData.get("bankId")!=null&&requestData.get("bankId")!="")
                        {
                            TKTDObject.setBankId(Integer.valueOf(requestData.get("bankId")));
                        }

//                        TKTDObject.setTenChiNhanh(requestData.get("tenChiNhanh"));
//                        TKTDObject.setMaChiNhanh(requestData.get("maChiNhanh"));
                        info.settKRutTien(TKTDObject);

//                        info.setMaSoThue(requestData.get("maSoThue"));
//                        info.setTenDoanhNghiep(requestData.get("tenDoanhNghiep"));
//                        info.setDiaChiDoanhNghiep(requestData.get("diaChiDoanhNghiep"));
                        String bankCode = requestData.get("maNganHang");
                        String bankNumber = requestData.get("soTaiKhoan");
                        String AccOwnerName = requestData.get("chuTK");
                        if(bankCode!=""&&bankNumber!="")
                        {
                            info.setAccBank(bankCode+"-"+bankNumber);
                        }
                        /*info.setHienThiNoiDungThanhToanQR(Integer.valueOf(requestData.get("hienThiNoiDungThanhToanQR")));*/
                    }
                    else
                    {

                        info.setIsToken(info.getIsToken());
                        info.setId(requestData.get("id"));
              /*          info.setLat(Double.parseDouble(requestData.get("lat")));
                        info.setLng(Double.parseDouble(requestData.get("lng")));*/
//                        info.setVisiable(Integer.parseInt(requestData.get("visiable")));
                        if(sDuongDanLinkAnh!="")
                        {
                            String[] link = sDuongDanLinkAnh.split(";");
                            if(link.length>0&&link[0].length()>0)
                            {
                                info.setLinkFrontIdCard(link[0]);
                            }
                            else
                            {
                                info.setLinkFrontIdCard(info.getLinkBackIdCard());
                            }
                            if(link.length>1&&link[1].length()>0)
                            {
                                info.setLinkBackIdCard(link[1]);
                            }
                            else
                            {
                                info.setLinkBackIdCard(info.getLinkBackIdCard());
                            }
                            if(link.length>2&&link[2].length()>0)
                            {
                                info.setAvatar(link[2]);
                            }
                            else
                            {
                                info.setAvatar(info.getAvatar());
                            }
                            if(link.length>3&&link[3].length()>0)
                            {
                                info.setLinkSignature(link[3]);
                            }
                            else
                            {
                                info.setLinkSignature(info.getLinkSignature());
                            }
                            if(link.length>3&&link[4].length()>0)
                            {
                                info.setImageBHYT(link[4]);
                            }
                            else
                            {
                                info.setImageBHYT(info.getImageBHYT());
                            }
                        }
                        else
                        {
                            info.setLinkFrontIdCard(info.getLinkFrontIdCard());
                            info.setLinkBackIdCard(info.getLinkBackIdCard());
                            info.setAvatar(info.getAvatar());
                            info.setLinkSignature(info.getLinkSignature());
                            info.setImageBHYT(info.getImageBHYT());
                        }
                        info.setNameAlias(requestData.get("nameAlias"));
                        if(requestData.get("nameAlias")!=null&&requestData.get("nameAlias")!="")
                        {
                            session().put(Service.KEY_TEN_DAI_DIEN,requestData.get("nameAlias"));
                        }
                        else
                        {
                            session().put(Service.KEY_TEN_DAI_DIEN, Utility.HiddenPhone(requestData.get("id")));
                        }
                        info.setEmail(requestData.get("email"));
                        info.setAcc_name(requestData.get("acc_name"));
                        info.setIdCard(requestData.get("idCard"));
                        info.setBirthday(birthDay);
                        System.out.println("gioiTinh " + requestData.get("gioiTinh") + "   quocGia " + requestData.get("quocGia"));
                        String gioiTinh = "";
                        if(requestData.get("gioiTinh")!="")
                        {
                            gioiTinh = "___gt:"+requestData.get("gioiTinh");
                        }
                        String quocGia = "";
                        if(requestData.get("quocGia")!="")
                        {
                            quocGia = "___qg:"+requestData.get("quocGia");
                        }
                        info.setHome(  objInfo.getHome()+gioiTinh+quocGia);
                        info.setDateIdCard(DateIdCard);
                        info.setPlaceIdCard(requestData.get("placeIdCard"));
                        info.setPhoneAuthenticate(requestData.get("phoneAuthenticate"));
                        info.setAccBank(requestData.get("maNganHang")+"-"+requestData.get("soTaiKhoan"));
                        TaiKhoanThuongDungObject TKTDObject = new TaiKhoanThuongDungObject();
                        TKTDObject.setBankNumber(requestData.get("bankNumber"));
                        TKTDObject.setAccOwnerName(requestData.get("accOwnerName"));
                        TKTDObject.setId(requestData.get("idtkrt"));
                        TKTDObject.setType(4);
                        TKTDObject.setPhoneOwner(requestData.get("phoneAuthenticate"));
                        TKTDObject.setAliasName("");
                        if(requestData.get("bankId")!=null&&requestData.get("bankId")!="")
                        {
                            TKTDObject.setBankId(Integer.valueOf(requestData.get("bankId")));
                        }

                        TKTDObject.setTenChiNhanh(requestData.get("tenChiNhanh"));
                        TKTDObject.setMaChiNhanh(requestData.get("maChiNhanh"));
                        info.settKRutTien(TKTDObject);
//                        info.setHienThiNoiDungThanhToanQR(Integer.valueOf(requestData.get("hienThiNoiDungThanhToanQR")));
                        info.setValueBHYT(requestData.get("valueBHYT"));
                        System.out.println("valueBHYT " + requestData.get("valueBHYT"));
                        info.setGioiTinh(Integer.parseInt(requestData.get("gioiTinh")));
                        info.setMaSoThue(requestData.get("maSoThue"));
                        info.setTenDoanhNghiep(requestData.get("tenDoanhNghiep"));
                        info.setDiaChiDoanhNghiep(requestData.get("diaChiDoanhNghiep"));
                    }
                    if(  objInfo.getId().toLowerCase().startsWith("dn_")||  objInfo.getId().toLowerCase().startsWith("d"))
                    {
                        CallService.luuThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID) + session().get(Service.KEY_DN), new Gson().toJson(info));
                    }
                    else
                    {

                        CallService.luuThongTin("AccInfo" + session().get(Service.KEY_PHONE_ID), new Gson().toJson(info));
                    }
                    return ok("SUCCESS:");
                }
                else
                {
                    return ok(objResponse.getMsgContent());
                }
            }
            else
            {
                return ok("FAIL");
            }
        }
        else
        {
            return ok("NotLogin");
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
    private static void uploadAnhBase64(int nIdex)
    {
        if(nIdex < dsAnh.size()) {
            String sDuLieuImage = dsAnh.get(nIdex);
            if(!sDuLieuImage.equals("")) {
                if(!sDuLieuImage.startsWith("vm/") && !sDuLieuImage.startsWith("http://") && !sDuLieuImage.startsWith("https://")) {
                    String sKetQua = CallService.upLoadAnh(session().get(Service.KEY_PHONE_ID), sDuLieuImage);
                    if (sKetQua != null && sKetQua.length() > 0 && Utility.isValidJSON(sKetQua)) {
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
}
