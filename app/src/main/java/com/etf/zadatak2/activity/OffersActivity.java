package com.etf.zadatak2.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.etf.zadatak2.R;
import com.etf.zadatak2.api.ApiClient;
import com.etf.zadatak2.api.ApiInterface;
import com.etf.zadatak2.data.Offer;
import com.etf.zadatak2.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_dropdown_item;

public class OffersActivity extends AppCompatActivity {
    private static final int request_code = 5;

    ApiInterface apiInterface;
    private ArrayList<String> listOfferCountry;
    private ArrayList<String> listOfferCity;
    private ArrayList<Offer> listOffer;
    private ArrayList<Offer> listForView;
    private Spinner offerCity;
    private Spinner offerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        listForView = new ArrayList<>();
        listOffer = new ArrayList<>();
        listOfferCountry = new ArrayList<>();
        listOfferCity = new ArrayList<>();
        listOfferCountry.add(getString(R.string.chooseCountry));
        listOfferCity.add(getString(R.string.chooseCity));

        initComponents();
    }

    private void initComponents() {
        apiInterface = ApiClient.getClient(ApiInterface.class);

        //Getting specific type of offer
        Bundle extr = getIntent().getExtras();
        String offer = null;
        if (extr != null) offer = extr.getString("offer");

        offerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        offerCity = (Spinner) findViewById(R.id.spinnerCity);

        ArrayAdapter<String> adapterOfferCountry = new ArrayAdapter<String>(this,
                simple_spinner_dropdown_item, listOfferCountry);
        ArrayAdapter<String> adapterOfferCity = new ArrayAdapter<String>(this,
                simple_spinner_dropdown_item, listOfferCity);
        offerCountry.setAdapter(adapterOfferCountry);
        offerCity.setAdapter(adapterOfferCity);

        getOffer(offer);

        ((ImageButton) findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OffersActivity.this, StartScreenActivity.class);
                startActivity(i);
            }
        });


        // Inflating Cities depending on the selected country
        offerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    listOfferCity.clear();
                    listOfferCity.add(getString(R.string.chooseCity));
                    offerCity.setSelection(0);
                    String selectedItemText = (String) parent.getItemAtPosition(position);
                    for (Offer temp : listOffer) {
                        if (selectedItemText.equals(temp.getCountry())) {
                            if (!listOfferCity.contains(temp.getLocation())) {
                                listOfferCity.add(temp.getLocation());
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                offerCountry.setSelection(0);
            }
        });

        // Inflating Offer depending on the selected city
        offerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedItemText = (String) parent.getItemAtPosition(position);
                    doInflate(selectedItemText);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                offerCity.setSelection(0);
                //doInflate(null);

            }
        });
    }


    private void getOffer(String offer) {
        Call<List<Offer>> callLanguage = apiInterface.getOfferByType(offer);
        final Dialog dialog = LoadDialog.loadDialog(OffersActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                if (dialog.isShowing()) dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    for (Offer temp : response.body()) {
                        if (!listOfferCountry.contains(temp.getCountry())) {
                            listOfferCountry.add(temp.getCountry());
                        }
                        if (!listOffer.contains(temp)) {
                            listOffer.add(temp);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OffersActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }


    private void doInflate(String location) {
        listForView.clear();
        LinearLayout mainScrollLayout = findViewById(R.id.offerScrollView);
        mainScrollLayout.removeAllViews();

        if (location != null) {
            for (Offer offer : listOffer) {
                if (offer.getLocation().equals(location)) listForView.add(offer);
            }
        }

        for (final Offer offer : listForView) {
            int id = offer.getOffer_id();

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.offer_view, mainScrollLayout, false);

            String name = offer.getLocation() + " - " + offer.getName();
            ((TextView) item.findViewById(R.id.textOffer)).setText(name);
            if (offer.getActive()) {
                ((TextView) item.findViewById(R.id.textDescription)).setText(offer.getDescription());
                item.setClickable(true);
                final int finalId = id;
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(OffersActivity.this, OfferDetailsActivity.class);
                        Bundle extra = new Bundle();
                        extra.putInt("id", finalId);
                        i.putExtras(extra);
                        startActivityForResult(i, request_code);

                    }
                });
            } else {
                ((TextView) item.findViewById(R.id.textDescription)).setText(R.string.unavailable);
                ((TextView) item.findViewById(R.id.textDescription)).setTextColor(Color.RED);
            }
            mainScrollLayout.addView(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == request_code) &&
                (resultCode == RESULT_OK)) {
            listOfferCity.clear();
            listOffer.clear();
            listForView.clear();
            offerCountry.setSelection(0);
            listOfferCity.add(getString(R.string.chooseCity));
            offerCity.setSelection(0);
            initComponents();
        }

    }
}