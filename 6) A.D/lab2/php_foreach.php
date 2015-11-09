<?php
$marks = array("mo" => array("comp" => 35, "cte" => 25, "lel" => 15),
               "ko" => array("comp" => 25, "cte" => 85, "lel" => 95),
               "so" => array("comp" => 36, "cte" => 15, "lel" => 25));

foreach($marks as $names => $mark) {
    foreach($mark as $subject => $sub_mark) {
        echo "$names has $sub_mark in $subject";
        echo "<br/>";
    }
}

function addarrays($array1, $array2) {
    $add = 0;
    
    for($i = 0; $i < count($array1); $i += 1) {
        $add += $array1[$i] + $array2[$i]; 
    }

    return $add;
}

$arr = array(1, 2, 3, 4);
$arr2 = array(2, 23, 3, 4);

echo "Reuslt is :" . addarrays($arr, $arr2);

?>
