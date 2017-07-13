package com.example.bhanu.hw2_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by gandh on 1/28/2017.
 */

public class Moviesbyrating extends AppCompatActivity {

    public ArrayList<Moviez> Moviez_list_rating = new ArrayList<>();
    String TAG = "demo_year";
    int posi = 0;

    TextView Et, Ett, Etd, Etg, Etr, Ety, Eti;

    protected void text_display(int posi)
    {
        Ett.setText(Moviez_list_rating.get(posi).Name);
        Etd.setText(Moviez_list_rating.get(posi).Description);
        Etg.setText(Moviez_list_rating.get(posi).Genre);
        Etr.setText(Moviez_list_rating.get(posi).Rating+"/5");
        Ety.setText(Moviez_list_rating.get(posi).Year+" ");
        Eti.setText(Moviez_list_rating.get(posi).IMDB);
        Et.setText("Movies by Rating");

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Movies by Rating");

        Ett = (TextView) findViewById(R.id.edittext_title);
        Etd = (TextView) findViewById(R.id.edittext_des);
        Etg = (TextView) findViewById(R.id.edittext_genre);
        Etr = (TextView) findViewById(R.id.edittext_rating);
        Ety = (TextView) findViewById(R.id.edittext_year);
        Eti = (TextView) findViewById(R.id.editext_imdb);
        Et = (TextView) findViewById(R.id.textView_head);



        if(getIntent().getExtras()!= null)
        {
            Moviez_list_rating = (ArrayList<Moviez>) getIntent().getExtras().getSerializable(MainActivity.KEYZ_R);
            Collections.sort(Moviez_list_rating, new Comparator<Moviez>() {
                @Override
                public int compare(Moviez o1, Moviez o2) {
                    if (o1.Rating > o2.Rating)
                    {
                        return -1;
                    }
                    else if (o1.Rating<o2.Rating)
                    {
                        return 1;
                    }
                    return 0;
                }
            });

            text_display(posi);
            findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi = 0;

                    text_display(posi);
                }
            });

            findViewById(R.id.imageButton_before).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(posi !=0)
                        posi--;

                    text_display(posi);

                }
            });

            findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(posi < Moviez_list_rating.size()-1)
                        posi++;

                    text_display(posi);
                }
            });

            findViewById(R.id.button_last).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi = Moviez_list_rating.size()-1;

                    text_display(posi);
                }
            });

            Button Bf = (Button) findViewById(R.id.button_finish);
            Bf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


        }


    }
}
