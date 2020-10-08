package com.etf.RMS.zadatak2.api;

import com.etf.RMS.zadatak2.data.Aranzman;
import com.etf.RMS.zadatak2.data.Korisnik;
import com.etf.RMS.zadatak2.data.Ponuda;
import com.etf.RMS.zadatak2.data.PonudaSlika;
import com.etf.RMS.zadatak2.data.PonudaVrsta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("ponuda/{ponuda_id}")
    Call<Ponuda> getPonudaById(@Path("ponuda_id") int ponuda_id);

    @GET("ponuda/vrsta_ponude/{vrsta}")
    Call<List<Ponuda>> getPonudaByVrsta(@Path("vrsta") String vrsta);

    @GET("ponuda/slika/ponuda/{ponuda_id}")
    Call<List<PonudaSlika>> getPonudaSlikaByPonudaId(@Path("ponuda_id") int ponuda_id);

    @GET("ponuda/vrsta/")
    Call<List<PonudaVrsta>> getPonudaVrsta();



    @GET("ponuda/drzava/{drzava}")
    Call<List<Ponuda>> getPonudaByDrzava(@Path("drzava") String drzava);

    @GET("ponuda/drzava/{drzava}/{opis}")
    Call<List<Ponuda>> getPonudaByDrzava(@Path("drzava") String drzava, @Path("opis") String opis);

    @GET("ponuda/mesto/{mesto}")
    Call<List<Ponuda>> getPonudaByMesto(@Path("mesto") String mesto);

    @POST("ponuda")
    Call<Void> addPonuda(@Body Ponuda ponuda);

    @POST("ponuda/vrsta")
    Call<Void> addPonudaVrsta(@Body PonudaVrsta ponuda_vrsta);

    @POST("ponuda/slika")
    Call<Void> addPonudaSlika(@Body PonudaSlika ponuda_slika);

    @PUT("ponuda")
    Call<Void> updatePonuda(@Body Ponuda ponuda);

    @DELETE("ponuda/{ponuda_id}")
    Call<Void> deletePonuda(@Path("ponuda_id") int ponuda_id);

    @GET("korisnik/{korisnik_id}")
    Call<Korisnik> getKorisnikById(@Path("korisnik_id") int korisnik_id);

    @POST("korisnik")
    Call<Void> addKorisnik(@Body Korisnik korisnik);

    @PUT("korisnik")
    Call<Void> updateKorisnik(@Body Korisnik korisnik);

    @DELETE("korisnik/{korisnik_id}")
    Call<Void> deleteKorisnik(@Path("korisnik_id") int korisnik_id);

    @GET("aranzman")
    Call<List<Aranzman>> getAranzman();

    @POST("aranzman")
    Call<Void> makeAranzman(@Body Aranzman aranzman);



}
