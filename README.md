# DataBase_RentSystem 
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
# Android code
###### 對話框
```js
 //對話框 ;Toast.LENGTH_LONG -->對話框持續大概3秒
                Toast.makeText(getApplicationContext(),"必須輸入帳號或密碼",Toast.LENGTH_LONG).show();
                //輸入回到 textaccount
                textaccount.requestFocus();
```
###### 有時候宣告沒有先打在全域 下面的private會吃不到
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
###### 將image丟入drawble 檔名只能有小寫字母以及數字
drawble的資料夾是沒辦法自己建立的 只能在res上new一個resource Dir
->resource type選drawble ->avalible qualifiers選density 點 " >> " -> 完成

###### 參考資料 
https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
###### AndroidLogin
http://hsingjungchen.blogspot.com/2017/06/android-sharedpreferencesalertdialognot.html 
###### 一次大量修改檔名 使用powershell
https://blog.gtwang.org/windows/how-to-batch-rename-files-in-windows/