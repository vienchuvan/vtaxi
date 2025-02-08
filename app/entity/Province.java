package entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/10/22
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Province {
    private int ID;
    private String NAME_VI;
    private String SMS;
    private String NAME_EN;
    private String ORDER;
    private String LAT;
    private String LNG;
    private List<Province> CHILDS;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME_VI() {
        return NAME_VI;
    }

    public void setNAME_VI(String NAME_VI) {
        this.NAME_VI = NAME_VI;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String SMS) {
        this.SMS = SMS;
    }

    public String getNAME_EN() {
        return NAME_EN;
    }

    public void setNAME_EN(String NAME_EN) {
        this.NAME_EN = NAME_EN;
    }

    public String getORDER() {
        return ORDER;
    }

    public void setORDER(String ORDER) {
        this.ORDER = ORDER;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLNG() {
        return LNG;
    }

    public void setLNG(String LNG) {
        this.LNG = LNG;
    }

    public List<Province> getCHILDS() {
        return CHILDS;
    }

    public void setCHILDS(List<Province> CHILDS) {
        this.CHILDS = CHILDS;
    }
}
