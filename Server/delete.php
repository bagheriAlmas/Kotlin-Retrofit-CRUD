<?php

    $id = $_GET['id'];

    require_once("connect.php");
    
    $query = "DELETE FROM `notes` WHERE id = '$id'";
    
    
    if(mysqli_query($conn,$query)){
        $response['success'] = true;
        $response['message'] = "Delete Successfully";
    } else {
        $response['success'] = false;
        $response['message'] = "Delete Failure!";
    }


echo json_encode($response);

?>