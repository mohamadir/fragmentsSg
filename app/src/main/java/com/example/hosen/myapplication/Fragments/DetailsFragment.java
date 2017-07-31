package com.example.hosen.myapplication.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hosen.myapplication.Activities.MainActivity;
import com.example.hosen.myapplication.Activities.ServicesActivity;
import com.example.hosen.myapplication.Activities.SignInActivity;
import com.example.hosen.myapplication.Classes.MySingleton;
import com.example.hosen.myapplication.R;
/*import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;*/
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   // LineGraphSeries<DataPoint> series;
    Button joinBt;
    public static String isJoind="";
    public static String groupID;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button flyBt;
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
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

        SharedPreferences settings=getActivity().getSharedPreferences("SnapGroup",MODE_PRIVATE);
        String image = settings.getString("gImage"," asdasdasd");
        String from = settings.getString("gOrigin"," asdasdasd");
        String to = settings.getString("gDestination"," asdasdasd");
        String description = settings.getString("gDescription"," asdasdasd");
        String title = settings.getString("gTitle"," asdasdasd");
        String id = settings.getString("gId","-1");
        groupID=id;
        Log.i("GROOPY",id);
        joinBt=(Button)view.findViewById(R.id.detailsFragment_joinBt);
        joinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings=getActivity().getSharedPreferences("UserLog",MODE_PRIVATE);
                String signed = settings.getString("isSigned","false");
                if(!signed.equals("false"))
                {
                    String email = settings.getString("email","false");
                    joinRequest(email);

                }
                else
                {

                }
            }
        });
        ImageView groupImage= (ImageView) view.findViewById(R.id.details_groupIv);
        TextView fromTv=(TextView)view.findViewById(R.id.details_fromTv);
        TextView toTv=(TextView)view.findViewById(R.id.detilas_toTv);
        TextView descTv=(TextView)view.findViewById(R.id.details_descriptionTv);
        TextView titleTv=(TextView)view.findViewById(R.id.details_titleTv);
        fromTv.setText(from);
        toTv.setText(to);
        descTv.setText(description);
        titleTv.setText(title);
        Picasso.with(getActivity()).load(image).into(groupImage);

/*
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

        graphView.addSeries(series);*/

       /* flyBt=(Button)view.findViewById(R.id.flyBtt);
        flyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("hello","hello");
            }
        });*/
        return view;

    }

    public void joinRequest(String email){

        String url="http://172.104.150.56/api/groups/join/"
                +groupID+"/"+email;
        Log.i("hihihi",url);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                Log.i("hihihi","hihihi");
                builder.setMessage("Join Successfully")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                    isJoind="OK";
                                joinBt.setText("JOIND :-)");
                                joinBt.setVisibility(View.INVISIBLE);


                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isJoind="NOTOK";

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                Log.i("errorr","error");
                builder.setMessage("Login Faild ! :-(")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                joinBt.setVisibility(View.INVISIBLE);

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(sr);

    }

}
