package entity;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 12/12/22
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class cameraEntity {
    public String appKey;
    public String appSecret;
    public String code;
    public String msg;

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

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
