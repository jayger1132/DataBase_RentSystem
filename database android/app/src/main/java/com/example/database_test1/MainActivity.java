package com.example.database_test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText textaccount=null,textpassword=null;
    //private TextView textView;
    private Button button;
    private  String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //link 元件
        //textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        textaccount = (EditText) findViewById(R.id.account);
        textpassword = (EditText) findViewById(R.id.password);
        //Listener
        button.setOnClickListener(myListener);

    }
    //設定button
    private Button.OnClickListener myListener = new Button.OnClickListener(){
        @Override
        //按下button所執行的code
        public void onClick(View v){
            if(textaccount.getText().toString().equals("")||textpassword.getText().toString().equals("")){
                //對話框 ;Toast.LENGTH_LONG -->對話框持續大概3秒
                Toast.makeText(getApplicationContext(),"必須輸入帳號或密碼",Toast.LENGTH_LONG).show();
                textaccount.requestFocus();
            }
            Thread thread = new Thread(mutiThread);
            thread.start();
        }
    };


// 建立一個執行緒執行的事件取得網路資料
private Runnable mutiThread = new Runnable(){
    public void run()
    {
        try {
            URL url = new URL("http://192.168.10.4/applogin.php");
            // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 建立 Google 比較挺的 HttpURLConnection 物件
            connection.setRequestMethod("POST");
            // 設定連線方式為 POST
            connection.setDoOutput(true); // 允許輸出
            connection.setDoInput(true); // 允許讀入
            //connection.setUseCaches(false); // 不使用快取
            connection.connect(); // 開始連線

            int responseCode =
                    connection.getResponseCode();
            // 建立取得回應的物件
            if(responseCode ==
                    HttpURLConnection.HTTP_OK){
                // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                InputStream inputStream =
                        connection.getInputStream();
                // 取得輸入串流
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                // 讀取輸入串流的資料
                String box = ""; // 宣告存放用字串
                String line = null; // 宣告讀取用的字串
                while((line = bufReader.readLine()) != null) {
                    box += line ;
                }
                inputStream.close(); // 關閉輸入串流
                result = box; // 把存放用字串放到全域變數
            }
            // 讀取輸入串流並存到字串的部分
            // 取得資料後想用不同的格式
            // 例如 Json 等等，都是在這一段做處理

        } catch(Exception e) {
            result = e.toString(); // 如果出事，回傳錯誤訊息
        }

        // 當這個執行緒完全跑完後執行
        runOnUiThread(new Runnable() {
            public void run() {
                /*跳轉*/
                Intent intent= new Intent();
                intent.setClass(MainActivity.this,index.class);

                String Fresult="",intentact="",intentpwd="";
                /*以";" 作為分界*/
                String[] act ,pwd = result.split(";");
                boolean flag = false;
                /*檢查每一行的切割的字串*/
                for(String test1:pwd){
                    //切割出 act , pwd
                    String act2 = test1.substring(0,test1.lastIndexOf("&"));
                    String pwd2 = test1.substring(test1.lastIndexOf("&")+1);
                    if(textaccount.getText().toString().equals(act2)){
                        if(textpassword.getText().toString().equals(pwd2)){ //login correct
                            flag=true;
                            Toast.makeText(getApplicationContext(),"唉唷不錯唷",Toast.LENGTH_LONG).show();
                            intentact=act2;
                            intentpwd=pwd2;
                        }
                        else{
                            textaccount.requestFocus();
                        }
                    }
                    else{
                        textaccount.requestFocus();

                    }
                    //測試字串
                    //Fresult+=act2+pwd2+"\n";
                }
                //textView.setText(Fresult); // 更改顯示文字

                if(flag==true) {
                    /*transfer data*/
                    Bundle bundle = new Bundle();
                    bundle.putString("act", intentact);
                    bundle.putString("pwd", intentpwd);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"帳號或密碼錯誤",Toast.LENGTH_SHORT).show();
                }
                // 執行附帶資料的 Intent

            }
        });
    }
};
}
