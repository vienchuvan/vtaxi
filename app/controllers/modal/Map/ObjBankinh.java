package controllers.modal.Map;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 5/4/22
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjBankinh {
    private String id = "";
    private int funcId; // 2
    private double km;
    private double lat;
    private double lng;
    private String keySearch; // neu ko tra cuu thi truyen ""

    private int limit;
    private int offset;

    private long timeRequest;
    private String checkSum; // = bamMD5("sjkvklcae982594tfkdHJTKcxkac325" + funcId + km + lat + lng + limit + offset + timeRequest + user + ip);
    private String user; // neu ko dang nhap thi truyen ""
    private String ip; // IP cuar client

    private String diaChi = "";
    private String tenHienThi;
    private String ghiChu;
    private int visiable = 0;
    private String tenDayDu;
    private String catId;
    private  String descLink;

    private int typeLL;
    private String  maGiaoDich;
    private String maQR;
    private String nameQR;
    private String  StoreAdd;
    private String  descQR;
    private String email;
    private String bankCode = "";
    private String bankNumber = "";


    private String  linkWeb;
    private String  linkFB;
    private String linkInstar;
    private String linkVideo ;
    private String emailLienHe ;

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public String getLinkFB() {
        return linkFB;
    }

    public void setLinkFB(String linkFB) {
        this.linkFB = linkFB;
    }

    public String getLinkInstar() {
        return linkInstar;
    }

    public void setLinkInstar(String linkInstar) {
        this.linkInstar = linkInstar;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getEmailLienHe() {
        return emailLienHe;
    }

    public void setEmailLienHe(String emailLienHe) {
        this.emailLienHe = emailLienHe;
    }

    private String accConfirmLv1;
    private String accNameConfirmLv1 = "";

    public long getTimeConfirmLv1() {
        return timeConfirmLv1;
    }

    public void setTimeConfirmLv1(long timeConfirmLv1) {
        this.timeConfirmLv1 = timeConfirmLv1;
    }

    private long timeConfirmLv1 ;

    public String getAccConfirmLv1() {
        return accConfirmLv1;
    }

    public void setAccConfirmLv1(String accConfirmLv1) {
        this.accConfirmLv1 = accConfirmLv1;
    }

    public String getAccNameConfirmLv1() {
        return accNameConfirmLv1;
    }

    public void setAccNameConfirmLv1(String accNameConfirmLv1) {
        this.accNameConfirmLv1 = accNameConfirmLv1;
    }



    public String getDescLink() {
        return descLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescLink(String descLink) {
        this.descLink = descLink;
    }

    private String bankName = "";
    private long timeCreate = 0;
    private String idViVimass = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public long getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(long timeRequest) {
        this.timeRequest = timeRequest;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenHienThi() {
        return tenHienThi;
    }

    public void setTenHienThi(String tenHienThi) {
        this.tenHienThi = tenHienThi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getVisiable() {
        return visiable;
    }

    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }

    public String getTenDayDu() {
        return tenDayDu;
    }

    public void setTenDayDu(String tenDayDu) {
        this.tenDayDu = tenDayDu;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getTypeLL() {
        return typeLL;
    }

    public void setTypeLL(int typeLL) {
        this.typeLL = typeLL;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }

    public String getNameQR() {
        return nameQR;
    }

    public void setNameQR(String nameQR) {
        this.nameQR = nameQR;
    }

    public String getStoreAdd() {
        return StoreAdd;
    }

    public void setStoreAdd(String storeAdd) {
        StoreAdd = storeAdd;
    }

    public String getDescQR() {
        return descQR;
    }

    public void setDescQR(String descQR) {
        this.descQR = descQR;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getIdViVimass() {
        return idViVimass;
    }

    public void setIdViVimass(String idViVimass) {
        this.idViVimass = idViVimass;
    }
}