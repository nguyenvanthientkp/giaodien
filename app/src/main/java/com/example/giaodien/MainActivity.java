package com.example.giaodien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView led;
    TextView nhietdo,ph,thucan;
    ToggleButton btnLed,btnTangNhiet,btnGiamNhiet,btnAn,btnO2,btnCamera;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetID();
        TinhTrang();
        initView();
        dieukhien();
    }
    public void SetID(){
        led=findViewById(R.id.img_led);
        nhietdo=findViewById(R.id.txt_nhiet);
        ph=findViewById(R.id.txt_ph);
        thucan=findViewById(R.id.txt_thucan);
        btnLed=findViewById(R.id.btnLed);
        btnTangNhiet=findViewById(R.id.btnTangNhiet);
        btnGiamNhiet=findViewById(R.id.btnGiamNhiet);
        btnAn=findViewById(R.id.btnAn);
        btnO2=findViewById(R.id.btnO2);
        btnCamera=findViewById(R.id.btnCamera);
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
                Toast.makeText(MainActivity.this,"loi ",Toast.LENGTH_SHORT);
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
    public void initView(){
        RecyclerView recyclerView=findViewById(R.id.List);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<data> arrayList=new ArrayList<>();
        arrayList.add(new data(R.drawable.led_on,"on","15°C","1am"));
        arrayList.add(new data(R.drawable.led_on,"on","20°C","3am"));
        arrayList.add(new data(R.drawable.led_off,"off","25°C","5am"));
        arrayList.add(new data(R.drawable.led_on,"on","15°C","7am"));
        arrayList.add(new data(R.drawable.led_off,"off","10°C","10am"));
        dataAdapter dataAdapter=new dataAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(dataAdapter);
    }
}
