
package com.example.gandh.inclass08;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Addmovie_fragment.OnFragmentInteractionListener,
        MyFavouriteMoviesFragment.OnFragmentInteractionListener,editmovie.OnFragmentInteractionListener
,Report.OnFragmentInteractionListener,Reportbyyear.OnFragmentInteractionListener{

    public static ArrayList<Moviez> Moviez_List;

    MyFavouriteMoviesFragment f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Moviez_List = new ArrayList<>();
        MyFavouriteMoviesFragment fav = new MyFavouriteMoviesFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.rl1,fav,"fav_movies")
                .commit();

        setTitle("My Favourite Movies");

    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }
        else
            super.onBackPressed();

    }

    @Override
    public void fromAddmovie_frag(Moviez moive) {
        Moviez_List.add(moive);
        Toast.makeText(this,"Movie has been added",Toast.LENGTH_LONG).show();
    }



    @Override
    public void from_Editmovie(Moviez movie1, Moviez movie) {
        Moviez_List.remove(movie);
        Moviez_List.add(movie1);
    }

    @Override
    public void listgetter() {
        f = (MyFavouriteMoviesFragment) getFragmentManager().findFragmentByTag("fav_movies");
        if(Moviez_List.size()!=0)
            f.data_getter(Moviez_List);
    }

    @Override
    public void editor(Moviez m) {


//        editmovie e = (editmovie) getFragmentManager().findFragmentByTag("edit_movies");
//        e.dataforedit(m);
    }

    @Override
    public void moviedeleter(ArrayList<Moviez> list) {
        Moviez_List = list;
    }

    @Override
    public void onFragmentInteraction( ) {

    }

    }

