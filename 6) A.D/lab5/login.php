<?php 
session_start();

function is_loged_in() { 
    if(isset($_SESSION['user'], $_SESSION['pass']))
        return true;

    return false;
}

function log_in($user, $pass) {
    $_SESSION['user'] = $user;
    $_SESSION['pass'] = $pass;
    echo "Login Success <br/>";
    echo "<a href='loggedin.php'> Goto login page </a>";
}

function check_valid_usr_pass($post_info) {
    $valid = false;
    if(!isset($post_info['user'])) {
        throw new Exception("Invalid Username");
        return false;
    }

    if(!isset($post_info['pass'])) {
        throw new Exception("Invalid Password");
        return false;
    }

    return true;
}

if(!is_loged_in()) { 
    try {
        if(check_valid_usr_pass($_POST))
            log_in($_POST['user'], $_POST['pass']);
    } catch (Exception $e) {
        echo $e->getMessage(); 
    }

} else {
    echo "Already Logged In <br/>";
    echo "<a href='loggedin.php' value='test' > Go to logged in  page </a>";
}

?>


