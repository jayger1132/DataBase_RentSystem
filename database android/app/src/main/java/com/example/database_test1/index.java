package com.example.database_test1;

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

public class index extends AppCompatActivity {
    //先宣告要使用的 item
    private ListView lstregion;
    private Button button;
    private TextView txtShow;
    String[] Region= new String[] {"後龍鎮","苗栗市","西湖鄉","頭屋鄉"};
    Integer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        // 取得介面元件
        txtShow=(TextView)findViewById(R.id.txtshow);
        button=(Button)findViewById(R.id.button);
        lstregion=(ListView)findViewById(R.id.lstregion);

        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 以多選範本建立 ArrayAdapter
        ArrayAdapter<String> adapterRegion = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, Region);
        // 設定可多選
        lstregion.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // 設定 ListView 的資料來源
        lstregion.setAdapter(adapterRegion);
        count = adapterRegion.getCount();  // 取得選取項目總數

        // 設定 lstPrefer 元件 ItemClick 事件的 listener 為 lstPreferListener
        lstregion.setOnItemClickListener(lstregionListener);

        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String act=bundle.getString("act"), pwd=bundle.getString("pwd");
        String str = "歡迎  " + act +"  ";
        txtShow.setText(str);
    }

    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            String str="";
            for(int i =0;i < count;i++){
                if(lstregion.isItemChecked(i))
                    str += Region[i] + ";";
            }
            //txtShow.setText(str);
            /*跳轉*/
            Intent intent= new Intent();
            intent.setClass(index.this,house.class);
            /*transfer data*/
            Bundle bundle = new Bundle();
            bundle.putString("selectR", str);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    //ListView
    private ListView.OnItemClickListener lstregionListener=
      new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {

        }
    };
}
