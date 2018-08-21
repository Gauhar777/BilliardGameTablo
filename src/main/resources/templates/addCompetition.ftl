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
                <a class="nav-link"  href="/main2">
                    ${model["resource"].getString("Competition")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link"  href="#">
                    ${model["resource"].getString("Add")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
        </ul>
        <div>
             <a href="/logout">
                <button type="button" class="btn btn-primary btn-lg">
                    <img src="/images/sign-out.png">
                </button>
             </a>
        </div>
    </nav>

    <div class="container">
        <!--    <h2>${model["resource"].getString("Please write your competition name")}</h2>-->
            <form class="form-inline"  name="comp" action="/addCompetition" method="POST">
                    <label for="name" class="mb-2 mr-sm-2">${model["resource"].getString("Name")}:</label>
                    <input  class="form-control mb-2 mr-sm-2" id="name" name="name" type="text"/>
                    <button type="submit" class="btn btn-primary btn-lg"> ${model["resource"].getString("Add new competition")}</button>
            </form>
    </div>
</body>
</html>