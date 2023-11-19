package com.example.musiccase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String[] adresses = {"airbunker93@gmail.com"};
    String subject = "Заказ Оформлен";

    String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntnent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntnent");
        int volume = receivedOrderIntent.getIntExtra("volumeForIntnent", 0 );
        double volumeOrder = receivedOrderIntent.getDoubleExtra("orderVolumeForIntnent", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);
        emailText =
                "Покупатель: " + userName + "\n" +
                "Товар: " +  goodsName + "\n" +
                "Количество: " +  volume + "\n" +
                "За 1ед: " +   price + "\n" +
                "Сумма: " +  volumeOrder ;

        TextView orderText = findViewById(R.id.orderText);
        orderText.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, adresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}