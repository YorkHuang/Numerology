package com.york.user.numerology;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BazuFragment extends Fragment {

    private static final String ARG_YY = "YY";
    private static final String ARG_MM = "MM";
    private static final String ARG_DD = "DD";
    private static final String ARG_HH = "hh";

    private int mYY;
    private int mMM;
    private int mDD;
    private int mHH;

    public BazuFragment() {

}

    public static BazuFragment newInstance(int yy, int mm, int dd, int hh) {
        BazuFragment fragment = new BazuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YY, yy);
        args.putInt(ARG_MM, mm);
        args.putInt(ARG_DD, dd);
        args.putInt(ARG_HH, hh);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYY = getArguments().getInt(ARG_YY);
            mMM = getArguments().getInt(ARG_MM);
            mDD = getArguments().getInt(ARG_DD);
            mHH = getArguments().getInt(ARG_HH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bazu, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
