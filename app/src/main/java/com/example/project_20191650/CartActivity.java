package com.example.project_20191650;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Integer[] textId = {R.id.text1, R.id.text2,R.id.text3,R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8};
        Integer[] text_Id = {R.id.text1_1, R.id.text2_1,R.id.text3_1,R.id.text4_1, R.id.text5_1, R.id.text6_1, R.id.text7_1, R.id.text8_1};
        Integer[] checkId = {R.id.check1, R.id.check2, R.id.check3, R.id.check4, R.id.check5, R.id.check6, R.id.check7, R.id.check8};

        final TextView[] textList = new TextView[8];
        final TextView[] text_List = new TextView[8];
        final CheckBox[] checkList = new CheckBox[8];

        for(int i=0; i<8; i++) {
            textList[i] = (TextView) findViewById(textId[i]);
            text_List[i] = (TextView) findViewById(text_Id[i]);
            checkList[i] = (CheckBox) findViewById(checkId[i]);
        }

        Button btn_cart_buy = (Button) findViewById(R.id.btn_cart_buy);
        Button btn_home = (Button) findViewById(R.id.btn_home);

        Intent intent = getIntent();

        ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem"); // 체크된 아이템 이름 ArrayList로 받기
        ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice"); // 체크된 아이템 가격 ArryaList로 받기

        for(int i=0; i<checkedItem.size(); i++) {
            textList[i].setText(checkedItem.get(i));
            text_List[i].setText(checkedPrice.get(i));
            checkList[i].setVisibility(View.VISIBLE); // 장바구니에 있는 아이템 개수만큼만 체크박스 보이게 하기
        }

        btn_cart_buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, BuyActivity.class);
                intent.putExtra("checkedItem", SelectActivity.GetCheckedItem(checkList, textList));
                intent.putExtra("checkedPrice", SelectActivity.GetCheckedPrice(checkList, text_List));
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
