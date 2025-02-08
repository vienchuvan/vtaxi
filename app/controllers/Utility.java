package controllers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import controllers.tools.VimassSercurityFunc;
import entity.AGRChiNhanh;
import entity.objPhanTichSaoKe;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.api.Play;

import javax.transaction.Transaction;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Toan.Dinh
 * Date: 10/25/18
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {

    public static String USER = "username";
    public static String SEASON = "sesion";
    public static String NAMEALIAS = "namealias";
    public static String AVATAR = "avatar";
    public static String ERROR = "Không kết nối được đến server";
    public static String MaDaiLy = "0B";
    public static String MaDiemThu = "DT0S1";
    public static String MercID = "mercID";
    public static String quyenTaoYeuCauThanhToan = "quyenTaoYeuCauThanhToan";
    public static String quyenNhanTinChoTheVId = "quyenNhanTinChoTheVId";
    public static int limitTransaction = 100;

    public static int limitPosition= 30;
    public static String ADMIN1 = "0917951277";
    public static String ADMIN = "01677249552";
    public static List<Transaction> transactionList;

    public static int SoLuongBaiViet = 15;
    public static boolean isValidJSON(final String json) {
        boolean valid = false;
        try {
            final JsonParser parser = new ObjectMapper().getJsonFactory()
                    .createJsonParser(json);
            while (parser.nextToken() != null) {
            }
            valid = true;
        } catch (JsonParseException jpe) {
            jpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return valid;
    }

    public static String convertLongTime(long time)
    {
        /*Logger.info("time = " + time);*/
        String dateText = "";
        try {
            String t1 = String.valueOf(time);
            if(t1.contains("/") || t1.contains("-"))
            {
                return t1;
            }
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }

    public static String VMencodeBase64(String input)
    {
        try {
            byte[] encodedBytes = Base64.encodeBase64(input.getBytes());
            String textEncode= new String(encodedBytes);
            return textEncode;
        }catch (Exception e)
        {

        }
        return "";
    }

    public static String VMdecodeBase64(String input)
    {
        try {
            byte[] decodedBytes = Base64.decodeBase64(input.getBytes());
            String textDecode = new String(decodedBytes, StandardCharsets.UTF_8);
            return textDecode;
        }catch (Exception e)
        {

        }
        return "";
    }

    public static boolean checkLogin(String user)
    {
        if(
                user.equals("0913201990")||
                user.equals("01677249552")||
                user.equals("0377249552")||
                user.equals("0358737373")||
                user.equals("0917951277")||
                user.equals("0948810890")||
                user.equals("0981455707")||
                user.equals("0363119280")||
                user.equals("0966520960"))
//                user.equals("0903418381")||
//                user.equals("0395981506"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkDaiLy(String user)
    {
        if(
                user.equals("0913201990")||
                user.equals("01677249552")||
                user.equals("0377249552")||
                user.equals("0358737373")||
                user.equals("0917951277")||
                user.equals("0948810890")||
                user.equals("0981455707")||
                user.equals("0363119280")||
                user.equals("0966520960"))

        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static String getBase64(String input)
    {
        String kq = "";
        try
        {
            if(input != null)
            {
                byte[] decodedBytes = Base64.decodeBase64(input.getBytes("UTF-8"));
                kq = new String(decodedBytes);
            }
        }
        catch(Exception e)
        {

        }
        return kq;
    }

    public static String getLinkAnh(String link)
    {
        if(link!=null)
        {
            if(link!=""&&link.contains("http")||link!=""&&link.contains("https"))
            {
                return link;
            }
            else
            {
                link = "https://vimass.vn/vmbank/services/media/getImage?id="+link;
                return link;
            }
        }
        else
        {
            return link;
        }
    }

    public static String converStringyyyyMMddToLongTime(String fromDate)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");
        try
        {
            Date date = simpleDateFormat.parse(fromDate);
            return convertLongTime2(date.getTime());
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
        return "";
    }

    public static String convertLongTime2(long time)
    {
        String dateText = "";
        try {
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }

    public static boolean checkLoginDoiSoat(String user)
    {
        if(
                user.equals("0913201990")||    // tich
                        user.equals("01677249552")||  //toan
                        user.equals("0917951277")||   //thanh
//                        user.equals("0395981506")||   //duyen
                        user.equals("0948810890")||   //hung
                        user.equals("0916050287")||   //ha
                        user.equals("0966520960")     //vu
                        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkLoginVimass(String user)
    {
        if(
                user.equals("0913201990")||
                user.equals("01677249552")||
                user.equals("0917951277")||
                user.equals("0933201990")||
                user.equals("0948810890")||
                user.equals("0358737373")||
                user.equals("0966520960")||
                user.equals("0395981506"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String signData(String priKeyTest, String dataNeedSign)
    {
        try
        {
            Signature sign = Signature.getInstance("SHA256withRSA");
            sign.initSign(getPrivateKey(priKeyTest));
            sign.update(dataNeedSign.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(sign.sign()), "UTF-8");
        }
        catch(Exception e)
        {

        }
        return "";
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;

        KeyFactory kf;
        try {
            kf = KeyFactory.getInstance("RSA");
            byte[] encodedPv = Base64.decodeBase64(base64PrivateKey.getBytes("utf-8"));
            PKCS8EncodedKeySpec keySpecPv = new PKCS8EncodedKeySpec(encodedPv);
            privateKey = kf.generatePrivate(keySpecPv);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static String getMD5(String input) {

        StringBuffer sb = new StringBuffer();
        try {
            input = input.trim();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            byte byteData[] = md.digest();

            // convert the byte to hex format method 1

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }
    public static String RevertTime(String time)
    {
        if(time!=null&&!time.equals(""))
        {
            if(time.contains("-"))
            {
                String[] str = time.split("-");
                if(str[0].length()>3)
                {
                    return str[2]+"/"+str[1]+"/"+str[0];
                }
                else
                {
                    return str[0]+"/"+str[1]+"/"+str[2];
                }

            }
            else if(time.contains("/"))
            {
                String[] str = time.split("/");
                if(str[0].length()>3)
                {
                    return str[2]+"/"+str[1]+"/"+str[0];
                }
                else
                {
                    return str[0]+"/"+str[1]+"/"+str[2];
                }

            }
            else
            {
                return time;
            }
        }
        else
        {
            return time;
        }
    }
    public static long TimeConverterFrom(String time)
    {
        long result = 0;
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            Date d = f.parse(time);
            d.setHours(0);
            d.setMinutes(0);
            d.setSeconds(0);
            result = d.getTime();
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        return result;
    }

    public static long TimeConverterTo(String time)
    {
        long result = 0;
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            Date d = f.parse(time);
            d.setHours(23);
            d.setMinutes(59);
            d.setSeconds(59);
            result = d.getTime();
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        return result;
    }
    public static String convertLongTimeToStringDate(long time)
    {
        String dateText = "";
        try {
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }

    public static String convertLongTimeToFullStringDate(long time)
    {
        String dateText = "";
        try {
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }

    public static String VMdecodeBase642(String input)
    {
        String kq = "";
        try
        {
            if(input != null)
            {
                byte[] decodedBytes = Base64.decodeBase64(input.getBytes("UTF-8"));
                kq = new String(decodedBytes);
            }
        }
        catch(Exception e)
        {

        }
        return kq;
    }

    public static String getVNDFormat(double money)
    {
        if(money>0)
        {
            DecimalFormat df;
            df = new DecimalFormat("#");
            df.setMaximumFractionDigits(8);
            String m = new StringBuilder(df.format(money)).reverse().toString();
            for(int i=0;i<m.length();i++)
            {
                if(i==3 || i ==7 || i == 11)
                {
                    m = new StringBuffer(m).insert(i, ".").toString();
                }
            }
            return new StringBuilder(m).reverse().toString();
        }
        else
        {
            return "0";
        }
    }
    public static String convertLongTime1(long time)
    {
        String dateText = "";
        try {
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }
    public static Boolean VIP(String id)
    {
        List<String> listVIP = new ArrayList<String>();
        listVIP.add("01677249552");   // Toan
        listVIP.add("0913201990");    // a Trung 091
        listVIP.add("0917951277");    // Thành
//        listVIP.add("0982426814");    // Trang
//        listVIP.add("0916050287");    // Ha
        boolean isVIP = false;
        for (String str:listVIP)
        {
            if(str.equalsIgnoreCase(id))
            {
                isVIP = true;
            }
        }
        return isVIP;
    }
    public static Boolean VIPNHNN(String id)
    {
        List<String> listVIP = new ArrayList<String>();

        /*listVIP.add("0903202255"); */   // Giang NHNN
        listVIP.add("0973126868");    // Giang NHNN
        listVIP.add("0982672966");    // Thanh NHNN
        /*listVIP.add("0913304696");  */  // a Thang cuc CNTT
        listVIP.add("0976881845");    // a Son cuc CNTH
        listVIP.add("fb_giamsatonline.vm@gmail.com");
        listVIP.add("0916050287");    // Ha
        boolean isVIP = false;
        for (String str:listVIP)
        {
            if(str.equalsIgnoreCase(id))
            {
                isVIP = true;
            }
        }
        return isVIP;
    }

    public static void clearInputData(Map<String, Object> data)
    {
        List<String> blackListString = new ArrayList<String>();
        blackListString.add("ALTER");
        blackListString.add("ALL");
        blackListString.add("AND");
        blackListString.add("BETWEEN");
        blackListString.add("CASE");
        blackListString.add("CONSTRAINT");
        blackListString.add("DELETE");
        blackListString.add("DESC");
        blackListString.add("EXISTS");
        blackListString.add("FROM");
        blackListString.add("LIMIT");
        blackListString.add("NOT");
        blackListString.add("OR");
        blackListString.add("ORDER BY");
        blackListString.add("SELECT");
        blackListString.add("UNION");
        blackListString.add("WHERE");
        blackListString.add("INSERT");
        blackListString.add("DROP");
        blackListString.add("CONCAT");
        blackListString.add("GETHOSTBYNAME");
        blackListString.add("--");
        blackListString.add("'");
        blackListString.add("#");
        blackListString.add("$");
        blackListString.add("%");
        blackListString.add("&");
        blackListString.add("(");
        blackListString.add(")");
        blackListString.add("('");
        blackListString.add("')");
        blackListString.add("(,)");
        blackListString.add("(, )");
        blackListString.add("' '");
        blackListString.add("HAVING");
        blackListString.add("GROUP BY");
        blackListString.add("INSERT");
        blackListString.add("SUBSTRING");

        String strReturn = "";
        for (Map.Entry<String, Object> pair : data.entrySet()) {
            for(String str:blackListString)
            {
                if(pair!=null&&str!=null)
                {
                    //System.out.println("pair: " + pair.getValue().toString());
                   // System.out.println("str: " + str);
                    if(pair.getValue().toString().contains(str)||pair.getValue().toString().toUpperCase().contains(str))
                    {
                        strReturn = pair.getValue().toString().replace(str, "");
                        pair.setValue(strReturn);
                    }
                }
            }
        }
    }

    public static String clearInputDataGet(String data)
    {
        List<String> blackListString = new ArrayList<String>();
        blackListString.add("ALTER");
        blackListString.add("ALL");
        blackListString.add("AND");
        blackListString.add("BETWEEN");
        blackListString.add("CASE");
        blackListString.add("CONSTRAINT");
        blackListString.add("DELETE");
        blackListString.add("DESC");
        blackListString.add("EXISTS");
        blackListString.add("FROM");
        blackListString.add("LIMIT");
        blackListString.add("NOT");
        blackListString.add("OR");
        blackListString.add("ORDER BY");
        blackListString.add("SELECT");
        blackListString.add("UNION");
        blackListString.add("WHERE");
        blackListString.add("INSERT");
        blackListString.add("DROP");
        blackListString.add("CONCAT");
        blackListString.add("GETHOSTBYNAME");
        blackListString.add("--");
        blackListString.add("'");
        blackListString.add("#");
        blackListString.add("$");
        blackListString.add("%");
        blackListString.add("&");
        blackListString.add("(");
        blackListString.add(")");
        blackListString.add("('");
        blackListString.add("')");
        blackListString.add("(,)");
        blackListString.add("(, )");
        blackListString.add("' '");
        blackListString.add("HAVING");
        blackListString.add("GROUP BY");
        blackListString.add("INSERT");
        blackListString.add("SUBSTRING");
        System.out.println("verify: " + data);
        String strReturn = "";
        for(String str:blackListString)
        {
            if(data.contains(str)||data.toUpperCase().contains(str))
            {
               // System.out.println("Log data: " + data +" LogStr: " + str);
                strReturn = data.replace(str, "");
            }
            else
            {
                strReturn = data;
            }
        }
        System.out.println("verified: " + strReturn);
        return strReturn;
    }

    public static String genWebToken(String page)
    {
//        RandomString gen = new RandomString(6, ThreadLocalRandom.current());
//        String text = gen.nextString();
        long completeTime = System.currentTimeMillis();
        String capt_confirm = page + "ok2conde" + completeTime;
        String dataSend = VimassSercurityFunc.chuanHoaDuLieuGui(VimassSercurityFunc.prefix,
                VimassSercurityFunc.encryptTripleDES(VimassSercurityFunc.Key1,
                        VimassSercurityFunc.Key2,
                        VimassSercurityFunc.Key3,
                        capt_confirm));
        return dataSend;
//        String capt_confirm = text + "ok2conde" + completeTime;
//        return text + "/" + Utility.getMD5(capt_confirm);
    }

    public static PublicKey readPublicKeyFromFile(String fileName) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(fileName));
            ois = new ObjectInputStream(fis);

            BigInteger modulus = (BigInteger) ois.readObject();
            BigInteger exponent = (BigInteger) ois.readObject();

            // Get Public Key
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus,
                    exponent);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);

            return publicKey;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
                if (fis != null) {
                    fis.close();
                }
            }
        }
        return null;
    }

    public static PrivateKey readPrivateKeyFromFile(String fileName)
            throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(fileName));
            ois = new ObjectInputStream(fis);

            BigInteger modulus = (BigInteger) ois.readObject();
            BigInteger exponent = (BigInteger) ois.readObject();

            // Get Private Key
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(
                    modulus, exponent);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);

            return privateKey;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
                if (fis != null) {
                    fis.close();
                }
            }
        }
        return null;
    }
    public static objPhanTichSaoKe phanTichNoiDungSaoKe(String noiDung)
    {
        String idVimass = "";
        String transId = "";
        String soTien = "0";
        String soPhi = "";
        String idRequestMc = "";
        String IdClient = "";
        if(noiDung!=null&&noiDung.length()>20)
        {
            if(noiDung.startsWith("Chi hộ đến"))
            {
                String chiHo = "Chi hộ đến TK " +
                        "AGR-6607205167938 của Nguyễn minh thành, " +
                        "số tiền: 4.977.000, " +
                        "phí: 5.000 " +
                        "IdClient:P917221aa440442f58a8d812aac52a348 " +
                        "IdVimass:1610697385644gety67igrc " +
                        "Nội dung:cash cow ** chuyển khoản cho bạn";
                soTien = noiDung.split("số tiền:")[1].split(",")[0];
                soPhi = noiDung.split("phí:")[1].split(" ")[0];
                idVimass = noiDung.split("IdVimass:")[1].split(" ")[0];
                IdClient = noiDung.split("IdClient:")[1].split(" ")[0];
            }
            else if(noiDung.startsWith("VA"))
            {
                String thuHo = "VA-902006499712-Truong Thi Hoa Huyen-TRUONG THI HOA HUYEN, 0853885286 FT21015309907048 " +
                        "idRequestMc:Cddc357d7cc5640539446862d1e5cd8c9 " +
                        "transId:202101151504080000287 " +
                        "(đã thu phí 5.000)";
                transId = noiDung.split("transId:")[1].split(" ")[0];
                soPhi = noiDung.split("đã thu phí ")[1].replace(")","");
                idRequestMc = noiDung.split("idRequestMc:")[1].split(" ")[0];
                //System.out.println("VA " + idRequestMc);
            }
        }
        return new objPhanTichSaoKe(soTien,soPhi,idVimass,transId,idRequestMc,IdClient);
    }
    public static String convertTime(String time)
    {
        String dateText = "";
        try {
            if(time.contains("/") || time.contains("-"))
            {
                return time;
            }
            double time1 = Double.parseDouble(time);
            Date date=new Date((long) time1);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }
    public static final int VI_VIMASS_ANDROID = 1;
    public static final int VI_VIMASS_IOS = 2;
    public static final int VI_VIMASS_WP = 3;
    public static final int VI_VIMASS_WEB = 4;
    public static final int VI_VIMASS_CTT = 5;
    public static final int VIMASS_ANDROID = 6;
    public static final int VIMASS_IOS = 7;
    public static final int VIMASS_WP = 8;
    public static final int VIMASS_KHAC = 9;

    public static String getLoaiThietBi(int VMApp)
    {
        String loaiThietBi = "Website";
        if (VMApp == VI_VIMASS_ANDROID) {
            loaiThietBi = "ĐT Android";
        } else if (VMApp == VI_VIMASS_IOS) {
            loaiThietBi = "iPhone";
        } else if (VMApp == VI_VIMASS_WP) {
            loaiThietBi = "ĐT WP";
        } else if (VMApp == VI_VIMASS_WEB) {
            loaiThietBi = "Website";
        } else if (VMApp == VI_VIMASS_CTT) {
            loaiThietBi = "Website";
        } else if (VMApp == VIMASS_ANDROID) {
            loaiThietBi = "ĐT Android";
        } else if (VMApp == VIMASS_IOS) {
            loaiThietBi = "iPhone";
        } else if (VMApp == VIMASS_WP) {
            loaiThietBi = "ĐT WP";
        }
        else if (VMApp == VIMASS_KHAC) {
            loaiThietBi = "Khác";
        }
        return loaiThietBi;
    }
    public static List<AGRChiNhanh> getListContries()
    {
        String line = "";
        StringBuilder builder = new StringBuilder();
        InputStream is = Play.current().classloader().getResourceAsStream("contries.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);   // add everything to StringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Type CType = new TypeToken<List<AGRChiNhanh>>(){}.getType();
        List<AGRChiNhanh> CList = new Gson().fromJson(builder.toString(),CType);
//        if(CList!=null&&CList.size()>0)
//        {
//            String htmlQuocGia = "";
//            htmlQuocGia += "<option value=\"0\">Chọn quốc tịch</option>";
//            for(int i = 0;i<CList.size();i++)
//            {
//                htmlQuocGia += "<option value=\""+CList.get(i).getValue()+"\">"+CList.get(i).getText()+"</option>";
//            }
//
//            return htmlQuocGia;
//        }
//        else
//        {
//            return "";
//        }
        return CList;
    }
    public static String HiddenPhone(String phone)
    {
        String result = "";
        if(phone!=null && !phone.equals(""))
        {
            String sphone = phone.substring(phone.length() - 6, phone.length());
            result = phone.replace(sphone, "******");
        }
        return result;
    }

    public static boolean isNumeric(String strNum)
    {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            System.out.println("khong phai so " + strNum);
            return false;
        }
        return true;
    }

    public static String convertLongTimeFull(long time)
    {
        String dateText = "";
        try {
            Date date=new Date(time);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dateText = df2.format(date);
        }catch (Exception e)
        {
            Logger.info("error: " + e.getMessage());
        }
        if(dateText.contains("AM"))
        {
            dateText = dateText.replace("AM","Sáng");
        }
        else if (dateText.contains("PM"))
        {
            dateText = dateText.replace("PM","Chiều");
        }
        return dateText;
    }
}
