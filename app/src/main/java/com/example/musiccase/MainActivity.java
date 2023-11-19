package com.example.musiccase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int volume = 1;
    Spinner product;
    ArrayList productArrayList;
    ArrayAdapter productAdapter;

    HashMap goodsMap;

    String goodsName;

    EditText userName;

    double Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createProduct();
        createMap();
        userName = findViewById(R.id.nameEditText);


    }
    void createProduct(){
        product = findViewById(R.id.product);
        product.setOnItemSelectedListener(this);
        productArrayList = new ArrayList();

        productArrayList.add("BANDANA");
        productArrayList.add("TEC");
        productArrayList.add("IN MY DNA");

        productAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, productArrayList);
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product.setAdapter(productAdapter);
    }
    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("BANDANA", 1790.0);
        goodsMap.put("TEC", 1390.0);
        goodsMap.put("IN MY DNA", 2190.0);

    }

    public void volume_pls(View view) {
        volume = volume + 1;
        if (volume > 10) {
            volume = 10;
        }
        TextView volumeText = findViewById(R.id.volumeText);
        volumeText.setText("" + volume);

        TextView price = findViewById(R.id.orderSum);
        price.setText("" + volume * Order);
    }

    public void volume_mns(View view) {
        volume = volume - 1;
        if (volume < 1) {
            volume = 1;
        }
        TextView volumeText = findViewById(R.id.volumeText);
        volumeText.setText("" + volume);

        TextView price = findViewById(R.id.orderSum);
        price.setText("" + volume * Order);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = product.getSelectedItem().toString();
        Order = (double)goodsMap.get(goodsName);
        TextView price = findViewById(R.id.orderSum);
        price.setText("" + volume * Order);

        ImageView reImage = findViewById(R.id.image);

       /* if (goodsName.equals("Bandana")){
            reImage.setImageResource(R.drawable.bandana);
        }
        else if (goodsName.equals("TEC")) {
            reImage.setImageResource(R.drawable.album2);
        }
        else if (goodsName.equals("IN MY DNA")) {
            reImage.setImageResource(R.drawable.album3);

        }*/
        switch (goodsName){
            case "bandana":
                reImage.setImageResource(R.drawable.bandana);
                break;
            case "TEC":
                reImage.setImageResource(R.drawable.tec);
                break;
            case "IN MY DNA":
                reImage.setImageResource(R.drawable.inmydna);
                break;
            default:
                reImage.setImageResource(R.drawable.bandana);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void add(View view) {

        Order ordersum = new Order();
        ordersum.userName = userName.getText().toString();
       /* Log.d("printUserName", ordersum.userName); logcat debud*/
        ordersum.goodsName = goodsName;
        ordersum.volume = volume;
        ordersum.orderVolume = volume * Order;
        ordersum.price = Order;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntnent", ordersum.userName);
        orderIntent.putExtra("goodsNameForIntnent", ordersum.goodsName);
        orderIntent.putExtra("volumeForIntnent", ordersum.volume);
        orderIntent.putExtra("orderVolumeForIntnent", ordersum.orderVolume);
        orderIntent.putExtra("price", ordersum.price);
        startActivity(orderIntent);

    }
}