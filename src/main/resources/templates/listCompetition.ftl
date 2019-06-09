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
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/vtornik2.css">
</head>
<body>
<#assign isAuthenticated = model["isAuthenticated"] />
<div class="wrp">
    <main class="main">
        <div class="container">
          <#if isAuthenticated==true>
                <a href="/logout" class="alert alert-light w-100 d-block text-center">Выйти из режима администратора</a>
          </#if>
            <div class="box_title">
                <a style="width: 5%; " href="/chooseList" class="mr-4"><img src="../img/back.png" alt=""></a>
                <div style="width: 90%; text-align:left; padding-left: 20px;font-size:30px; " class="main__title">Турниры</div>
            </div>
            <div class="create-tournament">
                <#if isAuthenticated==true>
                  <a href="/addCompetition" class="btn btn-dark w-100 mb-4">Создать турнир</a>
                </#if>
                <ul class="create-tournament__list">
                  <#list model["competitionList"] as competition>
                      <li><a href="/Competition/${competition.id}/showGames">${competition.name}</a></li>
                  </#list>
                </ul>
            </div>
        </div>
    </main>
</div>

<script src="/js/vtornik.2js"></script>
</body>
</html>