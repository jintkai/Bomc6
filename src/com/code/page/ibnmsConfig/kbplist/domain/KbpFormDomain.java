package com.code.page.ibnmsConfig.kbplist.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 16/5/4.
 */
public class KbpFormDomain {
    private String KBP_CLASS;
    private String KBP_CAPTION;
    private String KBP_TYPE;
    private String KBP_DESC;
    private String VIEW_STYLE;
    private String ENABLE;
    private String middle;//关联kpi前缀

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getKBP_CLASS() {
        return KBP_CLASS;
    }

    public void setKBP_CLASS(String KBP_CLASS) {
        this.KBP_CLASS = KBP_CLASS;
    }

    public String getKBP_CAPTION() {
        return KBP_CAPTION;
    }

    public void setKBP_CAPTION(String KBP_CAPTION) {
        this.KBP_CAPTION = KBP_CAPTION;
    }

    public String getKBP_TYPE() {
        return KBP_TYPE;
    }

    public void setKBP_TYPE(String KBP_TYPE) {
        this.KBP_TYPE = KBP_TYPE;
    }

    public String getKBP_DESC() {
        return KBP_DESC;
    }

    public void setKBP_DESC(String KBP_DESC) {
        this.KBP_DESC = KBP_DESC;
    }

    public String getVIEW_STYLE() {
        return VIEW_STYLE;
    }

    public void setVIEW_STYLE(String VIEW_STYLE) {
        this.VIEW_STYLE = VIEW_STYLE;
    }

    public String getENABLE() {
        return ENABLE;
    }

    public void setENABLE(String ENABLE) {
        this.ENABLE = ENABLE;
    }

    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
