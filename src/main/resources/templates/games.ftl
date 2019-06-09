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
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <#--<link rel="stylesheet" href="/css/vtornik.css">-->
    <link rel="stylesheet" href="/css/vtornik2.css">
    <link rel="stylesheet" href="/css/vtornikMedia.css">
</head>
<body>
<#assign isAuthenticated = model["isAuthenticated"] />
<div class="wrp">
    <main class="main">
        <div class="rating-all">
            <div class="container">
                <#if isAuthenticated==true>
                    <a href="/logout" class="alert alert-light w-100 d-block text-center">Выйти из режима администратора</a>
                </#if>
                <div class="rating-all__header">
                    <div class="box">
                        <div class="headline">
                            <a href="/main2" class="mr-4"><img src="/img/back.png" alt=""></a>
                            <div class="ttl_1">${model.competition.name}</div>
                        </div>

                            <#if isAuthenticated==true>
                                <#if model.container?exists>
                                    <form  class="rating-all__icon"  method="post" enctype="multipart/form-data" style="display:inline-block; width: 60px;" action="/${model.competition.id}/uploading">
                                        <div  class="rating-all__icon"  name="file">
                                            <label class="rating-all__icon" for="file"  name="file" class="img">
                                                <img class="competitionControl" style="height: 41px; width: auto;" src="/img/camera.png" alt="">
                                            </label>
                                            <input type="file" name="file" onchange="this.form.submit()" id="file" style="display:none;"/>
                                        </div>
                                    </form>
                                        <#--<a href="#" class="rating-all__icon" data-toggle="modal" data-target="#photo"><img class="competitionControl" src="/img/camera.png" alt=""></a>-->
                                <#else>
                                        <a href="/${model.competition.id}/photo" class="rating-all__icon" ><img class="competitionControl" id="avatar" src="/${model.competition.id}/CompetitionPhoto"  alt=""></a>
                                </#if>
                            </#if>


                        <div class="ttl_2">${model.competition.name}</div>

                        <div class="rating-all__control">
                       <#if isAuthenticated==true>
                          <a href="#" class="js-edit"><img src="/img/edit.png" alt=""></a>
                          <a href="#" class="ml-4" data-toggle="modal" data-target="#deleteTournament"><img src="/img/close.png" alt=""></a>
                       </#if>
                    </div>
                    </div>
                </div>


                <!--TABLE OF GAME-->
                <table class="table table-bordered text-center mb-4">
                    <thead>
                        <tr>
                            <th scope="col" class="gamer"><span class="word">Игроки</span></th>
                            <#list model["results"] as result>
                                 <th scope="col"><span class="rotate">${result.nick}</span></th>
                            </#list>
                            <th scope="col" class="a"><span class="word"><span class="rotate">${model["resource"].getString("Score")}</span></th>
                            <th scope="col" class="a"><span class="word"><span class="rotate">${model["resource"].getString("Deference")}</span></th>
                        </tr>
                    </thead>
                    <tbody>
                         <#list model["results"] as result>
                            <tr>
                              <td>
                                  <span class="rotate">
                                      ${result.nick}
                                      <#if isAuthenticated==true>
                                            <div class="table__control">
                                              <#if result.dezhuril?exists>
                                                  <a href="/${model.competition.id}/${result.id}/dezhurit" onclick="document.getElementById('formTitle').innerHTML='Вы уверены,<br> что хотите отметить дежурным  ${result.nick}?',document.getElementById('dezhurnyForm').action='/${model.competition.id}/${result.id}/dezhurit'" data-toggle="modal" data-target="#dezhurny">
                                                      <div class="table__ok"></div>
                                                  </a>
                                              <#else>
                                                  <a href="/${model.competition.id}/${result.id}/cancellDezhurny" onclick="document.getElementById('formTitle').innerHTML='Вы уверены,<br> что хотите  ${result.nick} не дежурный?',document.getElementById('dezhurnyForm').action='/${model.competition.id}/${result.id}/cancellDezhurny'" data-toggle="modal" data-target="#dezhurny">
                                                      <div class="table__ok active" ></div>
                                                  </a>
                                              </#if>
                                              <a href="/${model.competition.id}/${result.id}/excludePartner" onclick="document.getElementById('deletePayForm').action='/${model.competition.id}/${result.id}/excludePartner'"  data-toggle="modal" data-target="#deletePlay"><div class="table__close"></div></a>
                                          </div>
                                      </#if>
                                  </span>
                              </td>
                              <#list result.gameList as gamer2>
                                  <#if result.id==gamer2.idGamer>
                                    <td><input class="table__input" type="text" value="-" disabled></td>
                                  <#else>
                                    <td>
                                        <input class="table__input" type="text" onclick="document.getElementById('point1Label').innerHTML='${result.nick}',document.getElementById('point1').placeholder=${gamer2.point1},document.getElementById('point2').placeholder=${gamer2.point2},document.getElementById('point2Label').innerHTML='${gamer2.nickOfPartner2}', document.getElementById('pointsForm').action ='/${gamer2.idCompetition}/${result.id}/${gamer2.idGamer}/point'" href="/${gamer2.idCompetition}/${result.id}/${gamer2.idGamer}/point"    data-toggle="modal" data-target="#setPoint" value="${gamer2.point1}:${gamer2.point2}" disabled/>
                                    </td>
                                </#if>
                              </#list>
                              <td> ${result.deference}</td>
                              <td> ${result.agrBall}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <#if isAuthenticated==true>
                    <a href="/competition/${model.competition.id}/addGamers" class="btn btn-dark">Новый игрок +</a>
                </#if>
            </div>
        </div>
    </main>
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
                                    <button type="submit" class="rating-all__button" formaction="/delete/${model.competition.id}">Да</button>
                                    <button type="button" class="rating-all__button" data-dismiss="modal" aria-label="Close">Нет</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Добавить счет игры -->
                <div class="modal fade 2" id="setPoint" tabindex="-1" role="dialog" aria-labelledby="setPointLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">

                                <h4 class="modal-title" id="setPointLabel">Вы уверены,<br> что хотите внести счет игры?</h4>
                            </div>
                            <div class="modal-body">
                                <form class="tournament__form" id="pointsForm" action="" method="post">
                                    <p>
                                        <label id="point1Label" for="point1"></label>

                                        <input type="number" max="8" min="0" name="point1" id="point1" placeholder="" >
                                    </p>
                                    <p>
                                        <label id="point2Label" for="point2"></label>
                                        <input type="number" max="8"  min="0" name="point2" id="point2" >
                                    </p>
                                    <input class="tournament__submit"  type="submit" value="Да"/>
                                    <button class="tournament__submit"  aria-label="Close">Нет</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Удалить игрока -->
                <div class="modal fade" id="deletePlay" tabindex="-1" role="dialog" aria-labelledby="deletePlayLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="deletePlayLabel">Вы уверены,<br> что хотите удалить игрока?</h4>
                            </div>
                            <div class="modal-body">
                                <form class="rating-all__form" id="deletePayForm" action="">
                                    <button type="submit" class="rating-all__button">Да</button>
                                    <button type="button" class="rating-all__button" data-dismiss="modal" aria-label="Close">Нет</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Дежурный-->
                <div class="modal fade" id="dezhurny" tabindex="-1" role="dialog" aria-labelledby="chooseDezhurnyOfCompetition" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="formTitle"></h4>
                            </div>
                            <div class="modal-body">
                                <form class="rating-all__form" id="dezhurnyForm" action="">
                                    <button type="submit" class="rating-all__button">Да</button>
                                    <button type="button" class="rating-all__button" data-dismiss="modal" aria-label="Close">Нет</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>



    <script src="/js/vtornik2.js"></script>
</body>
</html>