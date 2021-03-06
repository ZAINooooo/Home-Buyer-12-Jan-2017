package com.example.asad.homebuyerproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Asad on 11/16/2016.
 */

public class Fragment_Sell_Commercial_CommercialLand extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button GetReady;
    private EditText mCoveredarea;
    private ArrayList<String> propertyData=new ArrayList<>();
    private RadioGroup mRadioGroup1;
    private String societytype,corner;

    private TextView mFloorsAllowedinc, mFloorsAlloweddec, mNumberSitesinc, mNumberSitesdecdec;
    private TextView mValueFloorsAllowed, mValueNumberSites;
    private Integer FloorsAllowedCounter = 1, NumberSitesCounter = 1;

    private OnFragmentInteractionListener mListener;

    public Fragment_Sell_Commercial_CommercialLand() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResidentialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResidentialFragment newInstance(String param1, String param2) {
        ResidentialFragment fragment = new ResidentialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_sell_commercial_commercialland, container, false);
        propertyData=getArguments().getStringArrayList("data");
        TypeCasting(view);
        ButtonClick();

        mRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.corneryes) {
                    corner="YES";
                } else if(checkedId == R.id.cornerno) {
                    corner="NO";
                }else if (checkedId == R.id.gatedyes) {
                    societytype="YES";
                }else if (checkedId == R.id.gatedno) {
                    societytype="NO";
                }

            }

        });




         /*
        On Click for Counters on first panel
         */

        //////////////////////////////////////////////////////////////////////////////////

        mNumberSitesdecdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int copy = DecrementFunction(NumberSitesCounter);
                mValueNumberSites.setText(String.valueOf(copy));
                NumberSitesCounter = copy;

            }
        });

        mNumberSitesinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer copy = IncrementFunction(NumberSitesCounter);
                mValueNumberSites.setText(String.valueOf(copy));
                NumberSitesCounter = copy;

            }
        });

        /////////////////////////////////
        mFloorsAlloweddec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int copy = DecrementFunction(FloorsAllowedCounter);
                mValueFloorsAllowed.setText(String.valueOf(copy));
                FloorsAllowedCounter = copy;
            }
        });
        mFloorsAllowedinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer copy = IncrementFunction(FloorsAllowedCounter);
                mValueFloorsAllowed.setText(String.valueOf(copy));
                FloorsAllowedCounter = copy;
            }
        });


        ////////////////////////////////////////////////////////////////////////////
        return view;
    }

    //Increment Counter for all counters
    private Integer IncrementFunction(int Counter) {


        if (Counter < 40) {
            Counter++;
            // mValueBathroom.setText(String.valueOf(Counter));
            // BathroomCounter++;
        } else {

            Toast.makeText(getActivity(), "Full", Toast.LENGTH_SHORT).show();
        }

        return Counter;
    }


    //Decrement Counter for all Counters
    private Integer DecrementFunction(int Counter) {

        if (Counter > 1) {
            Counter--;
            // mValueBathroom.setText(String.valueOf(Counter));
            // BathroomCounter--;
        } else {
            Toast.makeText(getActivity(), "Nothng Happen", Toast.LENGTH_SHORT).show();
        }

        return Counter;
    }

    private void ButtonClick() {
        GetReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidArea(mCoveredarea.getText().toString()))
                {
                    mCoveredarea.setError("Value Required..");
                }
                else {

                    Intent next = new Intent(getActivity(), Activity_Uplaod_Property_Part2.class);
                    propertyData.add("Bathroom");
                    propertyData.add(FloorsAllowedCounter.toString());
                    propertyData.add("FloorNumber");
                    propertyData.add(NumberSitesCounter.toString());
                    propertyData.add("isConerProperty");
                    propertyData.add(corner);
                    propertyData.add("SocietyType");
                    propertyData.add(societytype);
                    propertyData.add("PropertySize");
                    propertyData.add(mCoveredarea.getText().toString());
                    String r = "Rent";
                    next.putStringArrayListExtra("data", propertyData);
                    startActivity(next);
                }
            }
        });
    }

    private boolean isValidArea(String name) {

        Pattern pattern;
        Matcher matcher;
        final String AREA_PATTERN = "^(?=.*[0-9]).+$";
        pattern = Pattern.compile(AREA_PATTERN);
        matcher = pattern.matcher(name);

        return matcher.matches();
    }
    private void TypeCasting(View view) {
        mCoveredarea=(EditText)view.findViewById(R.id.plotareavalue);

        GetReady = (Button) view.findViewById(R.id.Readytogo);
        mRadioGroup1=(RadioGroup)view.findViewById(R.id.first);
        mFloorsAlloweddec = (TextView) view.findViewById(R.id.incfloorsallowed);
        mFloorsAllowedinc = (TextView) view.findViewById(R.id.decfloorsallowed);
        mNumberSitesinc = (TextView) view.findViewById(R.id.decopensites);
        mNumberSitesdecdec = (TextView) view.findViewById(R.id.incopensites);

        mValueFloorsAllowed = (TextView) view.findViewById(R.id.valuefloorsallowed);
        mValueNumberSites = (TextView) view.findViewById(R.id.valuesopensites);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
        void onFragmentInteraction(Uri uri);
    }
}


