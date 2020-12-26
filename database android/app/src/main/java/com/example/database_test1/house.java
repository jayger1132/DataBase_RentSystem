package com.example.database_test1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database_test1.HL.hl1;
import com.example.database_test1.HL.hl2;
import com.example.database_test1.ML.ml1;
import com.example.database_test1.ML.ml2;
import com.example.database_test1.TL.tl1;
import com.example.database_test1.TL.tl2;
import com.example.database_test1.YL.yl1;
import com.example.database_test1.YL.yl2;

public class house extends AppCompatActivity {
    private int[] HLimgId= {R.drawable.hl104,R.drawable.hl201},MLimgId={R.drawable.ml101,R.drawable.ml201}
    ,TLimgId={R.drawable.tl101,R.drawable.tl201},YLimgId={R.drawable.yl101,R.drawable.yl201};
    private ImageView imgShow;
    private String str2="",trnsstr="",trnsintent="";
    private Integer trnsint=0;
    private Intent intent=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house);

        // 取得介面元件
        TextView txtShow=(TextView)findViewById(R.id.txtshow);
        Button button=(Button)findViewById(R.id.button);
        GridView gridViewHL =(GridView) findViewById(R.id.gridregionHL);
        GridView gridViewML =(GridView) findViewById(R.id.gridregionML);
        GridView gridViewTL =(GridView) findViewById(R.id.gridregionTL);
        GridView gridViewYL =(GridView) findViewById(R.id.gridregionYL);
        imgShow=(ImageView) findViewById(R.id.imgShow);

        // 設定 button 的 Listener
        button.setOnClickListener(buttonListener);

        // 建立自訂的 Adapter
        MyAdapterHL adapterHL=new MyAdapterHL(this);
        MyAdapterML adapterML=new MyAdapterML(this);
        MyAdapterTL adapterTL=new MyAdapterTL(this);
        MyAdapterYL adapterYL=new MyAdapterYL(this);

        // 設定 GridView 的資料來源
        gridViewHL.setAdapter(adapterHL);
        gridViewML.setAdapter(adapterML);
        gridViewTL.setAdapter(adapterTL);
        gridViewYL.setAdapter(adapterYL);
        // 建立 GridView 的 ItemClick 事件
        gridViewHL.setOnItemClickListener(gridviewListenerHL);
        gridViewML.setOnItemClickListener(gridviewListenerML);
        gridViewTL.setOnItemClickListener(gridviewListenerTL);
        gridViewYL.setOnItemClickListener(gridviewListenerYL);
        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String selectR=bundle.getString("selectR");
        String str = selectR ;
        //切割 index 輸入的字串
        String[] str1= str.split(" ");
        for(String test:str1){
            switch (test){
                case "後龍鎮":
                    str2=str2+"HLimgId"+" ";
                    break;
                case "苗栗市":
                    str2=str2+"MLimgId"+" ";
                    break;
                case "銅鑼鄉":
                    str2=str2+"TLimgId"+" ";
                    break;
                case "苑裡鎮":
                    str2=str2+"YLimgId"+" ";
                    break;
            }
        }
        txtShow.setText(str);
    }

    // 自訂的 MyAdapter 類別，繼承 BaseAdapter 類別
    class MyAdapterHL extends BaseAdapter {
        private Context mContext;
        public MyAdapterHL(Context c){
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
            String[] str1= str2.split(" ");
            for(String test:str1){
                switch (test){
                    case "HLimgId":
                        iv.setImageResource(HLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                    case "MLimgId":
                        break;
                    case "TLimgId":
                        break;
                    case "YLimgId":
                        break;
                }
            }
            return iv;
        }
    }
    class MyAdapterML extends BaseAdapter {
        private Context mContext;
        public MyAdapterML(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return MLimgId.length; // 圖片共有多少張
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
            String[] str1= str2.split(" ");
            for(String test:str1){
                switch (test){
                    case "HLimgId":
                        break;
                    case "MLimgId":
                        iv.setImageResource(MLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                    case "TLimgId":
                        break;
                    case "YLimgId":
                        break;
                }
            }
            return iv;
        }
    }
    class MyAdapterTL extends BaseAdapter {
        private Context mContext;
        public MyAdapterTL(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return TLimgId.length; // 圖片共有多少張
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
            String[] str1= str2.split(" ");
            for(String test:str1){
                switch (test){
                    case "HLimgId":
                        break;
                    case "MLimgId":
                        break;
                    case "TLimgId":
                        iv.setImageResource(TLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                    case "YLimgId":
                        break;
                }
            }
            return iv;
        }
    }
    class MyAdapterYL extends BaseAdapter {
        private Context mContext;
        public MyAdapterYL(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return YLimgId.length; // 圖片共有多少張
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
            String[] str1= str2.split(" ");
            for(String test:str1){
                switch (test){
                    case "HLimgId":
                        break;
                    case "MLimgId":
                        break;
                    case "TLimgId":
                        break;
                    case "YLimgId":
                        iv.setImageResource(YLimgId[position]);
                        iv.setScaleType(ImageView.ScaleType.CENTER);
                        iv.setLayoutParams(new GridView.LayoutParams(600,540));
                        return iv;
                }
            }
            return iv;
        }
    }

    //Gridview 的Listener
    private GridView.OnItemClickListener gridviewListenerHL=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    imgShow.setImageResource(HLimgId[position]);
                    trnsint=position;
                    trnsstr="後龍鎮";
                    if(trnsint==0)
                        intent.setClass(house.this, hl1.class);
                    else
                        intent.setClass(house.this, hl2.class);
                }
            };
    private GridView.OnItemClickListener gridviewListenerML=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    imgShow.setImageResource(MLimgId[position]);
                    trnsint=position;
                    trnsstr="苗栗市";
                    if(trnsint==0)
                        intent.setClass(house.this, ml1.class);
                    else
                        intent.setClass(house.this, ml2.class);
                }
            };
    private GridView.OnItemClickListener gridviewListenerTL=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    imgShow.setImageResource(TLimgId[position]);
                    trnsint=position;
                    trnsstr="銅鑼鄉";
                    if(trnsint==0)
                        intent.setClass(house.this, tl1.class);
                    else
                        intent.setClass(house.this, tl2.class);
                }
            };
    private GridView.OnItemClickListener gridviewListenerYL=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    imgShow.setImageResource(YLimgId[position]);
                    trnsint=position;
                    trnsstr="苑裡鎮";
                    if(trnsint==0)
                        intent.setClass(house.this, yl1.class);
                    else
                        intent.setClass(house.this, yl2.class);
                }
            };
    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){

            /*transfer data*/
            Bundle bundle=new Bundle();
            bundle.putString("selectR",trnsstr);
            bundle.putInt("id",trnsint);
            intent.putExtras(bundle);
            // 執行附帶資料的 Intent
            startActivity(intent);
            //finish();
        }
    };
}
