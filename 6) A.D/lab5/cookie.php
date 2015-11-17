<?php
$visit_count = 0;
if(isset($_COOKIE['visit']))
    $visit_count =$_COOKIE['visit'];

echo "<p>" . $visit_count . "</p>";

echo "<p>" . session_save_path() . "</p>";
$visit_count++;

setcookie('visit', $visit_count, time()+60*60, "/");

?>
