package com.latrosoft.farmerfact.model;

public class TableModel {
    String fertilizer,qty,time;

    public TableModel(String fertilizer, String qty, String time) {
        this.fertilizer = fertilizer;
        this.qty = qty;
        this.time = time;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
