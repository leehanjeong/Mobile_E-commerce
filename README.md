# Project_20191650
--------------------------------
### 실행 환경
AVD: Pixel 2 API 29
---------------------------------
### 파일 구성
-activity_select.xml (상품 선택 페이지)
-activity_cart.xml (장바구니 페이지)
-activity_buy.xml (구매 페이지)
#####
-SelectActivity.java: 선택된 상품의 정보를 CartActivity 또는 BuyActivity로 전달  
-CartActivity.java: SelectActivity에서 받은 선택 상품을 보여주고, 다시 선택된 상품을 BuyActivity로 전달. Home버튼을 누르면 다시 상품 선택 페이지로 돌아감.  
-BuyActivity.java: CartActivity 또는 SelectActivity에서 받은 정보로 총 금액을 계산하고, 연락처, 주소를 입력받음. 이를 firebase에 저장.  
--------------------------------
### 실행 화면

##### 1. 상품 선택 페이지  
![2020-10-23 (13)](https://user-images.githubusercontent.com/54920378/97005907-0b04f600-157a-11eb-8504-fba3456f665a.png)
![2020-10-23 (14)](https://user-images.githubusercontent.com/54920378/97005926-0fc9aa00-157a-11eb-914c-404252bb264e.png)  
RelativeLayout을 이용하여 구성하였다.  
ScrollView, ImageView, TextView, CheckBox, Button을 이용하여 화면을 구성하였다.  
Buy버튼을 누르면 구매페이지로, Cart버튼을 누르면 장바구니 페이지로 이동한다.  

##### 2. 장바구니 페이지  
![2020-10-23 (15)](https://user-images.githubusercontent.com/54920378/97005939-135d3100-157a-11eb-810a-570f1e51660c.png)  
LinearLayout을 이용하여 구성하였다.  
TextView, CheckBox, Button을 이용하여 화면을 구성하였다.  
Buy버튼을 누르면 구매페이지로, Home버튼을 누르면 상품 선택 페이지로 돌아간다.  

##### 3. 구매페이지  
![2020-10-23 (16)](https://user-images.githubusercontent.com/54920378/97005954-16f0b800-157a-11eb-9cac-e69c5677488d.png)
![2020-10-23 (17)](https://user-images.githubusercontent.com/54920378/97005961-18ba7b80-157a-11eb-85cb-9cbeec3411c9.png)  
TableLayout을 이용하여 구성하였다.  
TableRow로 행을 추가하여 그 안에 TextView, EditText, Button을 배치시켰다.  
주소와 연락처를 입력한 후 결제하기 버튼을 누르면 다시 선택페이지로 돌아간 후,   
![2020-10-23 (20)](https://user-images.githubusercontent.com/54920378/97005969-1b1cd580-157a-11eb-92ab-16abc2ffd963.png)  
주문이 완료되었습니다. 라는 toast메세지가 뜨도록 하였다.  
--------------------------------
### 코드 구현 설명
1) SelectActivity.java

'''
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
 '''  
 다음 액티비티로 보낼 정보(상품 이름, 상품 가격)을 ArrayList에 담는 코드이다.  

'''
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
        '''    
버튼 클릭을 했을 때 Intent를 이용하여 정보와 함께 다른 액티비티로 넘어가는 코드이다.  

2) CartActivity  
'''
        Intent intent = getIntent();

        ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem"); // 체크된 아이템 이름 ArrayList로 받기
        ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice"); // 체크된 아이템 가격 ArryaList로 받기
'''  
SelectActivity에서 보낸 Intent를 받는 코드이다.  
'''
        for(int i=0; i<checkedItem.size(); i++) {
            textList[i].setText(checkedItem.get(i));
            text_List[i].setText(checkedPrice.get(i));
            checkList[i].setVisibility(View.VISIBLE); // 장바구니에 있는 아이템 개수만큼만 체크박스 보이게 하기
        }
'''  
8개의 체크박스 중 들어온 상품이 개수만큼만 체크박스를 보이게 하는 코드이다.  
'''
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
 '''  
 위의 SelectActivity에서와 같이 버튼 클릭 이벤트를 처리하는 코드이다.  
 
 3) BuyActivity  
 '''
    class User {
        public String id;
        public String phoneNumber;
        public String address;

        User() {
        }

        User(String id, String phoneNumber, String address) {
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
    }
'''  
User에 대한 정보를 가지고 있는 class이다.  
'''
        final ArrayList<String> checkedItem = (ArrayList<String>) intent.getSerializableExtra("checkedItem"); // 체크된 아이템 이름 ArrayList로 받기
        final ArrayList<String> checkedPrice = (ArrayList<String>) intent.getSerializableExtra("checkedPrice"); // 체크된 아이템 가격 ArryaList로 받기
'''  
Intent를 이용하여 체크된 아이템 정보를 받는 코드이다.  
'''
        for(String p : checkedPrice) { // 총 결제 금액 계산
            p = p.replace(",", "");
            p = p.substring(0, p.length()-1);
            totalPrice += Integer.valueOf(p);
        }

        String strTotalPrice = String.format("%,d", totalPrice); // 1000단위로 콤마 찍기
        strTotalPrice += "원";
 '''  
 금액에서 특수부호와 '원'을 제거한 뒤 덧셈을 통해 총 금액을 구하고, 다시 원 형태로 돌려놓는 코드이다.  
 '''
        t_List[8].setText(strTotalPrice);

        for(int i=0; i<checkedItem.size(); i++) {
            tList[i].setText(checkedItem.get(i));
            t_List[i].setText(checkedPrice.get(i));
        }
 '''
 TextView에 setText()하는 코드이다.  
 '''
          btn_order.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyActivity.this, SelectActivity.class);
                userId++;

                HashMap result = new HashMap<>();
                result.put("userId", userId);
                result.put("phone", phone_text);
                result.put("address", address_text);

                storeInfo(Integer.toString(userId), phone_text.getText().toString(), address_text.getText().toString());

                Toast.makeText(getApplicationContext(), "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                startActivity(intent);
                finish();
            }
            '''  
결제하기 버튼이 눌렸을 때 firebase로 정보를 전하고 toast메세지를 띄우는 코드이다.  
'''
//정보를 저장하는 함수
            private void storeInfo(String userId, String phone_text, String address_text) {
                User user = new User(userId, phone_text, address_text);

                mDatabase.child("users").child(userId).setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Toast.makeText(getApplicationContext(), "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(getApplicationContext(), "주문을 실패하였습니다..", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
'''
위의 onClick()함수에서 호출한 firebase에 정보를 저장하는 storeInfo()함수이다.  
