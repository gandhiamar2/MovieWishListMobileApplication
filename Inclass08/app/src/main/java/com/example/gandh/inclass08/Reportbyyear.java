package com.example.gandh.inclass08;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Reportbyyear.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Reportbyyear extends Fragment {
    public ArrayList<Moviez> Moviez_list_year = new ArrayList<>();
    String TAG = "demo_year";
    int posi = 0;


    TextView Ett, Etd, Etg, Etr, Ety, Eti;

    protected void text_display(int posi)
    {
        if(Moviez_list_year!=null) {
            Ett.setText(Moviez_list_year.get(posi).Name);
            Etd.setText(Moviez_list_year.get(posi).Description);
            Etg.setText(Moviez_list_year.get(posi).Genre);
            Etr.setText(Moviez_list_year.get(posi).Rating + "/5");
            Log.d(TAG, Moviez_list_year.get(posi).Year + "");
            Ety.setText(Moviez_list_year.get(posi).Year + " ");
            Eti.setText(Moviez_list_year.get(posi).IMDB);
        }
        else
        {
            Toast.makeText(getActivity(),"No movies",Toast.LENGTH_LONG);
        }
    }

    private OnFragmentInteractionListener mListener;
    void listgetter(ArrayList<Moviez> movielist){
        Moviez_list_year = movielist;
    }

    public Reportbyyear() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(Moviez_list_year.size()!=0) {
            Ett = (TextView) getView().findViewById(R.id.edittext_title);
            Etd = (TextView) getView().findViewById(R.id.edittext_des);
            Etg = (TextView) getView().findViewById(R.id.edittext_genre);
            Etr = (TextView) getView().findViewById(R.id.edittext_rating);
            Ety = (TextView) getView().findViewById(R.id.edittext_year);
            Eti = (TextView) getView().findViewById(R.id.editext_imdb);


            Collections.sort(Moviez_list_year, new Comparator<Moviez>() {
                @Override
                public int compare(Moviez o1, Moviez o2) {
                    if (o1.Year > o2.Year) {
                        return 1;
                    } else if (o1.Year < o2.Year) {
                        return -1;
                    }
                    return 0;
                }
            });

            text_display(posi);
            getView().findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi = 0;

                    text_display(posi);
                }
            });

            getView().findViewById(R.id.imageButton_before).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (posi != 0)
                        posi--;

                    text_display(posi);

                }
            });

            getView().findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (posi < Moviez_list_year.size() - 1)
                        posi++;

                    text_display(posi);
                }
            });

            getView().findViewById(R.id.button_last).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi = Moviez_list_year.size() - 1;

                    text_display(posi);
                }
            });

            Button Bf = (Button) getView().findViewById(R.id.button_finish);
            Bf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyFavouriteMoviesFragment fav = new MyFavouriteMoviesFragment();
                    fav.moviezlist = Moviez_list_year;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.rl1, fav, "fav_mocies")
                            .addToBackStack(null)
                            .commit();
                }
            });

        }
        else
        {
            Toast.makeText(getActivity(),"No movies",Toast.LENGTH_SHORT);
        }
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Movies by Year");
        return inflater.inflate(R.layout.fragment_reportbyyear, container, false);
    }



    @Override
    public void onAttach(Activity activity) {
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
