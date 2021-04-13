package com.etf.zadatak2.data;

import java.io.Serializable;


public class Korisnik implements Serializable {

    private int korisnik_id;
    private Kontakt kontakt;
    private Adresa adresa;
    private String ime;
    private String prezime;

    public Korisnik() {
    }

    public Korisnik(int korisnik_id, Kontakt kontakt, Adresa adresa, String ime, String prezime) {
        this.korisnik_id = korisnik_id;
        this.kontakt = kontakt;
        this.adresa = adresa;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Korisnik(Kontakt kontakt, Adresa adresa, String ime, String prezime) {
        this.kontakt = kontakt;
        this.adresa = adresa;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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
        sb.append("Korisnik{korisnik_id=").append(korisnik_id);
        sb.append(", kontakt=").append(kontakt);
        sb.append(", adresa=").append(adresa);
        sb.append(", ime=").append(ime);
        sb.append(", prezime=").append(prezime);
        sb.append('}');
        return sb.toString();
    }

}
