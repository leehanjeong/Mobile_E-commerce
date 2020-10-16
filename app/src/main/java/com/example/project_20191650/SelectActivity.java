package com.example.project_20191650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Integer[] tvId = {R.id.tv1, R.id.tv2,R.id.tv3,R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8};
        Integer[] tv_Id = {R.id.tv1_1, R.id.tv2_1,R.id.tv3_1,R.id.tv4_1, R.id.tv5_1, R.id.tv6_1, R.id.tv7_1, R.id.tv8_1};
        Integer[] cbId = {R.id.cb1, R.id.cb2, R.id.cb3, R.id.cb4, R.id.cb5, R.id.cb6, R.id.cb7, R.id.cb8};

        final TextView[] tvList = new TextView[8];
        final TextView[] tv_List = new TextView[8];
        final CheckBox[] cbList = new CheckBox[8];

        for(int i=0; i<8; i++) {
            tvList[i] = (TextView) findViewById(tvId[i]);
            tv_List[i] = (TextView) findViewById(tv_Id[i]);
            cbList[i] = (CheckBox) findViewById(cbId[i]);
        }

        Button btn_cart = (Button) findViewById(R.id.btn_cart);
        Button btn_buy = (Button) findViewById(R.id.btn_buy);

        //button click event 처리
        btn_cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, CartActivity.class);
                intent.putExtra("checkedItem", GetCheckedItem(cbList, tvList)); //GetCheckedItem()의 리턴값인 checkedItem을 CartActivity로 넘김
                intent.putExtra("checkedPrice", GetCheckedPrice(cbList, tv_List)); //GetCheckedPrice()의 리턴값인 checkedPrice를 CartActivity로 넘김
                startActivity(intent);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, BuyActivity.class);
                intent.putExtra("checkedItem", GetCheckedItem(cbList, tvList));
                intent.putExtra("checkedPrice", GetCheckedPrice(cbList, tv_List));
                startActivity((intent));
            }
        });

    } // End of OnCreate()

    public static ArrayList<String> GetCheckedItem(CheckBox[] cbArr, TextView[] tvArr) { //check된 상품의 상품명을 ArrayList에 담아 리턴해주는 함수, CartActivity에서도 사용하기 위해 static 선언.
        ArrayList<String> checkedItem = new ArrayList<String>();

        for(int i=0; i<8; i++) {
            if(cbArr[i].isChecked()) {
                checkedItem.add(tvArr[i].getText().toString());
            }
        }
        return checkedItem;
    }

    public static ArrayList<String> GetCheckedPrice(CheckBox[] cbArr, TextView[] tv_Arr) { //check된 상품의 가격을 ArrayList에 담아 리턴해주는 함수
        ArrayList<String> checkedPrice = new ArrayList<String>();

        for(int i=0; i<8; i++) {
            if(cbArr[i].isChecked()) {
                checkedPrice.add(tv_Arr[i].getText().toString());
            }
        }
        return checkedPrice;
    }
}