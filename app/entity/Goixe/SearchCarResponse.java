package entity.Goixe;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/28/22
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchCarResponse {
    public Double timeOrder;
    public String maDat;
    public Double latDon;
    public Double lngDon;
    public Double latDen;
    public Double lngDen;
    public Double km;
    public ArrayList<ListCar> listXe;

    public Double getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Double timeOrder) {
        this.timeOrder = timeOrder;
    }

    public String getMaDat() {
        return maDat;
    }

    public void setMaDat(String maDat) {
        this.maDat = maDat;
    }

    public Double getLatDon() {
        return latDon;
    }

    public void setLatDon(Double latDon) {
        this.latDon = latDon;
    }

    public Double getLngDon() {
        return lngDon;
    }

    public void setLngDon(Double lngDon) {
        this.lngDon = lngDon;
    }

    public Double getLatDen() {
        return latDen;
    }

    public void setLatDen(Double latDen) {
        this.latDen = latDen;
    }

    public Double getLngDen() {
        return lngDen;
    }

    public void setLngDen(Double lngDen) {
        this.lngDen = lngDen;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public ArrayList<ListCar> getListXe() {
        return listXe;
    }

    public void setListXe(ArrayList<ListCar> listXe) {
        this.listXe = listXe;
    }
}
