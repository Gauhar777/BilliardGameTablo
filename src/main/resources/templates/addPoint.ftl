<#-- @ftlvariable name="model.resource" type="java.util.ResourceBundle" -->
<#import "/spring.ftl" as spring/>
<html lang="en">
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
    <h1>Igroki</h1>
    <form name="editForm" action="/${model.game.idCompetition}/${model.game.id}/addPoint"  method="post">
        <input name="id"  type="hidden" value="${model.game.id}" />
        <label for="name">G ${model.game.idPartner1}</label>
        <input name="point1" id="point1" type="text" value="${model.game.point1}"/>
        <label for="name">G ${model.game.idPartner2}</label>
        <input name="point2"  id="point2" type="text" value="${model.game.point2}"/>
        <br> <button type="submit" class="btn btn-success btn-lg" >Edit</button> </br>
    </form>
</div>

</body>
</html>