<?php
    function check_input($input) {
    if(!empty($input))
        return stripslashes(trim($input));
    else 
        return "Empty name";
}

function validate_email($email) {
    if(empty($email)) {
        return "Email is Empty";
    }
    $pattern = "/^[a-zA-Z0-9]*@[a-zA-Z]{5}.[a-zA-Z]{3}$/";
 
    if(!preg_match($pattern, $email)) {
        return "Not a Valid Email";
    }

    return $email;
}

function validate_number($number) {
    // $pattern = "/^[0-9]{3}$/";
    if(empty($number)) {
        return "Email is Empty";
    }
    $pattern = "/^\+[0-9]{3}-[0-9]{3}-[0-9]{7}$/";
 
    if(!preg_match($pattern, $number)) {
        return "Not a Number";
    }

    return $number;
}
if($_SERVER["REQUEST_METHOD"] == "POST") {
    echo "Method used was POST<br/>";

    $name = $_POST["name"];
    $email = $_POST['email'];
    $number = $_POST['number'];

}

if($_SERVER["REQUEST_METHOD"] == "GET") {
    echo "Method used was GET<br/>";

    $name = $_GET["name"];
    $email = $_GET['email'];
    $number = $_GET['number'];
}
if(check_input($name)) {
    echo "Name: " . $name;
}

echo "<br/>";
echo "Email is: " . validate_email($email);
echo "<br/>";
echo "number is: " . validate_number($number);


?>
