<?php
function valid_image($file_link) {
    $extention = pathinfo($file_link, PATHINFO_EXTENSION); 
    echo "pat " . $extention;
    if(in_array($extention, ["PNG","png", "JPG", "jpg"])) return true;
    return false;
}

function upload_file($file_link) {
    $upload_dir = "sharo/"; 
    $full_path = $upload_dir . basename($file_link['name']);

    if(!file_exists($full_path)) {

        if(valid_image($full_path)) {
            move_uploaded_file($file_link['tmp_name'], $full_path); 
            echo "File Uploaded";
        } else {
            echo "Not a valid Image" ;
        }

    } else {
        echo "The File Exists";
    }
   
}

$file = $_FILES["Sharo"];
echo "<pre>";
print_r($file);
upload_file($file);

?>
