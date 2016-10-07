package com.androidsrc.server;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficNodes extends BotonesActivity {
    PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_nodes);
        mChart = new PieChart(this);
        setContentView(mChart);

        mChart.setUsePercentValues(true);
        mChart.setDescription("Numero de telefono");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        ArrayList<Entry> entries = new ArrayList<Entry>();
        MeshNode nodos = MapManager.listaMeshNodos.inicio;
        ArrayList<String> labels= new ArrayList<String>();
        int contador = 1;
        while(nodos!=null){
            entries.add(new BarEntry(nodos.getBytedisponibles(),contador));
            labels.add("id: " + String.valueOf(nodos.getId())+", bytes totales: "+String.valueOf(nodos.getBytesTot())+ ", bytes disponibles: " + String.valueOf(nodos.getBytedisponibles()));
            contador = contador +1;
            nodos= nodos.siguiente;

        }


        PieDataSet dataset = new PieDataSet(entries,"Numero de los nodos");
        dataset.setSliceSpace(3);
        dataset.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int Contador : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(Contador);

        for(int Contador : ColorTemplate.JOYFUL_COLORS)
            colors.add(Contador);
        for(int Contador : ColorTemplate.COLORFUL_COLORS)
            colors.add(Contador);
        for(int Contador : ColorTemplate.LIBERTY_COLORS)
            colors.add(Contador);
        for(int Contador : ColorTemplate.PASTEL_COLORS)
            colors.add(Contador);
        colors.add(ColorTemplate.getHoloBlue());
        dataset.setColors(colors);


        PieData data = new PieData(labels,dataset);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);
        mChart.highlightValue(null);

        //Actualizar pie chart
        mChart.invalidate();
    }
}
