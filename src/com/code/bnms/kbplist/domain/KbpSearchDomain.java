package com.code.bnms.kbplist.domain;

import com.code.common.JacksonUtil;

/**
 * Created by jon on 16/5/4.
 */
public class KbpSearchDomain {
    private String kbp_class;
    private String kbp_Caption;

    public String getKbp_class() {
        return kbp_class;
    }

    public void setKbp_class(String kbp_class) {
        this.kbp_class = kbp_class;
    }

    public String getKbp_Caption() {
        return kbp_Caption;
    }

    public void setKbp_Caption(String kbp_Caption) {
        this.kbp_Caption = kbp_Caption;
    }
    public String toString(){
        return JacksonUtil.toJSon(this);
    }
}
