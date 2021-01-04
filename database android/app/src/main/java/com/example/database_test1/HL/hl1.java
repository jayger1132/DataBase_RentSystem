package com.example.database_test1.HL;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.database_test1.ExpandableHeightGridView;
import com.example.database_test1.MainActivity;
import com.example.database_test1.MapsActivity;
import com.example.database_test1.R;
import com.example.database_test1.index;

public class hl1 extends AppCompatActivity {
    //先宣告要使用的 item
    private Button buttonmap,buttoncall;
    private TextView txtShow,txtprice,txttime,txtidentity,txtparkingspace,txtsex,txtmanagefee;
    private ImageView imgShow;
    private ExpandableHeightGridView gridView;
    private int[] imgId={R.drawable.hl101,R.drawable.hl102,R.drawable.hl103,R.drawable.hl104,R.drawable.hl105,R.drawable.hl106};
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
        String str = "苗栗縣後龍鎮四份仔路";
        txtShow.setText(str);
        txtprice.setText("7,500 元/月");
        txttime.setText("最短一年");
        txtidentity.setText("學生");
        txtparkingspace.setText("平面式停車位，費用另計");
        txtsex.setText("男女生皆可");
        txtmanagefee.setText("桌子,椅子,衣櫃,床,電視,冰箱,冷氣,洗衣機,網路,第四台,沙發");
        // 檢查授權
        requestPermission();
    }
    // 檢查授權
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {  // Androis 6.0 以上
            // 判斷是否已取得授權
            int hasPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // 未取得授權
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
        // 如果裝置版本是 Androis 6.0 以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權
        // 允許執行程式
    }
    // 使用者完成授權的選擇以後，會呼叫 onRequestPermissionsResult 方法
    //     第一個參數：請求授權代碼
    //     第二個參數：請求的授權名稱
    //     第三個參數：使用者選擇授權的結果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {  //按 拒絕 鈕
                Toast.makeText(this, "未取得授權！", Toast.LENGTH_SHORT).show();
                finish();  //結束應用程式
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
                    Uri uri = Uri.parse("tel:0999123456");
                    Intent intent = new Intent(Intent.ACTION_CALL, uri);
                    //  確認 CALL_PHONE 權限是否已授權
                    if (ActivityCompat.checkSelfPermission(hl1.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    }
                    break;
                }
                case R.id.buttonmap: {
                    String name="苗栗縣後龍鎮四份仔路";
                    Double v1=24.619625,v2=120.781448;
                    /*跳轉*/
                    Intent intent= new Intent();
                    intent.setClass(hl1.this, MapsActivity.class);
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
