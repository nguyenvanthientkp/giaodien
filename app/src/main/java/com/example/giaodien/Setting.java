package com.example.giaodien;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    private ToggleButton btnauto;
    private LinearLayout hideShow;

    public Setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        btnauto = view.findViewById(R.id.btnauto);
        hideShow = view.findViewById(R.id.hideShow);
        btnauto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == btnauto){
                    if (isChecked){
                        hideShow.setVisibility(View.VISIBLE);
                    }else{
                        hideShow.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        return view;
    }

}
