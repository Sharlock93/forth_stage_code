<?php
session_start();

echo "SessioID " . session_id();

$visit_count = 0;
if(isset($_SESSION['visit']))
    $visit_count =$_SESSION['visit'];

echo "<p>" . $visit_count . "</p>";
?>

