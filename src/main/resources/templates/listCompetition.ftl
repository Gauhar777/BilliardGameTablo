<!doctype html>
<html lang="ru">
<head>
    <title>Главная</title>
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
    <link rel="stylesheet" href="css/vtornik.css">
</head>
<body>
<#assign isAuthenticated = model["isAuthenticated"] />
<div class="wrp">
    <div class="logo">
        <img src="img/logo-vtornik.png" alt="">
    </div>
    <main class="main">
        <h1 class="main__title">Турниры</h1>
        <div class="create-tournament">
        <#if isAuthenticated==true>
            <a href="/addCompetition" class="create-tournament__btn">Создать турнир</a>
        </#if>
            <ul class="create-tournament__list">
            <#list model["competitionList"] as competition>
                <li><a href="/Competition/${competition.id}/showGames">${competition.name}</a></li>
            </#list>
            </ul>
        </div>
    </main>
</div>

<script src="/js/vtornik.js"></script>
</body>
</html>
