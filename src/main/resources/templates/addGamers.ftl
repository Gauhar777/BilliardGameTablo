<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Hello Edition</h1>
        <div>
                <form name="gamer" action="addGamers" method="POST">
                    <label for="FIO">FIO:</label>
                    <input class="form-control" id="FIO" name="FIO" type="text"/>
                    <label for="Nick name">Nick name:</label>
                    <input class="form-control"  id="Nick name" name="nick" type="text"/>
                    <button type="submit" class="btn btn-primary btn-lg" >Create</button>
                </form>
        </div>
    </div>
</body>
</html>