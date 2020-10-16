package com.example.project_20191650;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class BuyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Integer[] tId = {R.id.t1, R.id.t2, R.id.t3, R.id.t4, R.id.t5, R.id.t6, R.id.t7, R.id.t8, R.id.t9, R.id.t10, R.id.t11};
        Integer[] t_Id = {R.id.t1_1, R.id.t2_1, R.id.t3_1, R.id.t4_1, R.id.t5_1, R.id.t6_1, R.id.t7_1, R.id.t8_1, R.id.t9_1};

        TextView[] tList = new TextView[11];
        TextView[] t_List = new TextView[9];

        for(int i=0; i<11; i++) {
            tList[i] = (TextView) findViewById(tId[i]);
        }
        for(int i=0; i<9; i++) {
            t_List[i] = (TextView) findViewById(t_Id[i]);
        }

        Integer totalPrice = 0;

        Intent intent = getIntent();

        ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem"); // 체크된 아이템 이름 ArrayList로 받기
        ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice"); // 체크된 아이템 가격 ArryaList로 받기

        for(String p : checkedPrice) { // 총 결제 금액 계산
            p = p.replace(",", "");
            p = p.substring(0, p.length()-1);
            totalPrice += Integer.valueOf(p);
        }

        String strTotalPrice = String.format("%,d", totalPrice); // 1000단위로 콤마 찍기
        strTotalPrice += "원";

//        int cnt = 0;
//        for(int i=strTotalPrice.length()-2; i>=0; i--) {
//            if(strTotalPrice.charAt(i) != ',') {
//                cnt++;
//            }
//            if(cnt%3 == 0) {
//                strTotalPrice = strTotalPrice.substring(0,i-1) + "," + strTotalPrice.substring(i,strTotalPrice.length());
//            }
//        }

        t_List[8].setText(strTotalPrice);

        for(int i=0; i<checkedItem.size(); i++) {
            tList[i].setText(checkedItem.get(i));
            t_List[i].setText(checkedPrice.get(i));
        }


    }
}
