<?php

function prints($var) {
    echo "<pre>";
    print_r($var);
    echo "</pre>";
}

$db_user = 'root';
$db_pass = '';
$db_url = 'localhost';

$connection = mysql_connect($db_url, $db_user, $db_pass);
if(!$connection)
    die("oh he ded " . mysql_error());

$sql_create_db = 'drop database Tutorial';

$result_create_tut = mysql_query($sql_create_db, $connection);

prints($result_create_tut);

?>
