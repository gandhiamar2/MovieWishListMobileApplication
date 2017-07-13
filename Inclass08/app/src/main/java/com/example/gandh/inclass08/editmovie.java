package com.example.gandh.inclass08;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link editmovie.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class editmovie extends Fragment {

    private OnFragmentInteractionListener mListener;
    Moviez movie;
    Moviez movie1;
    public editmovie() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Edit Movie");
        return inflater.inflate(R.layout.fragment_editmovie, container, false);
    }

    void dataforedit(Moviez movie)
    {
        this.movie = movie;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText eName = (EditText) getView().findViewById(R.id.editTextName);
        final EditText eDes = (EditText) getView().findViewById(R.id.editTextDescription);
        final Spinner sp = (Spinner) getView().findViewById(R.id.spinner);
        final SeekBar sb = (SeekBar) getView().findViewById(R.id.seekBar);
        final EditText eYear = (EditText) getView().findViewById(R.id.editTextYear);
        final EditText eIMDB = (EditText) getView().findViewById(R.id.editTextIMDB);
        final Button Edit = (Button) getView().findViewById(R.id.Edit);
        final TextView tShow = (TextView) getView().findViewById(R.id.textViewShowRating);

        Edit.setText("Save Changes");
        eName.setText(movie.Name);
        eDes.setText(movie.Description);

        ArrayList<String> array = new ArrayList<>((Arrays.asList(getResources().getStringArray(R.array.spinner_values))));
        for (int i = 0; i < array.size(); i++) {
            Log.d("demo", array.get(i));
            if (array.get(i).equals(movie.Genre)) {
                Log.d("demo", "slected" + array.get(i));
                sp.setSelection(i);
            }
        }
        //sp.setPrompt(m.Genre);

        // sp.setSelection(ArrayUtils.indexOf(array, m.Genre));
        sb.setProgress(movie.Rating);
        eYear.setText(movie.Year + "");
        eIMDB.setText(movie.IMDB);
        tShow.setText(sb.getProgress() + "");


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tShow.setText(sb.getProgress() + "");
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
                    Toast.makeText(getActivity(),"All Fields are mandatory",Toast.LENGTH_LONG).show();
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
                         movie1 = new Moviez(name, des, genre, rating, year, imdb);

                       mListener.from_Editmovie(movie1,movie);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.rl1,new MyFavouriteMoviesFragment(),"fav_movies")
                                .addToBackStack(null)
                                .commit();


                    }

                    else
                    {
                        Toast.makeText(getActivity(),"Proper IMDB link should be entered",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
        void from_Editmovie(Moviez movie1, Moviez movie);
    }
}
