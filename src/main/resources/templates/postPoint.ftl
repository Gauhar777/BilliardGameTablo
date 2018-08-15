<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/myStyle.css">
    <link rel="stylesheet" href="/css/mediaStyle.css">
    <link rel="stylesheet" href="/css/navBarStyle.css">
</head>
<body>
    <form name="myPoint" action="point" method="POST">
        <label for="point1">${model.gamer1.nick}</label>
        <input name="point1" id="point1"/>
        <label  for="point2">${model.gamer2.nick}</label>
        <input name="point2" id="point2"/>
        <button type="submit" class="btn btn-primary btn-lg">done</button>
    </form>
</body>
</html>