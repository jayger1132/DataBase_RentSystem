package com.example.database_test1.HL;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database_test1.R;
import com.example.database_test1.house;
import com.example.database_test1.index;

public class hl1 extends AppCompatActivity {
    //先宣告要使用的 item
    private Button button;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        // 取得介面元件
        txtShow=(TextView)findViewById(R.id.txtshow);
        button=(Button)findViewById(R.id.button);
        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String selectR=bundle.getString("selectR");
        Integer id=bundle.getInt("id");
        String str = "歡迎  " + selectR +"  "+id;
        txtShow.setText(str);
    }

    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };

}
