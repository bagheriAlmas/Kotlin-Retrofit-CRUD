<?php



    $title = $_GET['title'];
    $note = $_GET['note'];
    $color = $_GET['color'];
    
    require_once("connect.php");
    
    $query = "INSERT INTO `notes` (title,note,color) VALUES ('$title','$note','$color')";
    
    
    
    if(mysqli_query($conn,$query)){
        $response['success'] = true;
        $response['message'] = "Insert Successfully";
    } else {
        $response['success'] = false;
        $response['message'] = "Insert Failure!";
    }


echo json_encode($response);

?>