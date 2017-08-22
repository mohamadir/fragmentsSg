package com.snapgroup.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.snapgroup.R;

public class MapRadiusActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
public static int radiusNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_radius);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.radiusMap);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                final LatLng latLng2 = latLng;
               mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                View radiusView= LayoutInflater.from(MapRadiusActivity.this).inflate(R.layout.add_circle_map_dialog,null);
                final EditText radiusEt = (EditText) radiusView.findViewById(R.id.radiusEt);
                radiusEt.setVisibility(View.GONE);
                final SeekBar seekBar = (SeekBar) radiusView.findViewById(R.id.mapSeekBar);
                final TextView radiusTv = (TextView) radiusView.findViewById(R.id.radiusTextView);
                final LinearLayout radius_layout = (LinearLayout) radiusView.findViewById(R.id.radius_layout);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        radiusNumber=seekBar.getProgress();
                        radiusTv.setText(""+(double)((double)seekBar.getProgress()/1000));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(MapRadiusActivity.this);
                builder.setMessage("Enter Radius");
                builder.setView(radiusView);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String  validateCodeString =radiusEt.getText().toString();
                        int radiusM=0;
                        if(radiusTv.getText()!=null&&!radiusTv.getText().equals(""))
                            radiusM= radiusNumber;
                        Toast.makeText(MapRadiusActivity.this, "Radius is:"+radiusM, Toast.LENGTH_SHORT).show();
                        LatLng latLng = latLng2;
                        int d = 500;
                        Bitmap bm = Bitmap.createBitmap(d, d, Bitmap.Config.ARGB_8888);
                        Canvas c = new Canvas(bm);
                        Paint p = new Paint();
                        p.setColor(getResources().getColor(R.color.orange));
                        p.setStyle(Paint.Style.FILL_AND_STROKE);
                        c.drawCircle(d/2, d/2, d/2, p);
                        BitmapDescriptor bmD = BitmapDescriptorFactory.fromBitmap(bm);
                        mMap.addGroundOverlay(new GroundOverlayOptions().
                                image(bmD).
                                position(latLng,radiusM*2,radiusM*2).
                                transparency(0.4f));
                    }
                });

                builder.setNegativeButton("בטל", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.0853,34.7818),9));
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap = googleMap;
        MapStyleOptions style=MapStyleOptions.loadRawResourceStyle(this,R.raw.style_json);
        mMap.setMapStyle(style);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;

        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMap.addMarker(new MarkerOptions()
                        .position(marker.getPosition())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                mMap.clear();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),13));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(marker.getPosition().latitude+0.0234,marker.getPosition().longitude))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(marker.getPosition().latitude,marker.getPosition().longitude+0.0234))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(marker.getPosition().latitude-0.0234,marker.getPosition().longitude+0.0234))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                mMap.addMarker(new MarkerOptions()
                        .position(marker.getPosition())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                mMap.addMarker(new MarkerOptions()
                        .position(marker.getPosition())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker_2)));
                return true;
            }
        });
        mMap.setMyLocationEnabled(true);
      // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.0853,34.7818),8));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
