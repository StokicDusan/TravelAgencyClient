package com.etf.zadatak2.api;

import com.etf.zadatak2.data.Arrangement;
import com.etf.zadatak2.data.Customer;
import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferPicture;
import com.etf.zadatak2.data.OfferType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("offer/{offer_id}")
    Call<Offer> getOfferById(@Path("offer_id") int offer_id);

    @GET("offer/type_of_offer/{type}")
    Call<List<Offer>> getOfferByType(@Path("type") String type);

    @GET("offer/picture/offer/{offer_id}")
    Call<List<OfferPicture>> getOfferPictureByOfferId(@Path("offer_id") int offer_id);

    @GET("offer/type/")
    Call<List<OfferType>> getOfferType();


    @GET("offer/country/{country}")
    Call<List<Offer>> getOfferByCountry(@Path("country") String country);

    @GET("offer/country/{country}/{description}")
    Call<List<Offer>> getOfferByCountry(@Path("country") String country, @Path("description") String description);

    @GET("offer/location/{location}")
    Call<List<Offer>> getOfferByLocation(@Path("location") String location);

    @POST("offer")
    Call<Void> addOffer(@Body Offer offer);

    @POST("offer/type")
    Call<Void> addOfferType(@Body OfferType offerType);

    @POST("offer/picture")
    Call<Void> addOfferPicture(@Body OfferPicture offerPicture);

    @PUT("offer")
    Call<Void> updateOffer(@Body Offer offer);

    @DELETE("offer/{offer_id}")
    Call<Void> deleteOffer(@Path("offer_id") int offer_id);

    @GET("customer/{customer_id}")
    Call<Customer> getCustomerById(@Path("customer_id") int customer_id);

    @POST("customer")
    Call<Void> addCustomer(@Body Customer customer);

    @PUT("customer")
    Call<Void> updateCustomer(@Body Customer customer);

    @DELETE("customer/{customer_id}")
    Call<Void> deleteCustomer(@Path("customer_id") int customer_id);

    @GET("arrangement")
    Call<List<Arrangement>> getArrangement();

    @POST("arrangement")
    Call<Void> makeArrangement(@Body Arrangement arrangement);


}
