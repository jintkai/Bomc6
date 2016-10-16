package com.code.page.ibnmsConfig.reslist.domain;

/**
 * Created by jon on 16/5/18.
 */
public class ResHostFormDomain {
    private String device_id;
    private String device_name;
    private String manufacturer;
    private String usage;
    private String ip_addr;
    private String linkman;
    private String bz_type;
    private String unit_id;
    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }



    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getIp_addr() {
        return ip_addr;
    }

    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getBz_type() {
        return bz_type;
    }

    public void setBz_type(String bz_type) {
        this.bz_type = bz_type;
    }
}
