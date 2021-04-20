package com.etf.zadatak2.data;

import java.io.Serializable;


public class Contact implements Serializable {

    private int contact_id;
    private String telephone;
    private String email;

    public Contact() {
    }

    public Contact(int contact_id, String telephone, String email) {
        this.contact_id = contact_id;
        this.telephone = telephone;
        this.email = email;
    }

    public Contact(String telephone, String email) {
        this.telephone = telephone;
        this.email = email;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact{contact_id=").append(contact_id);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

}
