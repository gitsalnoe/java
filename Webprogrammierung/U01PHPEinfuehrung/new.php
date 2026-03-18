<?php
// new.php

$oldName = $_SESSION['form_data']['name'] ?? '';
$oldWert = $_SESSION['form_data']['wert'] ?? '';
$oldDauer = $_SESSION['form_data']['dauer'] ?? 'session';

unset($_SESSION['form_data']);
?>

<h3>Neues cookie anlegen</h3>
<form action="newAction.php" method="POST">
    <table class="inner-table" style="width: auto;">
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="<?php echo htmlspecialchars($oldName); ?>"></td>
        </tr>
        <tr>
            <td>Wert:</td>
            <td><input type="text" name="wert" value="<?php echo htmlspecialchars($oldWert); ?>"></td>
        </tr>
        <tr>
            <td>Lebensdauer:</td>
            <td>
                <select name="dauer">
                    <option value="session" <?php if($oldDauer=='session') echo 'selected'; ?>>Stirbt beim Schließen des Browsers</option>
                    <option value="30" <?php if($oldDauer=='30') echo 'selected'; ?>>30 Sekunden</option>
                    <option value="60" <?php if($oldDauer=='60') echo 'selected'; ?>>1 Minute</option>
                    <option value="300" <?php if($oldDauer=='300') echo 'selected'; ?>>5 Minuten</option>
                    <option value="3600" <?php if($oldDauer=='3600') echo 'selected'; ?>>1 Stunde</option>
                    <option value="604800" <?php if($oldDauer=='604800') echo 'selected'; ?>>7 Tage</option>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Erstellen">
                <input type="reset" value="Zurücksetzen">
            </td>
        </tr>
    </table>
</form>