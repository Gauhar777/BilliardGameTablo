<!doctype html>
<html lang="ru">
<head>
    <title>Новый турнир</title>
    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <!-- Twitter Card data -->
    <meta name="twitter:site" content="">
    <meta name="twitter:title" content="Вторник клуб">
    <meta name="twitter:description" content="">
    <meta name="twitter:image" content="">

    <!-- Open Graph data -->
    <meta property="og:title" content="Вторник клуб">
    <meta property="og:type" content="article">
    <meta property="og:url" content="">
    <meta property="og:image" content="">
    <meta property="og:description" content="">
    <meta property="og:site_name" content="">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/vtornik.css">
</head>
<body>
<div class="wrp">
    <main class="main" id="main">
        <form id="new-tournament" class="new-tournament" name="comp"  method="POST">
            <input id="name" name="name" class="new-tournament__input" type="text" placeholder="Название турнира"/>
            <button type="submit" class="new-tournament__add" formaction="/addCompetition2">Добавить игроков</button>
            <input class="new-tournament__btn" type="submit" formaction="/addCompetition" value="Сохранить"/>
        </form>
    </main>
</div>

<script src="js/vtornik.js"></script>
</body>
</html>
