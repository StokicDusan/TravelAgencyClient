package com.etf.RMS.TravelAgencyClien.activity;

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

import com.etf.RMS.TravelAgencyClien.R;
import com.etf.RMS.TravelAgencyClien.api.ApiClient;
import com.etf.RMS.TravelAgencyClien.api.ApiInterface;
import com.etf.RMS.TravelAgencyClien.data.Ponuda;
import com.etf.RMS.TravelAgencyClien.data.PonudaSlika;
import com.etf.RMS.TravelAgencyClien.dialog.LoadDialog;

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
    private void initComponents(){
        ponudaVrsta = "";

        //prihvatanje id-ja ponude
        Bundle extr = getIntent().getExtras();
        int id = 0;
        if (extr != null) { id = extr.getInt("id"); }

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
        i.putExtra("ponuda",ponudaVrsta);
        setResult(RESULT_OK,i);
        super.finish();
    }

    private void getPonudaById(int id){
        Call<Ponuda> callLanguage = apiInterface.getPonudaById(id);
        final Dialog dialog = LoadDialog.loadDialog(DetaljiPonudeActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<Ponuda>() {
            @Override
            public void onResponse(Call<Ponuda> call, Response<Ponuda> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if(response.code() == HttpURLConnection.HTTP_OK && response.body() != null){
                    labelFotoGalerija.setText(String.format("%s Galerija", response.body().getNaziv()));
                    labelNaziv.setText(response.body().getNaziv());
                    labelDrzava.setText(String.format("Drzava: %s",response.body().getDrzava()));
                    labelMesto.setText(String.format("Mesto: %s",response.body().getMesto()));
                    labelOpis.setText(response.body().getOpis());
                    ponudaVrsta=response.body().getPonuda_vrsta().getNaziv();
                }
            }

            @Override
            public void onFailure(Call<Ponuda> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetaljiPonudeActivity.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    private void getPonudaSlikaByPonudaId(int id){
        Call<List<PonudaSlika>> callLanguage = apiInterface.getPonudaSlikaByPonudaId(id);
        final Dialog dialog = LoadDialog.loadDialog(DetaljiPonudeActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<PonudaSlika>>() {
            @Override
            public void onResponse(Call<List<PonudaSlika>> call, Response<List<PonudaSlika>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    if(response.body().isEmpty()){
                        labelFotoGalerija.setText(String.format("Galerija je prazna"));
                    }else {
                        for (PonudaSlika ponudaSlika : response.body()) {
                            horizontScroll = (LinearLayout) findViewById(R.id.horizontScroll);
                            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                            RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.slika_view, null);
                            ((TextView) item.findViewById(R.id.textSlika)).setText(ponudaSlika.getKratak_opis());
                            String naziv = ponudaSlika.getNaziv();
                            naziv = naziv.substring(0, naziv.lastIndexOf('.'));
                            String uri = "@drawable/" + naziv;
                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                            Drawable imageDrawable = getResources().getDrawable(imageResource);
                            ((ImageView) item.findViewById(R.id.imageViewSlika)).setImageDrawable(imageDrawable);

                            horizontScroll.addView(item);
                        }
                    }

                } else {
                    RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.slika_view, null);
                    ((TextView) item.findViewById(R.id.textSlika)).setText(R.string.nemaGalerije);
                    ((ImageView) item.findViewById(R.id.imageViewSlika)).setImageResource(R.drawable.image_001);
                    horizontScroll.addView(item);
                }
            }
            @Override
            public void onFailure(Call<List<PonudaSlika>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DetaljiPonudeActivity.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
}