<?php
// delete.php
if (isset($_GET['name'])) {
    $name = $_GET['name'];
    //ablaufdatum vom cookie in vergangenheit setzen um es zu löschen
    setcookie($name, "", time() - 3600);
}

header("Location: index.php?action=list");
exit;