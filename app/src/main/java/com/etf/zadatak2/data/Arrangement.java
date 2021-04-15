package com.etf.zadatak2.data;

import java.io.Serializable;

public class Aranzman implements Serializable {

    private int aranzman_id;
    private Customer customer;
    private Offer offer;

    public Aranzman() {
    }

    public Aranzman(int aranzman_id, Customer customer, Offer offer) {
        this.aranzman_id = aranzman_id;
        this.customer = customer;
        this.offer = offer;
    }

    public Aranzman(Customer customer, Offer offer) {
        this.customer = customer;
        this.offer = offer;
    }

    public int getAranzman_id() {
        return aranzman_id;
    }

    public void setAranzman_id(int aranzman_id) {
        this.aranzman_id = aranzman_id;
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
        sb.append("Aranzman{aranzman_id=").append(aranzman_id);
        sb.append(", customer=").append(customer);
        sb.append(", offer=").append(offer);
        sb.append('}');
        return sb.toString();
    }

}
