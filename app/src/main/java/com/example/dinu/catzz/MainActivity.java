package com.example.dinu.catzz;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class MainActivity extends AppCompatActivity {
    Button addToListBtn;
    Button theButton;
    TextView date;
    TextView factText;

    LinearLayout main_layout;

    public LinearLayout getMain_layout() {
        return main_layout;
    }

    public void setMain_layout(LinearLayout main_layout) {

        this.main_layout = main_layout;
    }

    final List<CatModel> catModelList = new ArrayList<>();

    public List<CatModel> getCatModelList() {
        return catModelList;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy / hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToListBtn = findViewById(R.id.addToListBtn);
        theButton = findViewById(R.id.theButton);
        date = findViewById(R.id.date);
        factText = findViewById(R.id.factText);
        main_layout = findViewById(R.id.main_layout);

        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progress = new ProgressDialog(MainActivity.this);
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
                catModelList.add(new CatModel(factText.getText().toString(),date.getText().toString()));
                draw(new ListFragment());
                main_layout.setVisibility(View.GONE);


            }
        });

    }
    public void draw(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
