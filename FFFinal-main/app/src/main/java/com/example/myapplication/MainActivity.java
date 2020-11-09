package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    LinearLayout layout_1;
    LinearLayout layout_bottom;
    LinearLayout menu_1;
    LinearLayout menu_2;
    LinearLayout menu_3;

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    private DrawerLayout drawerLayout;
    private View drawerView;

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Geocoder geocoder;
    private Button button;
    private EditText editText;
    private GoogleMap mMap;

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        //메뉴 소환 버튼
        layout_1 = findViewById(R.id.layout_1);
        layout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        //비상시 대피요령
        menu_1 = findViewById(R.id.menu_1);
        menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KnowHowActivity.class);
                startActivity(intent);
            }
        });

        //알림
        menu_2 = findViewById(R.id.menu_2);
        menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //전화걸기
        menu_3 = findViewById(R.id.menu_3);
        menu_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(intent);
            }
        });

        layout_bottom = findViewById(R.id.layout_bottom);
        layout_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setTitle("종료 알림창")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("종료 알림창");
                alert.show();
            }
        });
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

        @Override
        public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;
        geocoder = new Geocoder(this);

        LatLng location = new LatLng(37.545133, 126.964629);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("숙명여자대학교");
        markerOptions.snippet("대학교");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        MarkerOptions opt1_1 = new MarkerOptions();
        opt1_1.position(new LatLng(37.546432, 126.964717));
        opt1_1.title("순헌관");
        googleMap.addMarker(opt1_1);

        MarkerOptions opt1_2 = new MarkerOptions();
        opt1_2.position(new LatLng(37.546635, 126.965023));
        opt1_2.title("행파교수회관");
        googleMap.addMarker(opt1_2);

        MarkerOptions opt1_3 = new MarkerOptions();
        opt1_3.position(new LatLng(37.546663, 126.964339));
        opt1_3.title("수련교수회관");
        googleMap.addMarker(opt1_3);

        MarkerOptions opt1_4 = new MarkerOptions();
        opt1_4.position(new LatLng(37.546368, 126.963859));
        opt1_4.title("진리관");
        googleMap.addMarker(opt1_4);

        MarkerOptions opt1_5 = new MarkerOptions();
        opt1_5.position(new LatLng(37.545700, 126.963625));
        opt1_5.title("명신관");
        googleMap.addMarker(opt1_5);

        MarkerOptions opt1_6 = new MarkerOptions();
        opt1_6.position(new LatLng(37.545396, 126.963904));
        opt1_6.title("명신신관");
        googleMap.addMarker(opt1_6);

        MarkerOptions opt1_7 = new MarkerOptions();
        opt1_7.position(new LatLng(37.545414, 126.964545));
        opt1_7.title("행정관");
        googleMap.addMarker(opt1_7);

        MarkerOptions opt1_8 = new MarkerOptions();
        opt1_8.position(new LatLng(37.545459, 126.965055));
        opt1_8.title("학생회관");
        googleMap.addMarker(opt1_8);

        MarkerOptions opt1_9 = new MarkerOptions();
        opt1_9.position(new LatLng(37.545919, 126.965728));
        opt1_9.title("숙명여자대학교 대강당");
        googleMap.addMarker(opt1_9);

        MarkerOptions opt2_1 = new MarkerOptions();
        opt2_1.position(new LatLng(37.544687,126.964465));
        opt2_1.title("프라임관");
        googleMap.addMarker(opt2_1);

        MarkerOptions opt2_2 = new MarkerOptions();
        opt2_2.position(new LatLng(37.544760,126.964062));
        opt2_2.title("르네상스");
        googleMap.addMarker(opt2_2);

        MarkerOptions opt2_3= new MarkerOptions();
        opt2_3.position(new LatLng(37.544270,126.964023));
        opt2_3.title("음악대학");
        googleMap.addMarker(opt2_3);

        MarkerOptions opt2_4= new MarkerOptions();
        opt2_4.position(new LatLng(37.543811,126.963847));
        opt2_4.title("사회교육관");
        googleMap.addMarker(opt2_4);

        MarkerOptions opt2_5= new MarkerOptions();
        opt2_5.position(new LatLng(37.543856,126.964510));
        opt2_5.title("약학대학");
        googleMap.addMarker(opt2_5);

        MarkerOptions opt2_6= new MarkerOptions();
        opt2_6.position(new LatLng(37.544326,126.964906));
        opt2_6.title("미술대학");
        googleMap.addMarker(opt2_6);

        MarkerOptions opt2_7= new MarkerOptions();
        opt2_7.position(new LatLng(37.543775,126.965453));
        opt2_7.title("백주년기념관");
        googleMap.addMarker(opt2_7);

        MarkerOptions opt2_8= new MarkerOptions();
        opt2_8.position(new LatLng(37.544183,126.965894));
        opt2_8.title("중앙도서관");
        googleMap.addMarker(opt2_8);

        MarkerOptions opt2_9= new MarkerOptions();
        opt2_9.position(new LatLng(37.544598,126.966359));
        opt2_9.title("과학관");
        googleMap.addMarker(opt2_9);

        googleMap.setOnMarkerClickListener(this) ;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17));



            // 버튼 이벤트
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소
                            10); // 최대 검색 결과 개수
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String[] splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }

        if (marker.getTitle().equals("프라임관")) {
            Intent intent = new Intent(MainActivity.this, PopupActivity.class);
            startActivity(intent);
        }
        return true;
    }
}