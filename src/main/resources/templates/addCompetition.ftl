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
        <h1>Welcome</h1>
        <h2>Please add your competition</h2>
        <form name="comp" action="/addCompetition" method="POST">
                <label for="name">Name:</label>
                <input class="form-control" id="name" name="name" type="text"/>
                <button type="submit" class="btn btn-primary btn-lg" > Add new competition</button>
        </form>
    </div>
</body>
</html>