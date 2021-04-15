package com.etf.zadatak2.data;

import java.io.Serializable;


public class Korisnik implements Serializable {

    private int customer_id;
    private Kontakt contact;
    private Adresa address;
    private String name;
    private String prezime;

    public Korisnik() {
    }

    public Korisnik(int customer_id, Kontakt contact, Adresa address, String name, String prezime) {
        this.customer_id = customer_id;
        this.contact = contact;
        this.address = address;
        this.name = name;
        this.prezime = prezime;
    }

    public Korisnik(Kontakt contact, Adresa address, String name, String prezime) {
        this.contact = contact;
        this.address = address;
        this.name = name;
        this.prezime = prezime;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Kontakt getContact() {
        return contact;
    }

    public void setContact(Kontakt contact) {
        this.contact = contact;
    }

    public Adresa getAddress() {
        return address;
    }

    public void setAddress(Adresa address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Korisnik{korisnik_id=").append(customer_id);
        sb.append(", kontakt=").append(contact);
        sb.append(", adresa=").append(address);
        sb.append(", ime=").append(name);
        sb.append(", prezime=").append(prezime);
        sb.append('}');
        return sb.toString();
    }

}
