package com.example.database_test1.ML;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;

import com.example.database_test1.ExpandableHeightGridView;
import com.example.database_test1.HL.hl1;
import com.example.database_test1.MapsActivity;
import com.example.database_test1.R;

public class ml1 extends AppCompatActivity {
    //先宣告要使用的 item
    private Button buttonmap,buttoncall;
    private TextView txtShow,txtprice,txttime,txtidentity,txtparkingspace,txtsex,txtmanagefee;
    private ImageView imgShow;
    private ExpandableHeightGridView gridView;
    private int[] imgId={R.drawable.ml101,R.drawable.ml102,R.drawable.ml103,R.drawable.ml104,R.drawable.ml105,R.drawable.ml106};
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
        imgShow=(ImageView) findViewById(R.id.imgShow);
        gridView =(ExpandableHeightGridView) findViewById(R.id.gridregion);
        buttonmap=(Button)findViewById(R.id.buttonmap);
        buttoncall=(Button)findViewById(R.id.buttoncall);
        // 設定 button 的 Listener
        buttonmap.setOnClickListener(buttonListener);
        buttoncall.setOnClickListener(buttonListener);
        // 建立自訂的 Adapter
        MyAdapter adapter=new MyAdapter(this);
        // 設定 GridView 的資料來源
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);

        // 建立 GridView 的 ItemClick 事件
        gridView.setOnItemClickListener(gridviewListener);
        // 取得 bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String selectR=bundle.getString("selectR");
        Integer id=bundle.getInt("id");
        //String str = selectR +"  "+id;
        String str = "苗栗縣苗栗市文發路164巷1號";
        txtShow.setText(str);
        txtprice.setText("28000元/月");
        txttime.setText("一年");
        txtidentity.setText("學生、上班族、家庭");
        txtparkingspace.setText("無");
        txtsex.setText("無");
        txtmanagefee.setText("桌子,椅子,衣櫃,床,沙發,熱水器,天然瓦斯,電視,冰箱,冷氣,洗衣機");

    }
    class MyAdapter extends BaseAdapter {
        private Context mContext;
        public MyAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return imgId.length; // 圖片共有多少張
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
            iv.setImageResource(imgId[position]);
            iv.setScaleType(ImageView.ScaleType.CENTER);
            iv.setLayoutParams(new GridView.LayoutParams(600,540));
            return iv;
        }
    }
    //Gridview 的Listener
    private GridView.OnItemClickListener gridviewListener=
            new GridView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    imgShow.setImageResource(imgId[position]);

                }
            };

    private  Button.OnClickListener buttonListener=new Button.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.buttoncall: {
                    Uri uri = Uri.parse("tel:0919638541");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    //  確認 CALL_PHONE 權限是否已授權
                    if (ActivityCompat.checkSelfPermission(ml1.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    }
                    break;
                }
                case R.id.buttonmap: {
                    String name="苗栗縣苗栗市文發路164巷1號 ";
                    Double v1=24.572299,v2=120.813623;
                    /*跳轉*/
                    Intent intent= new Intent();
                    intent.setClass(ml1.this, MapsActivity.class);
                    /*transfer data*/
                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    bundle.putDouble("v",v1);
                    bundle.putDouble("v2",v2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                }

            }
        }
    };

}
