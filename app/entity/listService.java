package entity;

/**
 * Created with IntelliJ IDEA.
 * User: Toan.Dinh
 * Date: 10/29/18
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class listService {

    public static String ROOT_URL_QUAN_TRI = "http://118.69.84.243:8080";

    public static String ROOT = "https://vimass.vn/";
    public static String CONTENT_TYPE = "application/json";
    public static String SERVICE_LAY_ANH = "https://vimass.vn/vmbank/services/media/getImage?id=";
    public static String SERVICE_NAP_TIEN_DIEN_TU = ROOT + "vmbank/services/napTienDienTu/napTien";
    public static String SERVICE_LUU_THONG_TIN = ROOT + "vmbank/services/web/saveSession";
    public static String service_laydanhsachanh = "https://vimass.vn/vmbank/services/managerImage/searchImage";
    public static int LANG_ID_ALL = 0;
    public static int LANG_ID_VI = 1;
    public static int LANG_ID_EN = 2;
    public static String SERVICE_THANHTOAN = ROOT + "vmbank/services/billing/thanhToanHoaDon";
    public static String SERVICE_GUI_EMAIL = "https://vimass.vn/vmbank/services/emailPublic/sendEmail";
    public static String SERVICE_DANGNHAP_VID = ROOT + "vmbank/services/vIdService/dangNhap";
    public static String SERVICE_GUI_EXCEL_EMAIL = ROOT + "vmbank/services/account/sendInquiryToEmail";
    public static String service_laydanhsachbaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/getList";
    public static String getService_laydanhsachbaiviet_rutgon = "https://vimass.vn/vmbank/services/quangCaoUuDai/getListVer2";
    public static String service_laychitietbaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/getDetail";
    //    public static String service_laychitietbaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/getDetailVer2";
    public static String service_taobaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/create";
    public static String service_suabaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/edit";
    public static String SERVICE_CHECK_POINT = "https://vimass.vn/pushNotification/services/adminPush/addDevice";
    public static String service_xoabaiviet = "https://vimass.vn/vmbank/services/quangCaoUuDai/delete";
    public static int FUNC_DANG_BAI_QUANG_CAO_UU_DAI = 472;
    public static int FUNC_SUA_BAI_QUANG_CAO_UU_DAI = 473;
    public static String SERVICE_LAY_THONG_TIN = ROOT + "vmbank/services/web/getSession";
    public static String SERVICE_SUA_THONG_TIN_VI = ROOT + "vmbank/services/account/editAcc1";
    public static String SERVICE_LAY_SO_DU = ROOT + "vmbank/services/account/getAmount?id=";
    public static String SERVICE_INQUIRY1 = ROOT_URL_QUAN_TRI + "/vmNoiBo/services/account/inquiry1";
    public static String SERVICE_INQUIRY = ROOT + "vmbank/services/account/inquiry1";
    public static String SERVICE_TAOANH = "https://vimass.vn/VimassMedia/services/VMMedia/uploadImg";
    public static String SERVICE_LAYANH = "https://vimass.vn/vmbank/services/quanTriPush/layDSAnh";
    public static String SERVICE_XOAANH = "https://vimass.vn/vmbank/services/quanTriPush/xoaAnh";
    public static String SERVICE_UPLOAD_ANH = ROOT + "vmbank/services/media/upload";

    public static String service_laydanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/getList";
    //    public static String service_laychitietdanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/getDetail";
    public static String service_laychitietdanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/getDetailVer2";
    public static String service_taodanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/create";
    public static String service_suadanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/edit";
    public static String service_xoadanhmuc = "https://vimass.vn/vmbank/services/categoryAdv/delete";
    public static String SERVICE_ROOT = "https://vimass.vn/vmbank/services/";

    public static String SERVICE_TK_THUONGDUNG = ROOT + "vmbank/services/account/searchAccUsed";
    public static String SERVICE_LIST_DOANHNGHIEP = "http://118.69.84.243:8080/vmNoiBo/services/account/requestComand";
    public static String SERVICE_DANGNHAP_DOISOAT = ROOT + "/vmbank/services/hoTroTTYTe/requestComand";
    //tracuutiendien
    public static String SERVICE_TRACUU_HOADON = ROOT + "vmbank/services/billing/traCuuHoaDon";
    //napas
    public static String SERVICE_NAPAS_TRU_THE_ATM_NOI_DIA = ROOT + "vmbank/services/napas/requestComand";
    public static String SERVICE_DOISOAT_NAPAS = ROOT + "vmbank/services/napas/requestComand";

    //merchant
    public static String SERVICE_MERCHANT = ROOT + "vmbank/services/VimassGateway/requestComand";

    public static String SERVICE_FORGET_PASSWORD = ROOT + "vmbank/services/account/forgetPassAcc1";
    public static String SERVICE_CONFRIM_OTP_FORGET_PASS = ROOT + "vmbank/services/account/confirmOTPToForgetPassAcc1";
    public static String SERVICE_CREATE_NEW_ACC1 = ROOT + "vmbank/services/account/createNewAcc1";
    public static String SERVICE_CONFRIM_OTP_CREATE_ACC1 = ROOT + "vmbank/services/account/confirmOTPToCreateNewAcc1";

    // dich vu cong
    public static String SERVICES_THANHTOAN_DICHVUCONG = "https://vimass.vn/vmbank/services/VimassGateway/requestComand";

    //the quoc te
    public static String SERVICES_CREATE_CHECKOUT_SESSION = ROOT + "CardPayment/services/TheQuocTe/CreateCheckOutSession";
    public static String SERVICES_PAYMENT_PAGE = ROOT + "CardPayment/services/TheQuocTe/paymentPage";
    public static String SERVICES_PAYMENT_RESULT = ROOT + "CardPayment/services/TheQuocTe/paymentResult";

    //qr
    public static String SERVICES_CREATE_QR = ROOT + "qrservices/services/VietQR/taoQR";
    public static String SERVICES_CHECK_CRC = ROOT + "qrservices/services/VietQR/checkcrc";
    //dangnhap
    public static String SERVICE_LOGIN = ROOT + "vmbank/services/account/login1";

    //vnpay qr
    public static String SERVICE_TRACUU_QRVNPAY = ROOT + "vmbank/services/QRVietNam/traCuuThongTinQR";
    public static String SERVICE_THANHTOAN_QRVNPAY = ROOT + "vmbank/services/QRVietNam/thanhToanVNPayQR";
    //qrvn
    public static String SERVICES_TRACUU_QRVN = "https://vimass.vn/VimassQR/services/VMDonHang/requestCommand";
    public static String API_SERVICES = "http://192.168.100.8:8080/";
    //    public static String SERVICES_TRACUU_THONGTIN_DN = "http://192.168.100.8:8080/HDDT/services/cmc/searchCompany";
    public static String SERVICES_TRACUU_THONGTIN_DN = API_SERVICES + "HDDT/services/cmc/searchCompany";
    public static String SERVICES_TRACUU_THONGTIN_DN_IN_DB = API_SERVICES + "HDDT/services/fpt/searchMerchant";
    public static String SERVICES_UPDATE_THONGTIN = API_SERVICES + "HDDT/services/fpt/updateMerchant";
    public static String SERVICES_TRACUUHOADON = API_SERVICES + "HDDT/services/fpt/searchInvoice";

    public static String SERVICE_CHECK_TKNH = ROOT + "vmbank/services/admin/traCuuTaiKhoanNganHang";

    public static String SERVICE_CHUYENTIEN_VIVI = ROOT + "vmbank/services/account/transactionMoney1";
    public static String SERVICE_CHUYEN_TIEN_TK_NGAN_HANG = ROOT + "vmbank/services/autoBank/transactionToBank";

    //    Thông báo
    public static String SERVICE_LAY_DS_TIN = "https://vimass.vn/pushNotification/services/adminPush/getMess";
    public static String SERVICE_LAY_CHI_TIET_TIN = "https://vimass.vn/pushNotification/services/adminPush/getDetailMess";
    public static String SERVICE_XOA_THONG_BAO = "https://vimass.vn/pushNotification/services/adminPush/deleteMess";
    public static String SERVICE_PHANLOAI_THONGBAO = ROOT + "vmbank/services/quanTriPush/mappingTypeShowVaPhanLoai";

    //lay toa do
    public static String SERVICE_LAY_TOA_DO = "http://103.21.150.8:62168/toaDoVimass/services/toaDoVimass/requestCommand";
    public static String SERVICE_Tra_Cuu_Toa_Do_QR = "https://vimass.vn/VimassQR/services/VMQR/requestCommand";
    public static String SERVICE_CHI_TIET_QR = "https://vimass.vn/VimassQR/services/VMDonHang/requestCommand";
    public static String SERVICE_Check_Login = "http://118.69.84.243:8080/vmNoiBo/services/account/requestComand";
    //LIEN HE
    public static String SEVICE_DANH_SACH_CAN_BO_NV = "http://103.21.150.9:8080/vimass-staff/services/nhan-vien";
    //toa do di chuyen
    public static String SERVICE_List_Xe = "http://103.21.150.8:62168/toaDoVimass/services/toaDoDongVimass/requestCommand";

}
