package com.example.database_test1.ML;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database_test1.R;

public class ml1 extends AppCompatActivity {
    //先宣告要使用的 item
    private Button button;
    private TextView txtShow,txtprice,txttime,txtidentity,txtparkingspace,txtsex,txtmanagefee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // 取得介面元件
        txtShow=(TextView)findViewById(R.id.txtshow);
        txtprice=(TextView)findViewById(R.id.txtprice);
        txttime=(TextView)findViewById(R.id.txttime);
        txtidentity=(TextView)findViewById(R.id.txtidentity);
        txtparkingspace=(TextView)findViewById(R.id.txtparkingspace);
        txtsex=(TextView)findViewById(R.id.txtsex);
        txtmanagefee=(TextView)findViewById(R.id.txtmanagefee);
        button=(Button)findViewById(R.id.button);
        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String selectR=bundle.getString("selectR");
        Integer id=bundle.getInt("id");
        String str = selectR +"  "+id;
        txtShow.setText(str);
        txtprice.setText("28000元/月");
        txttime.setText("一年");
        txtidentity.setText("學生、上班族、家庭");
        txtparkingspace.setText("無");
        txtsex.setText("無");
        txtmanagefee.setText("桌子,椅子,衣櫃,床,沙發,熱水器,天然瓦斯,電視,冰箱,冷氣,洗衣機");

    }

    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };

}
