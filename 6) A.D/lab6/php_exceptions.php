<?php
function validate_user($user) {
    if(strlen($user) < 6) throw new Exception("Username must be bigger than 6 characters");
    if(strpos($user, "usr") === false) throw new Exception("Username must contains usr");
    return true;
}

try{
    validate_user("usr");
} catch(Exception $ex) {
    echo "<pre/>";
    print_r($ex);
}
?>
