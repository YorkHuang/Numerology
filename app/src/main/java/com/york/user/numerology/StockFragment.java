package com.york.user.numerology;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.GregorianCalendar;

import static com.york.user.numerology.Lunar.Deqi;
import static com.york.user.numerology.Lunar.Tianan;


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
        Lunar lunar = new Lunar(today);
        String[] tdBazi = lunar.getBaZiString();
        int[] def = lunar.getBaZiInt();

        tv_lunar_date.setText(tdBazi[3] + " " + tdBazi[2] + " " + tdBazi[1] + " " + tdBazi[0] + "\n"
                + tdBazi[7] + " " + tdBazi[6] + " " + tdBazi[5] + " " + tdBazi[4]);
    }


}
