package controllers.thongBao;

import entity.ResponseMessage_Root;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 7/7/22
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectResponse extends ResponseMessage_Root {
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}