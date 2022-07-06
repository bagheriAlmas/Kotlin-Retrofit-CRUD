<?php

define("DB_HOST", "localhost");
define("DB_USER", "<USERNAME>");
define("DB_PASSWORD", "<PASSWORD>");
define("DB_DATABASE", "<DATABASE NAME>");

$conn = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
	
// 	if($conn){
// 		echo "success";
// 	}else
// 	{
// 	 echo "No connection";   
// 	}
?>
