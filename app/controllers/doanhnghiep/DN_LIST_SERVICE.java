package controllers.doanhnghiep;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/10/22
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class DN_LIST_SERVICE {
    public static String ROOT = "https://vimass.vn/";
    public static String SERVICE_LOGIN = ROOT + "vmbank/services/account/login1";
    public static String SERVICE_REGISTER = ROOT + "vmbank/services/account/createNewCompanyAcc";
    public static String SERVICE_UPLOAD_ANH = ROOT + "vmbank/services/media/uploadVer2/";
    public static String SERVICE_LAY_DS_DN = ROOT + "vmbank/services/admin/getListNewCompanys";
    public static String SERVICE_PHE_DUYET = ROOT + "vmbank/services/admin/confirmCompanys";
    public static String SERVICE_LAY_DS_GIAODICH = ROOT + "vmbank/services/company/danhSachGiaoDich";
    public static String SERVICE_LAY_CHITIET_GIAODICH = ROOT + "vmbank/services/company/chiTietGiaoDich";
    public static String SERVICE_DUYET_GIAODICH = ROOT + "vmbank/services/company/duyetGiaoDich";
    public static String SERVICE_HUY_GIAODICH = ROOT + "vmbank/services/company/huyGiaoDich";
    public static String SERVICE_XOA_GIAODICH = ROOT + "vmbank/services/company/xoaGiaoDich";
    public static String SERVICE_UPLOAD_FILE = ROOT + "vmbank/services/media/uploadFileLapLenhTheoLo/";
    public static String SERVICE_LAPLENH_THEOLO = ROOT + "vmbank/services/company/lapGiaoDichTheoLo";
    public static String SERVICE_DS_CANHBAO = ROOT + "vmbank/services/admin/getListCanhBao";
    public static String SERVICE_LAY_DS_CN = ROOT + "vmbank/services/admin/getListAcc";
}
