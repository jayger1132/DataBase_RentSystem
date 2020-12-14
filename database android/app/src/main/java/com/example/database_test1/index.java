package com.example.database_test1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class index extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        // 取得介面元件
        TextView txtShow=(TextView)findViewById(R.id.txtshow);
        Button button=(Button)findViewById(R.id.button);

        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String act=bundle.getString("act"), pwd=bundle.getString("pwd");
        String str = "歡迎  " + act +"  ";
        txtShow.setText(str);
    }
    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };
}
