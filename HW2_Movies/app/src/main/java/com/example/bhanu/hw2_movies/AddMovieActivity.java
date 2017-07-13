package com.example.bhanu.hw2_movies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {
    public final static String KEYZ="KEY_Movie";
    String TAG = "demo_add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");
        final EditText eName=(EditText)findViewById(R.id.editTextName);
        final EditText eDes=(EditText)findViewById(R.id.editTextDescription);
        final Spinner sp=(Spinner)findViewById(R.id.spinner);
        final SeekBar sb=(SeekBar)findViewById(R.id.seekBar);
        final EditText eYear=(EditText)findViewById(R.id.editTextYear);
        final EditText eIMDB=(EditText)findViewById(R.id.editTextIMDB);
        final TextView tShow=(TextView)findViewById(R.id.textViewShowRating);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tShow.setText(sb.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eName.getText().toString().equals("") || sp.getSelectedItem().equals("Select") || sb.getProgress()==0 || eYear.getText().toString().equals("") || eDes.getText().toString().equals("") ||eIMDB.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"All Fields are mandatory",Toast.LENGTH_LONG).show();
                }
                else {
                    if(eIMDB.getText().toString().startsWith("www.")) {
                        String name = eName.getText().toString();
                        String des = eDes.getText().toString();
                        String genre = sp.getSelectedItem().toString();
                        int rating = sb.getProgress();
                        int year = Integer.parseInt(eYear.getText().toString());
                        String imdb = eIMDB.getText().toString();


                        Moviez movie = new Moviez(name, des, genre, rating, year, imdb);
                        Intent i = new Intent();

                        i.putExtra(KEYZ, movie);
                        setResult(RESULT_OK, i);

                        finish();
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(),"Proper IMDB link should be entered",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
