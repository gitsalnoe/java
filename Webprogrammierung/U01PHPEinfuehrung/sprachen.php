<?php
// sprachen.php
require_once 'language.php';
$lang = getLanguage();
$t = getTranslations($lang);

$acceptLanguageString = $_SERVER['HTTP_ACCEPT_LANGUAGE'] ?? '';
$sprachenArray = [];

if (!empty($acceptLanguageString)) {
    $teile = explode(',', $acceptLanguageString);
    foreach ($teile as $teil) {
        $sprache = explode(';', $teil)[0];
        $sprachenArray[] = trim($sprache);
    }
}
?>
<table>
    <tr>
        <th><?php echo $t['index']; ?></th>
        <th><?php echo $t['wert']; ?></th>
    </tr>
    <?php if (!empty($sprachenArray)): ?>
        <?php foreach ($sprachenArray as $index => $sprache): ?>
            <tr>
                <td><?php echo $index; ?></td>
                <td><?php echo htmlspecialchars($sprache); ?></td>
            </tr>
        <?php endforeach; ?>
    <?php endif; ?>
</table>