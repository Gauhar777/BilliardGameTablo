<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
    <!--
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/myStyle2.css">
    -->
</head>
<body>
<nav class="navbar navbar-dark navbar-expand">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a  class="nav-link" href="#">
                ${model["resource"].getString("mainPage")}
            </a>
        </li>
    </ul>
</nav>

<div class="container">
    <a href="/main2">
        <button type="button" class="btn btn-primary btn-block">
            ${model["resource"].getString("Competition")}
        </button>
    </a>

    <a href="/listGamer">
        <button type="button" class="btn btn-default btn-block">
            ${model["resource"].getString("Gamers")}
        </button>
    </a>
</div>

<div>

</div>
</body>