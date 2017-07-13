package com.example.bhanu.hw2_movies;

import android.content.Intent;
import android.support.v4.content.res.TypedArrayUtils;
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

import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;

public class EditActivity extends AppCompatActivity {
    Moviez m;
    String TAG = "demo_edit";
    int index;
    String selectedmovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Edit Movie");

  if(getIntent().getExtras()!=null) {
               m = (Moviez) getIntent().getExtras().getSerializable(MainActivity.KEYZ_E);
      index = getIntent().getExtras().getInt("indexE");
      selectedmovie = getIntent().getExtras().getString("indexEM");


  }

      final EditText eName = (EditText) findViewById(R.id.editTextName);
      final EditText eDes = (EditText) findViewById(R.id.editTextDescription);
      final Spinner sp = (Spinner) findViewById(R.id.spinner);
      final SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
      final EditText eYear = (EditText) findViewById(R.id.editTextYear);
      final EditText eIMDB = (EditText) findViewById(R.id.editTextIMDB);
        final Button Edit = (Button) findViewById(R.id.button);
        final TextView tShow=(TextView)findViewById(R.id.textViewShowRating);

        Edit.setText("Save Changes");
      eName.setText(m.Name);
      eDes.setText(m.Description);

        ArrayList<String> array=  new ArrayList<>((Arrays.asList(getResources().getStringArray(R.array.spinner_values))));
       for(int i = 0; i< array.size(); i++)
       {    Log.d(TAG,array.get(i));
           if (array.get(i).equals(m.Genre))
           {  Log.d(TAG,"slected"+ array.get(i));
               sp.setSelection(i);
           }
       }
        //sp.setPrompt(m.Genre);

       // sp.setSelection(ArrayUtils.indexOf(array, m.Genre));
      sb.setProgress(m.Rating);
      eYear.setText(m.Year+"");
      eIMDB.setText(m.IMDB);
        tShow.setText(sb.getProgress()+"");
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

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eName.getText().toString().equals("") || sp.getSelectedItem().equals("Select") || sb.getProgress()==0 || eYear.getText().toString().equals("") || eDes.getText().toString().equals("") ||eIMDB.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"All Fields are mandatory",Toast.LENGTH_LONG).show();
                }
                else {
                    if(eIMDB.getText().toString().startsWith("www."))
                    {
                    String name = eName.getText().toString();
                    String des = eDes.getText().toString();
                    String genre = sp.getSelectedItem().toString();
                    int rating = sb.getProgress();
                    int year = Integer.parseInt(eYear.getText().toString());
                    String imdb = eIMDB.getText().toString();
                    Moviez movie = new Moviez(name, des, genre, rating, year, imdb);

                    Moviez mo = new Moviez(name, des, genre, rating, year, imdb);

                    Intent AEdit = new Intent();
                    AEdit.putExtra(MainActivity.KEYZ_E, mo);
                    AEdit.putExtra("indexE", index);
                    AEdit.putExtra("indexEM",selectedmovie);

                    setResult(RESULT_OK, AEdit);
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
