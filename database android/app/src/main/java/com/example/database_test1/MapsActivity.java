package com.example.database_test1;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;//宣告 google map 物件
    private TextView txtShow;
    private float zoom;
    private Spinner spnMapType;
    private String[] MapTypes = new String[]{"一般地圖", "混合地圖", "衛星地圖", "地形圖"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // 取得介面元件
        txtShow=(TextView)findViewById(R.id.txtShow);
        spnMapType = (Spinner) findViewById(R.id.spntype);
        // 建立 ArrayAdapter
        ArrayAdapter<String> adapterspnMapType = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, MapTypes);
        // 設定 Spinner 顯示的格式
        adapterspnMapType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 設定 Spinner 的資料來源
        spnMapType.setAdapter(adapterspnMapType);

        // 設定 spnGeoPoint 元件 ItemSelected 事件的 listener
        spnMapType.setOnItemSelectedListener(spnMapTypeListener);

        // 利用 getSupportFragmentManager() 方法取得管理器
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // 以非同步方式取得 GoogleMap 物件
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String name =bundle.getString("name");
        Double v =bundle.getDouble("v"),v2=bundle.getDouble("v2");
        LatLng latLng= new LatLng(v,v2);

        //LatLng latLng= new LatLng(23.862696,120.904211);
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);       // 一般地圖
        mMap.getUiSettings().setCompassEnabled(true);     // 顯示指南針
        mMap.getUiSettings().setZoomControlsEnabled(true);// 顯示縮放圖示
        zoom = 17; //設定放大倍率1(地球)-21(街景)
        mMap.addMarker( new MarkerOptions().position(latLng));

        // 建立觀看地圖的視點位罝
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng) // 地圖的中心點
                .zoom(zoom)                  // 放大倍率 17
                .bearing(0)                  // 指南針旋轉 0 度
                .tilt(0)                     // 俯視的角度
                .build();                    // 建立 CameraPosition 物件

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        txtShow.setText(name);
    }

    private Spinner.OnItemSelectedListener spnMapTypeListener =
            new Spinner.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    String sel = parent.getSelectedItem().toString();
                    switch (sel) {
                        case "一般地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // 一般地圖
                            break;
                        case "混合地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // 混合地圖
                            break;
                        case "衛星地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // 衛星地圖
                            break;
                        case "地形圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);   // 地形圖
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };
}