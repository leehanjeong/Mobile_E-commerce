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
    //view 선언
    Button btn_cart;
    Button btn_buy;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    CheckBox cb6;
    CheckBox cb7;
    CheckBox cb8;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;

    TextView tv1_1;
    TextView tv2_1;
    TextView tv3_1;
    TextView tv4_1;
    TextView tv5_1;
    TextView tv6_1;
    TextView tv7_1;
    TextView tv8_1;

    CheckBox[] cbList = {cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8};
    TextView[] tvList = {tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8};
    TextView[] tv_List = {tv1_1, tv2_1, tv3_1, tv4_1, tv5_1, tv6_1, tv7_1, tv8_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        //view들 initialize
        this.InitializeView();

        //button click event 처리
        btn_cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, CartActivity.class);
                intent.putExtra("checkedItem", GetCheckedItem()); //GetCheckedItem()의 리턴값인 checkedItem을 CartActivity로 넘김
                intent.putExtra("checkedPrice", GetCheckedPrice()); //GetCheckedPrice()의 리턴값인 checkedPrice를 CartActivity로 넘김
                startActivity(intent);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this, BuyActivity.class);
                intent.putExtra("checkedItem", GetCheckedItem()); //Checked()의 리턴값인 checkedItem을 CartActivity로 넘김
                intent.putExtra("checkedPrice", GetCheckedPrice());
                startActivity((intent));
            }
        });

    }

    public void InitializeView() { //view들을 initialize 해주는 함수
        Button btn_cart = (Button) findViewById(R.id.btn_cart);
        Button btn_buy = (Button) findViewById(R.id.btn_buy);

        CheckBox cb1 = (CheckBox) findViewById(R.id.cb1);
        CheckBox cb2 = (CheckBox) findViewById(R.id.cb2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.cb3);
        CheckBox cb4 = (CheckBox) findViewById(R.id.cb4);
        CheckBox cb5 = (CheckBox) findViewById(R.id.cb5);
        CheckBox cb6 = (CheckBox) findViewById(R.id.cb6);
        CheckBox cb7 = (CheckBox) findViewById(R.id.cb7);
        CheckBox cb8 = (CheckBox) findViewById(R.id.cb8);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        TextView tv4 = (TextView) findViewById(R.id.tv4);
        TextView tv5 = (TextView) findViewById(R.id.tv5);
        TextView tv6 = (TextView) findViewById(R.id.tv6);
        TextView tv7 = (TextView) findViewById(R.id.tv7);
        TextView tv8 = (TextView) findViewById(R.id.tv8);

        TextView tv1_1 = (TextView) findViewById(R.id.tv1_1);
        TextView tv2_1 = (TextView) findViewById(R.id.tv2_1);
        TextView tv3_1 = (TextView) findViewById(R.id.tv3_1);
        TextView tv4_1 = (TextView) findViewById(R.id.tv4_1);
        TextView tv5_1 = (TextView) findViewById(R.id.tv5_1);
        TextView tv6_1 = (TextView) findViewById(R.id.tv6_1);
        TextView tv7_1 = (TextView) findViewById(R.id.tv7_1);
        TextView tv8_1 = (TextView) findViewById(R.id.tv8_1);
    }

    public ArrayList<String> GetCheckedItem() {
        ArrayList<String> checkedItem = new ArrayList<String>();

        for(int i=0; i<8; i++) {
            if(cbList[i].isChecked()) {
                checkedItem.add(tvList[i].getText().toString());
            }
        }
        return checkedItem;
    }

    public ArrayList<String> GetCheckedPrice() {
        ArrayList<String> checkedPrice = new ArrayList<String>();

        for(int i=0; i<8; i++) {
            if(cbList[i].isChecked()) {
                checkedPrice.add(tv_List[i].getText().toString());
            }
        }
        return checkedPrice;
    }
}