package com.snapgroup.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.snapgroup.Classes.DayPLan;
import com.snapgroup.Classes.MySingleton;
import com.snapgroup.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
     public  List<DayPLan> dayPlans=new ArrayList<DayPLan>();

    private static final PatternItem DOT = new Dash(20);
    //private static final PatternItem DOT = new Dot();
    private static final int PATTERN_GAP_LENGTH_PX = 10;
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    List<DayPLan> targets;

// Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        targets=new ArrayList<DayPLan>();
        requestJsonArray();
        targets=dayPlans;
        mapFragment.getMapAsync(this);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }

    /*
    * initial the map with a fake points and pollylines (with orange color)
    * */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;


        List<LatLng> coordList = new ArrayList<LatLng>();

        for(int i=0;i<targets.size();i++)
        {
            String name=targets.get(i).name;
            double lat=targets.get(i).lat;
            double lon=targets.get(i).lon;
            LatLng latlng=new LatLng(lat,lon);
                coordList.add(latlng);
            mMap.addMarker(new MarkerOptions()
                    .position(latlng)
                    .title(name)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.0444,31.2357), 15));
            return true;
            }
        });
        int orange = Color.parseColor("#ff6600");

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .addAll(coordList));
        polyline1.setEndCap(new RoundCap());
        polyline1.setWidth(15);
        polyline1.setColor(orange);
        polyline1.setJointType(JointType.ROUND);
        polyline1.setPattern(PATTERN_POLYLINE_DOTTED);


// Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.2227,35.2621),8));
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomIn());


    }



//
    public void requestJsonArray(){
        String url = "http://www.mocky.io/v2/596ddd130f00000c032b7fa8/";


        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                                JSONObject mainArray = response.getJSONObject(1);
                                JSONObject groupObject = mainArray.getJSONObject("group");
                                JSONObject planObject = groupObject.getJSONObject("plan");
                                Log.i("ress", "" + planObject.length());
                                for (int j = 1; j < planObject.length(); j++) {
                                   dayPlans.add(new DayPLan(planObject.getJSONObject("" + j + "").getString("name").toString(), toDouble(planObject.getJSONObject("" + j + "").getString("lat")), toDouble(planObject.getJSONObject("" + j + "").getString("long"))));
                                }
                                targets=dayPlans;
                                onMapReady(mMap);

                        }
                         catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                });

        MySingleton.getInstance(MapActivity.this).addToRequestQueue(jsObjRequest);


    }


    public double toDouble(String str){
        return Double.parseDouble(str);
    }
}
