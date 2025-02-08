package controllers.thongBao;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 7/7/22
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class aps {
    public ObjectAps aps;

    public class ObjectAps {
        public String sound;
        public int appId;
        public int funcID;
        public String alertId;
        public String sender;
        public String nameAlias;
        public String recipient;
        public String nameAliasRecipient;
        public String alert;
        public String alertContent;
        public int badge;
        public long time;
        public int status;
        public String lydo;
        public int totalFunc;
        public String storeId;
        public int typeShow;

        public String getIdShow() {
            return idShow;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }

        public int getFuncID() {
            return funcID;
        }

        public void setFuncID(int funcID) {
            this.funcID = funcID;
        }

        public String getAlertId() {
            return alertId;
        }

        public void setAlertId(String alertId) {
            this.alertId = alertId;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getNameAlias() {
            return nameAlias;
        }

        public void setNameAlias(String nameAlias) {
            this.nameAlias = nameAlias;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public String getNameAliasRecipient() {
            return nameAliasRecipient;
        }

        public void setNameAliasRecipient(String nameAliasRecipient) {
            this.nameAliasRecipient = nameAliasRecipient;
        }

        public String getAlert() {
            return alert;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public String getAlertContent() {
            return alertContent;
        }

        public void setAlertContent(String alertContent) {
            this.alertContent = alertContent;
        }

        public int getBadge() {
            return badge;
        }

        public void setBadge(int badge) {
            this.badge = badge;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLydo() {
            return lydo;
        }

        public void setLydo(String lydo) {
            this.lydo = lydo;
        }

        public int getTotalFunc() {
            return totalFunc;
        }

        public void setTotalFunc(int totalFunc) {
            this.totalFunc = totalFunc;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public int getTypeShow() {
            return typeShow;
        }

        public void setTypeShow(int typeShow) {
            this.typeShow = typeShow;
        }



        public void setIdShow(String idShow) {
            this.idShow = idShow;
        }

        public int getStatusShow() {
            return statusShow;
        }

        public void setStatusShow(int statusShow) {
            this.statusShow = statusShow;
        }

        public int getTuchoi() {
            return tuchoi;
        }

        public void setTuchoi(int tuchoi) {
            this.tuchoi = tuchoi;
        }

        public String getMaKhachHang() {
            return maKhachHang;
        }

        public void setMaKhachHang(String maKhachHang) {
            this.maKhachHang = maKhachHang;
        }

        public int getIconType() {
            return iconType;
        }

        public void setIconType(int iconType) {
            this.iconType = iconType;
        }

        public String idShow;
        public int statusShow;
        public int tuchoi;
        public String maKhachHang;
        public int iconType;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
