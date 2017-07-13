package com.example.bhanu.hw2_movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by gandh on 1/28/2017.
 */

public class Moviesbyyear extends AppCompatActivity {

    public ArrayList<Moviez> Moviez_list_year = new ArrayList<>();
    String TAG = "demo_year";
    int posi = 0;


    TextView Ett, Etd, Etg, Etr, Ety, Eti;

    protected void text_display(int posi)
    {

        Ett.setText(Moviez_list_year.get(posi).Name);
        Etd.setText(Moviez_list_year.get(posi).Description);
        Etg.setText(Moviez_list_year.get(posi).Genre);
        Etr.setText(Moviez_list_year.get(posi).Rating+"/5");
        Log.d(TAG,Moviez_list_year.get(posi).Year+"" );
        Ety.setText(Moviez_list_year.get(posi).Year+" ");
        Eti.setText(Moviez_list_year.get(posi).IMDB);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Movies by Year");

        Ett = (TextView) findViewById(R.id.edittext_title);
        Etd = (TextView) findViewById(R.id.edittext_des);
        Etg = (TextView) findViewById(R.id.edittext_genre);
        Etr = (TextView) findViewById(R.id.edittext_rating);
        Ety = (TextView) findViewById(R.id.edittext_year);
        Eti = (TextView) findViewById(R.id.editext_imdb);



        if(getIntent().getExtras()!= null)
        {
           Moviez_list_year = (ArrayList<Moviez>) getIntent().getExtras().getSerializable(MainActivity.KEYZ_Y);
           Collections.sort( Moviez_list_year, new Comparator<Moviez>() {
                @Override
                public int compare(Moviez o1, Moviez o2) {
                    if (o1.Year > o2.Year)
                    {
                    return 1;
                    }
                    else if (o1.Year<o2.Year)
                    {
                        return -1;
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
                    if(posi < Moviez_list_year.size()-1)
                    posi++;

                    text_display(posi);
                }
            });

            findViewById(R.id.button_last).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi = Moviez_list_year.size()-1;

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
