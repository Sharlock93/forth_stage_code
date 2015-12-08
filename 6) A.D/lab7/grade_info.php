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

mysql_select_db('university', $connection);

$sql = 'select * from gradeinfo';

$result = mysql_query($sql, $connection);

if(!$result)
    die("not able to create " . mysql_error());

while($row = mysql_fetch_assoc($result)) {
    prints($row);
}

?>
