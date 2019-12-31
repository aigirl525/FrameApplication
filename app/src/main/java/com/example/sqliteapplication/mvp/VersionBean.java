package com.example.sqliteapplication.mvp;

public class VersionBean  {
    /**
     * mupdate : 0
     * vernum : 1
     * province :
     * lng :
     * city :
     * loadurl : http://30.4.72.34/webchat_gongan_api/lyapi/wechat_gongan.apk
     * version : 1.0.0
     * content : 1、修复BUG；
     2、增加盗窃机动车报警；
     3、增加违法犯罪报警。
     * lat :
     * environ : stg
     */

    private String mupdate;
    private String vernum;
    private String province;
    private String lng;
    private String city;
    private String loadurl;
    private String version;
    private String content;
    private String lat;
    private String environ;

    public String getMupdate() {
        return mupdate;
    }

    public void setMupdate(String mupdate) {
        this.mupdate = mupdate;
    }

    public String getVernum() {
        return vernum;
    }

    public void setVernum(String vernum) {
        this.vernum = vernum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLoadurl() {
        return loadurl;
    }

    public void setLoadurl(String loadurl) {
        this.loadurl = loadurl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getEnviron() {
        return environ;
    }

    public void setEnviron(String environ) {
        this.environ = environ;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "mupdate='" + mupdate + '\'' +
                ", vernum='" + vernum + '\'' +
                ", province='" + province + '\'' +
                ", lng='" + lng + '\'' +
                ", city='" + city + '\'' +
                ", loadurl='" + loadurl + '\'' +
                ", version='" + version + '\'' +
                ", content='" + content + '\'' +
                ", lat='" + lat + '\'' +
                ", environ='" + environ + '\'' +
                '}';
    }
}

