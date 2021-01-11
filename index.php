<?php
//import 
require('config.php');

// Authenticate user
$con = new mysqli($db_host, $db_user, $db_pass , $db_name);

$sql = "SELECT Account , Password,ID From user";

//送出SQL語法到資料庫系統
$result = $con->query($sql);

while($row = $result->fetch_array()){ 
    echo $row["Account"]."&".$row["Password"].";";
  }

//釋放與Mysql的連線
$result->free();
//關閉連線
$con->close();
?>