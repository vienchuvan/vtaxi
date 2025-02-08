package entity;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 9/29/22
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectGiaoDichBaoCaoChung {
    private int stt;
    private double soTien;
    private double phi;
    private String chuyenTu;
    private String chuyenDen;
    private String noiDung;
    private String thoiGian;

    public int getStt() {
        return stt;
    }
    public void setStt(int stt) {
        this.stt = stt;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public String getChuyenTu() {
        return chuyenTu;
    }

    public void setChuyenTu(String chuyenTu) {
        this.chuyenTu = chuyenTu;
    }

    public String getChuyenDen() {
        return chuyenDen;
    }

    public void setChuyenDen(String chuyenDen) {
        this.chuyenDen = chuyenDen;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
