package com.etf.zadatak2.data;

import java.io.Serializable;


public class Customer implements Serializable {

    private int customer_id;
    private Contact contact;
    private Address address;
    private String name;
    private String surname;

    public Customer() {
    }

    public Customer(int customer_id, Contact contact, Address address, String name, String surname) {
        this.customer_id = customer_id;
        this.contact = contact;
        this.address = address;
        this.name = name;
        this.surname = surname;
    }

    public Customer(Contact contact, Address address, String name, String surname) {
        this.contact = contact;
        this.address = address;
        this.name = name;
        this.surname = surname;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{customer_id=").append(customer_id);
        sb.append(", contact=").append(contact);
        sb.append(", address=").append(address);
        sb.append(", name=").append(name);
        sb.append(", surname=").append(surname);
        sb.append('}');
        return sb.toString();
    }

}
