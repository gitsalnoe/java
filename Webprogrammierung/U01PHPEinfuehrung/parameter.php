<?php
// parameter.php
require_once 'language.php';
$lang = getLanguage();
$t = getTranslations($lang);
?>
<table>
    <tr>
        <th><?php echo $t['parameter']; ?></th>
        <th><?php echo $t['wert']; ?></th>
    </tr>
    <?php if (!empty($_REQUEST)): ?>
        <?php foreach ($_REQUEST as $key => $value): ?>
            <tr>
                <td><?php echo htmlspecialchars($key); ?></td>
                <td><?php echo htmlspecialchars($value); ?></td>
            </tr>
        <?php endforeach; ?>
    <?php endif; ?>
</table>