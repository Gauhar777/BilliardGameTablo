<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
</head>
<body>
<nav class="navbar navbar-dark navbar-expand">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a  class="nav-link" href="/main2">
                ${model["resource"].getString("Competition")}
                <span class="sr-only">(current)</span>
            </a>
        </li>
        <li class="nav-item active">
            <a  class="nav-link" href="#">
                ${model["resource"].getString("Game")}
                <span class="sr-only">(current)</span>
            </a>
        </li>
    </ul>
    <div>
            <a href="/competition/${model.competition.id}/addGamers">
                <button type="button" class="btn btn-primary btn-lg">
                    <!--    ${model["resource"].getString("Add new competition")}-->
                    <img src="/images/add.png">
                </button>
            </a>
            <a href="/logout">
                <button type="button" class="btn btn-primary btn-lg">
                    <img src="/images/sign-out.png">
                </button>
            </a>
        </div>
    </nav>

<div>
    <table class="table table-bordered">
            <th class="a"> <span class="rotate">${model["resource"].getString("Gamers")}</span></th>
            <#list model["results"] as result>
            <th class="a"><span class="rotate">${result.nick}</span> </th>
            </#list>
            <th class="a"><span class="rotate">${model["resource"].getString("Score")}</span> </th>
            <th class="a"><span class="rotate">${model["resource"].getString("Deference")}</span></th>
            <!--table body-->
            <#list model["results"] as result>
                    <tr>
                        <th ><span class="rotate">${result.nick}</span></th>
                            <#list result.gameList as game>
                        <td>
                                <#if result.id==game.idGamer>
                                    -
                                <#else>
                                    <a href="/${game.idCompetition}/${result.id}/${game.idGamer}/point">
                                        ${game.point1}:${game.point2}
                                    </a>
                                </#if>
                        </td>
                            </#list>
                        <td> ${result.agrBall}</td>
                        <td> ${result.deference}</td>
                    </tr>
            </#list>
    </table>
    </div>
</body>
</html>