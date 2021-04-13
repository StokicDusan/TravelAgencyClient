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
import com.etf.zadatak2.data.PonudaVrsta;
import com.etf.zadatak2.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PocetniEkran extends AppCompatActivity implements View.OnClickListener {
    private Spinner ponudaVrsta;
    private ArrayList<String> listaPonudaVrsta;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_ekran);
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
        Call<List<PonudaVrsta>> callLanguage = apiInterface.getPonudaVrsta();
        final Dialog dialog = LoadDialog.loadDialog(PocetniEkran.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<PonudaVrsta>>() {
            @Override
            public void onResponse(Call<List<PonudaVrsta>> call, Response<List<PonudaVrsta>> response) {
                if (dialog.isShowing()) dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    for (PonudaVrsta temp : response.body()) {
                        listaPonudaVrsta.add(temp.getNaziv());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PonudaVrsta>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(PocetniEkran.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
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