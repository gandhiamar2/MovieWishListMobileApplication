package com.example.bhanu.hw2_movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    public final int REQ_CODE=100;
    public static final String KEYZ_E = "KEYZEDIT";
    public static final String KEYZ_Y = "KEYZYEAR";
    public static final String KEYZ_R = "KEYZRATE";
    public  ArrayList<String> Moviez_List_try=new ArrayList<>();
    public  ArrayList<Moviez> Moviez_List = new ArrayList<>();
    String TAG = "demo";
    Toast toast;
    public void toast_delete(int temp) {
        toast.makeText(getApplicationContext(), "Movie is "+Moviez_List_try.get(temp) + " is deleted", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favourite Movies");
        Button bA=(Button)findViewById(R.id.buttonAddMovie);
        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iA= new Intent(MainActivity.this,AddMovieActivity.class);
                startActivityForResult(iA,REQ_CODE);
            }
        });
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final AlertDialog.Builder builder_D=new AlertDialog.Builder(this);

        Button bE=(Button)findViewById(R.id.buttonEdit);
        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] cs = Moviez_List_try.toArray(new CharSequence[Moviez_List_try.size()]);
                builder.setTitle("Pick a Movie")
                        .setItems(cs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent iE = new Intent(MainActivity.this, EditActivity.class);
                                Moviez m = (Moviez) Moviez_List.get(which);
                                iE.putExtra(KEYZ_E, m);
                                iE.putExtra("indexE", which);
                                iE.putExtra("indexEM", m.Name);
                                startActivityForResult(iE, 110);
                            }
                        });
                final AlertDialog a = builder.create();

                if(Moviez_List_try.size()!=0) {
                    Log.d(TAG,"not null");
                    a.show();
                }
                else
                {
                    toast.makeText(getApplicationContext(),"No movies to edit",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bD=(Button)findViewById(R.id.buttonDelete);
        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] cs1 = Moviez_List_try.toArray(new CharSequence[Moviez_List_try.size()]);

                builder_D.setTitle("Pick a Movie")
                        .setItems(cs1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               toast_delete(which);
                                Moviez_List.remove(which);
                                Moviez_List_try.remove(which);
                            }
                        });


                if(Moviez_List_try.size()!=0) {
                    Log.d(TAG,"not null");
                    builder_D.create().show();
                }
                else
                {
                    toast.makeText(getApplicationContext(),"No movies to delete",Toast.LENGTH_SHORT).show();
                }

            }


        });

        Button bY = (Button) findViewById(R.id.buttonYear);
        bY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Iy = new Intent("com.example.bhanu.hw2_movies.intent.action.year") ;
                Iy.putExtra(KEYZ_Y,Moviez_List);

                if(Moviez_List_try.size()!=0) {
                    Log.d(TAG,"not null");
                    startActivity(Iy);
                }
                else
                {
                    toast.makeText(getApplicationContext(),"No movies to show",Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button bR = (Button) findViewById(R.id.buttonRating);
        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Ir = new Intent("com.example.bhanu.hw2_movies.intent.action.rating") ;
                Ir.putExtra(KEYZ_R,Moviez_List);

                if(Moviez_List_try.size()!=0) {
                    Log.d(TAG,"not null");
                    startActivity(Ir);
                }
                else
                {
                    toast.makeText(getApplicationContext(),"No movies to show",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    {
     if (requestcode==100)
     {
         if (resultcode==RESULT_OK)
         {
             if (data.getExtras().getSerializable(AddMovieActivity.KEYZ)!= null)
             {
                 Moviez m = (Moviez) data.getExtras().getSerializable(AddMovieActivity.KEYZ);
                 Moviez_List.add(m);
                 Moviez_List_try.add(m.Name);
                 Log.d(TAG,m.Name + " movie added");
                 toast.makeText(getApplicationContext(),m.Name + " movie added",Toast.LENGTH_LONG).show();


             }
         }
     }
        else if (requestcode == 110)
     {
         if (resultcode==RESULT_OK)
         {
             if(data.getExtras().getSerializable(KEYZ_E)!=null)
             {
                 Moviez m = (Moviez) data.getExtras().getSerializable(KEYZ_E);
                 int index;
                 index = data.getExtras().getInt("indexE");
                 Moviez_List.set(index,m);
                 Moviez_List_try.set(index,m.Name);
                 toast.makeText(getApplicationContext(),"Movie edited: "+data.getExtras().get("indexEM"),Toast.LENGTH_LONG).show();

             }
         }
     }
    }
}
