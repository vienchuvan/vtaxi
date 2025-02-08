package entity.baiviet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 7/28/22
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class baivietEntiy {
    private String id;
    private String user;
    private String title_vi;
    private String img_vi;
    private String shortContent_vi;
    private String content_vi;
    private String title_en;
    private String img_en;
    private String shortContent_en;
    private String content_en;
    private long timeCreate;
    private long timeEdit;
    private List<ObjectDanhmuc> catParam;
    private String listCheckbox;
    private String listCat;
    private int pos;

    public String getTitle_vi() {
        return title_vi;
    }

    public String getContent_vi() {
        return content_vi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle_vi(String title_vi) {
        this.title_vi = title_vi;
    }

    public String getImg_vi() {
        return img_vi;
    }

    public void setImg_vi(String img_vi) {
        this.img_vi = img_vi;
    }

    public String getShortContent_vi() {
        return shortContent_vi;
    }

    public void setShortContent_vi(String shortContent_vi) {
        this.shortContent_vi = shortContent_vi;
    }

    public void setContent_vi(String content_vi) {
        this.content_vi = content_vi;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getImg_en() {
        return img_en;
    }

    public void setImg_en(String img_en) {
        this.img_en = img_en;
    }

    public String getShortContent_en() {
        return shortContent_en;
    }

    public void setShortContent_en(String shortContent_en) {
        this.shortContent_en = shortContent_en;
    }

    public String getContent_en() {
        return content_en;
    }

    public void setContent_en(String content_en) {
        this.content_en = content_en;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getTimeEdit() {
        return timeEdit;
    }

    public void setTimeEdit(long timeEdit) {
        this.timeEdit = timeEdit;
    }

    public List<ObjectDanhmuc> getCatParam() {
        return catParam;
    }

    public void setCatParam(List<ObjectDanhmuc> catParam) {
        this.catParam = catParam;
    }

    public String getListCheckbox() {
        return listCheckbox;
    }

    public void setListCheckbox(String listCheckbox) {
        this.listCheckbox = listCheckbox;
    }

    public String getListCat() {
        return listCat;
    }

    public void setListCat(String listCat) {
        this.listCat = listCat;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}