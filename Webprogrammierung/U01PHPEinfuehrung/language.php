<?php
// language.php

function getLanguage() {
    if (isset($_SERVER['HTTP_ACCEPT_LANGUAGE'])) {
        $langString = explode(',', $_SERVER['HTTP_ACCEPT_LANGUAGE'])[0];
        $langCode = substr(trim($langString), 0, 2);
        $langCode = strtolower($langCode);

        if (in_array($langCode, ['de', 'it', 'en'])) {
            return $langCode;
        }
    }
    return 'de';
}

function getTranslations($langCode) {
    $dictionary = [
        'de' => [
            'wert' => 'Wert',
            'parameter' => 'Parameter',
            'index' => 'Index',
            'sprachen' => 'Sprachen'
        ],
        'it' => [
            'wert' => 'Valore',
            'parameter' => 'Parametro',
            'index' => 'Indice',
            'sprachen' => 'Lingue'
        ],
        'en' => [
            'wert' => 'Value',
            'parameter' => 'Parameter',
            'index' => 'Index',
            'sprachen' => 'Languages'
        ]
    ];
    return $dictionary[$langCode];
}
?>