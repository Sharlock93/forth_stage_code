<?php
$file_to_write = fopen("Sharo.txt", "w+");

fwrite($file_to_write, "hello world");
fclose($file_to_write);

$file_to_write = fopen("Sharo.txt", "r+");
echo fread($file_to_write, filesize("Sharo.txt"));
fclose($file_to_write);
?>
