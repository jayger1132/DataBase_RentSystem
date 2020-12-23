package com.example.database_test1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class house extends AppCompatActivity {
    /*String[] Region= new String[] {"後龍鎮","苗栗市","西湖鄉","頭屋鄉"};
    Integer count;*/
    private int[] HLimgId= {R.drawable.hl104,R.drawable.hl201},MLimgId={R.drawable.ml101,R.drawable.ml201};
    private ImageView imgShow;
    private String str2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house);

        // 取得介面元件
        TextView txtShow=(TextView)findViewById(R.id.txtshow);
        Button button=(Button)findViewById(R.id.button);
        GridView gridView =(GridView) findViewById(R.id.gridregion);
        imgShow=(ImageView) findViewById(R.id.imgShow);

        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 建立自訂的 Adapter
        MyAdapter adapter=new MyAdapter(this);

        // 設定 GridView 的資料來源
        gridView.setAdapter(adapter);
        // 建立 GridView 的 ItemClick 事件
        gridView.setOnItemClickListener(gridviewListener);

        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String selectR=bundle.getString("selectR");
        String str = selectR ;
        //切割 index 輸入的字串
        String[] str1= str.split(";");
        for(String test:str1){
            switch (test){
                case "後龍鎮":
                    str2=str2+"HLimgId"+";";
                    break;
                case "苗栗市":
                    str2=str2+"MLimgId"+";";
                    break;
                case "西湖鄉":
                    break;
                case "頭屋鄉":
                    break;
            }
        }
        txtShow.setText(str);
    }

    // 自訂的 MyAdapter 類別，繼承 BaseAdapter 類別
    class MyAdapter extends BaseAdapter {
        private Context mContext;
        public MyAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return HLimgId.length; // 圖片共有多少張
        }
        @Override
        public Object getItem(int position){
            return position;
        }

        @Override
        public long getItemId(int position){
            return position; // 目前圖片索引
        }
        // 定義 GridView 顯示的圖片
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView iv = new ImageView(mContext);
            String[] str1= str2.split(";");
            for(String test:str1){
                switch (test){
                    case "HLimgId":
                        iv.setImageResource(HLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                    case "MLimgId":
                        iv.setImageResource(MLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                    case "西湖鄉":
                        break;
                    case "頭屋鄉":
                        break;
                }
            }
            return iv;
        }
    }

    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };
    //GridView
    private GridView.OnItemClickListener gridviewListener=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String[] str1= str2.split(";");
                    for(String test:str1){
                        switch (test){
                            case "HLimgId":
                                imgShow.setImageResource(HLimgId[position]);
                            case "MLimgId":
                                imgShow.setImageResource(MLimgId[position]);
                            case "西湖鄉":
                                break;
                            case "頭屋鄉":
                                break;
                        }
                    }

                }
            };
}
