<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style type="text/css">
        *{
        font-family: Georgia, Times, serif;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Games:</h1>

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
                            ${gamer2.point1}:${gamer2.point2}
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
