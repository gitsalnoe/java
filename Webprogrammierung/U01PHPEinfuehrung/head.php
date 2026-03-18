<?php
// head.php
$cookieCount = count($_COOKIE);
echo "<strong>$cookieCount Cookies gesetzt</strong><br><br>";

if ($letzterZugriff) {
    echo "Ihr letzter Zugriff erfolgte \"$letzterZugriff\"";
} else {
    echo "Zum ersten Mal auf der Seite";
}
?>