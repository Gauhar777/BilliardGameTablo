<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/myStyle.css">
</head>
<body>
<div class="container">
    <h1 class="text-info">Edit competition </h1>
    <form name="editForm" action="/editCompetitionSave"  method="post">
        <input name="id"  type="hidden" value="${model.competition.id}" />
        <label for="name">Name:</label>
        <input name="name" class="form-control" id="name" type="text" value="${model.competition.name}"/>
        <button type="submit" class="btn btn-success btn-lg" >Edit</button>
    </form>
    <a href="main">
        <button type="button" class="btn btn-info btn-lg">Back</button>
    </a>
</div>
</body>
</html>