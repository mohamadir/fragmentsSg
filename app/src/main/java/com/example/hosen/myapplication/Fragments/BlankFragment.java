package com.example.hosen.myapplication.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hosen.myapplication.Adapters.PaymentsAdapter;
import com.example.hosen.myapplication.R;
import com.example.hosen.myapplication.Models.paymentItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView paymentsLv;
    ArrayList<paymentItem> paymentArray = new ArrayList<paymentItem>();
    LineGraphSeries<DataPoint> series;


    TextView tv;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BlankFragment() {
        super();
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // print Toast Method
    private void printToast(String message) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment_blank, container, false);

        // what should be in onCreate
        paymentsLv=(ListView)view.findViewById(R.id.paymentsLv);
        //String type, String price, String cardStatus, String cardNumber
        paymentArray.add(new paymentItem("Flight","550","Card","**4522"));
        paymentArray.add(new paymentItem("Hotels","360","Card","**4522"));
        paymentArray.add(new paymentItem("Tour Guide","80","None",""));
        paymentArray.add(new paymentItem("Tranpotations","55","None",""));
        paymentArray.add(new paymentItem("Resturants","110","None",""));
        paymentArray.add(new paymentItem("PLaces","45","None",""));
        paymentsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("PayFragment","im in payment fragment");

                Fragment fr=new PaymentsCreditFragemnt();
                FragmentManager fm=getFragmentManager();
                android.app.FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("CID222", "im vlaue from fragment");
                fr.setArguments(args);
                ft.replace(R.id.fragment1, fr);
                ft.commit();
            }
        });
        PaymentsAdapter adapter = new PaymentsAdapter(getActivity(), paymentArray);
        paymentsLv.setAdapter(adapter);
        double x,y;
        x=-5.0;
        GraphView graphView = (GraphView)view.findViewById(R.id.graph);
        series= new LineGraphSeries<DataPoint>();
        for(int i=0;i<1000;i++)
        {
            x+=0.01;
            y=Math.pow(x,2);
            series.appendData(new DataPoint(x,y),true,500);

        }

        graphView.addSeries(series);
        return view;
    }


}
