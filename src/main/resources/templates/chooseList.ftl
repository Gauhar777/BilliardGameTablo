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
    <link rel="stylesheet" href="/css/vtornikMedia.css">
</head>
<body>
<#assign isAuthenticated = model["isAuthenticated"] />
<div class="wrp">
    <div class="logo">
        <img src="img/logo-vtornik.png" alt="">
    </div>
    <main class="main">
        <div class="container">
            <div class="box_title">
                <a href="/" style="width: 5%; " class="mr-4"><img src="/img/back.png" alt=""></a>
                <#if isAuthenticated==true>
                    <div style="width: 95%; text-align:left; padding-left: 20px;font-size:30px; " class="main__title">${model["resource"].getString("admin")}</div>
                <#else>
                    <div style="width:70%;text-align: left;padding-left: 20px;" class="main__title">Гость</div>
                </#if>
            </div>
        </div>
        <div class="login">
            <div class="container">
                <a href="/main2" class="btn btn-dark w-100 text-uppercase mb-4">${model["resource"].getString("Competition")}</a>
                <a href="/listGamer" class="btn btn-dark w-100 text-uppercase mb-4">${model["resource"].getString("Gamers")}</a>
                <#if isAuthenticated==true>
                    <a href="/galery" class="btn btn-dark w-100 text-uppercase">${model["resource"].getString("galery")}</a>
                </#if>
            </div>
        </div>
    </main>
</div>

<script src="/js/vtornik2.js"></script>
</body>
</html>