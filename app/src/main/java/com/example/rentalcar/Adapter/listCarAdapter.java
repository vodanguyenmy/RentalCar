package com.example.rentalcar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentalcar.R;
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

        TextView tvnameCar = (TextView) view.findViewById(R.id.txtCarName);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgCar);
        Button btnRental = (Button) view.findViewById(R.id.btnRentalCar);

        //int i = R.drawable.;
        vehicle c = lst.get(position);
        tvnameCar.setText(c.getV_name());

        int iconResource = context.getResources().getIdentifier(c.getV_image() , "drawable", context.getPackageName());
        //Log.i(TAG, "---getView: "+iconResource);
        imageView.setImageResource(iconResource);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //context.detail(c.getId(),c.getName(),c.getSem());
            }
        });


        return view;

    }
}
