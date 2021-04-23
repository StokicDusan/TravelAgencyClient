package com.etf.zadatak2.data;

import java.io.Serializable;

public class Arrangement implements Serializable {

    private int arrangement_id;
    private Customer customer;
    private Offer offer;

    public Arrangement() {
    }

    public Arrangement(int arrangement_id, Customer customer, Offer offer) {
        this.arrangement_id = arrangement_id;
        this.customer = customer;
        this.offer = offer;
    }

    public Arrangement(Customer customer, Offer offer) {
        this.customer = customer;
        this.offer = offer;
    }

    public int getArrangement_id() {
        return arrangement_id;
    }

    public void setArrangement_id(int arrangement_id) {
        this.arrangement_id = arrangement_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Arrangement{arrangement_id=").append(arrangement_id);
        sb.append(", customer=").append(customer);
        sb.append(", offer=").append(offer);
        sb.append('}');
        return sb.toString();
    }

}
