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

public class OfferDetailsActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    private String offerType;
    private TextView labelPhotoGallery;
    private TextView labelName;
    private TextView labelCountry;
    private TextView labelLocation;
    private TextView labelDescription;
    private LayoutInflater inflater;

    private LinearLayout horizontalScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);

        apiInterface = ApiClient.getClient(ApiInterface.class);
        initComponents();
    }

    private void initComponents() {
        offerType = "";

        //Getting offer id
        Bundle extr = getIntent().getExtras();
        int id = 0;
        if (extr != null) {
            id = extr.getInt("id");
        }

        labelPhotoGallery = findViewById(R.id.labelPhotoGallery);
        labelName = findViewById(R.id.labelName);
        labelCountry = findViewById(R.id.labelOfferCountry);
        labelLocation = findViewById(R.id.labelOfferLocation);
        labelDescription = findViewById(R.id.labelOfferDescription);

        ((ImageButton) findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getOfferById(id);
        getOfferPictureByOfferId(id);
    }

    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("offer", offerType);
        setResult(RESULT_OK, i);
        super.finish();
    }

    private void getOfferById(int id) {
        Call<Offer> callLanguage = apiInterface.getOfferById(id);
        final Dialog dialog = LoadDialog.loadDialog(OfferDetailsActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    labelPhotoGallery.setText(String.format("%s Gallery", response.body().getName()));
                    labelName.setText(response.body().getName());
                    labelCountry.setText(String.format("Country: %s", response.body().getCountry()));
                    labelLocation.setText(String.format("Location: %s", response.body().getLocation()));
                    labelDescription.setText(response.body().getDescription());
                    offerType = response.body().getOffer_type().getName();
                }
            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OfferDetailsActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    private void getOfferPictureByOfferId(int id) {
        Call<List<OfferPicture>> callLanguage = apiInterface.getOfferPictureByOfferId(id);
        final Dialog dialog = LoadDialog.loadDialog(OfferDetailsActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<OfferPicture>>() {
            @Override
            public void onResponse(Call<List<OfferPicture>> call, Response<List<OfferPicture>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    if (response.body().isEmpty()) {
                        labelPhotoGallery.setText(String.format(getString(R.string.emptyGallery)));
                    } else {
                        for (OfferPicture offerPicture : response.body()) {
                            horizontalScroll = (LinearLayout) findViewById(R.id.horizontalScroll);
                            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                            RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.picture_view, null);
                            ((TextView) item.findViewById(R.id.textPicture)).setText(offerPicture.getShort_description());
                            String name = offerPicture.getName();
                            name = name.substring(0, name.lastIndexOf('.'));
                            String uri = "@drawable/" + name;
                            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                            Drawable imageDrawable = getResources().getDrawable(imageResource);
                            ((ImageView) item.findViewById(R.id.imageViewPicture)).setImageDrawable(imageDrawable);

                            horizontalScroll.addView(item);
                        }
                    }

                } else {
                    RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.picture_view, null);
                    ((TextView) item.findViewById(R.id.textPicture)).setText(R.string.noGallery);
                    ((ImageView) item.findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.image_001);
                    horizontalScroll.addView(item);
                }
            }

            @Override
            public void onFailure(Call<List<OfferPicture>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OfferDetailsActivity.this, R.string.errorNetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }
}