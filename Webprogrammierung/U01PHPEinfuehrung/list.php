<?php
// list.php
?>
<table class="inner-table">
    <?php if (count($_COOKIE) > 0): ?>
        <?php foreach ($_COOKIE as $name => $value): ?>
            <tr>
                <td style="width: 30%;"><?php echo htmlspecialchars($name); ?></td>
                <td><?php echo htmlspecialchars($value); ?></td>
                <td style="width: 15%;">
                    <a href="delete.php?name=<?php echo urlencode($name); ?>" 
                       onclick="return confirm('Soll das Cookie gelöscht werden?');">Löschen</a>
                </td>
            </tr>
        <?php endforeach; ?>
    <?php else: ?>
        <tr><td colspan="3">Keine Cookies vorhanden.</td></tr>
    <?php endif; ?>
</table>