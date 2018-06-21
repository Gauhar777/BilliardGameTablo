<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style type="text/css">
        *{
        font-family: Georgia, Times, serif;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Competition list</h1>
             <table  class="table">
                    <thead class="active">
                    <tr class="active" >
                        <th  scope="col">#</th>
                        <th  scope="col">Name</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                        <th scope="col">Chose gamers</th>
                        <th scope="col">Games</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list model["competitionList"] as competition>
                            <tr >
                                <th>#</th>
                                <th scope="row">${competition.name}</th>

                                <th>
                                    <a  href="edit/${competition.id}">
                                        <button type="button" class="btn btn-success btn-md">Edit</button>
                                    </a>
                                </th>

                                <th>
                                    <a href="delete/${competition.id}">
                                        <button type="button" class="btn btn-danger btn-md">Delete</button>
                                    </a>
                                </th>

                                <th>
                                    <a href="competition/${competition.id}">
                                        <button type="button" class="btn btn-warning btn-md">Gamers</button>
                                    </a>
                                </th>

                                <th>
                                    <a href="competition/${competition.id}">
                                        <button type="button" class="btn btn-warning btn-md">Games</button>
                                    </a>
                                </th>
                            </tr>
                        </#list>
                    </tbody>
                </table>
        <a href="addCompetition">
            <button type="button" class="btn btn-primary btn-lg">Add new competition</button>
        </a>
    </div>
    <!--<script src="resources/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>-->
</body>
</html>