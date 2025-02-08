package entity;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: hnver
 * Date: 4/27/22
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocationResponse {

    private double lat;
    private double lng;
    private int visiable;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getVisiable() {
        return visiable;
    }

    public void setVisiable(int visiable) {
        this.visiable = visiable;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
