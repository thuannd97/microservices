package com.thuannd.api.api.composite.product;

import java.io.Serializable;

public class ServiceAddesses implements Serializable{

    private static final long serialVersionUID = 1L;

    private String cmp;
    private String pro;
    private String rev;
    private String rec;

    public ServiceAddesses(){
        cmp = null;
        pro = null;
        rev = null;
        rec = null;
    }

    public ServiceAddesses(String cmp, String pro, String rev, String rec) {
        this.cmp = cmp;
        this.pro = pro;
        this.rev = rev;
        this.rec = rec;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }
}