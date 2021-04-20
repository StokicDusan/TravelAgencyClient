package com.etf.zadatak2.data;

import java.io.Serializable;


public class OfferPicture implements Serializable {

    private int offer_picture_id;
    private Offer offer;
    private String name;
    private String short_description;
    private Boolean active;

    public OfferPicture() {
    }

    public OfferPicture(int offer_picture_id, Offer offer, String name, String short_description, Boolean active) {
        this.offer_picture_id = offer_picture_id;
        this.offer = offer;
        this.name = name;
        this.short_description = short_description;
        this.active = active;
    }

    public OfferPicture(Offer offer, String name, String short_description, Boolean active) {
        this.offer = offer;
        this.name = name;
        this.short_description = short_description;
        this.active = active;
    }

    public int getOffer_picture_id() {
        return offer_picture_id;
    }

    public void setOffer_picture_id(int offer_picture_id) {
        this.offer_picture_id = offer_picture_id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OfferPicture{offer_picture_id=").append(offer_picture_id);
        sb.append(", offer=").append(offer);
        sb.append(", name=").append(name);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

}
