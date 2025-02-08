package entity;

/**
 * Created with IntelliJ IDEA.
 * User: toandh
 * Date: 4/14/22
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectPhanQuyen {
    public String companyCode;
    public String walletId;
    public int nguoiLapGiaoDich; //1 là có, 0 là ko có quyền
    public int nguoiDuyetGiaoDich; //1 là có, 0 là ko có quyền
    public String nameWallet;
    public String note="";
    public String checkSum;
    public int trangThaiDuLieu; // -1: xóa; 1: thêm mới; 2: sửa 0: không thay đổi
    public String urlAvatar;
}
