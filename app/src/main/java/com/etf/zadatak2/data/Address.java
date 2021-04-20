package com.etf.zadatak2.data;

import java.io.Serializable;

public class Address implements Serializable {

    private int address_id;
    private String country;
    private String city;
    private String street;
    private int number;

    public Address() {
    }

    public Address(int address_id, String country, String city, String street, int number) {
        this.address_id = address_id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Address(String country, String city, String street, int number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{address_id=").append(address_id);
        sb.append(", country=").append(country);
        sb.append(", city=").append(city);
        sb.append(", street=").append(street);
        sb.append(", number=").append(number);
        sb.append('}');
        return sb.toString();
    }

}
