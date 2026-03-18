<?php
// deleteAll.php

//Über all cookie iterieren und dann löschen
if (isset($_COOKIE)) {
    foreach ($_COOKIE as $name => $value) {
        setcookie($name, "", time() - 3600);
    }
}
header("Location: index.php?action=list");
exit;