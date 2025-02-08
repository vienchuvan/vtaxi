package entity.Laixe;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/24/22
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dangkyxe {
    public int funcId;
    public String idViVimass;
    public String token;
    public long timeRequest;
    public int loaiXe;// mô tả ở dưới
    public String bangLaiXe; // data tham chiếu
    public String hangXe; // data tham chiếu <gửi tên tường minh>
    public String model;//daa tham chiếu < gửi tên tường minh>
    public String bienSoXe;
    public int soChoNgoi;
    public int namSanXuat;
    public String linkAnhXe1;
    public String linkAnhXe2;

    public int hinhThucPhatToaDo;//phone:1; thietBiVietMap:2
    public String idThietBiVietMap;
    public String  checkSum;

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public String getIdViVimass() {
        return idViVimass;
    }

    public void setIdViVimass(String idViVimass) {
        this.idViVimass = idViVimass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(long timeRequest) {
        this.timeRequest = timeRequest;
    }

    public int getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(int loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getBangLaiXe() {
        return bangLaiXe;
    }

    public void setBangLaiXe(String bangLaiXe) {
        this.bangLaiXe = bangLaiXe;
    }

    public String getHangXe() {
        return hangXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public int getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getLinkAnhXe1() {
        return linkAnhXe1;
    }

    public void setLinkAnhXe1(String linkAnhXe1) {
        this.linkAnhXe1 = linkAnhXe1;
    }

    public String getLinkAnhXe2() {
        return linkAnhXe2;
    }

    public void setLinkAnhXe2(String linkAnhXe2) {
        this.linkAnhXe2 = linkAnhXe2;
    }

    public int getHinhThucPhatToaDo() {
        return hinhThucPhatToaDo;
    }

    public void setHinhThucPhatToaDo(int hinhThucPhatToaDo) {
        this.hinhThucPhatToaDo = hinhThucPhatToaDo;
    }

    public String getIdThietBiVietMap() {
        return idThietBiVietMap;
    }

    public void setIdThietBiVietMap(String idThietBiVietMap) {
        this.idThietBiVietMap = idThietBiVietMap;
    }
}
