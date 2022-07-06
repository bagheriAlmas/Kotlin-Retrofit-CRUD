<?php
    $id = $_GET['id'];

    require_once("connect.php");
    
    $query = "SELECT * FROM `notes` WHERE id = '$id'";
    
    if(mysqli_query($conn,$query)){
        $result = mysqli_query($conn, $query);
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

 
