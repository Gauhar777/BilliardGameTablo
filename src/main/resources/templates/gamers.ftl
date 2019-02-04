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
    <link rel="stylesheet" href="/css/vtornik.css">
    <link rel="stylesheet" href="/css/vtornikMedia.css">
</head>
<body>
<div class="wrp">
    <main class="main">
        <div class="main__header">
            <a href="/Competition/${model.competition.id}/showGames" class="arrow">Таблица со счетом</a>
        </div>
        <h1 class="main__title">Общий список игроков</h1>
        <div class="tournament">






            <a href="#" class="tournament__btn" data-toggle="modal" data-target="#myModal">Новый игрок</a>

            <#if model["gamers"]?has_content>
                <#list model["gamers"] as gamer>
                        <ul class="tournament__list">
                        <#if gamer.choosed==false>
                            <li><a href="/${model.competition.id}/${gamer.idGamer}/choosePartner" class="js-tournament">${gamer.nick}</a></li>
                        <#else>
                            <li><a href="/${model.competition.id}/${gamer.idGamer}/excludePartner" class="ok js-tournament">${gamer.nick}</a></li>
                        </#if>
                    </ul>
                </#list>
            </#if>
            <a href="/Competition/${model.competition.id}/showGames" class="tournament__btn ok">ОК</a>








            <!-- Добавить нового игрока -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                            <h4 class="modal-title" id="myModalLabel">Введите имя игрока</h4>
                        </div>
                        <div class="modal-body">
                            <form class="tournament__form" method="POST"  action="/${model.competition.id}/addGamers">
                                <input class="tournament__input" name="nick" id="nick"  type="text"/>
                                <input class="tournament__submit"  type="submit" value="Ok"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


<script src="/js/vtornik.js"></script>
</body>
</html>
