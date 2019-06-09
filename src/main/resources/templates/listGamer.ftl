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
    <link rel="stylesheet" href="/css/vtornik2.css">
    <link rel="stylesheet" href="/css/vtornikMedia.css">
</head>
<body>
    <#assign isAuthenticated = model["isAuthenticated"] />
    <div class="wrp">
        <main class="main">
            <div class="player">
                <div class="container">
                    <#if isAuthenticated==true>
                        <a href="/logout" class="alert alert-light w-100 d-block text-center mb-5">Выйти из режима администратора</a>
                    </#if>
                    <div class="player__header">
                        <div class=" w-100">
                            <a href="/chooseList" class="player__back"><img src="/img/back.png" alt=""></a>
                            <div class="row justify-content-between align-items-center">
                                <div class="box">
                                    <div class="ttl_listGamer" >
                                        <h1>Игроки</h1>
                                    </div>
                                    <div>
                                        <div class="btns">
                                            <!-- Навигация -->
                                            <ul class="nav nav-tabs" role="tablist">
                                                <li class="active">
                                                    <a href="#list" class="btn btn-dark" aria-controls="list" role="tab" data-toggle="tab">Список</a>
                                                </li>
                                                <li class="ml-3">
                                                    <a href="#table" class="btn btn-dark" aria-controls="table" role="tab" data-toggle="tab">Таблица</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center mb-5">
                        <#if isAuthenticated==true>
                            <button id="newG" type="button" class="btn btn-light alert-light" data-toggle="modal" data-target="#myModal" onclick="document.getElementById('nick').value='', document.getElementById('description').value='',document.getElementById('id').value=null,document.getElementById('Button12').style.display='none',document.getElementById('Save').style.display='unset'">+ Новый игрок</button>
                        </#if>
                        <!-- Добавить нового игрока -->
                        <div class="modal modal-add fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                                    <div class="modal-body">
                                        <form method="POST"  action="/newGamer">
                                            <div class="form-group">
                                                <label for="nick">Введите имя игрока</label>
                                                <input type="text" minlength="1" class="form-control" id="nick" name="nick">
                                            </div>
                                            <div class="form-group">
                                                <label for="anons">Описание игрока</label>
                                                <input type="text" name="description" id="description" class="form-control" id="anons"/>
                                            </div>
                                                <input type=hidden name="id" id="id" >

                                                <button id="Save"  type="submit"  style="width:45%" class="btn btn-dark">Сохранить</button>
                                                <div class="buttons" id="Button12">
                                                    <button  type="submit"  style="width:45%" class="btn btn-dark">Сохранить</button>
                                                    <button  class="btn btn-dark " style="width:45%" formaction="/deleteGamer" ">Удалить</button>
                                                </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                <!-- Содержимое вкладок -->

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="list">
                        <div class="row">
                          <#list model["gamer"] as gamer>
                            <div class="gamer">

                                    <div class="player__img">
                                        <form  class="rating-all__icon"  method="post" enctype="multipart/form-data"  action="/${gamer.id}/uploadAvatar">
                                            <div  class="rating-all__icon"  name="file">
                                                <label class="rating-all__icon" for="file${gamer.id}"  name="file" class="img">
                                                    <#if gamer.avatar==true>
                                                        <img src="/${gamer.id}/avatar" style="margin:auto;width:120px;height:120px;border-radius:100px;" alt="">
                                                    <#else>
                                                        <img src="img/player-no.png" alt="">
                                                    </#if>
                                                </label>
                                                <input type="file" name="file" enctype="multipart/form-data" onchange="this.form.submit()" id="file${gamer.id}" style="display:none;"/>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="player__txt">
                                       <#if isAuthenticated==true>
                                            <h3><a href="#" onclick="document.getElementById('Button12').style.display='unset',document.getElementById('Save').style.display='none',document.getElementById('id').value='${gamer.id}', document.getElementById('nick').value='${gamer.nick}', document.getElementById('description').value='<#if gamer.description?exists>${gamer.description}</#if>'" data-toggle="modal" data-target="#myModal">${gamer.nick}</a></h3>
                                       <#else>
                                            <h3>${gamer.nick}</h3>
                                       </#if>
                                       <#if isAuthenticated==true>
                                            <a href="#" onclick="document.getElementById('Button12').style.display='unset',document.getElementById('Save').style.display='none',document.getElementById('id').value='${gamer.id}', document.getElementById('nick').value='${gamer.nick}', document.getElementById('description').value='<#if gamer.description?exists>${gamer.description}</#if>'" data-toggle="modal" data-target="#myModal">
                                                <#if gamer.description?exists>
                                                    ${gamer.description}
                                                 <#else >
                                                    Краткая информация об игроке.
                                                </#if>
                                            </a>
                                       <#else>
                                           <a href="#">
                                               <#if gamer.description?exists>
                                                   ${gamer.description}
                                               <#else >
                                                        Краткая информация об игроке.
                                               </#if>
                                           </a>
                                       </#if>
                                        <div class="player__itog">
                                            <div class="d-flex align-items-center mr-5">
                                                <img src="/img/win.png" alt=""> <span class="player__num">${gamer.win}</span>
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <img src="/img/games.png" alt=""> <span class="player__num">${gamer.gameCount}</span>
                                            </div>
                                        </div>
                                    </div>

                            </div>
                          </#list>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="table">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">Игроки</th>
                                    <th scope="col">Побед</th>
                                    <th scope="col">Разница</th>
                                </tr>
                            </thead>
                            <tbody>
                              <#list model["gamer"] as gamer>
                                  <tr>
                                      <td><input type="hidden" id="firstItem" value="{${gamer.id}?counter}"/>${gamer.nick}</td>
                                      <td>${gamer.agrBall}</td>
                                      <td>${gamer.deference}</td>
                                  </tr>
                              </#list>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </main>
</div>

<script src="/js/vtornik2.js"></script>
</body>
</html>