package com.york.user.numerology;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.york.user.numerology.YorkLifeCode.Bazi;
import com.york.user.numerology.YorkLifeCode.Lunar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class StockFragment extends Fragment implements OnChartValueSelectedListener {


    private LineChart mChart;

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

        mChart = (LineChart) view.findViewById(R.id.chart1);
        List<Integer> dataObjs = getToadyLunarString();
        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < dataObjs.size(); i++) {
            entries.add(new Entry(i, dataObjs.get(i)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(R.color.colorPrimary);

        LineData lineData = new LineData(dataSet);
        mChart.setData(lineData);
        mChart.invalidate();

        getToadyLunarString();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    private List<Integer> getToadyLunarString() {

        List<Integer> dataObjs = new ArrayList<Integer>();

        for (int i = 0 ; i < 100 ; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE , i);
            Date day = cal.getTime();
            Lunar lunar = new Lunar(day);
            int[] iBazi = lunar.getBaZiInt();
            Bazi bz = new Bazi(iBazi);
            int[] wuSinPrc = bz.getWuSinPercentage();
            dataObjs.add(wuSinPrc[4]);
        }

        return dataObjs;
    }
}
