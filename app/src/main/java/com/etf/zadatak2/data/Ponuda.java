package com.etf.zadatak2.data;

import java.io.Serializable;


public class Ponuda implements Serializable {

    private int ponuda_id;
    private PonudaVrsta ponuda_vrsta;
    private String drzava;
    private String mesto;
    private String naziv;
    private String opis;
    private Boolean aktivna;

    public Ponuda() {
    }

    public Ponuda(int ponuda_id, PonudaVrsta ponuda_vrsta, String drzava, String mesto, String opis, Boolean aktivna) {
        this.ponuda_id = ponuda_id;
        this.ponuda_vrsta = ponuda_vrsta;
        this.drzava = drzava;
        this.mesto = mesto;
        this.opis = opis;
        this.aktivna = aktivna;
    }

    public Ponuda(PonudaVrsta ponuda_vrsta, String drzava, String mesto, String opis, Boolean aktivna) {
        this.ponuda_vrsta = ponuda_vrsta;
        this.drzava = drzava;
        this.mesto = mesto;
        this.opis = opis;
        this.aktivna = aktivna;
    }

    public int getPonuda_id() {
        return ponuda_id;
    }

    public void setPonuda_id(int ponuda_id) {
        this.ponuda_id = ponuda_id;
    }

    public PonudaVrsta getPonuda_vrsta() {
        return ponuda_vrsta;
    }

    public void setPonuda_vrsta(PonudaVrsta ponuda_vrsta) {
        this.ponuda_vrsta = ponuda_vrsta;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Boolean getAktivna() {
        return aktivna;
    }

    public void setAktivna(Boolean aktivna) {
        this.aktivna = aktivna;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ponuda{ponuda_id=").append(ponuda_id);
        sb.append(", ponuda_vrsta=").append(ponuda_vrsta);
        sb.append(", drzava=").append(drzava);
        sb.append(", mesto=").append(mesto);
        sb.append(", opis=").append(opis);
        sb.append(", aktivna=").append(aktivna);
        sb.append('}');
        return sb.toString();
    }

}
