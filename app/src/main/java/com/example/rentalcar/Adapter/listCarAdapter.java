package com.example.rentalcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rentalcar.object.vehicle;

import java.util.List;

public class listCarAdapter extends BaseAdapter {
    private Context context;
    List<vehicle> lst;
    int layout;

    public listCarAdapter(Context context,int layout, List<vehicle> lst) {
        this.context = context;
        this.lst = lst;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    //Khởi tạo giao diện cho row với câu lệnh LayoutInflater
            view = inflater.inflate(layout, null);
        }
        else
            view = convertView;

        //TextView tvname = (TextView) view.findViewById(R.id.textViewName);
        //final Course c = lst.get(position);
        //tvname.setText(c.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.detail(c.getId(),c.getName(),c.getSem());
            }
        });


        return view;

    }
}
