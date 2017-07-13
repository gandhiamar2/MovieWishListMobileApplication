package com.example.gandh.inclass08;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFavouriteMoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyFavouriteMoviesFragment extends Fragment {
    ArrayList<Moviez> moviezlist;
    AlertDialog.Builder builder;
    private OnFragmentInteractionListener mListener;

    public MyFavouriteMoviesFragment() {
        // Required empty public constructor
    }
    void data_getter(ArrayList<Moviez> moviezlist)
    {
        this.moviezlist = moviezlist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_favourite_movies, container, false);


        return v;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bA=(Button) getView().findViewById(R.id.buttonAddMovie);

        bA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.rl1,new Addmovie_fragment(),"add_movies")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button bE=(Button)getView().findViewById(R.id.buttonEdit);
        bE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listgetter();
                if (moviezlist != null){

                builder = new AlertDialog.Builder(getActivity());
                CharSequence[] cs = new CharSequence[moviezlist.size()];
                int i = 0;
                for (Moviez m : moviezlist
                        ) {
                    cs[i] = m.Name;
                    i++;
                }
                builder.setTitle("Pick a Movie")
                        .setItems(cs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Moviez m = (Moviez) moviezlist.get(which);

                                editmovie e = new editmovie();
                                e.dataforedit(m);
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.rl1, e, "edit_movies")
                                        .addToBackStack(null)
                                        .commit();


                            }
                        });
                final AlertDialog a = builder.create();

                if (moviezlist.size() != 0) {
                    Log.d("demo", "not null");
                    a.show();
                } else {
                    Toast.makeText(getActivity(), "No movies to edit", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
        Button bR = (Button) getView().findViewById(R.id.buttonRating);
        bR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listgetter();
                Report byrating = new Report();
                byrating.listgetter(moviezlist);

                if(moviezlist!=null) {
                    if (moviezlist.size() != 0) {
                        Log.d("demo", "not null");
                        getFragmentManager().beginTransaction()
                                .replace(R.id.rl1, byrating, "reportbyrating")
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toast.makeText(getActivity(),"No movies to show",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"No movies to show",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button by = (Button) getView().findViewById(R.id.buttonYear);
        by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listgetter();
                Reportbyyear byyear = new Reportbyyear();
                byyear.listgetter(moviezlist);

                if(moviezlist!=null) {
                    if(moviezlist.size()!=0){
                    Log.d("demo","not null");
                    getFragmentManager().beginTransaction()
                            .replace(R.id.rl1,byyear,"reportbyyear")
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(getActivity(),"No movies to show",Toast.LENGTH_SHORT).show();
                }
                }
                else
                {
                    Toast.makeText(getActivity(),"No movies to show",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button bD=(Button)getView().findViewById(R.id.buttonDelete);
        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listgetter();
                if(moviezlist!=null) {

                AlertDialog.Builder builder_D = new AlertDialog.Builder(getActivity());

                CharSequence[] cs = new CharSequence[moviezlist.size()];
                int i=0;
                for (Moviez m:moviezlist
                        ) {
                    cs[i] = m.Name;
                    i++;
                }
                builder_D.setTitle("Pick a Movie")
                        .setItems(cs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                moviezlist.remove(which);
                                mListener.moviedeleter(moviezlist);
                            }
                        });


                if(moviezlist.size()!=0) {
                    Log.d("demo","not null");
                    builder_D.create().show();
                }
                else
                {
                    Toast.makeText(getActivity(),"No movies to delete",Toast.LENGTH_SHORT).show();
                }
                }
            }


        });


    }

    @Override
    public void onAttach(Context context){
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }

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
       void listgetter();
        void editor(Moviez m);
        void moviedeleter (ArrayList<Moviez> list);

    }
}
