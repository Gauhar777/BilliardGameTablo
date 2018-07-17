<#-- @ftlvariable name="model.resource" type="java.util.ResourceBundle" -->
<#import "/spring.ftl" as spring/>
<html lang="en">
<html>
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
        <a class="nav-link" href="/Competition/${model.competition.id}/showGames">
            ${model["resource"].getString("Game")}
            <span class="sr-only">(current)</span>
        </a>
            </li>
            <li class="nav-item active" id="active">
                <a class="nav-link"  href="#">${model["resource"].getString("Score")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
</nav>
<div class="container">
   <!-- <h1>${model["resource"].getString("Please, write score of game")}</h1>-->
    <form name="editForm" action="/${model.game.idCompetition}/${model.game.id}/addPoint"  method="post">
        <input name="id"  type="hidden" value="${model.game.id}" />
        <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="name"> ${model.gamer1.nick}</label>
            <div class="col-sm-10">
                <input name="point1" class="form-control" id="point1" type="text" value="${model.game.point1}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="name"> ${model.gamer2.nick}</label>
            <div class="col-sm-10">
                <input name="point2"  class="form-control" id="point2" type="text" value="${model.game.point2}"/>
            </div>
        </div>
        <button type="submit" class="btn btn-success btn-lg" >${model["resource"].getString("Write score")}</button>
    </form>
</div>
</body>
</html>