package entity;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessage_Root {
    private int msgCode;
    private String msgContent;

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
}
