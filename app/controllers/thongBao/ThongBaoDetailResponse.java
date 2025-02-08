package controllers.thongBao;

import entity.ResponseMessage_Root;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 7/7/22
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThongBaoDetailResponse extends ResponseMessage_Root {
    public aps result;

    public aps getResult() {
        return result;
    }

    public void setResult(aps result) {
        this.result = result;
    }
}
