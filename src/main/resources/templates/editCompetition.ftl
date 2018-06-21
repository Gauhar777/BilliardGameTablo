<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style>
        *{
        font-family: Georgia, Times, serif;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit competition</h1>
        <form name="editForm" action="/editCompetitionSave"  method="post">
            <input name="id"  type="hidden" value="${model.competition.id}" />
            <label for="name">Name:</label>
            <input name="name" class="form-control" id="name" type="text" value="${model.competition.name}"/>
            <button type="submit" class="btn btn-success btn-lg" >Edit</button>
        </form>
    </div>
</body>
</html>