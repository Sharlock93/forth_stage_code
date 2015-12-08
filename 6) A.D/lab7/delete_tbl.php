<?php

function prints($var) {
    echo "<pre>";
    print_r($var);
    echo "</pre>";
}

$db_user = 'root';
$db_pass = '';
$db_url = 'localhost';

$conn = mysql_connect($db_url, $db_user, $db_pass);
if(!$conn)
    die("oh he ded " . mysql_error());

$connection = mysql_select_db("tutorial", $conn);

$sql_create_tbl = 'drop table Tutorial';

$result_create_tut = mysql_query($sql_create_tbl, $conn);

if(!$result_create_tut)
    die("not able to create " . mysql_error());
?>
