<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Games</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
    <!--
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/myStyle2.css">
    -->
</head>
<body>
    <#assign isAuthenticated = model["isAuthenticated"] />
    <nav class="navbar navbar-dark navbar-expand">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a  class="nav-link" href="#">
                    ${model["resource"].getString("Competition")}
                </a>
            </li>
        </ul>
    <div>
        <#if isAuthenticated==true>
            <a href="addCompetition">
                <button type="button" class="btn btn-primary btn-lg">
                <!--    ${model["resource"].getString("Add new competition")}-->
                    <img src="/images/addition-sign.png">
                </button>
            </a>
        </#if>
        <a href="/loginpage">
            <button type="button" class="btn btn-primary btn-lg">
            <!--    ${model["resource"].getString("Add new competition")}-->
                <img src="/images/add.png">
            </button>
        </a>
    </div>
</nav>

    <table class="table table-striped table-borderless">
        <thead>
        <tr>
            <th class="col1">${model["resource"].getString("Name")}</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <#list model["competitionList"] as competition>
                <tr>
                    <td>
                        <div > ${competition.name}</div>
                    </td>
                    <td>
                        <a href="/Competition/${competition.id}/showGames">
                            <button type="button" class="btn btn-primary">
                                <img src="/images/scoreboard.png">
                            </button>
                        </a>
                        <#if isAuthenticated==true>
                            <a href="edit/${competition.id}">
                                <button type="button" class="btn btn-primary">
                                    <img src="/images/settings.png">
                                </button>
                            </a>
                        </#if>
                    </td>
                </tr>
            </#list>
        </tbody>
        </table>
<!--




    <div class="add">
        <a href="addCompetition">
            <button type="button" class="btn btn-info btn-lg">${model["resource"].getString("Add new competition")}</button>
        </a>
    </div>
    -->
</body>
</html>