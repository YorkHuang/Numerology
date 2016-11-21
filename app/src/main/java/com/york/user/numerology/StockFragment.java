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
import java.util.Collections;
import java.util.Date;


public class StockFragment extends Fragment implements OnChartValueSelectedListener{


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
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);

        XAxis x1 = mChart.getXAxis();
        x1.setAvoidFirstLastClipping(true);
        x1.setAxisMaximum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(true);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        setData(25, 50);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
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



    private void getToadyLunarString() {

        Date today = new Date(System.currentTimeMillis());
        Lunar lunar = new Lunar(today);
        String[] tdBazi = lunar.getBaZiString();
        int[] iBazi = lunar.getBaZiInt();
        Bazi bz = new Bazi(iBazi);
        int[] wuSinPrc =  bz.getWuSinPercentage();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float xVal = (float) (Math.random() * range);
            float yVal = (float) (Math.random() * range);
            entries.add(new Entry(xVal, yVal));
        }

        // sort by x-value
        Collections.sort(entries, new EntryXComparator());

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "DataSet 1");

        set1.setLineWidth(1.5f);
        set1.setCircleRadius(4f);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        // set data
        mChart.setData(data);
    }
}
