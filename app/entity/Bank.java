package entity;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/9/22
 * Time: 11:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bank {
    private int ID;
    private int CODE;
    private String NAME_VI;
    private String NAME_EN;
    private String SMS;
    private String ORDER;

    public Bank(int id, int code, String name_vi, String name_en, String sms, String order)
    {
        ID = id;
        CODE = code;
        NAME_VI = name_vi;
        NAME_EN = name_en;
        SMS = sms;
        ORDER = order;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCODE() {
        return CODE;
    }

    public void setCODE(int CODE) {
        this.CODE = CODE;
    }

    public String getNAME_VI() {
        return NAME_VI;
    }

    public void setNAME_VI(String NAME_VI) {
        this.NAME_VI = NAME_VI;
    }

    public String getNAME_EN() {
        return NAME_EN;
    }

    public void setNAME_EN(String NAME_EN) {
        this.NAME_EN = NAME_EN;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String SMS) {
        this.SMS = SMS;
    }

    public String getORDER() {
        return ORDER;
    }

    public void setORDER(String ORDER) {
        this.ORDER = ORDER;
    }
}
