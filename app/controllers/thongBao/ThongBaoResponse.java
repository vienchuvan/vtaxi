package controllers.thongBao;

import entity.ResponseMessage_Root;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 7/7/22
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThongBaoResponse extends ResponseMessage_Root {
    public ArrayList<aps> result;
    public int totalAll;

    public ArrayList<aps> getResult() {
        return result;
    }

    public void setResult(ArrayList<aps> result) {
        this.result = result;
    }

    public int getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(int totalAll) {
        this.totalAll = totalAll;
    }
}