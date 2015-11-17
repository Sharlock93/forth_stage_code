<?php
session_start();

if(isset($_SESSION['visit']))
    $_SESSION['visit']++;
else {
    $_SESSION['visit'] = 0;
}

?>
