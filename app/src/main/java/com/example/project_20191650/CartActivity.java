package com.example.project_20191650;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();

        Button btn_cart_buy = (Button) findViewById(R.id.btn_cart_buy);
        Button btn_home = (Button) findViewById(R.id.btn_home);

        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);
        TextView text5 = (TextView) findViewById(R.id.text5);
        TextView text6 = (TextView) findViewById(R.id.text6);
        TextView text7 = (TextView) findViewById(R.id.text7);
        TextView text8 = (TextView) findViewById(R.id.text8);

        TextView text1_1 = (TextView) findViewById(R.id.text1_1);
        TextView text2_1 = (TextView) findViewById(R.id.text2_1);
        TextView text3_1 = (TextView) findViewById(R.id.text3_1);
        TextView text4_1 = (TextView) findViewById(R.id.text4_1);
        TextView text5_1 = (TextView) findViewById(R.id.text5_1);
        TextView text6_1 = (TextView) findViewById(R.id.text6_1);
        TextView text7_1 = (TextView) findViewById(R.id.text7_1);
        TextView text8_1 = (TextView) findViewById(R.id.text8_1);

        TextView[] textList = {text1, text2, text3, text4, text5, text6, text7, text8};
        TextView[] text_List = {text1_1, text2_1, text3_1, text4_1, text5_1, text6_1, text7_1, text8_1};

        ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem");
        ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice");

        for(int i=0; i<checkedItem.size(); i++) {
            textList[i].setText(checkedItem.get(i));
            text_List[i].setText(checkedPrice.get(i));
        }

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
