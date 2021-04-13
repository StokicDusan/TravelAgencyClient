package com.etf.RMS.TravelAgencyClien.data;

import java.io.Serializable;

/**
 *
 * @author Dušan Stokić 2013/0625
 */
public class Adresa implements Serializable {

    private int adresa_id;
    private String drzava;
    private String grad;
    private String ulica;
    private int broj;

    public Adresa() {
    }

    public Adresa(int adresa_id, String drzava, String grad, String ulica, int broj) {
        this.adresa_id = adresa_id;
        this.drzava = drzava;
        this.grad = grad;
        this.ulica = ulica;
        this.broj = broj;
    }

    public Adresa(String drzava, String grad, String ulica, int broj) {
        this.drzava = drzava;
        this.grad = grad;
        this.ulica = ulica;
        this.broj = broj;
    }

    public int getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(int adresa_id) {
        this.adresa_id = adresa_id;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adresa{adresa_id=").append(adresa_id);
        sb.append(", drzava=").append(drzava);
        sb.append(", grad=").append(grad);
        sb.append(", ulica=").append(ulica);
        sb.append(", broj=").append(broj);
        sb.append('}');
        return sb.toString();
    }

}
