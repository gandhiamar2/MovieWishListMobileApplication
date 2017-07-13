package com.example.gandh.inclass08;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Addmovie_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Addmovie_fragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Addmovie_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Button add_movie = (Button) getView().findViewById(R.id.button);
        final EditText eName=(EditText) getView().findViewById(R.id.editTextName);
        final EditText eDes=(EditText) getView().findViewById(R.id.editTextDescription);
        final Spinner sp=(Spinner)getView().findViewById(R.id.spinner);
        final SeekBar sb=(SeekBar)getView().findViewById(R.id.seekBar);
        final EditText eYear=(EditText)getView().findViewById(R.id.editTextYear);
        final EditText eIMDB=(EditText)getView().findViewById(R.id.editTextIMDB);
        final TextView tShow=(TextView)getView().findViewById(R.id.textViewShowRating);
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

        add_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eName.getText().toString().equals("") || sp.getSelectedItem().equals("Select") || sb.getProgress()==0 || eYear.getText().toString().equals("") || eDes.getText().toString().equals("") ||eIMDB.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"All Fields are mandatory",Toast.LENGTH_LONG).show();
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
                        if( mListener!=null)
                        mListener.fromAddmovie_frag(movie);
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

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_addmovie_fragment, container, false);

        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        void fromAddmovie_frag(Moviez moive);
    }
}
