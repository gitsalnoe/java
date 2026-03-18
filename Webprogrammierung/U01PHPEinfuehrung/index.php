<?php
session_start(); //wiederherstellung der Formulardaten

//Verarbeiten vom cookie
$cookieName = "LETZTERZUGRIFF";
$letzterZugriff = $_COOKIE[$cookieName] ?? null;

//aktuelles Datum für den nächsten Besuchs setzen
$date = new DateTime();
setcookie($cookieName, $date->format("d.m.Y H:i:s"), time() + (7 * 24 * 60 * 60));

//liste oder neu
$action = $_GET['action'] ?? 'list';
?>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Cookiemanager</title>
    <style>
        .layout-table > tbody > tr > td { border: 1px solid #ccc; padding: 10px; vertical-align: top; }
        .inner-table th, .inner-table td { border: 1px solid #ccc; padding: 5px; text-align: left; }
        h2 { margin-top: 0; }
        .menu-link { display: block; margin-bottom: 5px; color: blue; text-decoration: none; }
        .menu-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

<table class="layout-table">
    <tr>
        <td style="width: 30%;"><h2>Cookiemanager</h2></td>
        <td style="text-align: right;">
            <?php include 'head.php'; ?>
        </td>
    </tr>
    <tr>
        <td>
            <?php include 'menu.php'; ?>
        </td>
        <td>
            <?php
            if ($action === 'new') {
                include 'new.php';
            } else {
                include 'list.php';
            }
            ?>
        </td>
    </tr>
</table>

</body>
</html>