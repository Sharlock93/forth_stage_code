<?php
session_start();
unset($_SESSION['user'], $_SESSION['pass']);

echo "Log out success <br/>";
echo "<a href='login.html'> Login </a>";
?>
