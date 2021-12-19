package com.latrosoft.farmerfact.model;

public class CropListModel {
    String link,name,acre,id;

    public CropListModel(String link, String name, String acre,String id) {
        this.link = link;
        this.name = name;
        this.acre = acre;
        this.id= id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcre() {
        return acre;
    }

    public void setAcre(String acre) {
        this.acre = acre;
    }
}
