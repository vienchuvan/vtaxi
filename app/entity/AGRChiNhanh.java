package entity;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class AGRChiNhanh {
    public AGRChiNhanh(String value, String text){
        this.setText(text);
        this.setValue(value);
    }
    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
