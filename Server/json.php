<?php

    require_once("connect.php");
    
    $query = "SELECT * FROM `notes`";
    
    if(mysqli_query($conn,$query)){
        $result = mysqli_query($conn, "SELECT * FROM `notes`");
        $rows = array();
        while($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        print json_encode($rows);
        
    } else {
        // $response['success'] = false;
        // $response['message'] = "Delete Failure!";
        echo "[]";
    }


//echo json_encode($response);

?>

 

