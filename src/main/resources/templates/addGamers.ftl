<#import "/spring.ftl" as spring/>
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
                <a class="nav-link" href="/competition/${model.competition.id}/addGamers">
                    ${model["resource"].getString("Gamers")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="#">
                    ${model["resource"].getString("Add new gamer")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>

        </ul>
    </div>
</nav>

<div class="container">
  <!--  <h1>${model["resource"].getString("Please, write your gamer name and nick")}</h1>-->
        <form   name="gamer" action="addGamers" method="POST">

            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="FIO">${model["resource"].getString("FIO")}:</label>
                <div class="col-sm-10">
                    <input class="form-control" id="FIO" name="FIO" type="text"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label" for="Nick name">${model["resource"].getString("Nick")}:</label>
                    <div class="col-sm-10">
                        <input class="form-control" id="Nick name" name="nick" type="text"/>
                    </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg" >${model["resource"].getString("Add new gamer")}</button>
        </form>
</div>
</body>
</html>