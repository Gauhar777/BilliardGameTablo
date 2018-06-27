<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/myStyle.css">
</head>
<body>
<div class="container">
        <h1 class="text-info">Rating of <b class="text-info">${model.competition.name}</b> :</h1>
        <table class="table">
            <th>Gamers</th>
            <#list model["results"] as result>
            <th>${result.nick}</th>
        </#list>
        <th>Score</th>
        <th>Deference</th>
        <#list model["results"] as result>
        <tr>
            <td><b>${result.nick}</b></td>
            <#list result.gameList as gamer2>
            <td>
                <#if result.id==gamer2.idGamer>
                -
                <#else>
                <a href="/${gamer2.idCompetition}/${gamer2.id}/addPoint"> ${gamer2.point1}:${gamer2.point2}</a>
            </#if>
            </td>
        </#list>
        <td> ${result.agrBall}</td>
        <td> ${result.deference}</td>
        </tr>
        </#list>
    </table>
    <a href="/competition/${model.competition.id}/addGamers"><button type="button" class="btn btn-info btn-lg">Add more gamers</button></a>
    <a href="/main"><button type="button" class="btn btn-info btn-lg">Home</button></a>
</div>
</body>
</html>
