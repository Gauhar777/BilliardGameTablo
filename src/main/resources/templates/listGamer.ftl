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
                <a class="nav-link" href="/main">
                    ${model["resource"].getString("mainPage")}
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item active">
                <a  class="nav-link" href="#">
                    ${model["resource"].getString("Gamers")}
                </a>
            </li>
        </ul>
    </nav>
    <table class="table table-striped table-borderless">
        <th scope="col">№</th>
        <th  scope="col">${model["resource"].getString("Nick")}</th>
        <th  scope="col">${model["resource"].getString("FIO")}</th>
        <th> ${model["resource"].getString("Score")}</span> </th>
        <th class="a"><span class="rotate">${model["resource"].getString("Deference")}</span></th>
        <#list model["gamer"] as gamer>

        <tr>
            <th>${gamer?counter}</th>
            <td>${gamer.nick}</td>
            <td>${gamer.FIO}</td>
            <td>${gamer.agrBall}</td>
            <td>${gamer.deference}</td>
        </tr>
        </#list>
    </table>
</body>
</html>