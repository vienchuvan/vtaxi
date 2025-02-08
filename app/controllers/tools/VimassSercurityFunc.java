package controllers.tools;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class VimassSercurityFunc {

    private final static String CHARSET_NAME_DES = "UTF8";
    private final static String ALGORITHM_DES = "DES";
    public final static String KEY_DES_DEFAULT = "xxx";

    public static String prefix = "zzz_1_1_5_zzz";
    public static String Key1 = "kjfaj34582DFaadfafdvafqwfadv";
    public static String Key2 = "82459dvskajef54rDADFAadgdsbwe";
    public static String Key3 = "8979843fadVAEGWRfaw342";


    public static String getDataVimassEncrypt(String input)
    {
        String output = input;
        if(input!=null)
        {
            if(input.contains("{"))
            {

            }
            else if(input.contains("zzz_") && input.contains("zzz"))
            {
                output = chuanHoaDuLieuNhan(input);
                output = decryptTripleDES(Key1, Key2,Key3, output);
            }
        }
        return output;
    }

    public static String chuanHoaDuLieuGui(String priFix, String dataEncrypt) {
        String output = "";
        if(priFix != null && dataEncrypt != null)
        {
            output = priFix + dataEncrypt;
        }
        return output;
    }

    public static String chuanHoaDuLieuNhan(String dataEncrypt) {
        String output = "";
        if(dataEncrypt != null && dataEncrypt.contains("zzz_") && dataEncrypt.contains("_zzz"))
        {
            output = dataEncrypt.substring(dataEncrypt.indexOf("_zzz") + 4);
        }
        return output;
    }


    public static String decryptTripleDES(String key1, String key2, String key3, String data)
    {
        String result = "";
        try
        {
			/*Data.ghiLogRequest("decryptTripleDES:k1:" + key1);
			Data.ghiLogRequest("decryptTripleDES:k2:" + key2);
			Data.ghiLogRequest("decryptTripleDES:k3:" + key3);
			Data.ghiLogRequest("decryptTripleDES:data:" + data);*/
            result = decryptDES(key3, data);
            result = decryptDES(key2, result);
            result = decryptDES(key1, result);
        }
        catch (Exception e)
        {
            result = "";
			/*Data.ghiLogRequest("decryptTripleDES:lỗi:" + e.getMessage());*/
        }
        return result;
    }


    public static String decryptDES(String key, String data)
    {
        if (key == null || data == null)
            return "";
        try
        {
            data = data.trim();
            byte[] dataBytes = Base64.decodeBase64(data.getBytes());
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET_NAME_DES));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] dataBytesDecrypted = (cipher.doFinal(dataBytes));
            return new String(dataBytesDecrypted);
        }
        catch (Exception e)
        {
            return "";
        }
    }


    public static String encryptDES(String key, String data) {
        if (key == null || data == null)
            return null;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(CHARSET_NAME_DES));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            byte[] dataBytes = data.getBytes(CHARSET_NAME_DES);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new String(Base64.encodeBase64(cipher.doFinal(dataBytes)));
        } catch (Exception e) {
            return null;
        }
    }


    public static String encryptTripleDES(String key1, String key2, String key3, String data) {
        String result = "";
        try {
//        	System.out.println("input1:" + data);
            result = encryptDES(key1, data);
//        	System.out.println("input2:" + result);
            result = encryptDES(key2, result);
//        	System.out.println("input3:" + result);
            result = encryptDES(key3, result);
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

}
