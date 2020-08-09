
package com.example.huylvph08000_adroidnetworking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("farm")
    @Expose
    private Integer farm;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ispublic")
    @Expose
    private Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    private Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    private Integer isfamily;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("date_faved")
    @Expose
    private String dateFaved;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("media_status")
    @Expose
    private String mediaStatus;
    @SerializedName("url_l")
    @Expose
    private String urlL;
    @SerializedName("height_l")
    @Expose
    private Integer heightL;
    @SerializedName("width_l")
    @Expose
    private Integer widthL;
    @SerializedName("url_o")
    @Expose
    private String urlO;
    @SerializedName("height_o")
    @Expose
    private Integer heightO;
    @SerializedName("width_o")
    @Expose
    private Integer widthO;
    @SerializedName("pathalias")
    @Expose
    private Object pathalias;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public Integer getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDateFaved() {
        return dateFaved;
    }

    public void setDateFaved(String dateFaved) {
        this.dateFaved = dateFaved;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediaStatus() {
        return mediaStatus;
    }

    public void setMediaStatus(String mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public Integer getHeightL() {
        return heightL;
    }

    public void setHeightL(Integer heightL) {
        this.heightL = heightL;
    }

    public Integer getWidthL() {
        return widthL;
    }

    public void setWidthL(Integer widthL) {
        this.widthL = widthL;
    }

    public String getUrlO() {
        return urlO;
    }

    public void setUrlO(String urlO) {
        this.urlO = urlO;
    }

    public Integer getHeightO() {
        return heightO;
    }

    public void setHeightO(Integer heightO) {
        this.heightO = heightO;
    }

    public Integer getWidthO() {
        return widthO;
    }

    public void setWidthO(Integer widthO) {
        this.widthO = widthO;
    }

    public Object getPathalias() {
        return pathalias;
    }

    public void setPathalias(Object pathalias) {
        this.pathalias = pathalias;
    }


}
