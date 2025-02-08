package controllers;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 6/16/22
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaiKhoanThuongDungObject {
    private String companyCode = "";
    private String soDienThoai = "";
    private int nhaMang = 0;
    private int loaiThueBao = 0;
    private boolean ngayVang = false;
    private int nhaCungCap = 0;
    private String taiKhoan = "";
    private String loaiGame = "";
    private String maHocPhi = "";
    private String maKhachHangHocPhi = "";
    private String tenKhachHangHocPhi = "";
    private int loaiDichVuHocPhi = 0;
    private int maCongTyChungKhoan = 0;
    private int loaiDichVuChungKhoan = 0;
    private String maKhachHangChungKhoan = "";
    private String tenKhachHangChungKhoan = "";
    private int maDuAnTuThien = 0;
    private String tenNguoiUngHo = "";
    private String diaChiNguoiUngHo = "";
    private String hoanCanhNguoiUngHo = "";
    private String emailNguoiUngHo = "";
    private String tenHoacMaNhanVat = "";
    private String tenNguoiGui = "";
    private int kieuThanhToan = 0;
    private String maKhachHang = "";
    private String maThueBao = "";
    private int maNhaCungCap = 0;
    private String maThoiGianGiaHan = "";
    private String maHopDong = "";
    private int maATM = 0;
    private String otpConfirm = "";
    private int typeAuthenticate = 0;
    private int appId = 0;

    private String id = "0";
    private String phoneOwner = ""; //Số điện thoại (tài khoản)
    private int type = 0;          //Loại tải khoản
    private String aliasName = "";  //Tên đại diện
    private double amount = 0;     //Số tiền
    private String desc = "";       //Mô tả
    private String token = "";
    private String toAccWallet = ""; // tài khoản ví cần đến (số điện thoại)
    private String AccOwnerName = ""; //Tên chủ tài khoản ngân hàng
    private String BankNumber = ""; //Số tài khoản Ngân hàng;
    private String bankName = ""; //Tên ngân hàng
    private int bankCode = 0;
    private int bankId = 0;
    private String provinceName = ""; // Tên tỉnh thành
    private int provinceCode = 0; // Mã tỉnh
    private int provinceId = 0; //Id tỉnh
    private int branchId = 0; // id chi nhánh
    private String branchName = ""; //tên chi nhánh
    private String branchCode = "";
    private int cardTypeId = 0; //id loại thẻ
    private String cardTypeName = ""; //Tên loại thẻ
    private String cardNumber = ""; //Số thẻ
    private String cardOwnerName = ""; //Chủ thẻ
    private long dateReg = 0; // Ngày đăng ký
    private long dateExp = 0; // Ngày hết hạn
    // them cho phan chuyen tien tan nha
    private String tenNguoiThuHuong = "";
    private String cellphoneNumber = "";
    private int soTien = 0;
    private String cmnd = "";
    private String tinhThanh = "";
    private String quanHuyen = "";
    private String phuongXa = "";
    private String diaChi = "";
    private String noiDung = "";
    //    ============the noi dia
    private int cardMonth = 0;
    private int cardYear = 0;
    private String otpGetType = "";

    // ==============the visa
// ===========buoc 1
    private String ten = "";
    private String ho = "";
    private String thanhPho = "";
    private String quocGia = "";
    private String zipCode = "";
    private String phone = "";
    private String email = "";
    // ==========buoc 2
    private String cvv = "";
    //=========== chi nhanh
    private String tenChiNhanh = "";
    private String maChiNhanh = "";
    //========= them cho so tiet kiem
    private String maNganHang = "";
    private int cachThucQuayVong = 0;
    private String kyHan = "";
    private int kyLinhLai = 0;
    private String soCmt = "";
    private int kieuNhanTien = 0;
    private String maNganHangNhanTien = "";
    private String tenChuTaiKhoan = "";
    private String soTaiKhoan = "";

    //==== them cho chuyen tien den CMND
    private String diaChiChiNhanh = "";
    private long ngayCap = 0;
    private String noiCap = "";

    private TaiKhoanThuongDungObject objectTheLuu;
    private String idTheLuu;
    private String idTKNHDaDung;
    private boolean isTKLuu;
    private String avatar;

    private String toAcc;
    private String message;
    private String idIcon;
    private int trangThaiThongBaoDinhKy;
    private String idThongBaoDinhKy;



    public int funcId; // = 3
    public String user;
    public String secssionLogin;
    public long timeRequest;

    public String checkSum; // = bamMD5("235948rfddasda6578ikjndsdafvFGNHRkvkj" + user + funcId + secssionLogin + timeRequest);>bamMD5("235948rfddasda6578ikjndsdafvFGNHRkvkj" + user + funcId + secssionLogin + timeRequest);
    public String rootCompanyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getNhaMang() {
        return nhaMang;
    }

    public void setNhaMang(int nhaMang) {
        this.nhaMang = nhaMang;
    }

    public int getLoaiThueBao() {
        return loaiThueBao;
    }

    public void setLoaiThueBao(int loaiThueBao) {
        this.loaiThueBao = loaiThueBao;
    }

    public boolean isNgayVang() {
        return ngayVang;
    }

    public void setNgayVang(boolean ngayVang) {
        this.ngayVang = ngayVang;
    }

    public int getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(int nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getLoaiGame() {
        return loaiGame;
    }

    public void setLoaiGame(String loaiGame) {
        this.loaiGame = loaiGame;
    }

    public String getMaHocPhi() {
        return maHocPhi;
    }

    public void setMaHocPhi(String maHocPhi) {
        this.maHocPhi = maHocPhi;
    }

    public String getMaKhachHangHocPhi() {
        return maKhachHangHocPhi;
    }

    public void setMaKhachHangHocPhi(String maKhachHangHocPhi) {
        this.maKhachHangHocPhi = maKhachHangHocPhi;
    }

    public String getTenKhachHangHocPhi() {
        return tenKhachHangHocPhi;
    }

    public void setTenKhachHangHocPhi(String tenKhachHangHocPhi) {
        this.tenKhachHangHocPhi = tenKhachHangHocPhi;
    }

    public int getLoaiDichVuHocPhi() {
        return loaiDichVuHocPhi;
    }

    public void setLoaiDichVuHocPhi(int loaiDichVuHocPhi) {
        this.loaiDichVuHocPhi = loaiDichVuHocPhi;
    }

    public int getMaCongTyChungKhoan() {
        return maCongTyChungKhoan;
    }

    public void setMaCongTyChungKhoan(int maCongTyChungKhoan) {
        this.maCongTyChungKhoan = maCongTyChungKhoan;
    }

    public int getLoaiDichVuChungKhoan() {
        return loaiDichVuChungKhoan;
    }

    public void setLoaiDichVuChungKhoan(int loaiDichVuChungKhoan) {
        this.loaiDichVuChungKhoan = loaiDichVuChungKhoan;
    }

    public String getMaKhachHangChungKhoan() {
        return maKhachHangChungKhoan;
    }

    public void setMaKhachHangChungKhoan(String maKhachHangChungKhoan) {
        this.maKhachHangChungKhoan = maKhachHangChungKhoan;
    }

    public String getTenKhachHangChungKhoan() {
        return tenKhachHangChungKhoan;
    }

    public void setTenKhachHangChungKhoan(String tenKhachHangChungKhoan) {
        this.tenKhachHangChungKhoan = tenKhachHangChungKhoan;
    }

    public int getMaDuAnTuThien() {
        return maDuAnTuThien;
    }

    public void setMaDuAnTuThien(int maDuAnTuThien) {
        this.maDuAnTuThien = maDuAnTuThien;
    }

    public String getTenNguoiUngHo() {
        return tenNguoiUngHo;
    }

    public void setTenNguoiUngHo(String tenNguoiUngHo) {
        this.tenNguoiUngHo = tenNguoiUngHo;
    }

    public String getDiaChiNguoiUngHo() {
        return diaChiNguoiUngHo;
    }

    public void setDiaChiNguoiUngHo(String diaChiNguoiUngHo) {
        this.diaChiNguoiUngHo = diaChiNguoiUngHo;
    }

    public String getHoanCanhNguoiUngHo() {
        return hoanCanhNguoiUngHo;
    }

    public void setHoanCanhNguoiUngHo(String hoanCanhNguoiUngHo) {
        this.hoanCanhNguoiUngHo = hoanCanhNguoiUngHo;
    }

    public String getEmailNguoiUngHo() {
        return emailNguoiUngHo;
    }

    public void setEmailNguoiUngHo(String emailNguoiUngHo) {
        this.emailNguoiUngHo = emailNguoiUngHo;
    }

    public String getTenHoacMaNhanVat() {
        return tenHoacMaNhanVat;
    }

    public void setTenHoacMaNhanVat(String tenHoacMaNhanVat) {
        this.tenHoacMaNhanVat = tenHoacMaNhanVat;
    }

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        this.tenNguoiGui = tenNguoiGui;
    }

    public int getKieuThanhToan() {
        return kieuThanhToan;
    }

    public void setKieuThanhToan(int kieuThanhToan) {
        this.kieuThanhToan = kieuThanhToan;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaThueBao() {
        return maThueBao;
    }

    public void setMaThueBao(String maThueBao) {
        this.maThueBao = maThueBao;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaThoiGianGiaHan() {
        return maThoiGianGiaHan;
    }

    public void setMaThoiGianGiaHan(String maThoiGianGiaHan) {
        this.maThoiGianGiaHan = maThoiGianGiaHan;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getMaATM() {
        return maATM;
    }

    public void setMaATM(int maATM) {
        this.maATM = maATM;
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

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneOwner() {
        return phoneOwner;
    }

    public void setPhoneOwner(String phoneOwner) {
        this.phoneOwner = phoneOwner;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToAccWallet() {
        return toAccWallet;
    }

    public void setToAccWallet(String toAccWallet) {
        this.toAccWallet = toAccWallet;
    }

    public String getAccOwnerName() {
        return AccOwnerName;
    }

    public void setAccOwnerName(String accOwnerName) {
        AccOwnerName = accOwnerName;
    }

    public String getBankNumber() {
        return BankNumber;
    }

    public void setBankNumber(String bankNumber) {
        BankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public long getDateReg() {
        return dateReg;
    }

    public void setDateReg(long dateReg) {
        this.dateReg = dateReg;
    }

    public long getDateExp() {
        return dateExp;
    }

    public void setDateExp(long dateExp) {
        this.dateExp = dateExp;
    }

    public String getTenNguoiThuHuong() {
        return tenNguoiThuHuong;
    }

    public void setTenNguoiThuHuong(String tenNguoiThuHuong) {
        this.tenNguoiThuHuong = tenNguoiThuHuong;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(int cardMonth) {
        this.cardMonth = cardMonth;
    }

    public int getCardYear() {
        return cardYear;
    }

    public void setCardYear(int cardYear) {
        this.cardYear = cardYear;
    }

    public String getOtpGetType() {
        return otpGetType;
    }

    public void setOtpGetType(String otpGetType) {
        this.otpGetType = otpGetType;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public String getMaNganHang() {
        return maNganHang;
    }

    public void setMaNganHang(String maNganHang) {
        this.maNganHang = maNganHang;
    }

    public int getCachThucQuayVong() {
        return cachThucQuayVong;
    }

    public void setCachThucQuayVong(int cachThucQuayVong) {
        this.cachThucQuayVong = cachThucQuayVong;
    }

    public String getKyHan() {
        return kyHan;
    }

    public void setKyHan(String kyHan) {
        this.kyHan = kyHan;
    }

    public int getKyLinhLai() {
        return kyLinhLai;
    }

    public void setKyLinhLai(int kyLinhLai) {
        this.kyLinhLai = kyLinhLai;
    }

    public String getSoCmt() {
        return soCmt;
    }

    public void setSoCmt(String soCmt) {
        this.soCmt = soCmt;
    }

    public int getKieuNhanTien() {
        return kieuNhanTien;
    }

    public void setKieuNhanTien(int kieuNhanTien) {
        this.kieuNhanTien = kieuNhanTien;
    }

    public String getMaNganHangNhanTien() {
        return maNganHangNhanTien;
    }

    public void setMaNganHangNhanTien(String maNganHangNhanTien) {
        this.maNganHangNhanTien = maNganHangNhanTien;
    }

    public String getTenChuTaiKhoan() {
        return tenChuTaiKhoan;
    }

    public void setTenChuTaiKhoan(String tenChuTaiKhoan) {
        this.tenChuTaiKhoan = tenChuTaiKhoan;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getDiaChiChiNhanh() {
        return diaChiChiNhanh;
    }

    public void setDiaChiChiNhanh(String diaChiChiNhanh) {
        this.diaChiChiNhanh = diaChiChiNhanh;
    }

    public long getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(long ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public TaiKhoanThuongDungObject getObjectTheLuu() {
        return objectTheLuu;
    }

    public void setObjectTheLuu(TaiKhoanThuongDungObject objectTheLuu) {
        this.objectTheLuu = objectTheLuu;
    }

    public String getIdTheLuu() {
        return idTheLuu;
    }

    public void setIdTheLuu(String idTheLuu) {
        this.idTheLuu = idTheLuu;
    }

    public String getIdTKNHDaDung() {
        return idTKNHDaDung;
    }

    public void setIdTKNHDaDung(String idTKNHDaDung) {
        this.idTKNHDaDung = idTKNHDaDung;
    }

    public boolean isTKLuu() {
        return isTKLuu;
    }

    public void setTKLuu(boolean TKLuu) {
        isTKLuu = TKLuu;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToAcc() {
        return toAcc;
    }

    public void setToAcc(String toAcc) {
        this.toAcc = toAcc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }

    public int getTrangThaiThongBaoDinhKy() {
        return trangThaiThongBaoDinhKy;
    }

    public void setTrangThaiThongBaoDinhKy(int trangThaiThongBaoDinhKy) {
        this.trangThaiThongBaoDinhKy = trangThaiThongBaoDinhKy;
    }

    public String getIdThongBaoDinhKy() {
        return idThongBaoDinhKy;
    }

    public void setIdThongBaoDinhKy(String idThongBaoDinhKy) {
        this.idThongBaoDinhKy = idThongBaoDinhKy;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSecssionLogin() {
        return secssionLogin;
    }

    public void setSecssionLogin(String secssionLogin) {
        this.secssionLogin = secssionLogin;
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

    public String getRootCompanyCode() {
        return rootCompanyCode;
    }

    public void setRootCompanyCode(String rootCompanyCode) {
        this.rootCompanyCode = rootCompanyCode;
    }
}
