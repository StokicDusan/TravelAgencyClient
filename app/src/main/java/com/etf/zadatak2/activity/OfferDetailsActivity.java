package com.etf.zadatak2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etf.zadatak2.R;
import com.etf.zadatak2.api.ApiClient;
import com.etf.zadatak2.api.ApiInterface;
import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.data.OfferPicture;
import com.etf.zadatak2.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetaljiPonudeActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    private String ponudaVrsta;

    private TextView labelFotoGalerija;
    private TextView labelNaziv;
    private TextView labelDrzava;
    private TextView labelMesto;
    private TextView labelOpis;
    private LayoutInflater inflater;

    private LinearLayout horizontScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_ponude);

        apiInterface = ApiClient.getClient(ApiInterface.class);
        initComponents();
    }

    private void initComponents() {
        ponudaVrsta = "";

        //prihvatanje id-ja ponude
        Bundle extr = getIntent().getExtras();
        int id = 0;
        if (extr != null) {
            id = extr.getInt("id");
        }

        labelFotoGalerija = findViewById(R.id.labelFotoGalerija);
        labelNaziv = findViewById(R.id.labelNaziv);
        labelDrzava = findViewById(R.id.labelDrzava);
        labelMesto = findViewById(R.id.labelMesto);
        labelOpis = findViewById(R.id.labelOpis);

        ((ImageButton) findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPonudaById(id);
        getPonudaSlikaByPonudaId(id);
    }

    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("ponuda", ponudaVrsta);
        setResult(RESULT_OK, i);
        super.finish();
    }

    private void getPonudaById(int id) {
        Call<Offer> callLanguage = apiInterface.getOfferById(id);
        final Dialog dialog = LoadDialog.loadDialog(DetaljiPonudeActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    labelFotoGalerija.setText(String.format("%s Galerija", response.body().getName()));
                    labelNaziv.setText(response.body().getName());
                    labelDrzava.setText(String.format("Drzava: %s", response.body().getCountry()));
                    labelMesto.setText(String.format("Mesto: %s", response.body().getLocation()));
                    labelOpis.setText(response.body().getDescription());
                    ponudaVrsta = response.body().getOffer_type().getName();
                }
            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetaljiPonudeActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    private void getPonudaSlikaByPonudaId(int id) {
        Call<List<OfferPicture>> callLanguage = apiInterface.getOfferPictureByOfferId(id);
        final Dialog dialog = LoadDialog.loadDialog(DetaljiPonudeActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<OfferPicture>>() {
            @Override
            public void onResponse(Call<List<OfferPicture>> call, Response<List<OfferPicture>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    if (response.body().isEmpty()) {
                        labelFotoGalerija.setText(String.format("Galerija je prazna"));
                    } else {
                        for (OfferPicture offerPicture : response.body()) {
                            horizontScroll = (LinearLayout) findViewById(R.id.horizontScroll);
                            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                            RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.picture_view, null);
                            ((TextView) item.findViewById(R.id.textPicture)).setText(offerPicture.getShort_description());
                            String naziv = offerPicture.getName();
                            naziv = naziv.substring(0, naziv.lastIndexOf('.'));
                            String uri = "@drawable/" + naziv;
                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                            Drawable imageDrawable = getResources().getDrawable(imageResource);
                            ((ImageView) item.findViewById(R.id.imageViewPicture)).setImageDrawable(imageDrawable);

                            horizontScroll.addView(item);
                        }
                    }

                } else {
                    RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.picture_view, null);
                    ((TextView) item.findViewById(R.id.textPicture)).setText(R.string.noGallery);
                    ((ImageView) item.findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.image_001);
                    horizontScroll.addView(item);
                }
            }

            @Override
            public void onFailure(Call<List<OfferPicture>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetaljiPonudeActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
}