package controllers;

import com.google.gson.Gson;
import controllers.tools.ResponseMessage;
import entity.baiviet.baivietEntiy;
import entity.baiviet.danhmucBaiViet;
import play.Logger;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 1/4/23
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class baiVietController extends VtaxiController{

    public static Result loadBaiBaoTrangChu()
    {
        int width = 1366;
        if(request().getQueryString("w")!=null&&request().getQueryString("w")!="")
        {
            width = Integer.valueOf(request().getQueryString("w"));
            System.out.println("width " + width);
        }
        String langId = "1";
        if(session().get("lang")==null||session().get("lang")=="")
        {
            session().put("lang","vi");
        }
        else
        {
            if(session().get("lang").equals("en"))
            {
                langId = "2";
            }
        }
        Logger.info("language = " + session().get("lang"));
        // lay ds bai viet
        int offset = 0;
        int litmit = controllers.Utility.SoLuongBaiViet;
        String htmlTong = "";
        String idDanhMucTrangChu = "167238913032781c7d";
        ResponseMessage responseMessage = (ResponseMessage) CallService.laychitietdanhmuc(idDanhMucTrangChu,langId,offset,litmit);
        if(responseMessage!=null&&responseMessage.getMsgCode()==1)
        {
            danhmucBaiViet danhmucEntity = new Gson().fromJson(new Gson().toJson(responseMessage.getResult()), danhmucBaiViet.class);
            List<baivietEntiy> list = danhmucEntity.getListDoccument();
            if(list!=null&&list.size()>0)
            {
                Logger.info("list 1 = " + list.size());
                String pre = "<li>\n" +
                        "   <div class=\"group\">";
                String suff = "   </div>" +
                        "</li>";
                List<baivietEntiy> baivietEntiyList = new ArrayList<baivietEntiy>();
                for(baivietEntiy entiy:list)
                {
                    if(langId=="2")
                    {
                        if(entiy.getTitle_en() !=null&& entiy.getTitle_en().length()>0)
                        {
                            baivietEntiyList.add(entiy);
                        }
                    }
                    else
                    {
                        baivietEntiyList.add(entiy);
                    }
                }
                Logger.info("list 2 = " + baivietEntiyList.size());

                if(width<978)
                {
                    for(int i=0;i<baivietEntiyList.size();i++)
                    {
                        htmlTong += pre;
                        htmlTong += genHtmlSingle(baivietEntiyList.get(i),langId);
                        htmlTong += suff;
                    }
                }
                else
                {
                    if(baivietEntiyList.size()>4)
                    {
                        int count = 4;
                        List<baivietEntiy> subList = new ArrayList<baivietEntiy>();
                        for(int i = 0;i<baivietEntiyList.size();i+=4)
                        {
                            if(i<(baivietEntiyList.size()-4))
                            {
                                subList = baivietEntiyList.subList(i,count);
                                count += 4;
                                htmlTong += pre;
                                htmlTong += genHtml(subList,langId);
                                htmlTong += suff;
                            }
                            else
                            {
                                subList = baivietEntiyList.subList(i,baivietEntiyList.size());
                                htmlTong += pre;
                                htmlTong += genHtml(subList,langId);
                                htmlTong += suff;
                            }
                        }
                    }
                    else
                    {
                        htmlTong += pre;
                        htmlTong += genHtml(baivietEntiyList,langId);
                        htmlTong += suff;
                    }
                }
            }
        }
        return ok(htmlTong);
    }
    public static String genHtmlSingle(baivietEntiy entity,String langId)
    {
        String html = "";
        if(entity!=null)
        {
            if(langId.equals("2"))
            {
                if(entity.getTitle_en().length()>3)
                {
                    html += "<article class=\"one_quarter\">\n" +
                            "<div class=\"h-303px\">"+
                            "   <a class=\"imgover\" target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">\n";
                    if(entity.getId().equals("156758944473077968"))
                    {
                        html += "       <img style=\"max-width:75%;margin: 0 auto;\" src=\""+ entity.getImg_en() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"\">\n";
                    }
                    else
                    {
                        html += "       <img src=\""+ entity.getImg_en() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"\">\n";
                    }
                    html +=
                            "   </a>\n" +
                                    "</div><hr/>" +
                                    "   <div class=\"excerpt\">\n" +
                                    "       <h6 class=\"heading\" style=\"margin-top: 10px;font-size: 20px;\">"+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"</h6>\n" +
                                    "       <p style=\"max-height: 200px;overflow: hidden;\">"+ controllers.Utility.VMdecodeBase64(entity.getShortContent_en())+"...</p>\n" +
                                    "       <div><a target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">Read more <i class=\"fas fa-angle-right\"></i></a></div>\n" +
                                    "   </div>\n" +
                                    "</article>";
                }
            }
            else if(langId.equals("1"))
            {
                if(entity.getTitle_vi().length()>3)
                {
                    html += "<article class=\"one_quarter\">\n";
                    html += "<div class=\"h-303px\">"+
                            "   <a class=\"imgover\" target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">\n";
                    if(entity.getId().equals("156758944473077968"))
                    {
                        html += "       <img style=\"max-width:75%;margin: 0 auto;\" src=\""+ entity.getImg_vi() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"\">\n";
                    }
                    else
                    {
                        html += "       <img src=\""+ entity.getImg_vi() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"\">\n";
                    }
                    html += "   </a>\n" +
                            "</div><hr/>" +
                            "   <div class=\"excerpt\">\n" +
                            "       <h6 class=\"heading\" style=\"margin-top: 10px;font-size: 20px;\">"+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"</h6>\n" +
                            "       <p style=\"max-height: 200px;overflow: hidden;\">"+ controllers.Utility.VMdecodeBase64(entity.getShortContent_vi())+"...</p>\n" +
                            "       <div><a target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">Đọc tiếp <i class=\"fas fa-angle-right\"></i></a></div>\n" +
                            "   </div>\n" +
                            "</article>";

                }
            }
        }
        return html;
    }

    public static String genHtml(List<baivietEntiy> list,String langId)
    {
        String html = "";
        if(list!=null&&list.size()>0)
        {
            //Logger.info("list size = " + list.size());
            for(baivietEntiy entity:list)
            {
                if(langId.equals("2"))
                {
                    if(entity.getTitle_en().length()>3)
                    {
                        html += "<article class=\"one_quarter\">\n" +
                                "<div class=\"h-303px\">"+
                                "   <a class=\"imgover\" target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">\n";
                        if(entity.getId().equals("156758944473077968"))
                        {
                            html += "       <img style=\"max-width:75%;margin: 0 auto;\" src=\""+ entity.getImg_en() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"\">\n";
                        }
                        else
                        {
                            html += "       <img src=\""+ entity.getImg_en() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"\">\n";
                        }
                        html +=
                                "   </a>\n" +
                                        "</div> <hr/>" +
                                        "   <div class=\"excerpt\">\n" +
                                        "       <h6 class=\"heading\" style=\"margin-top: 10px;font-size: 20px;\">"+ controllers.Utility.VMdecodeBase64(entity.getTitle_en())+"</h6>\n" +
                                        "       <p style=\"max-height: 200px;overflow: hidden;\">"+ controllers.Utility.VMdecodeBase64(entity.getShortContent_en())+"...</p>\n" +
                                        "       <div><a target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">Read more <i class=\"fas fa-angle-right\"></i></a></div>\n" +
                                        "   </div>\n" +
                                        "</article>";
                    }
                }
                else if(langId.equals("1"))
                {
                    if(entity.getTitle_vi().length()>3)
                    {
                        html += "<article class=\"one_quarter\">\n";
                        html += "<div class=\"h-303px \">"+
                                "   <a class=\"imgover\" target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">\n";
                        if(entity.getId().equals("156758944473077968"))
                        {
                            html += "       <img style=\"max-width:75%;margin: 0 auto;\" src=\""+ entity.getImg_vi() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"\">\n";
                        }
                        else
                        {
                            html += "       <img src=\""+ entity.getImg_vi() +"\" alt=\""+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"\">\n";
                        }
                        html += "   </a>\n" +
                                "</div> <hr/>" +
                                "   <div class=\"excerpt\">\n" +
                                "       <h6 class=\"heading\" style=\"margin-top: 10px;font-size: 20px;\">"+ controllers.Utility.VMdecodeBase64(entity.getTitle_vi())+"</h6>\n" +
                                "       <p style=\"max-height: 200px;overflow: hidden;\">"+ controllers.Utility.VMdecodeBase64(entity.getShortContent_vi())+"...</p>\n" +
                                "       <div><a target=\"_blank\" href=\"/baiviet/chitiet/"+ entity.getId() +"\">Đọc tiếp <i class=\"fas fa-angle-right\"></i></a></div>\n" +
                                "   </div>\n" +
                                "</article>";

                    }
                }
            }
        }
        return html;
    }
}
