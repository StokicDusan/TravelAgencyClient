package com.etf.zadatak2.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.etf.zadatak2.R;
import com.etf.zadatak2.api.ApiClient;
import com.etf.zadatak2.api.ApiInterface;
import com.etf.zadatak2.data.OfferType;
import com.etf.zadatak2.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {
    private Spinner ponudaVrsta;
    private ArrayList<String> listaPonudaVrsta;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        apiInterface = ApiClient.getClient(ApiInterface.class);
        initUiComponents();
    }

    private void initUiComponents() {

        listaPonudaVrsta = new ArrayList<String>();
        listaPonudaVrsta.add("izaberite vrstu putovanja");
        getPonudaVrsta();
        ponudaVrsta = (Spinner) findViewById(R.id.spinnerVrste);
        ArrayAdapter<String> adapterPonudaVrsta = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listaPonudaVrsta);

        ponudaVrsta.setAdapter(adapterPonudaVrsta);

        findViewById(R.id.buttonSacuvaj).setOnClickListener(this);
        findViewById(R.id.buttonRecreate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSacuvaj:
                doSend();
                break;
            case R.id.buttonRecreate:
                recreate();
                break;
            default:
                finish();
        }
    }

    private void getPonudaVrsta() {
        Call<List<OfferType>> callLanguage = apiInterface.getOfferType();
        final Dialog dialog = LoadDialog.loadDialog(StartScreen.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<OfferType>>() {
            @Override
            public void onResponse(Call<List<OfferType>> call, Response<List<OfferType>> response) {
                if (dialog.isShowing()) dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    for (OfferType temp : response.body()) {
                        listaPonudaVrsta.add(temp.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OfferType>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(StartScreen.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    private void doSend() {
        Intent izabranaVrstaPonude = new Intent(this, PonudeActivity.class);
        Bundle extra = new Bundle();
        String ponuda = (String) ponudaVrsta.getSelectedItem();
        extra.putString("ponuda", ponuda);
        izabranaVrstaPonude.putExtras(extra);
        startActivity(izabranaVrstaPonude);
    }
}