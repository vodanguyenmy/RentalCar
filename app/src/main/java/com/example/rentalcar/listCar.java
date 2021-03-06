package com.example.rentalcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.rentalcar.object.vehicle;
import com.example.rentalcar.Adapter.listCarAdapter;
import com.example.rentalcar.DAO.Repository;

import java.util.ArrayList;
import java.util.List;

public class listCar extends AppCompatActivity {

    ListView carListView;
    List<vehicle> lstCar;
    Repository repository;
    listCarAdapter listCarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);
        carListView = (ListView) findViewById(R.id.listViewCar);



        repository = new Repository(this,"rental_car.sqlite",null,1);
        //repository.insertDataCar();
        lstCar = new ArrayList<>();
        lstCar = repository.getCarList();
        listCarAdapter = new listCarAdapter(this, R.layout.dong_list_car, lstCar);
        carListView.setAdapter(listCarAdapter);


    }
}
