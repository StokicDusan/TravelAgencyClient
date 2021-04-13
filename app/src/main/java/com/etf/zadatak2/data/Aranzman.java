package com.etf.zadatak2.data;

import java.io.Serializable;

public class Aranzman implements Serializable {

    private int aranzman_id;
    private Korisnik korisnik;
    private Ponuda ponuda;

    public Aranzman() {
    }

    public Aranzman(int aranzman_id, Korisnik korisnik, Ponuda ponuda) {
        this.aranzman_id = aranzman_id;
        this.korisnik = korisnik;
        this.ponuda = ponuda;
    }

    public Aranzman(Korisnik korisnik, Ponuda ponuda) {
        this.korisnik = korisnik;
        this.ponuda = ponuda;
    }

    public int getAranzman_id() {
        return aranzman_id;
    }

    public void setAranzman_id(int aranzman_id) {
        this.aranzman_id = aranzman_id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Ponuda getPonuda() {
        return ponuda;
    }

    public void setPonuda(Ponuda ponuda) {
        this.ponuda = ponuda;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aranzman{aranzman_id=").append(aranzman_id);
        sb.append(", korisnik=").append(korisnik);
        sb.append(", ponuda=").append(ponuda);
        sb.append('}');
        return sb.toString();
    }

}
