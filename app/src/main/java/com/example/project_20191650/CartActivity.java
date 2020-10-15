package com.example.project_20191650;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();

        Button btn_cart_buy = (Button) findViewById(R.id.btn_cart_buy);
        Button btn_home = (Button) findViewById(R.id.btn_home);

        ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem");
        ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice");

        btn_cart_buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}
