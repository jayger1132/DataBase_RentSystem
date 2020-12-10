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
###### *參考資料 
https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
######      *androidlogin
http://hsingjungchen.blogspot.com/2017/06/android-sharedpreferencesalertdialognot.html 