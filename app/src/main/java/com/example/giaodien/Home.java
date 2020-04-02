package com.example.giaodien;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ImageView led;
    TextView nhietdo,ph,thucan;
    ToggleButton btnLed,btnTangNhiet,btnGiamNhiet,btnAn,btnO2,btnCamera;
    RecyclerView recyclerView;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SetID(view);
        TinhTrang();
        initView(recyclerView);
        dieukhien();
        return view ;
    }
    public void SetID(View view){
        led=view.findViewById(R.id.img_led);
        nhietdo=view.findViewById(R.id.txt_nhiet);
        ph=view.findViewById(R.id.txt_ph);
        thucan=view.findViewById(R.id.txt_thucan);
        btnLed=view.findViewById(R.id.btnLed);
        btnTangNhiet=view.findViewById(R.id.btnTangNhiet);
        btnGiamNhiet=view.findViewById(R.id.btnGiamNhiet);
        btnAn=view.findViewById(R.id.btnAn);
        btnO2=view.findViewById(R.id.btnO2);
        btnCamera=view.findViewById(R.id.btnCamera);
        recyclerView=view.findViewById(R.id.List);
    }
    public void TinhTrang(){
        myRef.child("HoCa").child("Led").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int k =Integer.parseInt(dataSnapshot.getValue().toString());
                if(k==1) {
                    btnLed.setChecked(true);
                    led.setImageResource(R.drawable.led_on);
                }
                else {
                    btnLed.setChecked(false);
                    led.setImageResource(R.drawable.led_off);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"loi ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void dieukhien(){
        CompoundButton.OnCheckedChangeListener listener=
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(buttonView.getId()==R.id.btnLed){
                            if(isChecked) myRef.child("HoCa").child("Led").setValue(1);
                            else myRef.child("HoCa").child("Led").setValue(0);
                        }
                        if(buttonView.getId()==R.id.btnO2){
                            if(isChecked) myRef.child("HoCa").child("Oxi").setValue(1);
                            else myRef.child("HoCa").child("Oxi").setValue(0);
                        }
                        if(buttonView.getId()==R.id.btnAn){
                            if(isChecked) myRef.child("HoCa").child("ChoAn").setValue(1);
                            else myRef.child("HoCa").child("ChoAn").setValue(0);
                        }
                    }
                };
        btnLed.setOnCheckedChangeListener(listener);
        btnO2.setOnCheckedChangeListener(listener);
        btnAn.setOnCheckedChangeListener(listener);
    }
    public void initView(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<data> arrayList=new ArrayList<>();
        arrayList.add(new data(R.drawable.led_on,"on","15°C","1am"));
        arrayList.add(new data(R.drawable.led_on,"on","20°C","3am"));
        arrayList.add(new data(R.drawable.led_off,"off","25°C","5am"));
        arrayList.add(new data(R.drawable.led_on,"on","15°C","7am"));
        arrayList.add(new data(R.drawable.led_off,"off","10°C","10am"));
        dataAdapter dataAdapter=new dataAdapter(arrayList,getActivity().getApplicationContext());
        recyclerView.setAdapter(dataAdapter);
    }
}
