<?php
    $id = $_GET['id'];
    $title = $_GET['title'];
    $note = $_GET['note'];
    $color = $_GET['color'];
    
    require_once("connect.php");
    
    
    
    $sql = "UPDATE `notes` SET title='$title',note='$note',color='$color' WHERE id=$id";

    if ($conn->query($sql) === TRUE) {
        $response['success'] = true;
        $response['message'] = "Update Successfully";
    } else {
        $response['success'] = false;
        $response['message'] = "Update Failure!";
    }
    
    $conn->close();
    

echo json_encode($response);

?>