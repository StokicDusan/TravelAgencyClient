package com.etf.zadatak2.data;

import java.io.Serializable;


public class Kontakt implements Serializable {

    private int kontakt_id;
    private String telefon;
    private String email;

    public Kontakt() {
    }

    public Kontakt(int kontakt_id, String telefon, String email) {
        this.kontakt_id = kontakt_id;
        this.telefon = telefon;
        this.email = email;
    }

    public Kontakt(String telefon, String email) {
        this.telefon = telefon;
        this.email = email;
    }

    public int getKontakt_id() {
        return kontakt_id;
    }

    public void setKontakt_id(int kontakt_id) {
        this.kontakt_id = kontakt_id;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
        sb.append("Kontakt{kontakt_id=").append(kontakt_id);
        sb.append(", telefon=").append(telefon);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

}
