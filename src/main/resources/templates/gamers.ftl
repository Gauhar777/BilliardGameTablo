<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style>
        table{
            width: 50%;
            border-spacing: 1px;
        }
     </style>
</head>
<body>
<div class="container">
    <h1>Chose gamers for competition <b>${model.competition.name}</b></h1>
    <input type="hidden" name="id" value="${model.competition.id}">
    <form name="ShowForm" action="/gamers" method="POST">
        <table class="table">
            <thead class="active">
                <tr class="active" >
                    <th  scope="col">#</th>
                    <th  scope="col">FIO</th>
                    <th  scope="col">Nick name</th>
                    <th  scope="col">Delete</th>
                    <th scope="col">Choose</th>
                </tr>
            </thead>
            <tbody>
                <#list model["gamers"] as gamer>
                <tr>
                    <th scope="row">#</th>
                    <th>${gamer.FIO} </th>
                    <th>${gamer.nick} </th>
                    <th>
                        <a href="/${model.competition.id}/deleteGamer/${gamer.id}">
                            <button type="button" class="btn btn-danger btn-md">Delete</button>
                        </a>
                    </th>
                    <th>
                        <a href="/${model.competition.id}/${gamer.id}">
                            <button type="button" class="btn btn-warning btn-md">Choose</button>
                        </a>
                    </th>
                </tr>
                </#list>
            </tbody>
        </table>
    </form>
    <p> <a href="/${model.competition.id}/addGamers"><button type="button" class="btn btn-primary btn-lg">Add new gamer</button></a> </p>
</div>
</body>
</html>