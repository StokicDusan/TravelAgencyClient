package com.etf.zadatak2.data;

import java.io.Serializable;


public class OfferType implements Serializable {

    private int offer_type_id;
    private String name;

    public OfferType() {
    }

    public OfferType(int offer_type_id, String name) {
        this.offer_type_id = offer_type_id;
        this.name = name;
    }

    public OfferType(String name) {
        this.name = name;
    }

    public int getOffer_type_id() {
        return offer_type_id;
    }

    public void setOffer_type_id(int offer_type_id) {
        this.offer_type_id = offer_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OfferType{offer_type_id=").append(offer_type_id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

}
