package com.york.user.numerology;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;


public class StockFragment extends Fragment {

    private TextView tv_lunar_date;

    public StockFragment() {
        // Required empty public constructor
    }

    public static StockFragment newInstance() {
        StockFragment fragment = new StockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stock, container, false);

        tv_lunar_date = (TextView) view.findViewById(R.id.textView_lunardate);

        getToadyLunarString();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void getToadyLunarString() {

        Date today = new Date(System.currentTimeMillis());
        Lunar lu = new Lunar(today);
        int a = lu.getTiananD();
        int b = lu.getTiananM();
        int c = lu.getTiananY();
        int d = lu.getDeqiD();
        int e = lu.getDeqiM();
        int f = lu.getDeqiY();
    }
}
