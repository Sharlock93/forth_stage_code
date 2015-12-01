<?php
    session_start();
    echo "You have Been Logged In <br/>";

    echo "user: " . $_SESSION['user'] . "<br/>";
    echo "pass: " . $_SESSION['pass'] . "<br/>";

    echo "<a href='logout.php' value='test' > Log Out </a>";
?>
