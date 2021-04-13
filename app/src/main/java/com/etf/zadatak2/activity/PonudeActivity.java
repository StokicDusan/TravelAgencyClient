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
import com.etf.zadatak2.data.Ponuda;
import com.etf.zadatak2.dialog.LoadDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_dropdown_item;

public class PonudeActivity extends AppCompatActivity {
    private static final int request_code = 5;

    ApiInterface apiInterface;
    private ArrayList<String> listaPonudaDrzave;
    private ArrayList<String> listaPonudaGradovi;
    private ArrayList<Ponuda> listaPonuda;
    private ArrayList<Ponuda> listaZaPrikaz;
    private Spinner ponudaGradovi;
    private Spinner ponudaDrzave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponude);
        listaZaPrikaz = new ArrayList<>();
        listaPonuda = new ArrayList<>();
        listaPonudaDrzave = new ArrayList<>();
        listaPonudaGradovi = new ArrayList<>();
        listaPonudaDrzave.add("izaberite drzavu");
        listaPonudaGradovi.add("izaberite grad");

        initComponents();
    }

    private void initComponents() {
        apiInterface = ApiClient.getClient(ApiInterface.class);

        //prihvatanje konkretne vrste ponude
        Bundle extr = getIntent().getExtras();
        String ponuda = null;
        if (extr != null) ponuda = extr.getString("ponuda");

        ponudaDrzave = (Spinner) findViewById(R.id.spinnerDrzave);
        ponudaGradovi = (Spinner) findViewById(R.id.spinnerGradovi);

        ArrayAdapter<String> adapterPonudaDrzave = new ArrayAdapter<String>(this,
                simple_spinner_dropdown_item, listaPonudaDrzave);
        ArrayAdapter<String> adapterPonudaGradovi = new ArrayAdapter<String>(this,
                simple_spinner_dropdown_item, listaPonudaGradovi);
        ponudaDrzave.setAdapter(adapterPonudaDrzave);
        ponudaGradovi.setAdapter(adapterPonudaGradovi);

        getPonuda(ponuda);

        ((ImageButton) findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PonudeActivity.this, PocetniEkran.class);
                startActivity(i);
            }
        });


        // Inflejtovanje Gradova u odnosu na Drzavu koja je izabrana
        ponudaDrzave.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    listaPonudaGradovi.clear();
                    listaPonudaGradovi.add("izaberite grad");
                    ponudaGradovi.setSelection(0);
                    String selectedItemText = (String) parent.getItemAtPosition(position);
                    for (Ponuda temp : listaPonuda) {
                        if (selectedItemText.equals(temp.getDrzava())) {
                            if (!listaPonudaGradovi.contains(temp.getMesto())) {
                                listaPonudaGradovi.add(temp.getMesto());
                            }
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ponudaDrzave.setSelection(0);
            }
        });

        // Inflejtovanje Ponuda u odnosu na Grad koji je izabran
        ponudaGradovi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedItemText = (String) parent.getItemAtPosition(position);
                    doInflate(selectedItemText);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ponudaGradovi.setSelection(0);
                //doInflate(null);

            }
        });
    }


    private void getPonuda(String ponuda) {
        Call<List<Ponuda>> callLanguage = apiInterface.getPonudaByVrsta(ponuda);
        final Dialog dialog = LoadDialog.loadDialog(PonudeActivity.this);
        dialog.show();
        callLanguage.enqueue(new Callback<List<Ponuda>>() {
            @Override
            public void onResponse(Call<List<Ponuda>> call, Response<List<Ponuda>> response) {
                if (dialog.isShowing()) dialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    for (Ponuda temp : response.body()) {
                        if (!listaPonudaDrzave.contains(temp.getDrzava())) {
                            listaPonudaDrzave.add(temp.getDrzava());
                        }
                        if (!listaPonuda.contains(temp)) {
                            listaPonuda.add(temp);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ponuda>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(PonudeActivity.this, R.string.errornetwork, Toast.LENGTH_SHORT).show();
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }


    private void doInflate(String mesto) {
        listaZaPrikaz.clear();
        LinearLayout mainScrollLayout = findViewById(R.id.ponudaScrollView);
        mainScrollLayout.removeAllViews();

        if (mesto != null) {
            for (Ponuda ponuda : listaPonuda) {
                if (ponuda.getMesto().equals(mesto)) listaZaPrikaz.add(ponuda);
            }
        }

        for (final Ponuda ponuda : listaZaPrikaz) {
            //RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.my_view, null);
            int id = ponuda.getPonuda_id();

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout item = (RelativeLayout) inflater.inflate(R.layout.ponuda_view, mainScrollLayout, false);

            String naslov = ponuda.getMesto() + " - " + ponuda.getNaziv();
            ((TextView) item.findViewById(R.id.textPonuda)).setText(naslov);
            if (ponuda.getAktivna()) {
                ((TextView) item.findViewById(R.id.textOpis)).setText(ponuda.getOpis());
                item.setClickable(true);
                final int finalId = id;
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(PonudeActivity.this, DetaljiPonudeActivity.class);
                        Bundle extra = new Bundle();
                        extra.putInt("id", finalId);
                        i.putExtras(extra);
                        startActivityForResult(i, request_code);

                    }
                });
            } else {
                ((TextView) item.findViewById(R.id.textOpis)).setText(R.string.nedostupno);
                ((TextView) item.findViewById(R.id.textOpis)).setTextColor(Color.RED);
            }
            mainScrollLayout.addView(item);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == request_code) &&
                (resultCode == RESULT_OK)) {
            listaPonudaGradovi.clear();
            listaPonuda.clear();
            listaZaPrikaz.clear();
            ponudaDrzave.setSelection(0);
            listaPonudaGradovi.add("izaberite grad");
            ponudaGradovi.setSelection(0);
            initComponents();
        }

    }
}