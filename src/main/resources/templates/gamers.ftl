<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/myStyle.css">
</head>
<body>
<div class="container">
    <h1 class="text-info"> Chose gamers for competition <b class="text-info">${model.competition.name}</b></h1>
    <input type="hidden" name="id" value="${model.competition.id}">

    <table class="table">
            <thead>
            <tr>
                <th  scope="col">#</th>
                <th  scope="col">FIO</th>
                <th  scope="col">Nick name</th>
                <th scope="col">Choose</th>
            </tr>
            </thead>
            <tbody>
            <#list model["answers"] as answer>
            <tr>
                <td scope="row">#</td>
                <td>${answer.FIO} </td>
                <td>${answer.nick} </td>
                <td>
                    <#if answer.choosed==false>
                    <a href="/${model.competition.id}/${answer.idGamer}/choosePartner">
                    <button type="button" class="btn btn-warning btn-md">Choose</button>
                    </a>
                    <#else>
                        <button type="button" class="btn btn-danger btn-md">exclude</button>
                    </#if>
                <td>
            </tr>
            </#list>
    </table>
    <a href="/main"><button type="button" class="btn btn-info btn-lg">Back</button></a>
    <a href="/${model.competition.id}/addGamers"><button type="button" class="btn btn-info btn-lg">Add new gamer</button></a>
    <a href="/Competition/${model.competition.id}/showGames"><button type="button" class="btn btn-info btn-lg">Start</button></a>

</div>
</body>
</html>