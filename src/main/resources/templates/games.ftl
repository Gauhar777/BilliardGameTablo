<!doctype html>
<html lang="ru">
<head>
    <title>Рейтинг игроков</title>
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
<#assign isAuthenticated = model["isAuthenticated"] />
<div class="wrp">
    <main class="main">
        <div class="main__header">
            <a href="/listGamer">Общий рейтинг игроков</a>
        </div>
        <div class="rating-all">
            <div class="rating-all__header">
                <#if model.container?exists>
                    <a href="#" class="rating-all__icon" data-toggle="modal" data-target="#photo"><img src="/img/camera.png" alt=""></a>
                <#else>
                    <a href="/${model.competition.id}/photo" class="rating-all__icon" ><img src="/img/camera.png" alt=""></a>
                </#if>

                <h1>${model.competition.name}</h1>

                <div class="rating-all__control">
                    <a href="/edit/${model.competition.id}" class="js-edit"><img src="/img/edit.png" alt=""></a>
                    <a href="/delete/${model.competition.id}" data-toggle="modal" data-target="#deleteTournament"><img src="/img/close.png" alt=""></a>
                </div>

            </div>
            <div class="table tabs">
                <div class="table__header">
                    <div>Игроки</div>
                    <ul class="table__list">
                    <#list model["results"] as result>
                        <li class="table__tab">${result?counter}</li>
                    </#list>
                    </ul>
                    <div>Балл</div>
                    <div>Разница</div>
                </div>
            <#list model["results"] as result>
            <div class="table__contant">
            <#--/*****************************игроки номер 1*************************************/-->
                <div class="tab">
                    <#list result.gameList as game>
                            <div class="table__item">

                            <div class="table__name">
                            ${game?counter}. ${game.idGamer}
                                    <div class="table__control">
                                    <div class="table__ok"></div>
                                    <div class="table__close" data-toggle="modal" data-target="#deletePlay-1"></div>


                                        <!-- Удалить игрока -->
                                        <div class="modal fade" id="deletePlay-1" tabindex="-1" role="dialog" aria-labelledby="deletePlayLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title" id="deletePlayLabel">Вы уверены,<br> что хотите удалить игрока?</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form class="rating-all__form" action="">
                                                            <button type="submit" class="rating-all__button" formaction="/${model.competition.id}/${game.idGamer}/excludePartner">Да</button>
                                                            <button type="button" class="rating-all__button" data-dismiss="modal" aria-label="Close">Нет</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                            </div>
                                    <input class="table__input" type="text" value="<#if result.id==game.idGamer> - <#else>${game.point1}</#if>">
                                <div class="table__row">${result.agrBall}</div>
                                <div class="table__row">${result.deference}</div>
                            </div>
                    </#list>
                </div>
            </#list>
            </div>
            </div>
            <a href="#" class="rating-all__add"><img src="img/add.png" alt=""></a>

            <!-- Добавить фото -->
            <div class="modal photo fade" id="photo" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-body">
                            <form class="rating-all__form" method="post" enctype="multipart/form-data" action="/${model.competition.id}/uploading">
                                <div class="rating-all__button" name="file">
                                    <label for="file"  name="file" class="img">
                                        <img src="/img/gallery.png" alt="">
                                    </label>
                                    <label for="file" name="file">Загрузить из галереи</label>

                                    <input type="file" name="file" onchange="this.form.submit()" id="file" style="display:none;"/>
                                </div>
                                <button type="button" class="rating-all__button">
                                    <div class="img"><img src="/img/photo-black.png" alt=""></div>
                                    Сделать фото
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Удалить турнир -->
            <div class="modal fade" id="deleteTournament" tabindex="-1" role="dialog" aria-labelledby="deleteTournamentLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="deleteTournamentLabel">Вы уверены,<br> что хотите удалить турнир?</h4>
                        </div>
                        <div class="modal-body">
                            <form class="rating-all__form" action="">
                                <button type="submit" class="rating-all__button">Да</button>
                                <button type="button" class="rating-all__button" data-dismiss="modal" aria-label="Close">Нет</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>




        </div>
    </main>
</div>

<script src="/js/vtornik.js"></script>
</body>
</html>