<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
</head>
<body>
<nav class="navbar navbar-dark navbar-expand">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/main2">
                ${model["resource"].getString("Competition")}
                <span class="sr-only">(current)</span>
            </a>
        </li>

        <li class="nav-item active">
            <a class="nav-link" href="#">
                ${model["resource"].getString("Gamers")}
                <span class="sr-only">(current)</span>
            </a>
        </li>
    </ul>
    <div>
        <a href="/${model.competition.id}/addGamers">
            <button type="button" class="btn  btn-primary btn-lg">
                <!--${model["resource"].getString("Add new gamer")}-->
                <img src="/images/add.png">
            </button>
        </a>

        <a href="/Competition/${model.competition.id}/showGames">
            <button type="button" class="btn btn-primary btn-lg">
                <!--${model["resource"].getString("Game")}-->
                <img src="/images/scoreboard.png">
            </button>
        </a>
    </div>
</nav>
<!--
     <h1 class="text-info">${model["resource"].getString("Choose gamers for competition")} : <b class="text-info">${model.competition.name}</b></h1>
 -->
<input type="hidden" name="id" value="${model.competition.id}">

<table class="table table-striped table-borderless">
    <th  scope="col">${model["resource"].getString("FIO")}</th>
    <th  scope="col">${model["resource"].getString("Nick")}</th>
    <#list model["answers"] as answer>
    <tr>
        <td class="gamerI">${answer.FIO} </td>
        <td class="gamerI">${answer.nick} </td>
        <td>

            <a href="/${model.competition.id}/deleteGamer/${answer.idGamer}">
                <button type="button" class="btn btn-danger">
                <!--   ${model["resource"].getString("Exclude")}-->
                    <img src="/images/delete-button.png">
                </button>
            </a>
            <#if answer.dezhuril==false>
            <a href="/${model.competition.id}/${answer.idGamer}/dezhurit">
                <button type="button" class="btn btn-primary">
                    <!--${model["resource"].getString("Choose")}-->
                </button>
            </a>
            <#else>
            <a href="/${model.competition.id}/${answer.idGamer}/cancellDezhurny">
                <button type="button" class="btn btn-primary">
                    <!--   ${model["resource"].getString("Exclude")}-->
                    <img src="/images/done.png">
                </button>
            </a>
        </#if>

        <#if answer.choosed==false>
            <a href="/${model.competition.id}/${answer.idGamer}/choosePartner">
                <button type="button" class="btn btn-success">
                    <!--${model["resource"].getString("Choose")}-->
                    <img src="/images/addition.png">
                </button>
            </a>
            <#else>
            <a href="/${model.competition.id}/${answer.idGamer}/excludePartner">
                <button type="button" class="btn btn-danger">
                    <!--   ${model["resource"].getString("Exclude")}-->
                    <img src="/images/minus.png">
                </button>
            </a>
        </#if>

        </td>
    </tr>
</#list>
</table>
<!--<div class="add">
    <a href="/${model.competition.id}/addGamers"><button type="button" class="btn  btn-primary btn-lg">${model["resource"].getString("Add new gamer")}</button></a>
</div>
<!--  <p>
    <a href="/main"><button type="button" class="btn btn-info btn-lg">${model["resource"].getString("Back")}</button></a>
    <a href="/Competition/${model.competition.id}/showGames"><button type="button" class="btn btn-info btn-lg">${model["resource"].getString("Game")}</button></a>
</p>
-->
</body>
</html>