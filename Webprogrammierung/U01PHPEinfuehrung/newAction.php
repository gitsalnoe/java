<?php
// newAction.php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = trim($_POST['name'] ?? '');
    $wert = trim($_POST['wert'] ?? '');
    $dauer = $_POST['dauer'] ?? 'session';

    $errors = [];

   
    if (empty($name)) {
        $errors[] = "Name des Cookies wurde nicht gesetzt";
    }
    if (empty($wert)) {
        $errors[] = "Wert des Cookies wurde nicht gesetzt";
    }

    if (count($errors) > 0) {

        $_SESSION['form_data'] = [
            'name' => $name,
            'wert' => $wert,
            'dauer' => $dauer
        ];
      
        echo "<!DOCTYPE html><html lang='de'><head><title>Fehler</title></head><body>";
        echo "<h3>Neues Cookie: Fehler</h3>";
        foreach ($errors as $error) {
            echo "<p>$error</p>";
        }
        echo "<br><a href='index.php?action=new'>Zurück</a>";
        echo "</body></html>";
        exit;
    }

    $expire = 0;
    if (is_numeric($dauer) && $dauer > 0) {
        $expire = time() + (int)$dauer;
    }

    setcookie($name, $wert, $expire);
   
    echo "<!DOCTYPE html><html lang='de'><head><title>Erfolg</title></head><body>";
    echo "<h3>Cookie erfolgreich gesetzt!</h3>";
    echo "<p>Das Cookie '$name' wurde mit dem Wert '$wert' gespeichert.</p>";
    echo "<br><a href='index.php?action=list'>Zurück zum Cookiemanager</a>";
    echo "</body></html>";
    exit;
}

header("Location: index.php");
exit;