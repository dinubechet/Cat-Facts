package com.example.dinu.catzz;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactsFragment extends Fragment {

    Button addToListBtn;
    Button theButton;
    TextView date;
    TextView factText;

    public void setCatModelList(List<CatModel> catModelList) {
        this.catModelList = catModelList;
    }

    private List<CatModel> catModelList = new ArrayList<>();



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy / hh:mm");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facts_fragment, container, false);

        addToListBtn = view.findViewById(R.id.addToListBtn);
        theButton = view.findViewById(R.id.theButton);
        date = view.findViewById(R.id.date);
        factText = view.findViewById(R.id.factText);
        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progress = new ProgressDialog(getActivity());
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false);
                progress.show();

                API api = retrofit.create(API.class);
                Call<CatFacts> call = api.getFacts();
                call.enqueue(new Callback<CatFacts>() {
                    @Override
                    public void onResponse(Call<CatFacts> call, Response<CatFacts> response) {

                        factText.setText(response.body().getText());
                        progress.dismiss();

                        try {
                            date.setText(format2.format(format1.parse(response.body().getCreatedAt())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<CatFacts> call, Throwable t) {

                    }
                });
            }
        });



        addToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).draw(new ListFragment());
                catModelList.add(new CatModel(factText.getText().toString(),date.getText().toString()));

            }
        });


            return view;
    }
}
