package com.etf.zadatak2.data;

import java.io.Serializable;


public class PonudaSlika implements Serializable {

    private int ponuda_slika_id;
    private Ponuda ponuda;
    private String naziv;
    private String kratak_opis;
    private Boolean aktivna;

    public PonudaSlika() {
    }

    public PonudaSlika(int ponuda_slika_id, Ponuda ponuda, String naziv, String kratak_opis, Boolean aktivna) {
        this.ponuda_slika_id = ponuda_slika_id;
        this.ponuda = ponuda;
        this.naziv = naziv;
        this.kratak_opis = kratak_opis;
        this.aktivna = aktivna;
    }

    public PonudaSlika(Ponuda ponuda, String naziv, String kratak_opis, Boolean aktivna) {
        this.ponuda = ponuda;
        this.naziv = naziv;
        this.kratak_opis = kratak_opis;
        this.aktivna = aktivna;
    }

    public int getPonuda_slika_id() {
        return ponuda_slika_id;
    }

    public void setPonuda_slika_id(int ponuda_slika_id) {
        this.ponuda_slika_id = ponuda_slika_id;
    }

    public Ponuda getPonuda() {
        return ponuda;
    }

    public void setPonuda(Ponuda ponuda) {
        this.ponuda = ponuda;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKratak_opis() {
        return kratak_opis;
    }

    public void setKratak_opis(String kratak_opis) {
        this.kratak_opis = kratak_opis;
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
        sb.append("PonudaSlika{ponuda_slika_id=").append(ponuda_slika_id);
        sb.append(", ponuda=").append(ponuda);
        sb.append(", naziv=").append(naziv);
        sb.append(", aktivna=").append(aktivna);
        sb.append('}');
        return sb.toString();
    }

}
