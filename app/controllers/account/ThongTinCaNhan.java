package controllers.account;

import controllers.TaiKhoanThuongDungObject;
import controllers.modal.lichChuyenTienEntity;


/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThongTinCaNhan {
    private String id = "";
    private String secssion = "";
    private int isToken = 0;
    private int status = 0;    // 0 = phong toa, 1 = duyet
    private double amount = 0;
    public String acc_name = "";
    private String home = "";
    private String placeIdCard = "";
    private String dateIdCard = "";
    private String linkFrontIdCard = "";
    private String linkBackIdCard = "";
    private String linkSignature = "";
    private String nameAlias ="";
    private String avatar = "";
    private long start = 0;
    private String pass = "";
    private String phone = "";
    private String idCard = "";
    private String accBank = "";
    private String email = "";
    private String birthday = "";
    private String phoneToken = "";
    private String token = "";
    private int uId = 0;
    private String phoneAuthenticate = "";
    private String otpConfirm = "";
    private int typeAuthenticate;
    private double promotionTotal;
    private boolean promotionStatus;
    private int gioiTinh = 0;
    private String quocTich = "VN";
    private TaiKhoanThuongDungObject tKRutTien;

    // them cho DN
    private int appId = 0;
    private int deviceId = 0;
    private int funcId = 0;
    private int accType = 0;
    private String companyCode = "";
    private String walletId = "";
    private String companyName = "";
    private String nameRepresent = "";
    private String imageCompany1 = "";
    private String imageCompany2 = "";
    private int toTalGift = 0;
    private int totalCreateGift = 0;
    private String walletLogin = "";
    private String roles = "";
    private String walletLoginEmail = "";
    private String walletLoginName = "";
    private long thoiGianDangKy = 0;
    private long thoiGianDuyet = 0;
    private String nguoiDuyet = "";
    private String ghiChu = "";
    private String dsLap = "";
    private String dsDuyet = "";
    private String dangKyTai;

    // them cho phan kiem duyet
    private int trangThaiTK;  //0 = chua dinh danh, 1 = da dinh danh
    private int trangThaiCMND;  //0 = chua kiem tra, 1 = da kiem tra
    private long thoiGianDoiTrangThaiTK;
    private long thoiGianDoiTrangThaiCMND;
    private String viDoiTrangThaiTK;
    private String viDoiTrangThaiCMND;


    private boolean coTKLienKet;
    private int hienThiNoiDungThanhToanQR;
    private String linkQR;
    private String linkQRHoaDon;

    private int thietBiDangKy;

    private int giauViChuyen;

    private int loaiMappingAnDanh;
    private String idMappingAnDanh;


    private int listDangKy;



    private String imageBHYT;
    private String valueBHYT;

    private String maSoThue;
    private String diaChiDoanhNghiep;
    private String tenDoanhNghiep;

    private boolean thuChiHo;
    private lichChuyenTienEntity cauHinhChuyenTienTuDong;
    public double lat;
    public double lng;
    public int visiable;
    public long getThoiGianDangKy() {
        return thoiGianDangKy;
    }

    public void setThoiGianDangKy(long thoiGianDangKy) {
        this.thoiGianDangKy = thoiGianDangKy;
    }

    public long getThoiGianDuyet() {
        return thoiGianDuyet;
    }

    public void setThoiGianDuyet(long thoiGianDuyet) {
        this.thoiGianDuyet = thoiGianDuyet;
    }
    public void settKRutTien(TaiKhoanThuongDungObject tKRutTien) {
        this.tKRutTien = tKRutTien;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecssion() {
        return secssion;
    }

    public void setSecssion(String secssion) {
        this.secssion = secssion;
    }

    public String getToken() {
        return token;
    }

    public int getIsToken() {
        return isToken;
    }

    public void setIsToken(int IsToken) {
        isToken = IsToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPlaceIdCard() {
        return placeIdCard;
    }

    public void setPlaceIdCard(String placeIdCard) {
        this.placeIdCard = placeIdCard;
    }

    public String getDateIdCard() {
        return dateIdCard;
    }

    public void setDateIdCard(String dateIdCard) {
        this.dateIdCard = dateIdCard;
    }

    public String getLinkFrontIdCard() {
        return linkFrontIdCard;
    }

    public void setLinkFrontIdCard(String linkFrontIdCard) {
        this.linkFrontIdCard = linkFrontIdCard;
    }

    public String getLinkBackIdCard() {
        return linkBackIdCard;
    }

    public void setLinkBackIdCard(String linkBackIdCard) {
        this.linkBackIdCard = linkBackIdCard;
    }

    public String getLinkSignature() {
        return linkSignature;
    }

    public TaiKhoanThuongDungObject gettKRutTien() {
        return tKRutTien;
    }
    public void setLinkSignature(String linkSignature) {
        this.linkSignature = linkSignature;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAccBank() {
        return accBank;
    }

    public void setAccBank(String accBank) {
        this.accBank = accBank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneToken() {
        return phoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        this.phoneToken = phoneToken;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getPhoneAuthenticate() {
        return phoneAuthenticate;
    }

    public void setPhoneAuthenticate(String phoneAuthenticate) {
        this.phoneAuthenticate = phoneAuthenticate;
    }

    public String getOtpConfirm() {
        return otpConfirm;
    }

    public void setOtpConfirm(String otpConfirm) {
        this.otpConfirm = otpConfirm;
    }

    public int getTypeAuthenticate() {
        return typeAuthenticate;
    }

    public void setTypeAuthenticate(int typeAuthenticate) {
        this.typeAuthenticate = typeAuthenticate;
    }

    public double getPromotionTotal() {
        return promotionTotal;
    }

    public void setPromotionTotal(double promotionTotal) {
        this.promotionTotal = promotionTotal;
    }

    public boolean isPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(boolean promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNameRepresent() {
        return nameRepresent;
    }

    public void setNameRepresent(String nameRepresent) {
        this.nameRepresent = nameRepresent;
    }

    public String getImageCompany1() {
        return imageCompany1;
    }

    public void setImageCompany1(String imageCompany1) {
        this.imageCompany1 = imageCompany1;
    }

    public String getImageCompany2() {
        return imageCompany2;
    }

    public void setImageCompany2(String imageCompany2) {
        this.imageCompany2 = imageCompany2;
    }

    public int getToTalGift() {
        return toTalGift;
    }

    public void setToTalGift(int toTalGift) {
        this.toTalGift = toTalGift;
    }

    public int getTotalCreateGift() {
        return totalCreateGift;
    }

    public void setTotalCreateGift(int totalCreateGift) {
        this.totalCreateGift = totalCreateGift;
    }

    public String getWalletLogin() {
        return walletLogin;
    }

    public void setWalletLogin(String walletLogin) {
        this.walletLogin = walletLogin;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getWalletLoginEmail() {
        return walletLoginEmail;
    }

    public void setWalletLoginEmail(String walletLoginEmail) {
        this.walletLoginEmail = walletLoginEmail;
    }

    public String getWalletLoginName() {
        return walletLoginName;
    }

    public void setWalletLoginName(String walletLoginName) {
        this.walletLoginName = walletLoginName;
    }

    public String getDsLap() {
        return dsLap;
    }

    public void setDsLap(String dsLap) {
        this.dsLap = dsLap;
    }

    public String getDsDuyet() {
        return dsDuyet;
    }

    public void setDsDuyet(String dsDuyet) {
        this.dsDuyet = dsDuyet;
    }

    public String getDangKyTai() {
        return dangKyTai;
    }

    public void setDangKyTai(String dangKyTai) {
        this.dangKyTai = dangKyTai;
    }



    public int getTrangThaiTK() {
        return trangThaiTK;
    }

    public void setTrangThaiTK(int trangThaiTK) {
        this.trangThaiTK = trangThaiTK;
    }

    public int getTrangThaiCMND() {
        return trangThaiCMND;
    }

    public void setTrangThaiCMND(int trangThaiCMND) {
        this.trangThaiCMND = trangThaiCMND;
    }

    public long getThoiGianDoiTrangThaiTK() {
        return thoiGianDoiTrangThaiTK;
    }

    public void setThoiGianDoiTrangThaiTK(long thoiGianDoiTrangThaiTK) {
        this.thoiGianDoiTrangThaiTK = thoiGianDoiTrangThaiTK;
    }

    public long getThoiGianDoiTrangThaiCMND() {
        return thoiGianDoiTrangThaiCMND;
    }

    public void setThoiGianDoiTrangThaiCMND(long thoiGianDoiTrangThaiCMND) {
        this.thoiGianDoiTrangThaiCMND = thoiGianDoiTrangThaiCMND;
    }

    public String getViDoiTrangThaiTK() {
        return viDoiTrangThaiTK;
    }

    public void setViDoiTrangThaiTK(String viDoiTrangThaiTK) {
        this.viDoiTrangThaiTK = viDoiTrangThaiTK;
    }

    public String getViDoiTrangThaiCMND() {
        return viDoiTrangThaiCMND;
    }

    public void setViDoiTrangThaiCMND(String viDoiTrangThaiCMND) {
        this.viDoiTrangThaiCMND = viDoiTrangThaiCMND;
    }

    public boolean isCoTKLienKet() {
        return coTKLienKet;
    }

    public void setCoTKLienKet(boolean coTKLienKet) {
        this.coTKLienKet = coTKLienKet;
    }

    public int getThietBiDangKy() {
        return thietBiDangKy;
    }

    public void setThietBiDangKy(int thietBiDangKy) {
        this.thietBiDangKy = thietBiDangKy;
    }

    public int getHienThiNoiDungThanhToanQR() {
        return hienThiNoiDungThanhToanQR;
    }

    public void setHienThiNoiDungThanhToanQR(int hienThiNoiDungThanhToanQR) {
        this.hienThiNoiDungThanhToanQR = hienThiNoiDungThanhToanQR;
    }

    public String getLinkQR() {
        return linkQR;
    }

    public void setLinkQR(String linkQR) {
        this.linkQR = linkQR;
    }

    public int getGiauViChuyen() {
        return giauViChuyen;
    }

    public void setGiauViChuyen(int giauViChuyen) {
        this.giauViChuyen = giauViChuyen;
    }

    public int getLoaiMappingAnDanh() {
        return loaiMappingAnDanh;
    }

    public void setLoaiMappingAnDanh(int loaiMappingAnDanh) {
        this.loaiMappingAnDanh = loaiMappingAnDanh;
    }

    public String getIdMappingAnDanh() {
        return idMappingAnDanh;
    }

    public void setIdMappingAnDanh(String idMappingAnDanh) {
        this.idMappingAnDanh = idMappingAnDanh;
    }



    public String getLinkQRHoaDon() {
        return linkQRHoaDon;
    }

    public void setLinkQRHoaDon(String linkQRHoaDon) {
        this.linkQRHoaDon = linkQRHoaDon;
    }

    public int getListDangKy() {
        return listDangKy;
    }

    public void setListDangKy(int listDangKy) {
        this.listDangKy = listDangKy;
    }



    public String getImageBHYT() {
        return imageBHYT;
    }

    public void setImageBHYT(String imageBHYT) {
        this.imageBHYT = imageBHYT;
    }

    public String getValueBHYT() {
        return valueBHYT;
    }

    public void setValueBHYT(String valueBHYT) {
        this.valueBHYT = valueBHYT;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getDiaChiDoanhNghiep() {
        return diaChiDoanhNghiep;
    }

    public void setDiaChiDoanhNghiep(String diaChiDoanhNghiep) {
        this.diaChiDoanhNghiep = diaChiDoanhNghiep;
    }

    public String getTenDoanhNghiep() {
        return tenDoanhNghiep;
    }

    public void setTenDoanhNghiep(String tenDoanhNghiep) {
        this.tenDoanhNghiep = tenDoanhNghiep;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public boolean isThuChiHo() {
        return thuChiHo;
    }

    public void setThuChiHo(boolean thuChiHo) {
        this.thuChiHo = thuChiHo;
    }
    public lichChuyenTienEntity getCauHinhChuyenTienTuDong() {
        return cauHinhChuyenTienTuDong;
    }
    public void setCauHinhChuyenTienTuDong(lichChuyenTienEntity cauHinhChuyenTienTuDong) {
        this.cauHinhChuyenTienTuDong = cauHinhChuyenTienTuDong;
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

    public int getVisiable() {
        return visiable;
    }

    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }
}
