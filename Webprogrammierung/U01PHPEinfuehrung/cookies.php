<?php
// cookies.php

$cookieName = "LETZTERZUGRIFF";

//Prüfen ob cookie schon existiert
$cookieVorhanden = isset($_COOKIE[$cookieName]);
if ($cookieVorhanden) {
    $letzterZugriff = $_COOKIE[$cookieName];
}

//Datum und Zeit 
$date = new DateTime();
$aktuellesDatum = $date->format("d.m.Y H:i:s");

$lebensdauer = time() + (7 * 24 * 60 * 60);

//Setzen vom cookie
setcookie($cookieName, $aktuellesDatum, $lebensdauer);

?>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Cookie Test</title>
</head>
<body>
    <?php
    //ausgabe
    if ($cookieVorhanden) {
        echo "<p>Ihr letzter Zugriff erfolgte " . htmlspecialchars($letzterZugriff) . "</p>";
    } else {
        echo "<p>Zum ersten Mal auf der Seite</p>";
    }
    ?>
</body>
</html>