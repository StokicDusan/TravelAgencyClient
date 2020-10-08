package com.etf.RMS.zadatak2.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class PonudaVrsta implements Serializable {

    private int ponuda_vrsta_id;
    private String naziv;

    public PonudaVrsta() {
    }

    public PonudaVrsta(int ponuda_vrsta_id, String naziv) {
        this.ponuda_vrsta_id = ponuda_vrsta_id;
        this.naziv = naziv;
    }

    public PonudaVrsta(String naziv) {
        this.naziv = naziv;
    }

    public int getPonuda_vrsta_id() {
        return ponuda_vrsta_id;
    }

    public void setPonuda_vrsta_id(int ponuda_vrsta_id) {
        this.ponuda_vrsta_id = ponuda_vrsta_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PonudaVrsta{ponuda_vrsta_id=").append(ponuda_vrsta_id);
        sb.append(", naziv=").append(naziv);
        sb.append('}');
        return sb.toString();
    }

}
