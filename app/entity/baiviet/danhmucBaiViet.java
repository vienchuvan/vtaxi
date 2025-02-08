package entity.baiviet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 11/3/22
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class danhmucBaiViet {
        private String id;
        private String title_vi;
        private String title_en;
        private String img_vi;
        private String img_en;
        private int pos;
        private int langId;
        private List<baivietEntiy> listDoccument;

    public String getTitle_vi() {
        return title_vi;
    }

    public void setTitle_vi(String title_vi) {
        this.title_vi = title_vi;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getImg_vi() {
        return img_vi;
    }

    public void setImg_vi(String img_vi) {
        this.img_vi = img_vi;
    }

    public String getImg_en() {
        return img_en;
    }

    public void setImg_en(String img_en) {
        this.img_en = img_en;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public List<baivietEntiy> getListDoccument() {
        return listDoccument;
    }

    public void setListDoccument(List<baivietEntiy> listDoccument) {
        this.listDoccument = listDoccument;
    }
}