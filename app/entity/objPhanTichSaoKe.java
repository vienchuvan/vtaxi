package entity;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class objPhanTichSaoKe {
    public objPhanTichSaoKe(String s_soTien, String s_soPhi, String s_idVimass, String s_transId, String s_idRequestMc, String s_IdClient)
    {
        this.soTien = s_soTien;
        this.soPhi = s_soPhi;
        this.idVimass = s_idVimass;
        this.transId = s_transId;
        this.IdClient = s_IdClient;
        this.idRequestMc = s_idRequestMc;
    }
    public String soTien;
    public String soPhi;
    public String idVimass;
    public String transId;
    public String idRequestMc;
    public String IdClient;
}
