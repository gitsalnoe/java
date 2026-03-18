<?php
// serverAnalyse.php
require_once 'language.php';
$lang = getLanguage();
$t = getTranslations($lang);
?>
<!DOCTYPE html>
<html lang="<?php echo $lang; ?>">
<head>
    <meta charset="UTF-8">
    <title>Server Analyse</title>
    <style>
        table { border-collapse: collapse; width: 100%; max-width: 800px; }
        th, td { border: 1px solid black; padding: 4px 8px; text-align: left; vertical-align: top; }
        td table { margin: -4px -8px; width: calc(100% + 16px); border-top: none; border-bottom: none; border-left: none; border-right: none;}
        td table th, td table td { border-left: none; border-right: none; }
        td table tr:first-child th { border-top: none; }
        td table tr:last-child td { border-bottom: none; }
    </style>
</head>
<body>

    <table>
        <tr>
            <th>$_SERVER</th>
            <th><?php echo $t['wert']; ?></th>
        </tr>
        <tr>
            <td>SERVER_NAME</td>
            <td><?php echo $_SERVER['SERVER_NAME'] ?? ''; ?></td>
        </tr>
        <tr>
            <td>SERVER_PORT</td>
            <td><?php echo $_SERVER['SERVER_PORT'] ?? ''; ?></td>
        </tr>
        <tr>
            <td>HTTP_ACCEPT_LANGUAGE</td>
            <td><?php echo $_SERVER['HTTP_ACCEPT_LANGUAGE'] ?? ''; ?></td>
        </tr>
        <tr>
            <td>PHP_SELF</td>
            <td><?php echo $_SERVER['PHP_SELF'] ?? ''; ?></td>
        </tr>
        <tr>
            <td>QUERY_STRING</td>
            <td><?php echo $_SERVER['QUERY_STRING'] ?? ''; ?></td>
        </tr>
        <tr>
            <td>REQUEST_URI</td>
            <td><?php echo $_SERVER['REQUEST_URI'] ?? ''; ?></td>
        </tr>
        
        <tr>
            <td><strong>$_REQUEST</strong></td>
            <td style="padding: 0;">
                <?php include 'parameter.php'; ?>
            </td>
        </tr>

        <tr>
            <td><strong><?php echo $t['sprachen']; ?></strong></td>
            <td style="padding: 0;">
                <?php include 'sprachen.php'; ?>
            </td>
        </tr>
    </table>

</body>
</html>