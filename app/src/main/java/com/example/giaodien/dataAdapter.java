package com.example.giaodien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.Viewhoder>{
    ArrayList<data> data;
    Context context;

    public dataAdapter(ArrayList<com.example.giaodien.data> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
    holder.txtTG.setText(data.get(position).getTGian());
    holder.txtNhiet.setText(data.get(position).getNhiet());
    holder.txtAs.setText(data.get(position).getASang());
    holder.hinh.setImageResource(data.get(position).getHinh());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        TextView txtAs,txtTG,txtNhiet;
        ImageView hinh;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            txtAs=itemView.findViewById(R.id.txtAs);
            txtNhiet=itemView.findViewById(R.id.txtNhiet);
            txtTG=itemView.findViewById(R.id.txtTg);
            hinh=itemView.findViewById(R.id.img);
        }
    }
}
