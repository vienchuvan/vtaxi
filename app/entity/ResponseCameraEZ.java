package entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 12/12/22
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseCameraEZ <T> implements Serializable {

    public String object;
    public String code;
    public String msg;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
