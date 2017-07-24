package com.example.hosen.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class DetailsFragmetn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LineGraphSeries<DataPoint> series;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button flyBt;
    public static DetailsFragmetn newInstance(String param1, String param2) {
        DetailsFragmetn fragment = new DetailsFragmetn();
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
        // Inflate the layout for this fragment4

        View view= inflater.inflate(R.layout.fragment_details_fragmetn, container, false);


        double x,y;
        x=-5.0;
        GraphView graphView = (GraphView)view.findViewById(R.id.graph3);
        series= new LineGraphSeries<DataPoint>();
        for(int i=0;i<1000;i++)
        {
            x+=0.01;
            y=Math.pow(x,2);
            series.appendData(new DataPoint(x,y),true,500);

        }

        graphView.addSeries(series);

       /* flyBt=(Button)view.findViewById(R.id.flyBtt);
        flyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("hello","hello");
            }
        });*/
        return view;

    }


}
