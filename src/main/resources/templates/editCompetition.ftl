<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
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
                    ${model["resource"].getString("Edit")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>

        <div>
            <a href="/delete/${model.competition.id}">
                <button style="font-size:1.5em;" type="button" class="btn btn-danger btn-lg">
                    <img src="/images/delete-button.png">
                </button>
            </a>
                <a href="/logout">
                    <button type="button" class="btn btn-primary btn-lg">
                        <img src="/images/sign-out.png">
                    </button>
                </a>
            </div>
    </nav>

    <div class="container">
     <!--   <h1 class="text-info">${model["resource"].getString("Please,write your competition new name")} </h1>-->
        <form class="form-inline"   name="editForm" action="/editCompetitionSave"  method="post">
                <input name="id"  type="hidden" value="${model.competition.id}" />
                <label class="mb-2 mr-sm-2" for="name">${model["resource"].getString("Name")}:</label>
                <input name="name" class="form-control mb-2 mr-sm-2" id="name" type="text" value="${model.competition.name}"/>
                <button type="submit" class="btn btn-primary btn-lg" >${model["resource"].getString("Edit")}</button>
        </form>
    </div>
</body>
</html>