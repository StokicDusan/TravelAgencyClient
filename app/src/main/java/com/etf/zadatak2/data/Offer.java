package com.etf.zadatak2.data;

import java.io.Serializable;


public class Offer implements Serializable {

    private int offer_id;
    private OfferType offer_type;
    private String country;
    private String location;
    private String name;
    private String description;
    private Boolean active;

    public Offer() {
    }

    public Offer(int offer_id, OfferType offer_type, String country, String location, String name, String description, Boolean active) {
        this.offer_id = offer_id;
        this.offer_type = offer_type;
        this.country = country;
        this.location = location;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public Offer(OfferType offer_type, String country, String location, String name, String description, Boolean active) {
        this.offer_type = offer_type;
        this.country = country;
        this.location = location;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public OfferType getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(OfferType offer_type) {
        this.offer_type = offer_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append("Offer{offer_id=").append(offer_id);
        sb.append(", offer_type=").append(offer_type);
        sb.append(", country=").append(country);
        sb.append(", location=").append(location);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

}
