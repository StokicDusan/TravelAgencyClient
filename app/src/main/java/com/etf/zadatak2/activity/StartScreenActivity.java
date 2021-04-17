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

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner offerType;
    private ArrayList<String> listOfferType;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        apiInterface = ApiClient.getClient(ApiInterface.class);
        initUiComponents();
    }

    private void initUiComponents() {

        listOfferType = new ArrayList<String>();
        listOfferType.add(getString(R.string.chooseTravel));
        getOfferType();
        offerType = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapterOfferType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listOfferType);

        offerType.setAdapter(adapterOfferType);

        findViewById(R.id.buttonSave).setOnClickListener(this);
        findViewById(R.id.buttonRecreate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                doSend();
                break;
            case R.id.buttonRecreate:
                recreate();
                break;
            default:
                finish();
        }
    }

    private void getOfferType() {
        Call<List<OfferType>> callLanguage = apiInterface.getOfferType();
        final Dialog dialog = LoadDialog.loadDialog(StartScreenActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<OfferType>>() {
            @Override
            public void onResponse(Call<List<OfferType>> call, Response<List<OfferType>> response) {
                if (dialog.isShowing()) dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    for (OfferType temp : response.body()) {
                        listOfferType.add(temp.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OfferType>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(StartScreenActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    private void doSend() {
        Intent chooseOfferType = new Intent(this, OffersActivity.class);
        Bundle extra = new Bundle();
        String offer = (String) offerType.getSelectedItem();
        extra.putString("offer", offer);
        chooseOfferType.putExtras(extra);
        startActivity(chooseOfferType);
    }
}