# DataBase_RentSystem 

## 將image丟入drawble 檔名只能有小寫字母以及數字
drawble的資料夾是沒辦法自己建立的 只能在res上new一個resource Dir
->resource type選drawble ->avalible qualifiers選density 點 " >> " -> 完成

## 新增res/xml/network_security_config.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
    </domain-config>
</network-security-config>
```
## 在AndroidManifest.xml 加上網路連線的權限
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest ...>
    <uses-permission android:name="android.permission.INTERNET" />
    <application
        ...
        android:networkSecurityConfig="@xml/network_security_config"
        ...>
        ...
    </application>
</manifest>
```
## ScrollView 如果中間有設定horizontal 要特別注意有可能會沒辦法顯示
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical"> 
    ...
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"> //這樣會讓所有版面呈現水平狀 horizontal--->vertical
    ...
    </LinearLayout>  
</ScrollView>
```
## ScrollView 跟 girdview 會發生衝突 要重新自訂一個gridview 新增的java code放參考資料
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical"> 
    ...
    <com.example.database_test1.ExpandableHeightGridView
        android:id="@+id/gridregion"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:verticalSpacing="6dp"
        android:isScrollContainer="false"
        android:numColumns="2"
        android:padding="10dp"
        android:layout_gravity="center_horizontal">
    </com.example.database_test1.ExpandableHeightGridView>
    ...
</ScrollView>
```
```js
//要注意宣告時要更改為 定義girdview的名稱
    private ExpandableHeightGridView gridView;
    gridView =(ExpandableHeightGridView) findViewById(R.id.gridregion);
    gridView.setExpanded(true); //拉長
```
#### 利用intent撥通電話需要先申請權限
```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
```
```js
...
    Uri uri = Uri.parse("tel:0999123456");
    Intent intent = new Intent(Intent.ACTION_CALL, uri);
    //  確認 CALL_PHONE 權限是否已授權
    if (ActivityCompat.checkSelfPermission(hl1.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
...
```
# Map與Api
#### 申請Api利用google console 建立專案並申請一個api
##### 需要先編輯 為Android應用程式限定
##### 套件名稱 自己的packge SHA-1 憑證指紋 Grandle在右手邊(預設) 參考資料有
#### google_maps_api.xml
```xml
<!-- 先利用 new->google->google maps activity 建立基本的檔案-->
<resources>
<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">輸入自己的api</string>
</resources>
```
#### activity_maps.xml
```xml
<!-- 如果不是用new的通常上面的name資料包會找不到 -->

...
    <fragment
        android:id="@+id/map"
        android:name="com.google.android.gms.maps.SupportMapFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="15dp" />
...
```
#### MapsActivity 利用LatLng 來存放座標 
```js
LatLng[] aryLatLng= new LatLng[]{new LatLng(v,v1),new LatLng(v,v1)}
```
# Android code 
#### 對話框
```js
 //對話框 ;Toast.LENGTH_LONG -->對話框持續大概3秒
                Toast.makeText(getApplicationContext(),"必須輸入帳號或密碼",Toast.LENGTH_LONG).show();
                //輸入回到 textaccount
                textaccount.requestFocus();
```
#### 有時候宣告沒有先打在全域 下面的private會吃不到
```js 
public class index extends AppCompatActivity {
    //先宣告要使用的 item
    private ListView lstregion;
    private Button button;
    private TextView txtShow;
    String[] Region= new String[] {"後龍鎮","苗栗市","西湖鄉","頭屋鄉"};
    Integer count;
    ...
```


##### 參考資料 
https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
###### AndroidLogin
http://hsingjungchen.blogspot.com/2017/06/android-sharedpreferencesalertdialognot.html 
###### 一次大量修改檔名 使用powershell
https://blog.gtwang.org/windows/how-to-batch-rename-files-in-windows/
###### Scrollview
https://blog.csdn.net/ACM_TH/article/details/51103889
###### 重新定義gridview 處理scrollview與girdview衝突
https://stackoverflow.com/questions/8481844/gridview-height-gets-cut
###### 找自己的SHA憑證
https://anson-site.blogspot.com/2018/02/sha1md5.html