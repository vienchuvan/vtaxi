package controllers.tools;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessage<T> implements Serializable {
    private int msgCode;
    private String msgContent;
    private T result;
    private double total;
    private double totalMoney;
    private int total2;

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }
}

