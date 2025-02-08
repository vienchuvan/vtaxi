package controllers.thaydoi;

import controllers.tools.ResponseMessage;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorCodeWeb {
    public final static int SUCCESS = 1;
    public final static String MES_SUCCESS = "Đang xử lý giao dịch !";
    public final static int FALSE = 0;
    public final static String MES_FALSE = "Giao dịch không thành công!";
    public final static String MES_FALSE_EN = "Transaction failed!";
    public static ResponseMessage getObjectMessageResult(int code, String mess)
    {
        ResponseMessage obj = new ResponseMessage();
        obj.setMsgCode(code);
        obj.setMsgContent(mess);

        obj.setResult("");

        return obj;
    }
}
