package entity.createQR;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/17/22
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class createQREntity {
    public String idViVimass = "";
    public int typeAuthenticate = 0;
    public String token  = "";
    public String session = "";

    public String signData = "";
    public String mcId = "";

    public int funcId = 7;

    public int typeQR = -1;
    public String nameQR = "";

    public int logoIndex = 0;
    public String line1 = "";
    public String line2 = "";
    public String line3 = "";
    public String line4 = "";

    public String bankCode = "";
    public String bankNumber = "";
    public String bankName = "";

    public int providerCode = -1;
    public String customerCode = "";

    public int amount = -1;

    public String contentQRNonFinal = "";

    public String cellPhoneNumber = "";
    public String email = "";
    public String mstExport = "";
    public String mst = "";
    public String nameCompany = "";
    public String addCompany = "";
    public String descQR = "";
    public String descLink = "";

    public String checkSum = "";

    public String maQR = "";
    public String maGiaoDich= "";
    public long timeCreate = 0;
    public String base64ImageLogoCustomer;
    public String frameType;
    //dung cho qr cửa hàng
    public double lat;
    public double lng;
    public String StoreAdd;
    //Bo sung 20220505
    public String catId; // co the chọn nhiều catId, phân cách nhau bởi dấu ;
    public int visiable; //0 la an, 1 la hien thi

    //Bo sung 20220531
    public String viNhanThongBao;

    //Bo sung 20220625
    public String image1;
    public String sdt;
    public String emailLienHe;
    public String bsx;

    public String linkWeb;
    public String linkFB;
    public String linkInstar;
    public String linkVideo; // youtube
    public String linkImageThumbnail;

    public String hintLinkWeb;
    public String hintLinkFB;
    public String hintLinkInstar;
    public String hintLinkVideo;


}
