package com.example.ttb.regisn.bean;

/**
 * Created by ttb on 16/4/18.
 */
public class InfoBean {
    private String code;
    private String text;
    private String category;

    public InfoBean( String code,String text, String category) {
        this.text = text;
        this.code = code;
        this.category = category;
    }
    public InfoBean( ) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return text;
    }
}
