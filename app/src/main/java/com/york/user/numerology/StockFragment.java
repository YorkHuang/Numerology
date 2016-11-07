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

        tv_lunar_date.setText(tdBazi[0] + " " + tdBazi[1] + " " + tdBazi[2] + " " + tdBazi[3] + " "
                + tdBazi[4] + " " + tdBazi[5] + " " + tdBazi[6] + " " + tdBazi[7]);
    }

    // Time of TanGan
    private int Month_Time_TanGan_cal(int D, int T) {
        int A = 0, B = 0, C = 0;
//        for (int i = 0; i < Deqi.length; i++) {
//            if (Deqi[i].equals(sbuf1[D])) {
//                A = i;
//            }
//        }
//        for (int i = 0; i < 10; i++) {
//            if (Tianan[i].equals(sbuf1[T])) {
//                if (i < 5) {
//                    B = i * 2;
//                } else {
//                    B = i * 2 - 10;
//                }
//            }
//        }

        A = D;
        if (T < 5) {
            B = T * 2;
        } else {
            B = T * 2 - 10;
        }

        C = A + B;
        C = C % 10;

        return C;
    }
}
