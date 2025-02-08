package entity.Laixe;

/**
 * Created with IntelliJ IDEA.
 * User: welcome
 * Date: 10/26/22
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectItem1DiemDen {
    public double latDen;
    public double lngDen;
    public String diaChiMoTaDen;


    public ObjectItem1DiemDen(double latDen, double lngDen, String diaChiMoTaDen, double km) {
        this.latDen = latDen;
        this.lngDen = lngDen;
        this.diaChiMoTaDen = diaChiMoTaDen;
        this.km = km;
    }

    public double km;


    public double getLatDen() {
        return latDen;
    }

    public void setLatDen(double latDen) {
        this.latDen = latDen;
    }

    public double getLngDen() {
        return lngDen;
    }

    public void setLngDen(double lngDen) {
        this.lngDen = lngDen;
    }

    public String getDiaChiMoTaDen() {
        return diaChiMoTaDen;
    }

    public void setDiaChiMoTaDen(String diaChiMoTaDen) {
        this.diaChiMoTaDen = diaChiMoTaDen;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
